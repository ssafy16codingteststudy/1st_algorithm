package 건휘;

import java.util.*;
import java.io.*;

public class BOJ24230_트리색칠하기 {

	static int n, result = 0;
	static int[] color;
	static List<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		color = new int[n+1];
		tree = new ArrayList[n+1];
		
		for(int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= n; i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dfs(1, -1, 0);
		
		System.out.println(result);
	}
	
	static void dfs(int currentNode, int parentNode, int parentColor) {
		if (color[currentNode] != parentColor) {
			result++;
		}
		for(int child : tree[currentNode]) {
			if(child != parentNode) {
				dfs(child, currentNode, color[currentNode]);
			}
		}
	}
}