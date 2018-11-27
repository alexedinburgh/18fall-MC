For testing:

In the src/test folder, we wrote several junit tests for all the parallel sorting algorithms we have implemented:
1) BitonicSortAbsTest.java: Junit test for parallel bitonic sort.
2) BrickSortTest.java: Junit test for parallel general odd-even sort.
3) BrickSortV2Test.java: Junit test for parallel Batcher odd-even merge sort.
4) MergesortAbsTest.java: Junit test for parallel merge sort.
5) QuickSortTest.java: Junit test for parallel quick sort.
6) RadixSortTest.java: Junit test for parallel radix sort.

You can run each of them as you wish. The results will be shown in the console.

In the src/algo folder, we place all the source code here. To generate images for comparison, you can run the main function in Main.java. This will write results into the result.txt. Then you can run the python code in Plot.ipynb to get those images (the ones shown in our paper).