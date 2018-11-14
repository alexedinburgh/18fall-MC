package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


/**
* https://www.geeksforgeeks.org/bitonic-sort/
*
* https://en.wikipedia.org/wiki/Bitonic_sorter
*
*
   Class	Sorting algorithm
   Data structure	Array
   Worst-case performance	{\displaystyle O(\log ^{2}(n))} O(\log ^{2}(n)) parallel time
   Best-case performance	{\displaystyle O(\log ^{2}(n))} O(\log ^{2}(n)) parallel time
   Average performance	{\displaystyle O(\log ^{2}(n))} O(\log ^{2}(n)) parallel time
   Worst-case space complexity	{\displaystyle O(n\log ^{2}(n))} {\displaystyle O(n\log ^{2}(n))} non-parallel time
**/

public class BitonicSort {
	
	ForkJoinPool pool;
	int threshold;
	
	public BitonicSort(int threadNum, int threshold) {
		pool = new ForkJoinPool(threadNum);
		this.threshold = threshold;
	}
	
	public void compareAndSwap(ArrayList<Integer> input, int i, int j, boolean isUp) {
		if(isUp == (input.get(i) > input.get(j))) {
			Collections.swap(input, i, j);
		}
	}
	
	public void bitonicMerge(ArrayList<Integer> input, int begin, int length, boolean isUp) {
		if (length > 1) {
			pool.invoke(new BitonicMergeTask(input, begin, length, isUp));
		}
	}
	
	public void bitonicSort(ArrayList<Integer> input, int begin, int length, boolean isUp) {
		try {
			if (length > 1) {
				pool.invoke(new BitonicSortTask(input, begin, length, isUp));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void sort(List<Integer> input) {
		bitonicSort((ArrayList<Integer>) input, 0, input.size(), true);
	}
	
	public class SwapTask implements Callable<Void> {
		ArrayList<Integer> input;
		int i, j;
		boolean isUp;
		
		public SwapTask(ArrayList<Integer> input, int i, int j, boolean isUp) {
			this.input = input;
			this.i = i;
			this.j = j;
			this.isUp = isUp;
		}
		
		@Override
		public Void call() {
			if(isUp == (input.get(i) > input.get(j))) {
				Collections.swap(input, i, j);
			}
			return null;
		}
	}

	public class BitonicMergeTask extends RecursiveAction {

		private static final long serialVersionUID = 1L;
		
		ArrayList<Integer> input;
		int begin, length;
		boolean isUp;
		
		public BitonicMergeTask(ArrayList<Integer> input, int begin, int length, boolean isUp) {
			this.input = input;
			this.begin = begin;
			this.length = length;
			this.isUp = isUp;
		}
		
		@Override
		protected void compute() {
			if (length > 1) {
				int halfLength = length / 2;
				
				//ArrayList<Callable<Void>> temp = new ArrayList<Callable<Void>>();
				for (int i = begin;i < begin + halfLength;i++) {
					//compareAndSwap(input, i, i + halfLength, isUp);
					//temp.add(new SwapTask(input, i, i + halfLength, isUp));
					if(isUp == (input.get(i) > input.get(i + halfLength))) {
						Collections.swap(input, i, i + halfLength);
					}
				}
				//pool.invokeAll(temp);
				
				BitonicMergeTask left = new BitonicMergeTask(input, begin, halfLength, isUp);
				//left.fork();
				
				BitonicMergeTask right = new BitonicMergeTask(input, begin + halfLength, halfLength, isUp);
				invokeAll(left, right);

				//right.compute();
				//left.join();
			}
		}
	}
	
	public class BitonicSortTask extends RecursiveAction {
		
		private static final long serialVersionUID = 1L;
		
		ArrayList<Integer> input;
		int begin, length;
		boolean isUp;
		
		public BitonicSortTask(ArrayList<Integer> input, int begin, int length, boolean isUp) {
			this.input = input;
			this.begin = begin;
			this.length = length;
			this.isUp = isUp;
		}
		
		@Override
		protected void compute() {
//			System.out.println(Thread.currentThread().getName());
			if (length > 1 && length <= threshold) {
				if (isUp) {
					Collections.sort(input.subList(begin, begin + length));
				} else {
					Collections.sort(input.subList(begin, begin + length), Collections.reverseOrder());
				}
			} else {
				int halfLength = length / 2;
				BitonicSortTask left = new BitonicSortTask(input, begin, halfLength, true);
				//left.fork();
				
				BitonicSortTask right = new BitonicSortTask(input, begin + halfLength, halfLength, false);
				invokeAll(left, right);
				//right.compute();
				//left.join();
				
				bitonicMerge(input, begin, length, isUp);
			}
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
		BitonicSort bitonicSort = new BitonicSort(8, 512);

		long start = System.nanoTime();
		bitonicSort.sort(input);
		long end = System.nanoTime();

		System.out.println(Arrays.toString(input.toArray()));
		System.out.println(end - start);
		bitonicSort.pool.shutdown();
	}
}
