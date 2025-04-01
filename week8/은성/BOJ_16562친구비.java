import java.io.*;
import java.util.*;


public class BOJ_16562친구비 {
	
	/**
	 * 16562 친구비
	 * 유니온 파인드에서 누가 대표가 될 것인가? -> 친구비가 작은 값을 기준으로 대표가 되면 됨
	 * m 번의 union 연산 이후, 모든 노드를 순회하며 각자의 대표들과의 친구비의 합을 구해 비교하면 됨
	 */
	private static int[] parents;	// 대표 배열
	private static int[] cost;		// 친구비 배열
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        parents = new int[n + 1];
        cost = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
        	parents[i] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
        	cost[i] = Integer.parseInt(st.nextToken());
        }        
        
        for(int i = 1; i <= m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	union(a, b);
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < n + 1; i++) {
        	set.add(find(i));
        }
        
        int sum = 0;
        for (Integer parent : set) {
        	sum += cost[parent];
        }
        
        if(sum <= k) {
        	System.out.println(sum);
        } else {
        	System.out.println("Oh no");
        }
    }
    
    private static int find(int node) {
    	if(parents[node] == node) {
    		return node;
    	}
    	return parents[node] = find(parents[node]);
    }
    
    private static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if(aRoot == bRoot) {
    		return false;
    	}
    	
    	// 친구비가 가장 작은 친구를 대표로 삼음,
    	if(cost[aRoot] <= cost[bRoot]) {
    		parents[bRoot] = aRoot;    			
    	}
    	else {
    		parents[aRoot] = bRoot;    		
    	}
    	return true;
    }
}
