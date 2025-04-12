import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int min, max;

        Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 1_000_000_001;
    private static int[] arr;
    private static Node[] tree;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 배열 크기
        int M = Integer.parseInt(st.nextToken()); // 명령 수

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new Node[4 * N];
        build(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) { // a ~ b번째 수의 최소/최대값
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node result = query(1, 0, N - 1, a - 1, b - 1);
            sb.append(result.min).append(" ").append(result.max).append("\n");
        }

        System.out.println(sb);
    }

    private static void build(int node, int nodeL, int nodeR) {
        if (nodeL == nodeR) {
            tree[node] = new Node(arr[nodeL], arr[nodeL]);
            return;
        }
        int mid = (nodeL + nodeR) / 2;
        build(node * 2, nodeL, mid);
        build(node * 2 + 1, mid + 1, nodeR);

        Node left = tree[node * 2];
        Node right = tree[node * 2 + 1];

        tree[node] = new Node(
                Math.min(left.min, right.min),
                Math.max(left.max, right.max)
        );
    }

    private static Node query(int node, int nodeL, int nodeR, int queryL, int queryR) {
        if (queryR < nodeL || nodeR < queryL) { // 구간이 완전히 벗어난 경우
            return new Node(MAX_VALUE, MIN_VALUE);
        }
        if (queryL <= nodeL && nodeR <= queryR) { // 완전히 포함되는 경우
            return tree[node];
        }
        // 일부만 겹치는 경우
        int mid = (nodeL + nodeR) / 2;
        Node leftResult = query(node * 2, nodeL, mid, queryL, queryR);
        Node rightResult = query(node * 2 + 1, mid + 1, nodeR, queryL, queryR);
        return new Node(
                Math.min(leftResult.min, rightResult.min),
                Math.max(leftResult.max, rightResult.max)
        );
    }
}
