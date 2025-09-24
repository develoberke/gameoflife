package com.develoberke;

public class GameLogic {
    private final int boardHeight;
    private final int boardWidth;
    private boolean[][] board;
    private boolean[][] nextBoard;

    public GameLogic(int width, int height) {
        this.boardHeight = height;
        this.boardWidth = width;
        this.board = new boolean[boardHeight][boardWidth];
        this.nextBoard = new boolean[boardHeight][boardWidth];
    }

    public void runGameLogic() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j]) {
                    populatedCellLogics(i, j);
                } else {
                    unPopulatedCellLogics(i, j);
                }
            }
        }

        board = copyBoard(nextBoard);
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public boolean getCell(int x, int y) {
        return board[x][y];
    }

    public void setCell(int x, int y) {
        board[x][y] = !getCell(x, y);
        nextBoard = copyBoard(board);
    }

    private void populatedCellLogics(int colIndex, int rowIndex) {
        int numberOfNeighbours = getNumberOfNeighbours(colIndex, rowIndex);

        if (numberOfNeighbours <= 1 || numberOfNeighbours >= 4) {
            nextBoard[colIndex][rowIndex] = false;
        }
    }

    private void unPopulatedCellLogics(int colIndex, int rowIndex) {
        int numberOfNeighbours = getNumberOfNeighbours(colIndex, rowIndex);

        if (numberOfNeighbours == 3) {
            nextBoard[colIndex][rowIndex] = true;
        }
    }

    private int getNumberOfNeighbours(int colIndex, int rowIndex) {
        int numberOfNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            if (colIndex + i < 0 || colIndex + i >= boardHeight) {
                continue;
            }
            for (int j = -1; j <= 1; j++) {
                if (rowIndex + j < 0 || rowIndex + j >= boardWidth) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    continue;
                }
                if (board[colIndex + i][rowIndex + j]) {
                    numberOfNeighbours++;
                }
            }
        }

        return numberOfNeighbours;
    }

    private boolean[][] copyBoard(boolean[][] board) {
        boolean[][] copy = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
}
