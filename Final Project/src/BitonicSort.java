//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//import jdk.nashorn.internal.codegen.CompilerConstants.Call;
//
///**
// * https://www.geeksforgeeks.org/bitonic-sort/
// *
// * https://en.wikipedia.org/wiki/Bitonic_sorter
// *
// *
//     Class	Sorting algorithm
//     Data structure	Array
//     Worst-case performance	{\displaystyle O(\log ^{2}(n))} O(\log ^{2}(n)) parallel time
//     Best-case performance	{\displaystyle O(\log ^{2}(n))} O(\log ^{2}(n)) parallel time
//     Average performance	{\displaystyle O(\log ^{2}(n))} O(\log ^{2}(n)) parallel time
//     Worst-case space complexity	{\displaystyle O(n\log ^{2}(n))} {\displaystyle O(n\log ^{2}(n))} non-parallel time
// **/
//
//public class BitonicSort {
//	
//	ExecutorService executor;
//	
//	public BitonicSort(int threadNum) {
//		executor  = Executors.newFixedThreadPool(threadNum); 
//	}
//	
//	public void compareAndSwap(ArrayList<Integer> input, int i, int j, boolean isUp) {
//		if(isUp == (input.get(i) > input.get(j))) {
//			Collections.swap(input, i, j);
//		}
//	}
//	
//	public void bitonicMerge(ArrayList<Integer> input, int begin, int length, boolean isUp) {
//		if (length > 1) {
//			int halfLength = length / 2;
//			for (int i = begin;i < begin + halfLength;i++) {
//				compareAndSwap(input, i, i + halfLength, isUp);
//			}
//			bitonicMerge(input, begin, halfLength, isUp);
//			bitonicMerge(input, begin + halfLength, halfLength, isUp);
//		}
//	}
//	
//	public void bitonicSort(ArrayList<Integer> input, int begin, int length, boolean isUp) {
//		try {
//			if (length > 1) {
//				int halfLength = length / 2;
////				Future<Void> f1 = executor.submit(new BitonicTask(input, begin, halfLength, true));
////				f1.get();
//				bitonicSort(input, begin, halfLength, true);
//				Future<Void> f2 = executor.submit(new BitonicTask(input, begin + halfLength, halfLength, false));
////			bitonicSort(input, begin, halfLength, true);
////			bitonicSort(input, begin + halfLength, halfLength, false);
//				//f2.get();
//				bitonicMerge(input, begin, length, isUp);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
//	
//	public void sort(ArrayList<Integer> input) {
//		bitonicSort(input, 0, input.size(), true);
//	}
//	
//	public class BitonicTask implements Callable<Void> {
//		ArrayList<Integer> input;
//		int begin, length;
//		boolean isUp;
//		
//		public BitonicTask(ArrayList<Integer> input, int begin, int length, boolean isUp) {
//			this.input = input;
//			this.begin = begin;
//			this.length = length;
//			this.isUp = isUp;
//		}
//		
//		@Override
//		public Void call() {
//			bitonicSort(input, begin, length, isUp);
//			return null;
//		}
//	}
//	
//	public static void main(String args[]) {
//		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
//		BitonicSort bitonicSort = new BitonicSort(3);
//		bitonicSort.sort(input);
//		System.out.println(Arrays.toString(input.toArray()));
//		bitonicSort.executor.shutdown();
//	}
//}

//import java.util.Random;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.RecursiveAction;
//import java.util.concurrent.TimeUnit;
// 
//public class BitonicSort {
//	
//	
//    /**定义一个可分解的的任务类，继承了RecursiveAction抽象类
//     * 必须实现它的compute方法
//     */
//    public static class myTask extends RecursiveAction {
// 
//		private static final long serialVersionUID = 1L;
//		//定义一个分解任务的阈值——50,即一个任务最多承担50个工作量
//    	int THRESHOLD=50;
//    	//任务量
//    	int task_Num=0;
//		myTask(int Num){
//			this.task_Num=Num;
//		}
//		@Override
//		protected void compute() {
//			if(task_Num<=THRESHOLD){
//				System.out.println(Thread.currentThread().getName()+"承担了"+task_Num+"份工作");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}else{
//				//随机解成两个任务
//				Random m=new Random();
//				int x=m.nextInt(50);
//						
//				myTask left=new myTask(x);
//				myTask right=new myTask(task_Num-x);
//				
//				left.fork();
//				right.fork();
//			}
//		}
//    }
// 
//	public static void main(String[] args) throws Exception {
//        //创建一个支持分解任务的线程池ForkJoinPool
//		ForkJoinPool pool=new ForkJoinPool(1);
//		myTask task=new myTask(178);
//		
//		pool.submit(task);
//		pool.awaitTermination(20, TimeUnit.SECONDS);//等待20s，观察结果
//		pool.shutdown();
//	}
//}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import jdk.nashorn.internal.codegen.CompilerConstants.Call;

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
	
	ExecutorService executor;
	
	public BitonicSort(int threadNum) {
		executor  = Executors.newFixedThreadPool(threadNum); 
	}
	
	public void compareAndSwap(ArrayList<Integer> input, int i, int j, boolean isUp) {
		if(isUp == (input.get(i) > input.get(j))) {
			Collections.swap(input, i, j);
		}
	}
	
	public void bitonicMerge(ArrayList<Integer> input, int begin, int length, boolean isUp) {
		if (length > 1) {
			int halfLength = length / 2;
			for (int i = begin;i < begin + halfLength;i++) {
				compareAndSwap(input, i, i + halfLength, isUp);
			}
			bitonicMerge(input, begin, halfLength, isUp);
			bitonicMerge(input, begin + halfLength, halfLength, isUp);
		}
	}
	
	public void bitonicSort(ArrayList<Integer> input, int begin, int length, boolean isUp) {
		try {
			if (length > 1) {
				ForkJoinPool pool = new ForkJoinPool(1);
				pool.invoke(new BitonicTask(input, begin, length, isUp));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void sort(ArrayList<Integer> input) {
		bitonicSort(input, 0, input.size(), true);
	}
	
	public class BitonicTask extends RecursiveAction {
		
		private static final long serialVersionUID = 1L;
		
		ArrayList<Integer> input;
		int begin, length;
		boolean isUp;
		
		public BitonicTask(ArrayList<Integer> input, int begin, int length, boolean isUp) {
			this.input = input;
			this.begin = begin;
			this.length = length;
			this.isUp = isUp;
		}
		
		@Override
		protected void compute() {
			if (length > 1) {
				int halfLength = length / 2;
				BitonicTask left = new BitonicTask(input, begin, halfLength, true);
				left.fork();
				
				BitonicTask right = new BitonicTask(input, begin + halfLength, halfLength, false);

				right.compute();	
				left.join();
				
				bitonicMerge(input, begin, length, isUp);
			}
		}
	}
	
	public void printArray(ArrayList<Integer> input) {
		for (int item : input) {
			System.out.print(item);
		}
		System.out.println();
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
//		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4));
		BitonicSort bitonicSort = new BitonicSort(3);
		bitonicSort.sort(input);
		System.out.println(Arrays.toString(input.toArray()));
		bitonicSort.executor.shutdown();
	}
}
