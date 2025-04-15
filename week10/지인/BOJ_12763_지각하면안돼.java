import java.io.*;
import java.util.*;

public class BOJ_12763_지각하면안돼 {
	static int[][] dp;
	
	static void update(int n, int t, int val) {
		for (int i=t;i<dp[0].length;i++) {
			if (dp[n][i] > val)
				dp[n][i] = val;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int T = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int L = Integer.parseInt(st.nextToken());
    	
    	dp = new int[N + 1][T + 1];
    	for (int i=2;i<=N;i++) {
    		for (int j=0;j<=T;j++) {
    			dp[i][j] = Integer.MAX_VALUE;
    		}
    	}
    	
    	Map<Integer, List<int[]>> routes = new HashMap<>();
    	
    	for (int i=0;i<L;i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int time = Integer.parseInt(st.nextToken());
    		int money = Integer.parseInt(st.nextToken());
    		
    		routes.putIfAbsent(a, new ArrayList<>());
    		routes.get(a).add(new int[] {b, time, money});
    		routes.putIfAbsent(b, new ArrayList<>());
    		routes.get(b).add(new int[] {a, time, money});
    	}
    	
    	int[] now = {1, 0}; //노드 번호, 걸린 시간
    	PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
    	q.add(now);
    	while(!q.isEmpty()) {
    		now = q.poll();
    		
    		for (int[] next: routes.get(now[0])) {
    			int n = next[0];
    			int t = next[1];
    			int m = next[2];
    			
    			if (now[1] + t > T) 
    				continue;
    			
    			if (dp[n][now[1] + t] > dp[now[0]][now[1]] + m) {
//    				dp[n][now[1] + t] = dp[now[0]][now[1]] + m;
    				update(n, now[1] + t, dp[now[0]][now[1]] + m);
    				if (n == N) //이거 밖에있거나 밑에잇거나..
            			continue;
    				q.add(new int[] {n, now[1] + t});
    			}
    		}
    	}
    	int answer = Integer.MAX_VALUE;
    	for (int i=0;i<=T; i++) {
    		answer = Math.min(answer, dp[N][i]);
    	}
    	System.out.println(answer <= M ? answer :-1);
    }
}