package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * https://en.wikipedia.org/wiki/Odd%E2%80%93even_sort
 *
 *
     Class: Sorting algorithm
     Data structure: Array
     Worst-case performance: O(n^{2})
     Best-case performance: O(n)
     Worst-case space complexity: O(1)
 */
public class BrickSort {
	
	ExecutorService pool;
	boolean isSorted;
	
	public BrickSort(int numThreads) {
		pool = Executors.newFixedThreadPool(numThreads);
		isSorted = false;
	}
	
	public void sort(ArrayList<Integer> input) {
		while (!isSorted) {
			isSorted = true;
			ArrayList<Callable<Void>> oddSort = new ArrayList<Callable<Void>>();
			for (int i = 0; i < input.size();i += 2) {
				oddSort.add(new BrickSortTask(input, i, i+1));
			}
			try {
				pool.invokeAll(oddSort);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ArrayList<Callable<Void>> evenSort = new ArrayList<Callable<Void>>();
			for (int i = 1; i < input.size();i += 2) {
				evenSort.add(new BrickSortTask(input, i, i+1));
			}
			try {
				pool.invokeAll(evenSort);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void swap(ArrayList<Integer> input, int i, int j) {
		Collections.swap(input, i, j);
	}
	
	public class BrickSortTask implements Callable<Void> {
		
		ArrayList<Integer> input;
		int i, j;
		
		public BrickSortTask(ArrayList<Integer> input, int i, int j) {
			this.input = input;
			this.i = i;
			this.j = j;
		}
		
		@Override
		public Void call() {
			if (input.get(i) > input.get(j)) {
				swap(input, i, j);
				isSorted = false;
			}
			return null;
		}
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
		BrickSort brickSort = new BrickSort(8);
		
		long start = System.nanoTime();
		brickSort.sort(input);
		long end = System.nanoTime();
		
		System.out.println(Arrays.toString(input.toArray()));
		System.out.println(end - start);
		brickSort.pool.shutdown();
	}
}
