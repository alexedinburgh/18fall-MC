package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * https://en.wikipedia.org/wiki/Radix_sort
 * Class: sorting algorithm
 * Data structure: Array
 * Worst-case performance: O(wn)
 * Worst-case space complexity: O(w + N)
 */
public class RadixSort {
	ExecutorService pool;
	ArrayList<Integer> input;
	ArrayList<Integer> pos;
	ArrayList<Integer> neg;
	Lock lock = new ReentrantLock();
	
	public RadixSort(int numThreads) {
		pool = Executors.newFixedThreadPool(numThreads);
	}
	
	public void sort(ArrayList<Integer> input) {
		this.input = input;
		pos = new ArrayList<>();
		neg = new ArrayList<>();
		
		ArrayList<Callable<Object>> separate = new ArrayList<Callable<Object>>();
		for (int i = 0;i < input.size();++i) {
			separate.add(Executors.callable(new SeparatePosAndNegTask(i)));
		}
		try {
			pool.invokeAll(separate);
			for (int i = 0;i < 32;++i) {
				if (pos.size() > 0) {
					RadixSortHelper radixSortHelperPos = new RadixSortHelper(true, i);
					radixSortHelperPos.execute();
				}
				if (neg.size() > 0) {
					RadixSortHelper radixSortHelperNeg = new RadixSortHelper(false, i);
					radixSortHelperNeg.execute();
				}
			}
			ArrayList<Callable<Object>> concatenate = new ArrayList<Callable<Object>>();
			for (int i = 0;i < neg.size();++i) {
				concatenate.add(Executors.callable(new ConcatenateTask(false, true, i, -neg.get(neg.size() - i - 1))));
			}
			for (int i = 0;i < pos.size();++i) {
				concatenate.add(Executors.callable(new ConcatenateTask(true, true, i + neg.size(), pos.get(i))));
			}
			pool.invokeAll(concatenate);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
	
	public class RadixSortHelper {
		boolean isPos;
		int bit;
		Integer[] temp;
		Integer[] ones;
		Integer[] zeros;
		
		public RadixSortHelper(boolean isPos, int bit) {
			this.isPos = isPos;
			this.bit = bit;
		}
		
		public void execute() {
			int size = isPos?pos.size():neg.size();
			ArrayList<Integer> list = isPos?pos:neg;
			temp = new Integer[size];
			Arrays.fill(temp, 0);
			ArrayList<Callable<Object>> setOne = new ArrayList<Callable<Object>>();
			for (int i = 0;i < list.size();++i) {
				setOne.add(Executors.callable(new SetOneTask(i, bit, 0, temp, isPos)));
			}
			try {
				pool.invokeAll(setOne);
				prefixSum(temp, isPos);
				ArrayList<Callable<Object>> setValue = new ArrayList<Callable<Object>>();
				zeros = new Integer[temp[size - 1]];
				for (int i = 0;i < size;++i) {
					setValue.add(Executors.callable(new SetValueTask(temp, zeros, i, isPos, bit, 0)));
				}
				pool.invokeAll(setValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Arrays.fill(temp, 0);
			setOne = new ArrayList<Callable<Object>>();
			for (int i = 0;i < list.size();++i) {
				setOne.add(Executors.callable(new SetOneTask(i, bit, 1, temp, isPos)));
			}
			try {
				pool.invokeAll(setOne);
				prefixSum(temp, isPos);
				ArrayList<Callable<Object>> setValue = new ArrayList<Callable<Object>>();
				ones = new Integer[temp[size - 1]];
				for (int i = 0;i < size;++i) {
					setValue.add(Executors.callable(new SetValueTask(temp, ones, i, isPos, bit, 1)));
				}
				pool.invokeAll(setValue);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ArrayList<Callable<Object>> concatenate = new ArrayList<Callable<Object>>();
			for (int i = 0;i < zeros.length;++i) {
				concatenate.add(Executors.callable(new ConcatenateTask(isPos, false, i, zeros[i])));
			}
			for (int i = 0;i < ones.length;++i) {
				concatenate.add(Executors.callable(new ConcatenateTask(isPos, false, i + zeros.length, ones[i])));
			}
			try {
				pool.invokeAll(concatenate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void prefixSum(Integer[] temp, boolean isPos) {
		int size = isPos?pos.size():neg.size();
		for (int d = 1;d < size; d *= 2) {
			ArrayList<Callable<Integer>> getValue = new ArrayList<Callable<Integer>>();
			for (int i = d;i < size;++i) {
				getValue.add(new GetValTask(temp, i - d));
			}
			try {
				List<Future<Integer>> res = pool.invokeAll(getValue);
				ArrayList<Callable<Object>> addPrefix = new ArrayList<Callable<Object>>();
				for (int i = d;i < size;++i) {
					try {
						addPrefix.add(Executors.callable(new AddPrefixTask(temp, i, res.get(i-d).get())));
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
				pool.invokeAll(addPrefix);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class SeparatePosAndNegTask implements Runnable {
		int index;
		
		public SeparatePosAndNegTask(int index) {
			this.index = index;
		}
		
		@Override
		public void run() {
			lock.lock();
			if (input.get(index) >= 0) {
				pos.add(input.get(index));
			} else {
				neg.add(-input.get(index));
			}
			lock.unlock();
		}
	}
	
	public class AddPrefixTask implements Runnable {
		Integer temp[];
		int index, value;
		
		public AddPrefixTask(Integer[] temp, int index, int value) {
			this.temp = temp;
			this.index = index;
			this.value = value;
		}
		
		@Override
		public void run() {
			temp[index] += value;
		}
	}
	
	public class GetValTask implements Callable<Integer> {
		Integer temp[];
		int index;
		
		public GetValTask(Integer[] temp, int index) {
			this.temp = temp;
			this.index = index;
		}
		
		@Override
		public Integer call() {
			return temp[index];
		}
	}
	
	public class SetOneTask implements Runnable {
		int index, bit, targetBit;
		Integer temp[];
		boolean isPos;
		
		public SetOneTask(int index, int bit, int targetBit, Integer[] temp, boolean isPos) {
			this.index = index;
			this.bit = bit;
			this.targetBit = targetBit;
			this.temp = temp;
			this.isPos = isPos;
		}
		
		@Override
		public void run() {
			if (isPos) {
				if (((pos.get(index)>>bit) & 1) == targetBit) {
					temp[index] = 1;
				}
			} else {
				if (((neg.get(index)>>bit) & 1) == targetBit) {
					temp[index] = 1;
				}
			}
		}
	}
	
	public class SetValueTask implements Runnable {
		Integer[] temp;
		Integer[] output;
		int index, bit, targetBit;
		boolean isPos;
		
		public SetValueTask(Integer[] temp, Integer[] output, int index, boolean isPos, int bit, int targetBit) {
			this.temp = temp;
			this.output = output;
			this.index = index;
			this.isPos = isPos;
			this.bit = bit;
			this.targetBit = targetBit;
		}
		
		@Override
		public void run() {
			ArrayList<Integer> input = isPos?pos:neg;
			if (((input.get(index)>>bit) & 1) == targetBit) {
				output[temp[index] - 1] = input.get(index);
			}
		}
	}
	
	public class ConcatenateTask implements Runnable {
		boolean isPos, toInput;
		int index, value;
		
		public ConcatenateTask(boolean isPos, boolean toInput, int index, int value) {
			this.isPos = isPos;
			this.index = index;
			this.value = value;
			this.toInput = toInput;
		}
		
		@Override
		public void run() {
			if (toInput) {
				input.set(index, value);
			} else {
				ArrayList<Integer> list = isPos?pos:neg;
				list.set(index, value);
			}
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(-323,-1212,-44,0,33,-435,87,-1212,6,0,23333,-1455,22,-71,52,0));
		RadixSort radixSort = new RadixSort(2);
		
		long start = System.nanoTime();
		radixSort.sort(input);
		long end = System.nanoTime();

		System.out.println(Arrays.toString(input.toArray()));
		System.out.println(end - start);
		radixSort.pool.shutdown();
	}
}
