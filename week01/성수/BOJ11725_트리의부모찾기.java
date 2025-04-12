import java.io.*;
import java.util.*;


public class BOJ11725_트리의부모찾기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] tree = new  ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			tree[i] = new ArrayList<>();
			
		}
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			tree[u].add(v);
			tree[v].add(u);
		}
		
		boolean[] visit = new boolean[N+1]; //boolean defalut -> false
		int[] parent = new int[N+1];
		
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(1);
		
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			visit[current] = true;
			
			for(int neighbor : tree[current]) {
				if(!visit[neighbor]) {
					parent[neighbor] = current;
					queue.add(neighbor);
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<=N; i++) {
			sb.append(parent[i]).append("\n");
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();

	}

}
