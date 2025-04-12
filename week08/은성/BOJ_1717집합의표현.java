import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1717집합의표현 {
    public static int[] unf;
    // 1717, 유니온 파인드 알고리즘
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        unf = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            unf[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order == 1) {
                findUnion(a, b);
            } else {
                union(a, b);
            }
        }
    }

    private static int find(int v) {
        if (v == unf[v]) {
            return v;
        }
        // 일직선 경로가 되지 않도록 경로 단축
        return unf[v] = find(unf[v]);
    }

    private static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        if (findA != findB) {
            unf[findA] = findB;
        }
    }

    private static void findUnion(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA == findB) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
