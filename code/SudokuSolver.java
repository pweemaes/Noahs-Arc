import java.io.*;
import java.util.Scanner;
/**
 * SudokuSolver
 * 
 */
public class SudokuSolver
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter name of puzzle file: ");
        String fileName = scan.nextLine();
        int[] board = new int[81];
        
        try 
        {
            Scanner input = new Scanner(new File(fileName));
            board = insertIntoArray(input);
        } catch (IOException e) {
            System.out.println("error accessing file " + fileName);
            System.out.println(e);
            System.exit(1);
        }
        
        SudokuAC3 sudokuAC3 = new SudokuAC3(board);
        double sudokuAC3Time = sudokuAC3.solveAndPrint(sudokuAC3);
        
        System.out.print("Solving times: " + sudokuAC3Time );
    }
    
    private static int[] insertIntoArray(Scanner input)
    {
        int[] board = new int[81];
        for (int i = 0, length = board.length; i < length; i++)
        {
            board[i] = input.nextInt();
        }
        return board;
    }
}
