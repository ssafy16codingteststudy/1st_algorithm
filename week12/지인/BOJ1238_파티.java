import java.io.*;
import java.util.*;

public class BOJ1238_파티 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int X = Integer.parseInt(st.nextToken());
    	int[] route1 = new int[N + 1];
    	int[] route2 = new int[N + 1];
    	
    	Map<Integer, List<int[]>> roads1 = new HashMap<>();
    	Map<Integer, List<int[]>> roads2 = new HashMap<>();
    	for (int i=1;i<=N;i++) {
    		roads1.put(i, new ArrayList<>());
    		roads2.put(i, new ArrayList<>());
    		route1[i] = Integer.MAX_VALUE;
    		route2[i] = Integer.MAX_VALUE;
    	}
    	route1[X] = 0;
    	route2[X] = 0;
    	for (int i=0;i<M;i++) {
    		st = new StringTokenizer(br.readLine());
    		int from = Integer.parseInt(st.nextToken());
    		int to = Integer.parseInt(st.nextToken());
    		int time = Integer.parseInt(st.nextToken());
    		
    		roads1.get(from).add(new int[] {to, time});
    		roads2.get(to).add(new int[] {from, time});
    	}
    	
    	PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> {
    		int cmp = Integer.compare(a[1], b[1]);
    		if (cmp == 0)
    			cmp = Integer.compare(a[0], b[0]);
    		return cmp;
    	});
    	q.add(new int[] {X, 0});
    	
    	while (!q.isEmpty()) {
    		int[] now = q.poll();
    		List<int[]> nexts = roads1.get(now[0]);
    		
    		for (int[] next: nexts) {
    			if (next[1] + route1[now[0]] < route1[next[0]]) {
    				route1[next[0]] = next[1] + route1[now[0]];
    				q.add(new int[] {next[0], route1[next[0]]});
    			}
    		}
    	}
    	
    	q.add(new int[] {X, 0});
    	while (!q.isEmpty()) {
    		int[] now = q.poll();
    		List<int[]> nexts = roads2.get(now[0]);
    		
    		for (int[] next: nexts) {
    			if (next[1] + route2[now[0]] < route2[next[0]]) {
    				route2[next[0]] = next[1] + route2[now[0]];
    				q.add(new int[] {next[0], route2[next[0]]});
    			}
    		}
    	}
    	
    	int answer = 0;
    	for(int i=1;i<=N;i++)
    		if (answer < route1[i] + route2[i])
    			answer = route1[i] + route2[i];
    	System.out.println(answer);
    }
}