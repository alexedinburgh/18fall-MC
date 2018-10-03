#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

double MonteCarlo(int s) {
  // Write your code here
  double R = 1.0;
  int c = 0, i;
  srand(time(0));

  omp_set_dynamic(0);
#pragma omp parallel for private(i), shared(c), num_threads(2)
  for (i = 0; i < s; i++) {
    double x = ((double)rand() / (RAND_MAX));
    double y = ((double)rand() / (RAND_MAX));
    // double x = 0;
    // double y = 0;
    if (x * x + y * y < R * R) {
#pragma omp critical
      c++;
      // double t = 9;
    }
  }
  return 4.0 * c / (double)s;
}

void main() {
  double pi;
  pi = MonteCarlo(100000000);
  printf("Value of pi is: %lf\n", pi);
}
