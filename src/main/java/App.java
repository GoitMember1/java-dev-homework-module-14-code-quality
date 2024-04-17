import java.util.Scanner;

public class App {

    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[] board = initializeBoard();
        boolean isPlayerTurn = true;
        char winner = '\0';

        System.out.println("Enter box number to select. Enjoy!\n");

        while (winner == '\0') {
            printBoard(board);

            int move = getPlayerMove(board);
            board[move] = PLAYER_SYMBOL;
            isPlayerTurn = false;

            winner = checkWinner(board);
            if (winner != '\0') break;

            move = getComputerMove(board);
            board[move] = COMPUTER_SYMBOL;
            isPlayerTurn = true;

            winner = checkWinner(board);
        }

        printBoard(board);
        printResult(winner);
    }

    private static char[] initializeBoard() {
        char[] board = new char[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
        return board;
    }

    private static void printBoard(char[] board) {
        System.out.println("\n");
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " ");
            if (i < 6) System.out.println("-----------");
        }
        System.out.println("\n");
    }

    private static int getPlayerMove(char[] board) {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < BOARD_SIZE && board[move] == EMPTY_CELL) {
                break;
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
        return move;
    }

    private static int getComputerMove(char[] board) {
        int move;
        do {
            move = (int) (Math.random() * BOARD_SIZE);
        } while (board[move] != EMPTY_CELL);
        return move;
    }

    private static char checkWinner(char[] board) {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == board[i + 1] && board[i + 1] == board[i + 2] && board[i] != EMPTY_CELL) {
                return board[i];
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i] == board[i + 3] && board[i + 3] == board[i + 6] && board[i] != EMPTY_CELL) {
                return board[i];
            }
        }
        // Check diagonals
        if ((board[0] == board[4] && board[4] == board[8] && board[0] != EMPTY_CELL) ||
                (board[2] == board[4] && board[4] == board[6] && board[2] != EMPTY_CELL)) {
            return board[4];
        }
        // Check for draw
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] == EMPTY_CELL) {
                return '\0';
            }
        }
        return 'T'; // 'T' represents tie
    }

    private static void printResult(char winner) {
        if (winner == PLAYER_SYMBOL) {
            System.out.println("You won the game!\nThanks for playing!");
        } else if (winner == COMPUTER_SYMBOL) {
            System.out.println("You lost the game!\nThanks for playing!");
        } else if (winner == 'T') {
            System.out.println("It's a draw!\nThanks for playing!");
        }
    }
}