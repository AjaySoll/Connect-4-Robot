import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.robotics.subsumption.Behavior;

public class Connect4 implements Behavior {
    
	private boolean suppressed = false;
    private int[][] board;
    private WinChecker winChecker;

    public Connect4() {
        // Initialise the game board
        int rows = 6;
        int columns = 7;
        board = new int[rows][columns];
        
        // Initialise the win checker
        winChecker = new WinChecker();
        winChecker.setBoard(board);
    }

    @Override
    public boolean takeControl() {
        // Checking if sensorCalibration is calibrated.
        return sensorCalibration.isCalibrated();
    }

    @Override
    public void action() {
        while (!suppressed) {
            
            int rows = 6;
            int columns = 7;

            int[][] board = new int[rows][columns];

            // Loop player's turn and AI's turn until 4 in a row
            while (!WinChecker.checkWin(board)) {
                playersTurn(board);
                

                if (WinChecker.checkWin(board)) {
                    LCD.drawString("Player wins!", 2, 2);
                    Delay.msDelay(2000);
                    LCD.clear();
                    break;
                }

                AITurn(board);
               

                if (WinChecker.checkWin(board)) {
                    LCD.drawString("AI wins!", 2, 2);
                    Delay.msDelay(2000);
                    LCD.clear();
                    break;
                }
            }
            
        }   
    }

    @Override
    public void suppress() {
        suppressed = true;
    }

    public static int userInput() {
        int selection = 1;
        boolean selected = false;

        //loop to allow user to select through 1-7 on the LCD
        while (!selected) {
            LCD.clear();
            LCD.drawString("Choose 1-7:", 1, 1);
            LCD.drawString("<  " + selection + "  >", 5, 2);

            int buttonID = Button.waitForAnyPress();
            switch (buttonID) {
                case Button.ID_LEFT:
                    if (selection > 1) {
                        selection--;
                    }
                    break;
                case Button.ID_RIGHT:
                    if (selection < 7) {
                        selection++;
                    }
                    break;
                case Button.ID_ENTER:
                    selected = true;
                    break;
            }
        }
        //confirmation of the choice made
        LCD.clear();
        LCD.drawString("You chose: " + selection, 1, 1);
        Delay.msDelay(2000);
        LCD.clear();
        
        return selection;
    }

    public static void playersTurn(int[][] board) {
        int column = userInput(); // Call userInput method to get the column from the user
        column--; // Adjust for 0-based index

        // Check if the column is valid
        if (column < 0 || column >= board[0].length || board[0][column] != 0) {
            playersTurn(board); // Call the method again for re-entry of input
            return; // Return to avoid executing the rest of the method
        }

        dropPiece(board, column, 1); // 1 represents the player's piece
        
        if (column != 0) { // Move robot if column is not the first one
            MoveRobot.move(column); // Pass the column to the MoveRobot move method
        }
        
        DispensePuck.dispense();
        resetBot.reset();
    }
    
    public static void AITurn(int[][] board) {
        // AI's turn
        LCD.drawString("AI's Turn:", 1, 1);
        Delay.msDelay(2000);
        LCD.clear();
        
        int column = (int) (Math.random() * 7); // Randomly choose a column
        dropPiece(board, column, 2); // 2 represents the AI's piece
        MoveRobot.move(column);// Pass the column to the MoveRobot move method
        DispensePuck.dispense();
        resetBot.reset();
    }
    
    public static void dropPiece(int[][] board, int column, int player) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == 0) {
                board[i][column] = player;
                break;
            }
        }
    }
    
   
}