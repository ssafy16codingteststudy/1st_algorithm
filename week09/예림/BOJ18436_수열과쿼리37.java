import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int evenCount, oddCount;

        Node(int evenCount, int oddCount) {
            this.evenCount = evenCount;
            this.oddCount = oddCount;
        }
    }

    private static int[] arr; // 원본 배열
    private static Node[] tree; // 세그먼트 트리
    private static int N; // 배열 길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new Node[4 * N];
        build(1, 0, N - 1); // 루트는 1번

        int M = Integer.parseInt(br.readLine()); // 명령 수
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 1) { // a 인덱스 값 b로 바꾸기
                update(1, 0, N - 1, a - 1, b);
            } else if (cmd == 2) { // 짝수 개수 쿼리
                int result = query(1, 0, N - 1, a - 1, b - 1).evenCount;
                sb.append(result).append("\n");
            } else if (cmd == 3) {
                int result = query(1, 0, N - 1, a - 1, b - 1).oddCount;
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void build(int node, int nodeL, int nodeR) {
        if (nodeL == nodeR) {
            if (arr[nodeL] % 2 == 0) { // 짝수
                tree[node] = new Node(1, 0);
                return;
            }
            tree[node] = new Node(0, 1); // 홀수
            return;
        }
        int mid = (nodeL + nodeR) / 2;
        build(node * 2, nodeL, mid); // 왼쪽 자식 노드 만들기
        build(node * 2 + 1, mid + 1, nodeR); // 오른쪽 자식 노드 만들기

        Node left = tree[node * 2];
        Node right = tree[node * 2 + 1];

        tree[node] = new Node(
                left.evenCount + right.evenCount,
                left.oddCount + right.oddCount
        );
    }

    private static void update(int node, int nodeL, int nodeR, int index, int value) {
        if (nodeL == nodeR) {
            arr[index] = value;
            if (value % 2 == 0) {
                tree[node] = new Node(1, 0); // 짝수
                return;
            }
            tree[node] = new Node(0, 1); // 홀수
            return;
        }

        int mid = (nodeL + nodeR) / 2;
        if (index <= mid) {
            update(node * 2, nodeL, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, nodeR, index, value);
        }

        Node left = tree[node * 2];
        Node right = tree[node * 2 + 1];
        tree[node] = new Node(
                left.evenCount + right.evenCount,
                left.oddCount + right.oddCount
        );
    }

    private static Node query(int node, int nodeL, int nodeR, int queryL, int queryR) {
        if (queryR < nodeL || nodeR < queryL) { // 구간이 완전히 벗어난 경우
            return new Node(0, 0);
        }
        if (queryL <= nodeL && nodeR <= queryR) { // 완전히 포함되는 경우
            return tree[node];
        }
        // 일부만 겹치는 경우
        int mid = (nodeL + nodeR) / 2;
        Node leftResult = query(node * 2, nodeL, mid, queryL, queryR);
        Node rightResult = query(node * 2 + 1, mid + 1, nodeR, queryL, queryR);
        return new Node(
                leftResult.evenCount + rightResult.evenCount,
                leftResult.oddCount + rightResult.oddCount
        );
    }
}
