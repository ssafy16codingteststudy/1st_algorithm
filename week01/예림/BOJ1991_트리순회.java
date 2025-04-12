package 예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1991_트리순회 {

    private static StringBuilder sb;
    private static Map<Character, Node> tree;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tree = new HashMap<>();
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree.putIfAbsent(root, new Node(root));
            if (left != '.') {
                tree.putIfAbsent(left, new Node(left));
                tree.get(root).left = tree.get(left);
            }
            if (right != '.') {
                tree.putIfAbsent(right, new Node(right));
                tree.get(root).right = tree.get(right);
            }
        }

        preorder(tree.get('A'));
        sb.append("\n");
        inorder(tree.get('A'));
        sb.append("\n");
        postorder(tree.get('A'));

        System.out.println(sb);
    }

    private static class Node {

        char value;
        Node left, right;

        public Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private static void preorder(Node node) {
        if (node == null) {
            return;
        }
        sb.append(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    private static void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);
    }

    private static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);
    }
}
