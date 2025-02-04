package 용범;

import java.util.*;
import java.io.*;

public class BOJ1991_트리순회 {

    static class Node {
        char left, right;

        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static char left, root, right;
    static Map<Character, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {

        input();

        solve();

    }

    // 전위 순회: root -> left -> right
    private static void preOrder(char p) {

        if (p == '.')
            return;

        Node node = tree.get(p);
        sb.append(p);

        preOrder(node.left);
        preOrder(node.right);

    }

    // 중위 순회: left -> root -> right
    private static void inOrder(char p) {

        if (p == '.')
            return;

        Node node = tree.get(p);

        inOrder(node.left);
        sb.append(p);
        inOrder(node.right);

    }

    // 후위 순회: left -> right -> root
    private static void postOrder(char p) {

        if (p == '.')
            return;

        Node node = tree.get(p);
        postOrder(node.left);
        postOrder(node.right);
        sb.append(p);
    }

    private static void solve() throws IOException {

        preOrder('A');
        sb.append("\n");
        inOrder('A');
        sb.append("\n");
        postOrder('A');

        // 출력 부분
        System.out.println(sb.toString());

    }

    private static void input() throws IOException {

        // 입력 부분
        N = Integer.parseInt(br.readLine()); // N: 노드의 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            root = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);

            // 노드 정보 저장 (루트, 노드(왼쪽, 오른쪽))
            tree.put(root, new Node(left, right));
        }

        br.close();
    }
}
