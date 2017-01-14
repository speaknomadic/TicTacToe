import java.io.Serializable;

public class Board implements Serializable {

	public boolean isFirstOnTurn() {
		return isFirstOnTurn;
	}

	int size = 3;
    private int matrix[][];
    private boolean isFirstOnTurn;
	private int currCount;

	public Board(int newSize) {
		this.size = newSize;
		this.matrix = new int[size][size];
		emptyBoard();
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void emptyBoard() {
		currCount = 0;
		isFirstOnTurn = true;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				matrix[row][col] = Player.EMPTY;
			}
		}
	}

	public void choosePlayers() {
	}

	public void addNumberOnPosition(int row, int col) {
		if (isFirstOnTurn) {
			matrix[row][col] = Player.X_PLAYER;
		} else {
			matrix[row][col] = Player.O_PLAYER;
		}
		isFirstOnTurn = !isFirstOnTurn;
		currCount++;
	}

	private boolean checkVerticalWinner(int player) {
		for (int col = 0; col < matrix.length; col++) {
			boolean found = true;
			for (int row = 0; row < matrix.length; row++) {
				if (matrix[row][col] == player) {
				} else {
					found = false;
					break;
				}
			}

			if (found == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkHorizontalWinner(int player) {
		for (int row = 0; row < matrix.length; row++) {
			boolean found = true;
			for (int col = 0; col < matrix.length; col++) {
				if (matrix[row][col] == player) {
				} else {
					found = false;
					break;
				}
			}

			if (found == true) {
				return true;
			}
		}
		return false;
	}

	private boolean checkMainDiagonalWinner(int player) {

		for (int row = 0; row < matrix.length; row++) {
			if (matrix[row][row] != player) {
				return false;
			}
		}
		return true;

	}

	public boolean checkSecDiagonalWinner(int player) {
		for (int row = 0; row < matrix.length; row++) {
			if (matrix[row][matrix.length - 1 - row] != player) {
				return false;
			}

		}
		return true;
	}

	public int checkForWinner() {
		int player;
		if (!isFirstOnTurn) {
			player = Player.X_PLAYER;
		} else {
			player = Player.O_PLAYER;
		}

		if ((checkVerticalWinner(player) || checkHorizontalWinner(player) || checkMainDiagonalWinner(player))
				|| checkSecDiagonalWinner(player)) {
			return player;
		}

		if (currCount == matrix.length * matrix.length) {
			return Player.EMPTY;
		}
		return 2;
	}

	public void printBoard() {

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}
	}
}
