public class NoahsArc
{
    public static void main(String[] args)
    {
        System.out.print("CS51 Final Project\n2015\nAndrew Fai\nTomoya Hasegawa\nRandall Raymond\nPieter Weemaes\n");
        System.out.print("\n\n\n\n");
        System.out.print("Solve blank sudoku board using simple backtracking:\n\n");
        
        Sudoku sudokuBlank = new Sudoku();
        double sudokuBlankBacktrackTime = sudokuBlank.solveAndPrint(sudokuBlank);
        
        printLine();
        
        System.out.print("\n\n=========Solve a blank sudoku board using AC1 domain purging combined with backtracking:\n\n");
        
        SudokuAC1 sudokuBlankAC1 = new SudokuAC1();
        double sudokuBlankAC1Time = sudokuBlankAC1.solveAndPrint(sudokuBlankAC1);
        printLine();
        
        System.out.print("\n\n=========Solve a blank sudoku board using AC3 domain purging combined with backtracking:\n\n");
        
        SudokuAC3 sudokuBlankAC3 = new SudokuAC3();
        double sudokuBlankAC3Time = sudokuBlankAC3.solveAndPrint(sudokuBlankAC3);
        
        
        printLine();
        
        System.out.print("\n\n=========Solve a blank sudoku board using AStar domain purging combined with backtracking:\n\n");
        
        SudokuAStar sudokuBlankAStar = new SudokuAStar();
        double sudokuBlankAStarTime = sudokuBlankAStar.solveAndPrint(sudokuBlankAStar);
        
        
        printLine();
        // solve a sudoku with starting constraints.
        System.out.print("Solve easy sudoku board using simple backtracking:\n\n");
        
        
        int[] easyBoard = new int[81];
        easyBoard[5] = 8;
        easyBoard[8] = 4;
        easyBoard[10] = 8;
        easyBoard[11] = 4;
        easyBoard[13] = 1;
        easyBoard[14] = 6;
        easyBoard[21] = 5;
        easyBoard[24] = 1;
        easyBoard[27] = 1;
        easyBoard[29] = 3;
        easyBoard[30] = 8;
        easyBoard[33] = 9;
        easyBoard[36] = 6;
        easyBoard[38] = 8;
        easyBoard[42] = 4;
        easyBoard[44] = 3;
        easyBoard[47] = 2;
        easyBoard[50] = 9;
        easyBoard[51] = 5;
        easyBoard[53] = 1;
        easyBoard[56] = 7;
        easyBoard[59] = 2;
        easyBoard[66] = 7;
        easyBoard[67] = 8;
        easyBoard[69] = 2;
        easyBoard[70] = 6;
        easyBoard[72] = 2;
        easyBoard[75] = 3;

        Sudoku sudokuEasy = new Sudoku(easyBoard);
        double sudokuEasyBacktrackTime = sudokuBlank.solveAndPrint(sudokuEasy);
        printLine();
        
        System.out.print("Solve easy sudoku board using AC1:\n\n");
        
        SudokuAC1 sudokuEasyAC1 = new SudokuAC1(easyBoard);
        double sudokuEasyAC1Time = sudokuEasyAC1.solveAndPrint(sudokuEasyAC1);
        
        printLine();
        
        System.out.print("Solve easy sudoku board using AC3:\n\n");
        
        SudokuAC3 sudokuEasyAC3 = new SudokuAC3(easyBoard);
        double sudokuEasyAC3Time = sudokuEasyAC3.solveAndPrint(sudokuEasyAC3);
        
        printLine();
        
        System.out.print("Solve easy sudoku board using AStar:\n\n");
        
        SudokuAStar sudokuEasyAStar = new SudokuAStar(easyBoard);
        double sudokuEasyAStarTime = sudokuEasyAStar.solveAndPrint(sudokuEasyAStar);
        
        printLine();
        
        System.out.print("Solve hard sudoku board using simple backtracking:\n\n");
        
        
        int[] hardBoard = new int[81];
        hardBoard[0] = 3;
        hardBoard[8] = 8;
        hardBoard[11] = 8;
        hardBoard[12] = 9;
        hardBoard[14] = 6;
        hardBoard[15] = 5;
        hardBoard[19] = 2;
        hardBoard[22] = 8;
        hardBoard[25] = 7;
        hardBoard[28] = 1;
        hardBoard[30] = 4;
        hardBoard[32] = 7;
        hardBoard[34] = 3;
        hardBoard[38] = 2;
        hardBoard[42] = 9;
        hardBoard[46] = 3;
        hardBoard[48] = 1;
        hardBoard[50] = 8;
        hardBoard[52] = 4;
        hardBoard[55] = 8;
        hardBoard[58] = 7;
        hardBoard[61] = 9;
        hardBoard[65] = 1;
        hardBoard[66] = 8;
        hardBoard[68] = 9;
        hardBoard[69] = 2;
        hardBoard[72] = 5;
        hardBoard[80] = 4;

        Sudoku sudokuHard = new Sudoku(hardBoard);
        double sudokuHardBacktrackTime = sudokuBlank.solveAndPrint(sudokuHard);
        printLine();
        
        System.out.print("Solve hard sudoku board using AC1:\n\n");
        
        SudokuAC1 sudokuHardAC1 = new SudokuAC1(hardBoard);
        double sudokuHardAC1Time = sudokuEasyAC1.solveAndPrint(sudokuHardAC1);
        
        printLine();
        
        System.out.print("Solve hard sudoku board using AC3:\n\n");
        
        SudokuAC3 sudokuHardAC3 = new SudokuAC3(hardBoard);
        double sudokuHardAC3Time = sudokuEasyAC3.solveAndPrint(sudokuHardAC3);
        
        printLine();
        
        System.out.print("Solve hard sudoku board using AStar:\n\n");
        
        SudokuAStar sudokuHardAStar = new SudokuAStar(hardBoard);
        double sudokuHardAStarTime = sudokuEasyAStar.solveAndPrint(sudokuHardAStar);
        
        printLine();
        printLine();
        
        System.out.print("Solve the n-queens problem of placing n number of queens\non a board of n x n size using backtracking:\n\n");
        
        Queens queens4 = new Queens();
        double queens4BacktrackTime = queens4.solveAndPrint(queens4);
        
        printLine();
        
        Queens queens8 = new Queens(8);
        double queens8BacktrackTime = queens8.solveAndPrint(queens8);
        
        printLine();
        
        Queens queens15 = new Queens(15);
        double queens15BacktrackTime = queens15.solveAndPrint(queens15);
        
        printLine();
        
        System.out.print("Solve the same boards using AC1:\n\n");
        
        QueensAC1 queensAC1_4 = new QueensAC1();
        double queensAC1_4BacktrackTime = queensAC1_4.solveAndPrint(queensAC1_4);
        
        printLine();
        
        QueensAC1 queensAC1_8 = new QueensAC1(8);
        double queensAC1_8BacktrackTime = queensAC1_8.solveAndPrint(queensAC1_8);
        
        printLine();
        
        QueensAC1 queensAC1_15 = new QueensAC1(15);
        double queensAC1_15BacktrackTime = queensAC1_15.solveAndPrint(queensAC1_15);
        
        System.out.print("Solve the same boards using AC3:\n\n");
        
        QueensAC3 queensAC3_4 = new QueensAC3();
        double queensAC3_4BacktrackTime = queensAC3_4.solveAndPrint(queensAC3_4);
        
        printLine();
        
        QueensAC3 queensAC3_8 = new QueensAC3(8);
        double queensAC3_8BacktrackTime = queensAC3_8.solveAndPrint(queensAC3_8);
        
        printLine();
        
        QueensAC3 queensAC3_15 = new QueensAC3(15);
        double queensAC3_15BacktrackTime = queensAC3_15.solveAndPrint(queensAC3_15);
        
        printLine();
        
        System.out.print("Solve the same boards using AStar:\n\n");
        
        QueensAStar queensAStar_4 = new QueensAStar();
        double queensAStar_4BacktrackTime = queensAStar_4.solveAndPrint(queensAStar_4);
        
        printLine();
        
        QueensAStar queensAStar_8 = new QueensAStar(8);
        double queensAStar_8BacktrackTime = queensAStar_8.solveAndPrint(queensAStar_8);
        
        printLine();
        
        QueensAStar queensAStar_15 = new QueensAStar(15);
        double queensAStar_15BacktrackTime = queensAStar_15.solveAndPrint(queensAStar_15);
        
        printLine();
        
        System.out.print("\nRunning Times:\n\n");
        System.out.print("Blank Sudoku Backtrack: " + sudokuBlankBacktrackTime);
        System.out.print("\nBlank Sudoku AC1:       " + sudokuBlankAC1Time);
        System.out.print("\nBlank Sudoku AC3:       " + sudokuBlankAC3Time);
        System.out.print("\nBlank Sudoku AStar:     " + sudokuBlankAStarTime);
        System.out.print("\n\nEasy Sudoku Backtrack:  " + sudokuEasyBacktrackTime);
        System.out.print("\nEasy Sudoku AC1:        " + sudokuEasyAC1Time);
        System.out.print("\nEasy Sudoku AC3:        " + sudokuEasyAC3Time);
        System.out.print("\nEasy Sudoku AStar:      " + sudokuEasyAStarTime);
        System.out.print("\n\nHard Sudoku Backtrack:  " + sudokuHardBacktrackTime);
        System.out.print("\nHard Sudoku AC1:        " + sudokuHardAC1Time);
        System.out.print("\nHard Sudoku AC3:        " + sudokuHardAC3Time);
        System.out.print("\nHard Sudoku AStar:      " + sudokuHardAStarTime);
        System.out.print("\n\n4x4 Queens Backtrack:   " + queens4BacktrackTime);
        System.out.print("\n4x4 Queens AC1:         " + queensAC1_4BacktrackTime);
        System.out.print("\n4x4 Queens AC3:         " + queensAC3_4BacktrackTime);
        System.out.print("\n4x4 Queens AStar:       " + queensAStar_4BacktrackTime);
        System.out.print("\n\n8x8 Queens Backtrack:   " + queens8BacktrackTime);
        System.out.print("\n8x8 Queens AC1:         " + queensAC1_8BacktrackTime);
        System.out.print("\n8x8 Queens AC3:         " + queensAC3_8BacktrackTime);
        System.out.print("\n8x8 Queens AStar:       " + queensAStar_8BacktrackTime);
        System.out.print("\n\n15x15 Queens Backtrack: " + queens15BacktrackTime);
        System.out.print("\n15x15 Queens AC1:       " + queensAC1_15BacktrackTime);
        System.out.print("\n15x15 Queens AC3:       " + queensAC3_15BacktrackTime);
        System.out.print("\n15x15 Queens AStar:     " + queensAStar_15BacktrackTime);
        
        
        
    }
    
    private static void printLine()
    {
        System.out.print("=====================================\n\n");
    }
}