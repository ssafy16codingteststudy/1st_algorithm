package week9.성수;

import java.util.*;
import java.io.*;

public class BOJ_18436_수열과쿼리37 {

    // 1 i x -> Ai를 x로 바꾼다.
    // 2 l r -> l ~ r 에 속하는 i에 대해 Ai 중에서 짝수의 개수를 출력한다.
    // 3 l r -> l ~ r 에 속하는 i에 대해 Ai 중에서 홀수의 개수를 출력한다.
    static int N; // 수열의 개수(1~100,000)
    static int[] A; // 수열(1~1,000,000,000)
    static int M; // 쿼리의 개수(1~100,000)
    static int[] tree; // 구간에 대한 짝수의 개수 -> 홀수는 구간 내 원소 개수 - 짝수의 개수
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        tree = new int[4 * N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        init(1, N, 1);
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (q == 1) {
                if (A[l] % 2 == 0 && r % 2 == 1) { // 원래 짝수인데 홀수로 바꾸면 짝수의 개수 -1;
                    update(1, N, 1, l, -1);
                } else if (A[l] % 2 == 1 && r % 2 == 0) { // 원래 홀수인데 짝수로 바꾸면 짝수의 개수 +1;
                    update(1, N, 1, l, 1);
                }
                A[l] = r;
            } else if (q == 2) {
                sb.append(query(1, N, 1, l, r)).append("\n");
            } else {
                int a = query(1, N, 1, l, r); // 짝수의 개수로 홀수 계산.
                int b = r - l + 1 - a;
                sb.append(b).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    private static int init(int s, int e, int node) {
        if (s == e) {
            if (A[s] % 2 == 0) {
                return tree[node] = 1;
            } else {
                return 0;
            }
        }
        int mid = (s + e) / 2;
        int left = init(s, mid, node * 2);
        int right = init(mid + 1, e, node * 2 + 1);
        return tree[node] = left + right;
    }

    private static int update(int s, int e, int node, int idx, int val) {
        if (idx < s || idx > e)
            return tree[node];

        if (s == e) {
            return tree[node] += val;
        }
        int mid = (s + e) / 2;
        int left = update(s, mid, node * 2, idx, val);
        int right = update(mid + 1, e, node * 2 + 1, idx, val);
        return tree[node] = left + right;
    }

    private static int query(int s, int e, int node, int l, int r) {
        if (s > r || e < l)
            return 0;

        if (s >= l && e <= r) {
            return tree[node];
        }
        int mid = (s + e) / 2;
        return query(s, mid, node * 2, l, r) + query(mid + 1, e, node * 2 + 1, l, r);
    }
}