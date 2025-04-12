import java.io.*;
import java.util.*;

class Main {

    static class Node {
        Node left, right;
        char data;

        Node(char data) {
            this.data = data;
        }
    }

    static void preorder(Node node) {
        if(node == null) return;
        System.out.print(node.data);

        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data);
    }

    static Map<Character, Node> tree = new HashMap<>();

    public static void main(String args[]) throws Exception {
        // 전위 순회 : 루트 -> 왼쪽 -> 오른쪽
        // 중위 순회 : 왼쪽 -> 루트 -> 오른쪽
        // 후위 순회 : 왼쪽 -> 오른쪽 -> 루트

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree.putIfAbsent(root, new Node(root));

            if (left != '.' ) {
                tree.putIfAbsent(left, new Node(left));
                tree.get(root).left = tree.get(left);
            }

            if (right != '.') {
                tree.putIfAbsent(right, new Node(right));
                tree.get(root).right = tree.get(right);
            }
        }

        Node root = tree.get('A');

        preorder(root);
        System.out.println();

        inorder(root);
        System.out.println();

        postorder(root);
        System.out.println();
    }
}