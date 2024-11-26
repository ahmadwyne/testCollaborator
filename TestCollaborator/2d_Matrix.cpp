#include <iostream>
//kxdskicjmiedmcj
using namespace std;
//raffat...
// Function to print a 2D matrix
void printMatrix(int matrix[][3], int rows, int cols) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

// Function to compute the transpose of a matrix
void transposeMatrix(int matrix[][3], int transpose[][3], int rows, int cols) {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            transpose[j][i] = matrix[i][j];
        }
    }
}

// Function to compute the sum of all entries in the matrix
int sumMatrix(int matrix[][3], int rows, int cols) {
    int sum = 0;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            sum += matrix[i][j];
        }
    }
    return sum;
}

// Function to compute the multiply of all entries in the matrix
int sumMatrix(int matrix[][3], int rows, int cols) {
    int multiply = 1;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            multiply *= matrix[i][j];
        }
    }
    return multiply;
}

int main() {
    // Example 2D matrix with fixed size 3x3
    int matrix[3][3] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    // Print the 2D matrix
    cout << "The matrix is:" << endl;
    printMatrix(matrix, 3, 3);

    return 0;
}
