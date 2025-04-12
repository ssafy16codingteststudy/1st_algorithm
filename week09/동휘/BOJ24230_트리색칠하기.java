package month4week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ24230_트리색칠하기 {

	public static int[] colors;
	public static boolean[] visited;
	public static List<Integer>[] line;
	
	public static int answer;
	

	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		colors = new int[N + 1];
		visited = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}
		line = new ArrayList[N+1];
		for(int i = 0; i<= N; i++) {
			line[i] = new ArrayList<>();
		}
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			line[a].add(b);
			line[b].add(a);
		}
		
		answer = 0;
		visited[1] = true;
		Queue<point> q = new ArrayDeque<>();
		q.add(new point(1, 0));
		while(!q.isEmpty()) {
			point p = q.poll();
			int newColor;
			if(p.color == colors[p.index]){
				newColor = p.color;
			}else {
				newColor = colors[p.index];
				answer++;
			}
			for(int i : line[p.index]) {
				if(!visited[i]) {
					visited[i] = true;
					q.add(new point(i, newColor));
				}
			}
		}
		
		System.out.println(answer);
		
		bf.close();
	}

}

class point{
	int index;
	int color;
	point(int index, int color){
		this.index = index;
		this.color = color;
	}
}
