#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void MatrixMult(char file1[], char file2[], int T) {
  // Write your code here
  FILE *fptr1, *fptr2;
  fptr1 = fopen(file1, "r");
  fptr2 = fopen(file2, "r");

  if (fptr1 == NULL || fptr2 == NULL) {
    printf("Cannot open files\n");
    return;
  }

  int row1, row2, col1, col2;
  int i, j, k;
  char string[100];

  fscanf(fptr1, "%d %d", &row1, &col1);
  fscanf(fptr2, "%d %d", &row2, &col2);

  double **matrix1 = (double **)malloc(sizeof(double *) * row1);
  for (i = 0; i < row1; i++) {
    matrix1[i] = (double *)malloc(sizeof(double) * col1);
    for (j = 0; j < col1; j++) {
      fscanf(fptr1, "%lf", matrix1[i] + j);
    }
  }

  double **matrix2 = (double **)malloc(sizeof(double *) * row2);
  for (i = 0; i < row2; i++) {
    matrix2[i] = (double *)malloc(sizeof(double) * col2);
    for (j = 0; j < col2; j++) {
      fscanf(fptr2, "%lf", matrix2[i] + j);
    }
  }

  fclose(fptr1);
  fclose(fptr2);

  double **matrix_output = (double **)malloc(sizeof(double *) * row1);
  for (i = 0; i < row1; i++) {
    matrix_output[i] = (double *)malloc(sizeof(double) * col2);
    for (j = 0; j < col2; j++) {
      matrix_output[i][j] = 0;
    }
  }

// clock_t begin = clock();
#pragma omp parallel for private(j, k), \
    shared(row1, col2, col1, matrix1, matrix2, matrix_output), num_threads(T)
  for (i = 0; i < row1; i++) {
    //#pragma omp for schedule(static)
    for (j = 0; j < col2; j++) {
      //#pragma omp for schedule(static)
      for (k = 0; k < col1; k++) {
        matrix_output[i][j] += matrix1[i][k] * matrix2[k][j];
      }
    }
  }
  // clock_t end = clock();

  // printf("%lf\n", (double)(end - begin) / CLOCKS_PER_SEC);

  printf("%d %d\n", row1, col2);
  for (i = 0; i < row1; i++) {
    for (j = 0; j < col2; j++) {
      printf("%lf ", matrix_output[i][j]);
    }
    printf("\n");
  }

  for (i = 0; i < row1; i++) {
    free(*(matrix1 + i));
    free(*(matrix_output + i));
  }

  for (i = 0; i < row2; i++) {
    free(*(matrix2 + i));
  }
}

void main(int argc, char *argv[]) {
  char *file1, *file2;
  file1 = argv[1];
  file2 = argv[2];
  int T = atoi(argv[3]);
  MatrixMult(file1, file2, T);
}
