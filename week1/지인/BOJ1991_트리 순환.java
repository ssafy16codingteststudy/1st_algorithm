import java.util.*;

class Node {
	String name = "";
	Node left;
	Node right;

	
	public Node(String name) {
		this.name = name;
		this.left = null;
		this.right = null;	
	}
}

public class Main {	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine(); //개행 처리
		Map<String, Node> tree = new HashMap<>();
		
		for (int i=0;i<N;i++) {
			String str = sc.nextLine();
			String[] arr = str.split(" ");
			
			tree.putIfAbsent(arr[0], new Node(arr[0]));
			
			if (!arr[1].equals(".")) {
				tree.putIfAbsent(arr[1], new Node(arr[1]));
				tree.get(arr[0]).left = tree.get(arr[1]);
			}
			
			if (!arr[2].equals(".")) {
				tree.putIfAbsent(arr[2], new Node(arr[2]));
				tree.get(arr[0]).right = tree.get(arr[2]);
			}
		}
		
		int idx = 0;
		preorder(tree.get("A"));
		System.out.println();
		inorder(tree.get("A"));
		System.out.println();
		postorder(tree.get("A"));
		
	}
	
	public static void preorder(Node n) {
		if (n == null)
			return;
		if (n.name.equals("."))
			return;
		System.out.print(n.name);
		preorder(n.left);
		preorder(n.right);
	}
	
	public static void inorder(Node n) {
		if (n == null)
			return;
		if (n.name.equals("."))
			return;
		
		inorder(n.left);
		System.out.print(n.name);
		inorder(n.right);
		
	}
	
	public static void postorder(Node n) {
		if (n == null)
			return;
		if (n.name.equals("."))
			return;
		
		postorder(n.left);
		postorder(n.right);
		System.out.print(n.name);
	}
	
}
