package April_week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1799_비숍 {

	/* 가장 중요한 발상
	 * 		체스판의 비숍은 같은 바닥의 색에만 갈 수 있음!!!
	 *
	 * 근데 굳이 나눌 필요가 있나?
	 * 풀이 1 : 체스판을 1칸씩 이동하면서 각 칸에 비숍을 뒀을 때/안뒀을 때의 경우를 완탐으로 체크
	 * 	=> 10%에서 시간 초과
	 * -> 탐색하는 양이 너무 많은 걸까?
	 * 풀이 2 : 비숍을 뒀을 때, 그 비숍에 의해 비숍을 둘 수 없어지는 공간을 처리(N-Queens 풀이 방법)
	 * 		   백트래킹을 이용해 체스판을 원래대로 돌려야 하기 때문에 기본 맵을 기록한 int[][]배열 추가
	 * 	=> 10%에서 메모리 초과
	 * -> dfs가 너무 깊어 기본 맵을 기록한 int[][]배열이 너무 많아져서 메모리가 터지는 구나
	 * -> dfs를 줄이는 방법은 없을까?
	 * 풀이 3 : 바닥이 검정일 때와 흰색일 때를 나눠서 계산 -> 체스판의 비숍은 같은 바닥의 색만 갈 수 있기 때문에 두 상황은 서로 독립적
	 * 	=> 50%에서 메모리 초과
	 * -> 메모리를 더 줄이는 방법은 없을까?
	 * 풀이 4 : 백트레킹을 위해 기본 맵을 기록할 때 int[][] 배열이 아니라 boolean[][]배열을 사용해 필드의 값의 변경 여부만 체크
	 * 	=> 통과
	*/	
	public static int[][] map;
	public static int[] dx = {-1, -1, 1, 1};
	public static int[] dy = {-1, 1, -1, 1};
	
	public static int N;
	public static int max;
	
	public static boolean canPosition(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int newx = x;
			int newy = y;
			while(true) { // 비숍의 경로상에 다른 비숍이 있는지 체크
				newx += dx[i];
				newy += dy[i];
				if(newx < 0 || newx >= N || newy < 0 || newy >= N ) {
					break;
				}
				if(map[newy][newx] == 2) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void check(int color, int x, int y, int count) {
		if(x >= N) { //현재 x의 위치가 맵 밖이면 다음 줄로 넘어감
			y++;
			if(y == N) { // y의 위치가 맵 밖이면 체스판을 전부 다 확인한 것이기 때문에 탐색 종료
				max = Math.max(max, count);

				return;
			}else { // 아니면 시작 바닥 색에 따라 x좌표의 시작 위치가 달라짐
				if(color == 0) {
					if(y%2 == 0) {
						x = 0;
					}else {
						x = 1;
					}
				}else {
					if(y%2 == 0) {
						x = 1;
					}else {
						x = 0;
					}
				}
				
				
			}
		}
		if(map[y][x] == 0) { //비숍을 둘 수 없는 칸이면 스킵
			check(color, x+2, y, count); // 같은 색의 칸은 옆으로 2칸 넘어가야 함
		}else {
			if(canPosition(x, y)) { // 비숍을 둘 수 있는 칸이고, 그 칸에 비숍을 둘 수 있다면
				boolean[][] change = new boolean[N][N]; // 각 좌표의 값 변경이 있었는지를 기록하는 변수
				change[y][x] = true;
				map[y][x] = 2; //비숍을 해당 칸에 위치시킴
				for(int i = 0; i < 4; i++) { //비숍을 둘 수 없게되는 칸을 갱신
					int newx = x;
					int newy = y;
					while(true) {
						newx += dx[i];
						newy += dy[i];
						if(newx < 0 || newx >= N || newy < 0 || newy >= N ) {
							break;
						}
						if(map[newy][newx] == 1) {
							map[newy][newx] = 0;
							change[newy][newx] = true;
						}
					}
				}
				check(color, x+2, y, count+1);
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(change[i][j]) { //값이 변경됐던 칸이면 되돌림
							map[i][j] = 1;
						}
							
						
					}
				}
			}
			check(color, x+2, y, count);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		check(0, 0, 0, 0); // 바닥이 흰색일 때 체크
		int temp = max;
		max = 0;
		check(1, 1, 0, 0); // 바닥이 검정색일 때 체크
		
		System.out.println(max +temp);
		bf.close();
	}
}
