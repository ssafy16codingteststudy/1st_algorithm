

import java.util.*;

class Node {
	Node parent;
	Node left;
	Node right;
	int name;
	
	Node(int name) {
		this.name = name;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
}

public class Main {	
	
	public static void postorder(Node node) {
		if (node == null)
			return;
		
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.name);
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> nodesInt = new ArrayList<>();
		Map<String, Node> nodes = new HashMap<>();
		
		while (sc.hasNext()) {
			int n = sc.nextInt();
			nodesInt.add(n);
			nodes.putIfAbsent(Integer.toString(n), new Node(n));
		}
		
		int i = 1;
		while (i < nodesInt.size()) {
			Node now = nodes.get(Integer.toString(nodesInt.get(0))); // 루트노드
			Node toPut = nodes.get(Integer.toString(nodesInt.get(i)));
			int beforeIndex = i;
//			System.out.println("i is: " + i);
			while (beforeIndex == i) {
				if (now.name > toPut.name) {
					if (now.left == null) {
						now.left = toPut;
						i++;
					} else {
						now = now.left;
					}
				} else if (now.name < toPut.name) {
					if (now.right == null) {
						now.right = toPut;
						i++;
					} else {
						now = now.right;
					}
				}
			}
			
		}
		
		postorder(nodes.get(Integer.toString(nodesInt.get(0))));
	}	
}

