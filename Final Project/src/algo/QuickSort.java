package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


/**
 * https://en.wikipedia.org/wiki/Quicksort
 *
 * Class: Sorting algorithm
 * Worst-case performance: O(n2)
 * Best-case performance: O(n log n)
 * Average performance: O(n log n)
 * Worst-case space complexity	O(n) auxiliary (naive) / O(log n) auxiliary (Sedgewick 1978)
 */
public class QuickSort {
	ForkJoinPool pool;
	ArrayList<Integer> input;
	
	public QuickSort(int numThreads) {
		this.pool = new ForkJoinPool(numThreads);
	}
	
	public void sort(ArrayList<Integer> input) {
		if (input.size() == 0) {
			return;
		}
		this.input = input;
		pool.invoke(new QuickSortTask(0, input.size() - 1));
	}
	
	public int partition(int begin, int end) {
		int pivot = input.get(end);
		int i = begin - 1;
		for (int j = begin;j < end;++j) {
			if (input.get(j) < pivot && i != j) {
				i++;
				Collections.swap(input, i, j);
			}
		}
		i++;
		Collections.swap(input, end, i);
		return i;
	}
	
	public class QuickSortTask extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		int begin, end;
		
		public QuickSortTask(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
		
		@Override
		protected void compute() {
			if (end > begin) {
				int index = partition(begin, end);
				QuickSortTask left = new QuickSortTask(begin, index - 1);
				QuickSortTask right = new QuickSortTask(index + 1, end);
				left.fork();
				right.compute();
				left.join();
			}
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
		QuickSort quickSort = new QuickSort(8);
		
		long start = System.nanoTime();
		quickSort.sort(input);
		long end = System.nanoTime();
		
		System.out.println(Arrays.toString(input.toArray()));
		System.out.println(end - start);
		quickSort.pool.shutdown();
	}
}
