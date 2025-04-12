
import java.util.*;

class Node {
	Node parent;
	int distanceWithParent;
	List<Node> children;
	int name;
	
	Node(int name){
		this.name = name;
		parent = null;
		distanceWithParent = 0;
		children = new ArrayList<>();
	}
}


public class Main {	
	
	public static int maxLength(Node n, Map<String, Node> nodes) {
		if (n == null)
			return 0;
		if (n.children.size() == 0) {
			return n.distanceWithParent;
		}
		int answer = 0;
		
		for (Node child: n.children) {
			answer = Math.max(answer, maxLength(child, nodes));
		}
		return answer + n.distanceWithParent;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		if (n == 1) {
			System.out.println("0");
			return;
		}
		
		Map<String, Node> nodes = new HashMap<>();
		for (int i=1;i<n;i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			int distance = sc.nextInt();
			
			String p = Integer.toString(parent);
			String c = Integer.toString(child);
			
			nodes.putIfAbsent(p, new Node(parent));
			nodes.putIfAbsent(c, new Node(child));
			
			nodes.get(p).children.add(nodes.get(c));
			nodes.get(c).parent = nodes.get(p);
			nodes.get(c).distanceWithParent = distance;
		}
		
		int answer = 0;
		for (int i=1;i<=n;i++) {
			Node now = nodes.get(Integer.toString(i));
			if (now.children.size() == 0)
				continue;
			
			List<Integer> routes = new ArrayList<>();
			for (Node c: now.children) {
				int maxRoute = 0;
				maxRoute = Math.max(maxRoute, maxLength(c,nodes));
				routes.add(maxRoute);
//				System.out.printf("node num: %d %d+%d", i, c.distanceWithParent, maxRoute);
			}
			routes.sort(Collections.reverseOrder());
			
			if (now.children.size() == 1)
				answer = Math.max(answer, routes.get(0));
			else
				answer = Math.max(answer, routes.get(0) + routes.get(1));
			
			
//			System.out.printf("\n node: %d answer: %d\n", now.name, answer);
		}
		System.out.println(answer);
	}	
}

