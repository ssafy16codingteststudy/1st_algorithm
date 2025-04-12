
import java.util.*;

class Node {
	Node parent;
	List<Node> children;
	String name;
	
	Node(int name) {
		this.name = Integer.toString(name);
		this.parent = null;
		this.children = new ArrayList<>();
	}
}

public class Main {	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		
		for (int loop = 0;loop<T;loop++) {
			int N = sc.nextInt();
//			System.out.printf("N: %d",N);
			
			Map<String, Node> tree = new HashMap<>();
			for (int i=0;i<N-1;i++) {
				int parent = sc.nextInt();
				int child = sc.nextInt();
				
				tree.putIfAbsent(Integer.toString(parent), new Node(parent));
				tree.putIfAbsent(Integer.toString(child), new Node(child));
				tree.get(Integer.toString(parent)).children.add(tree.get(Integer.toString(child)));
				tree.get(Integer.toString(child)).parent = tree.get(Integer.toString(parent));
			}
			
			boolean[] nearestParent = new boolean[N+1];
			
			int nodeA = sc.nextInt();
			int nodeB = sc.nextInt();
//			System.out.printf("A,B: %d %d\n", nodeA, nodeB);
			
			Node nowNode = tree.get(Integer.toString(nodeA));
//			System.out.println("now node: " + nowNode.name);
			
			while (nowNode != null) {
				String nodeNum = nowNode.name;
				nearestParent[Integer.parseInt(nodeNum)] = true;
				nowNode = nowNode.parent; 
			}
			
			nowNode = tree.get(Integer.toString(nodeB));
			
			while (nowNode != null) {
				String nodeNum = nowNode.name;
				if (nearestParent[Integer.parseInt(nodeNum)]) {
					System.out.println(nodeNum);
					break;
				}
				nowNode = nowNode.parent;
			}
			
			
		}
		
		
	}	
}



