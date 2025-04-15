package april_week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ12763_지각하면안돼 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int T = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(bf.readLine());
		
		List<Line>[] lists = new ArrayList[N+1];
		for(int i =0; i <= N; i++) {
			lists[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			lists[start].add(new Line(end, time, cost));
			lists[end].add(new Line(start, time, cost));
		}
		
		PriorityQueue<Line> pq = new PriorityQueue<>((o1, o2)->{return Integer.compare(o1.cost, o2.cost);});
		pq.add(new Line(1, 0, 0));
		int[][] distance = new int[N+1][T+1];
		for(int i = 0; i <= N; i++) {
			Arrays.fill(distance[i], 123456789);
		}

		distance[1][0] = 0;
		while(!pq.isEmpty()) {
			Line l = pq.poll();
			if(distance[l.end][l.time] < l.cost) {
				continue;
			}
			for(Line newl : lists[l.end]) {
				if(l.time + newl.time <= T) {
					if(distance[newl.end][l.time + newl.time] > l.cost + newl.cost) {
						distance[newl.end][l.time + newl.time] = l.cost + newl.cost;
						pq.add(new Line(newl.end, l.time + newl.time, l.cost + newl.cost));
					}
				}
			}
		}
		
		
		int a = 123456789;
		for (int i = 0; i <= T; i++){
            a = Math.min(a, distance[N][i]);
        }
		if(a > M) {
			System.out.println(-1);
		}else {
			System.out.println(a);
		}
		
		
		
		
	}
}

class Line{
	int end;
	int time;
	int cost;
	Line(int end, int time, int cost){
		this.end = end;
		this.time = time;
		this.cost =cost;
	}
}