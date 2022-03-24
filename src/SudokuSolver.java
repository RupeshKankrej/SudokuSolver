public class SudokuSolver {
    private static final int GRID_VALUE = 9;

    public static void main(String[] args) {
//        creating local sudoku board
        int[][] board = {
                {3, 0, 9, 0, 7, 2, 5, 0, 0},
                {4, 0, 0, 0, 0, 0, 1, 0, 7},
                {0, 8, 6, 9, 5, 1, 3, 4, 0},
                {5, 0, 0, 8, 0, 0, 9, 2, 0},
                {0, 9, 8, 0, 0, 5, 0, 0, 3},
                {6, 2, 7, 0, 9, 4, 0, 5, 0},
                {9, 0, 0, 0, 2, 0, 6, 0, 0},
                {0, 0, 0, 5, 8, 0, 0, 0, 0},
                {8, 3, 0, 7, 0, 6, 0, 0, 0}
        };
//        Printing the board before solving
        printBoard(board);

//        Solving the board using solveBoard method which takes board/ 9x9 matrix as parameter
        if(solveBoard(board)){
            System.out.println("Successfully Solved!");
        }else{
            System.out.println("Unsolvable Board :(");
        }
        System.out.println();
//        Printing the board after solving
        printBoard(board);
    }

//    Creating a method which will help us to print the 9x9 matrix in board format
    private static void printBoard(int[][] board){
        for(int i = 0; i < GRID_VALUE; i++){
            if(i%3==0 && i!=0){
                System.out.println("-----------");
            }
            for(int j = 0; j < GRID_VALUE; j++){
                if(j%3==0 && j!=0){
                    System.out.print("|");
                }
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

//    To check whether the entered number already exists in the current row
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for(int i = 0; i<GRID_VALUE; i++){
            if(board[row][i]==number){
                return true;
            }
        }
        return false;
    }

//    To check whether the entered number already present in the column
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for(int i = 0; i<GRID_VALUE; i++){
            if(board[i][column]==number){
                return true;
            }
        }
        return false;
    }

//    To check whether the entered number already present in the respective box
    private static boolean isNumberInBox(int[][] board, int number,int row,  int column){
        int localRow = row - row%3;
        int localColumn = column - column%3;
        for(int i = localRow; i < localRow + 3; i++){
            for(int j = localColumn; j < localColumn + 3; j++){
                if(board[i][j]==number){
                    return true;
                }
            }
        }
        return false;
    }

//    The combination of all methods which will tell us if the element is correctly placed or not
    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, column) &&
               !isNumberInBox(board, number, row, column);
    }

//    The method to solve the sudoku
    private static boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_VALUE; row++){
            for (int column = 0; column < GRID_VALUE; column++){
//                The empty slot will be represented by the 0
                if(board[row][column]==0){
//                    To check which value from 1 to 9 will suit the given position
                    for(int valueToCheck = 1; valueToCheck <= GRID_VALUE; valueToCheck++){
                        if(isValidPlacement(board, valueToCheck, row, column)){
                            board[row][column] = valueToCheck;

//                            after placing the value in position we will check whether other positions will also be satisfied or not
//                            if yes then return true else set the value of the position to 0
                            if(solveBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}
