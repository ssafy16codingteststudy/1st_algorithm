package April_week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1507_궁금한민호 {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] map = new int[N][N];
		PriorityQueue<Route> pq = new PriorityQueue<>((o1, o2)->{return Integer.compare(o1.cost, o2.cost);});
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(j > i) {
					pq.add(new Route(i, j, num));
				}
				
			}
		}
		
		int[][] originalMap = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				originalMap[i][j] = 123456789;
			}
		}

		
		boolean wrongAnswer = false;
		while(!pq.isEmpty()) {
			Route r = pq.poll();
			
			boolean hasRoute = false;
			for(int i = 0; i < N; i++) {
				if(i == r.start || i == r.end) {
					continue;
				}
				//System.out.println(map[r.start][i]  + "       " + map[i][r.end]);
				//System.out.println();
				if(r.cost > map[r.start][i] + map[i][r.end]) {
					wrongAnswer = true;
				}
				if(r.cost == map[r.start][i] + map[i][r.end]) {
					hasRoute = true;
					break;
				}
			}
			if(wrongAnswer) {
				break;
			}
			if(!hasRoute) {
				//System.out.println(r.start + " " + r.end + " " + r.cost);
				originalMap[r.start][r.end] = r.cost;
				originalMap[r.end][r.start] = r.cost;
			}
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if(originalMap[i][j] != 123456789) {
					answer += originalMap[i][j];
				}
			}
		}
		if(wrongAnswer) {
			System.out.println(-1);
		}else {
			System.out.println(answer);
		}

		bf.close();
	}
}

class Route{
	int start;
	int end;
	int cost;
	public Route(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	
}