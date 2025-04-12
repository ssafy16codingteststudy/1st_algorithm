import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static Node[] segTree;

    // 각 구간의 최소값과 최대값을 저장할 노드 클래스
    static class Node {
        int min, max;
        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    // 두 자식 노드를 합쳐 부모 노드를 만드는 함수
    static Node combine(Node leftNode, Node rightNode) {
        return new Node(Math.min(leftNode.min, rightNode.min), Math.max(leftNode.max, rightNode.max));
    }

    // 세그먼트 트리 빌드 함수
    static void build(int pos, int left, int right) {
        if (left == right) {
            segTree[pos] = new Node(arr[left], arr[left]);
            return;
        }
        int mid = (left + right) / 2;
        build(pos * 2, left, mid);
        build(pos * 2 + 1, mid + 1, right);
        segTree[pos] = combine(segTree[pos * 2], segTree[pos * 2 + 1]);
    }

    // 구간 [ql, qr] 내의 최소값과 최대값을 반환하는 쿼리 함수
    static Node query(int pos, int left, int right, int ql, int qr) {
        // 구간이 아예 겹치지 않는 경우
        if (qr < left || ql > right)
            return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
        // 구간이 완전히 포함되는 경우
        if (ql <= left && right <= qr)
            return segTree[pos];
        int mid = (left + right) / 2;
        Node leftResult = query(pos * 2, left, mid, ql, qr);
        Node rightResult = query(pos * 2 + 1, mid + 1, right, ql, qr);
        return combine(leftResult, rightResult);
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1번부터 N번까지의 수를 저장할 배열 (인덱스 1부터 사용)
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 세그먼트 트리 배열 초기화 (최대 4*N 크기)
        segTree = new Node[4 * N];
        build(1, 1, N);

        StringBuilder sb = new StringBuilder();
        // 각 쿼리마다 최소값과 최대값을 구해서 출력 문자열에 추가
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node result = query(1, 1, N, a, b);
            sb.append(result.min).append(" ").append(result.max).append("\n");
        }
        System.out.print(sb);
    }
}
