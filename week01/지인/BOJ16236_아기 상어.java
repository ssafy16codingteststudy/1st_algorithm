package main;

import java.util.*;


public class Main {	
	
	public static int bfs(int[][] area, int[] now, int[] to, int weight) {
		Deque<int[]> q = new ArrayDeque<>();
		int[] dx = {-1,0,0,1};
		int[] dy = {0,-1,1,0};
		
		int x,y;
		
		q.addLast(new int[] {now[0], now[1], 0});
		boolean[][] visited = new boolean[area.length][area.length];
		
		while (!q.isEmpty()) { 
			int[] temp = q.pollFirst();
			if (temp[0] == to[0] && temp[1] == to[1])
				return temp[2];
				//break;
			for (int i = 0;i<4;i++) {
				x = temp[0] + dx[i];
				y = temp[1] + dy[i];
				if (x >= 0 && x < area.length && y >= 0 && y < area.length && area[x][y] <= weight && !visited[x][y]) {
					visited[x][y] = true;
					q.addLast(new int[] {x, y, temp[2] + 1});
				}
			}
			
		}
		
		return -1; //이거!!!!!!!!!!!!!!!!!!!!!!!
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] area = new int[N][N];
				
		int[] shark = new int[2];
		int sharkWeight = 2;
		List<int[]> fishXY = new ArrayList<>();
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				int now = sc.nextInt();
				area[i][j] = now;
				if (now == 9) {
					shark[0] = i; shark[1] = j;
					area[i][j] = 0; //바보
				} else if (now != 0) {
					fishXY.add(new int[]{i, j, now});
				}
			}
		}
		
		int move = 0;
		int eat = 0;
		List<int[]> fishes = new ArrayList<>(); // = fishXY.get(fish); //이러면 안돼~! 이하를 전부 가져와야해
		
		while (true) {
			fishes.clear();
			for (int i=0;i<fishXY.size();i++) {
				if (fishXY.get(i)[2] < sharkWeight) {
					fishes.add(fishXY.get(i)); 
				}
			}
			
			if (fishes.isEmpty()) //이제 먹을애가 없음
				break;
			
			
			int minDist = 999999;
			
			int [] toEat = new int[2];
			
			for (int[] f: fishes) {
				int dist = bfs(area, shark, f, sharkWeight);
				int x = f[0]; int y = f[1];
				
				if (dist == -1)
					continue;
				if (minDist > dist) {
					minDist = dist;
					toEat[0] = x; toEat[1] = y;
				} else if (minDist == dist) {
					if (toEat[0] > x) {
						toEat[0] = x; toEat[1] = y;
					} else if (toEat[0] == x) {
						if (toEat[1] > y) {
							toEat[0] = x;
							toEat[1] = y;
						}
					}
				}
			}
			
			if (minDist == 999999)
				break; // 더 못먹음
			
			move += minDist;
			shark[0] = toEat[0];
			shark[1] = toEat[1];
			eat++;
//			System.out.printf("x: %d y: %d\n", shark[0], shark[1]);
			// 방금 먹은애를 fishes 리스트에서 삭제해야함
//			for (int[] t: fishXY) {
//				System.out.printf("fish : %d %d\n",t[0], t[1]);
//			}
//			System.out.println("");
			
			fishXY.removeIf(f -> f[0] == shark[0] &&  f[1] == shark[1]);

			
//			fishes.removeIf(f -> f[0] == shark[0] && f[1] == shark[1]);
//			for (int[] t: fishXY) {
//				System.out.printf("fished : %d %d\n",t[0], t[1]);
//			}
//			System.out.println("---");

			if (sharkWeight == eat) {
				eat = 0;
				sharkWeight++;
			}
//			System.out.println(sharkWeight.get());
			
		}
		
		System.out.println(move);
		
	}	
}