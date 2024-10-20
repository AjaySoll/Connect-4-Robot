import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;


public class WinChecker implements Behavior {

	private int[][] board;

    public WinChecker() {

    }

    // Method to set the game board
    public void setBoard(int[][] board) {
        this.board = board;
    }

    @Override
    public boolean takeControl() {
        return board != null && checkWin(board);
    }

    @Override
    public void action() {
        
        LCD.clear();
        LCD.drawString("4 in a row detected!", 2, 2);
        
    }

    @Override
    public void suppress() {

    }

    public static boolean checkWin(int[][] board) {
    	// Check rows
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i][j + 1] &&
                    board[i][j] == board[i][j + 2] &&
                    board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }
    
        // Check columns
        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i <= board.length - 4; i++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i + 1][j] &&
                    board[i][j] == board[i + 2][j] &&
                    board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }
    
        // Check diagonals (from top-left to bottom-right)
        for (int i = 0; i <= board.length - 4; i++) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i + 1][j + 1] &&
                    board[i][j] == board[i + 2][j + 2] &&
                    board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }
    
        // Check diagonals (from top-right to bottom-left)
        for (int i = 0; i <= board.length - 4; i++) {
            for (int j = 3; j < board[i].length; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i + 1][j - 1] &&
                    board[i][j] == board[i + 2][j - 2] &&
                    board[i][j] == board[i + 3][j - 3]) {
                    return true;
                }
            }
        }
    
        return false; // No win condition found
    }
}
