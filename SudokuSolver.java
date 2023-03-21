package BackTracking;

import java.util.Scanner;

public class SudokuSolver {

    // is safe function to check 1 - 9 digit place on cell or not
    public static boolean isSafe(int[][] sudoku, int row, int col, int digit) {

        // to check column
        // range is 1 to 9
        for (int i = 0; i <= 8; i++) {
            if (sudoku[i][col] == digit) {
                return false;
            }
        }

        // to check Row
        // range is 1 to 9
        for (int j = 0; j <= 8; j++) {
            if (sudoku[row][j] == digit) {
                return false;
            }
        }

        // Check grid 3x3
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    // sudoku solve function
    public static boolean solveSudoku(int sudoku[][], int row, int col) {

        // base case

        if (row == 9) {
            return true;
        }

        // to check col is last pos then row increase by 1
        int nextRow = row, nextCol = col + 1;
        if (col + 1 == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }

        // This condition is already elements on the row col is present the call the
        // next level
        if (sudoku[row][col] != 0) {
            return solveSudoku(sudoku, nextRow, nextCol);
        }

        // recursion
        for (int digit = 1; digit <= 9; digit++) {
            if (isSafe(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (solveSudoku(sudoku, nextRow, nextCol)) { // solution exist
                    return true;
                }
                sudoku[row][col] = 0; // solution not exist the start from 0
            }
        }

        return false; // solution is not exist then return false
    }

    // Printing the ans sudoku
    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("| ");
                System.out.print(sudoku[i][j] + " ");
            }

            System.out.print("| ");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int sudoku[][] = new int[9][9];

        System.out.println("---------------------------------------------");
        System.out.println("Enter The Sudoku Problem :)");
        System.out.println("You Enter 9 X 9 Sudoku Problem");
        System.out.println("---------------------------------------------");
        System.out.println();
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                sudoku[i][j] = sc.nextInt();
            }
        }

        if (solveSudoku(sudoku, 0, 0)) {
            System.out.println("---------------------------------------------");
            System.out.println("Solution is exits");
            System.out.println("---------------------------------------------");
            printSudoku(sudoku);
        } else {
            System.out.println("Solution is not exist");
        }
    }

}
