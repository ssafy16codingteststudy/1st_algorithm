package April_week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_파티 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		List<Route>[] routes = new ArrayList[N+1];
		List<Route>[] reverseRoutes = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			routes[i] = new ArrayList<>();
		}
		for(int i = 0; i <= N; i++) {
			reverseRoutes[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			routes[start].add(new Route(end, cost));
			reverseRoutes[end].add(new Route(start, cost));
		}
		PriorityQueue<Route> pq = new PriorityQueue<>((o1, o2)->{return Integer.compare(o1.cost, o2.cost);});
		pq.add(new Route(X, 0));
		boolean[] visited = new boolean[N+1];
		int[] costs = new int[N+1];
		Arrays.fill(costs, 123456789);
		costs[X] = 0;
		while(!pq.isEmpty()) {
			Route r = pq.poll();
			if(visited[r.end]) {
				continue;
			}
			visited[r.end] = true;
			
			costs[r.end] = r.cost;
			for(Route newr : routes[r.end]) {
				int end = newr.end;
				int cost = newr.cost;
				if(!visited[end] && costs[end] > costs[r.end] + cost) {
					costs[end] = costs[r.end] + cost;
					pq.add(new Route(end, costs[end]));
				}
			}
		}
		
		PriorityQueue<Route> reverspq = new PriorityQueue<>((o1, o2)->{return Integer.compare(o1.cost, o2.cost);});
		reverspq.add(new Route(X, 0));
		visited = new boolean[N+1];
		int[] reverseCosts = new int[N+1];
		Arrays.fill(reverseCosts, 123456789);
		reverseCosts[X] = 0;
		while(!reverspq.isEmpty()) {
			Route r = reverspq.poll();
			if(visited[r.end]) {
				continue;
			}
			visited[r.end] = true;
			
			reverseCosts[r.end] = r.cost;
			for(Route newr : reverseRoutes[r.end]) {
				int end = newr.end;
				int cost = newr.cost;
				if(!visited[end] && reverseCosts[end] > reverseCosts[r.end] + cost) {
					reverseCosts[end] = reverseCosts[r.end] + cost;
					reverspq.add(new Route(end, reverseCosts[end]));
				}
			}
		}
		
		/*		for(int i = 1; i <= N; i++) {
					System.out.print(costs[i] +"  ");
				}
				System.out.println();
				for(int i = 1; i <= N; i++) {
					System.out.print(reverseCosts[i] +"  ");
				}
				System.out.println();*/
		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, costs[i] + reverseCosts[i]);
		}
		System.out.println(max);
		bf.close();
	}
}

class Route{
	int end;
	int cost;
	Route(int end, int cost){
		this.end = end;
		this.cost =cost;
	}
}