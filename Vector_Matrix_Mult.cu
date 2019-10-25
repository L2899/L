#include <stdio.h>
#include<time.h>
#define SIZE 10

__global__ void VectorMatrixMult(int a[], int b[], int c[], int n)
{
	int i = threadIdx.x;

	if(i < n){
		//j is used for iterating through columns;---
		for(int j=0; j<SIZE; j++){
			c[i] +=(a[j] * *(b + i*SIZE + j));
		}
	}
}

int main()
{
	int *a, *b, *c;
	clock_t t;

	//vector
	a = (int*)malloc(SIZE * sizeof(int));
	//matrix
	b = (int*)malloc(SIZE * SIZE * sizeof(int));
	//result
	c = (int*)malloc(SIZE * sizeof(int));

	for (int i = 0; i < SIZE; i++){
		a[i] = i+1;
		for (int j = 0; j < SIZE; j++){
			*(b + i*SIZE + j) = i*j;
			//int index=blockDim.x * blockIdx.x + threadIdx.x;
		}
	}

	int *d_a, *d_b, *d_c;
	int size=SIZE * sizeof(int);
	int size2d=SIZE * SIZE * sizeof(int);
	cudaMalloc(&d_a, size);
	cudaMalloc(&d_b, size2d);
	cudaMalloc(&d_c, size);

	cudaMemcpy(d_a, a, size, cudaMemcpyHostToDevice);
	cudaMemcpy(d_b, b, size2d, cudaMemcpyHostToDevice);
t=clock();
	VectorMatrixMult <<< 1, SIZE >>> (d_a, d_b, d_c, SIZE);

	cudaDeviceSynchronize();
	t=clock()-t;
double t2=((double)t/CLOCk_PER_SEC);
	cudaMemcpy(c, d_c, size, cudaMemcpyDeviceToHost);

	printf("Vector: \n");
	for (int i = 0; i < SIZE; i++){
		printf("%d ", a[i]);
	}
	printf("\n");
	printf("Matrix: \n");
	for (int i = 0; i < SIZE; i++){
		for (int j = 0; j < SIZE; j++){
			printf("%d ", *(b + i*SIZE + j));
		}
		printf("\n");
	}
	printf("Product: \n");
	for (int i = 0; i < SIZE; i++){
		printf("%d ", c[i]);
	}
	printf("\n");

	cudaFree(d_a);
	cudaFree(d_b);
	cudaFree(d_c);

	free(a);
	free(b);
	free(c);

	return 0;
}
