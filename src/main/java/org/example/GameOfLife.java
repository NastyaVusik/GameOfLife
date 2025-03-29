package org.example;


public class GameOfLife {
    public static void main(String[] args) {
        String input = "000_111_000";
        String nextGen = nextGeneration(input);
        System.out.println(nextGen);
    }

    public static String nextGeneration(String input) {
        int[][] board = parseBoard(input);
        int rows = board.length, cols = board[0].length;
        int[][] next = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = countLiveNeighbors(board, r, c);
                if (board[r][c] == 1) {
                    next[r][c] = (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0;
                } else {
                    next[r][c] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }
        return formatBoard(next);
    }

    private static int[][] parseBoard(String input) {
        String[] rows = input.split("_");
        int[][] board = new int[rows.length][rows[0].length()];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                board[i][j] = rows[i].charAt(j) - '0';
            }
        }
        return board;
    }

    private static int countLiveNeighbors(int[][] board, int r, int c) {
        int rows = board.length, cols = board[0].length;
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nr = r + i, nc = c + j;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    count += board[nr][nc];
                }
            }
        }
        return count;
    }

    private static String formatBoard(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            if (i > 0) sb.append("_");
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
}