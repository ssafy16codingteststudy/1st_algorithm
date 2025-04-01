
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		Map<Integer, List<int[]>> lines = new HashMap<>();
		
		for (int i=0;i<E;i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			
			lines.putIfAbsent(A, new ArrayList<>());
			lines.putIfAbsent(B, new ArrayList<>());
			lines.get(A).add(new int[] {C, B});
			lines.get(B).add(new int[] {C, A});
		}
		boolean[] visited = new boolean[V + 1];
		long sum = 0;
		
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		q.addAll(lines.get(1));
		visited[1] = true;
		int count = 1;
		while(count < V) {
			int[] now = q.poll();
			while (visited[now[1]]) {
				now = q.poll();
			}
			visited[now[1]] = true;
			count++;
			q.addAll(lines.get(now[1]));
			sum += now[0];
		}
		
		System.out.println(sum);
    }
}