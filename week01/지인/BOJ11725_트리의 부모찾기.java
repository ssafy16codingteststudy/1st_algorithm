
import java.util.*;

public class Main {	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Map<String, List<Integer>> nodes = new HashMap<>();
		
		for (int i=1;i<N;i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			nodes.putIfAbsent(Integer.toString(A), new ArrayList<>());
			nodes.putIfAbsent(Integer.toString(B), new ArrayList<>());
			
			nodes.get(Integer.toString(A)).add(B);
			nodes.get(Integer.toString(B)).add(A);
		}
		
		int[] parents = new int[N + 1];
		parents[1] = -1;
		
		Deque<Integer> q = new ArrayDeque<>();
		
		q.addLast(1);
		while(!q.isEmpty()) {
			int parent = q.poll();
			List<Integer> children = nodes.get(Integer.toString(parent));
			for (int child:children) {
				if (parents[child] == 0) {
					parents[child] = parent;
					q.addLast(child);
				}
			}
		}
		
		for (int i=2;i<=N;i++) {
			System.out.println(parents[i]);
		}
		
	}	
}

