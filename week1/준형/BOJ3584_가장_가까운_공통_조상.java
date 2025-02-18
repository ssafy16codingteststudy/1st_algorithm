package algo;

import java.io.*;
import java.util.*;

class Main { 
	
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static int[] depth, parent;
	
	static void dfs(int node, int d) {
		visited[node] = true;
		depth[node] = d;
		
        for (int child : tree[node]) {
            if (!visited[child]) {
                parent[child] = node;
                dfs(child, d + 1);
            }
        }
	}
	
	static int lca(int a, int b) {
		while (depth[a] > depth[b]) a = parent[a];
		while (depth[b] > depth[a]) b= parent[b];
		
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}
	
	
    public static void main(String args[]) throws Exception {

    	// 입력
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int T = Integer.parseInt(br.readLine());
    	
    	while (T-- > 0) {
    		int N = Integer.parseInt(br.readLine());
    		StringTokenizer st; 
    		visited = new boolean[N + 1];
    		boolean[] hasParent = new boolean[N + 1];
    		depth = new int[N + 1];
    		parent = new int[N + 1];
    		tree = new ArrayList[N + 1];
    		
    		for (int i = 1; i <= N; i++) {
    			tree[i] = new ArrayList<>();
    		}
    		
    		for (int i = 0; i < N - 1; i++) {
        		st = new StringTokenizer(br.readLine());
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			tree[a].add(b);
    			tree[b].add(a);
    			hasParent[b] = true;
    		}
    		
    		int root = 1;
    		for (int i = 1; i <= N; i++) {
    			if (!hasParent[i]) {
    				root = i;
    				break;
    			}
    		}
    		
    		dfs(root, 0);
    		
    		st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            sb.append(lca(A, B)).append("\n");
    	}
    	
    	System.out.println(sb);
    }
}
