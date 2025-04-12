import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int num;
		Node parent;
		int ga; //부모와의 가중치
		List<Node> children = new ArrayList<>();

		Node(int num){
			this.num = num;
		}

		void setParent(Node node, int ga) {
			parent = node;
			this.ga = ga;
		}

		void addChild(Node node) {
			children.add(node);
		}
	}

	static int n;
	static Node[] nodes;
	static int maxLength;
	static int maxLengthNode;
	static boolean[] visited;

	static void dfs(Node node, int total) {

		visited[node.num] = true;

		if (node.children.isEmpty()) {
			if (total > maxLength) {
				maxLength = Math.max(total, maxLength);
				maxLengthNode = node.num;
			}
		}

		for (Node n : node.children) {
			if (!visited[n.num]) {
				dfs(n, total + n.ga);
			}
		}

		if (node.parent != null) {
			if (!visited[node.parent.num]) {
				dfs(node.parent, total + node.ga);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// init
		n = Integer.parseInt(br.readLine());

		nodes = new Node[n + 1];

		for (int i = 0; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < n - 1; i++ ) {
			st = new StringTokenizer(br.readLine());

			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int ga = Integer.parseInt(st.nextToken());

			Node pNode = nodes[parent];
			Node cNode = nodes[child];

			pNode.addChild(cNode);
			cNode.setParent(pNode, ga);
		}

		maxLength = 0;
		maxLengthNode = 1;

		visited = new boolean[n + 1];
		dfs(nodes[1], 0);

		visited = new boolean[n + 1];
		dfs(nodes[maxLengthNode], 0);

		System.out.println(maxLength);
	}
}