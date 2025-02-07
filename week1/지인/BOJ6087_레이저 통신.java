

package main;

import java.util.*;


public class Main {	
	
	
	public static boolean cango(int x, int y, char[][] board) {
		int H = board.length;
		int W = board[0].length;
		if (x < 0 || x >= H || y < 0 || y >= W) {
			return false;
		}
		
		if (board[x][y] == '*') {
			return false;
		}
		
		return true;
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int W = sc.nextInt();
		int H = sc.nextInt();
		sc.nextLine();
		
		char[][] board = new char[H][W];
		int[][] C = new int[2][2];
		int tempIdxC = 0;
		boolean[][] visited = new boolean[H][W];
		
		for (int i=0;i<H;i++) {			
			String input = sc.nextLine();
			for (int j=0;j<input.length();j++) {
				board[i][j] = input.charAt(j);
				if (board[i][j] == 'C') {
					C[tempIdxC][0] = i; C[tempIdxC++][1] = j;
				}
			}
		}
		
		Deque<int[]> q = new ArrayDeque<>();

		
		q.addLast(new int[] {C[0][0], C[0][1], 0});
		visited[C[0][0]][C[0][1]] = true;
		
		int[] end = C[1];
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		
		
		while (!q.isEmpty()) {
			int[] now = q.pollFirst();
			int x = now[0];
			int y = now[1];
			int val = now[2];
			
			if (x == end[0] && y == end[1]) {
				System.out.println(val - 1);
				break;
			}
			
			for (int i=0;i<4;i++) {
				int nx = x, ny = y;
				while (cango(nx, ny, board)) {
					if (!visited[nx][ny]) {
						q.addLast(new int[]{nx, ny, val + 1});
						visited[nx][ny] = true;
					}
					nx += dx[i];
					ny += dy[i];
				}
			}
		}
	}	
}