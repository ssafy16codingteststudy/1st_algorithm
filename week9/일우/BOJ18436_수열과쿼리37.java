import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static Node[] segTree;

    // 구간 내 짝수와 홀수의 개수를 저장하는 노드 클래스
    static class Node {
        int even, odd;
        public Node(int even, int odd) {
            this.even = even;
            this.odd = odd;
        }
    }

    // 두 자식 노드를 합쳐 부모 노드를 만드는 함수
    static Node combine(Node left, Node right) {
        return new Node(left.even + right.even, left.odd + right.odd);
    }

    // 세그먼트 트리를 구성하는 빌드 함수
    static void build(int pos, int left, int right) {
        if (left == right) {
            // arr[left]가 짝수면 even 1, 홀수면 odd 1로 초기화
            if (arr[left] % 2 == 0)
                segTree[pos] = new Node(1, 0);
            else
                segTree[pos] = new Node(0, 1);
            return;
        }
        int mid = (left + right) / 2;
        build(pos * 2, left, mid);
        build(pos * 2 + 1, mid + 1, right);
        segTree[pos] = combine(segTree[pos * 2], segTree[pos * 2 + 1]);
    }

    // 인덱스 idx의 값을 newVal로 업데이트 하는 함수 (세그먼트 트리 업데이트)
    static void update(int pos, int left, int right, int idx, int newVal) {
        if (idx < left || idx > right)
            return;
        if (left == right) {
            if (newVal % 2 == 0)
                segTree[pos] = new Node(1, 0);
            else
                segTree[pos] = new Node(0, 1);
            arr[idx] = newVal;
            return;
        }
        int mid = (left + right) / 2;
        if (idx <= mid)
            update(pos * 2, left, mid, idx, newVal);
        else
            update(pos * 2 + 1, mid + 1, right, idx, newVal);
        segTree[pos] = combine(segTree[pos * 2], segTree[pos * 2 + 1]);
    }

    // 구간 [ql, qr] 내의 짝수, 홀수 개수를 구하는 쿼리 함수
    static Node query(int pos, int left, int right, int ql, int qr) {
        // 겹치지 않는 경우 (구간 바깥)
        if (qr < left || ql > right)
            return new Node(0, 0);
        // 완전히 포함하는 경우
        if (ql <= left && right <= qr)
            return segTree[pos];
        int mid = (left + right) / 2;
        Node leftResult = query(pos * 2, left, mid, ql, qr);
        Node rightResult = query(pos * 2 + 1, mid + 1, right, ql, qr);
        return combine(leftResult, rightResult);
    }

    public static void main(String[] args) throws Exception {
        // 빠른 입출력을 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        segTree = new Node[4 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 세그먼트 트리 빌드 (인덱스 1부터 시작)
        build(1, 1, N);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {  // update 쿼리: 1 i x
                int idx = Integer.parseInt(st.nextToken());
                int newVal = Integer.parseInt(st.nextToken());
                update(1, 1, N, idx, newVal);
            } else if (type == 2) {  // 짝수 개수 쿼리: 2 l r
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                Node result = query(1, 1, N, l, r);
                out.println(result.even);
            } else if (type == 3) {  // 홀수 개수 쿼리: 3 l r
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                Node result = query(1, 1, N, l, r);
                out.println(result.odd);
            }
        }
        out.flush();
        out.close();
    }
}
