package week9.성수;

import java.util.*;
import java.io.*;

public class BOJ_2357_최솟값과최댓값 {

    static int N, M; // 정수의 개수(1~100,000), 쿼리의 개수(1~100,000)
    static int[] arr; // 수열을 저장.
    static int[][] tree; // i번째 노드, 0은 최소, 1은 최대

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        tree = new int[4 * N][2];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        init(1, N, 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] ans = query(1, N, 1, a, b);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static int[] init(int s, int e, int node) {
        if (s == e) {
            tree[node][0] = arr[s]; // 구간의 크기가 1인 노드(리프노드) ->
            tree[node][1] = arr[s];
            return tree[node];
        }

        int mid = (s + e) / 2;
        int[] left = init(s, mid, node * 2);
        int[] right = init(mid + 1, e, node * 2 + 1);
        if (left[0] < right[0]) {
            tree[node][0] = left[0];
        } else {
            tree[node][0] = right[0];
        }

        if (left[1] > right[1]) {
            tree[node][1] = left[1];
        } else {
            tree[node][1] = right[1];
        }

        return tree[node];
    }

    private static int[] query(int s, int e, int node, int l, int r) {
        if (s > r || e < l)
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };

        if (l <= s && e <= r) {
            return tree[node];
        }
        int mid = (s + e) / 2;
        int[] left = query(s, mid, node * 2, l, r);
        int[] right = query(mid + 1, e, node * 2 + 1, l, r);
        int min = Math.min(left[0], right[0]);
        int max = Math.max(left[1], right[1]);

        return new int[] { min, max };
    }
}
