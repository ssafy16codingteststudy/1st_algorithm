package week8.세연;

import java.util.*;
import java.io.*;

public class BOJ1717_집합의표현 {
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        root = new int[n+1];
        Arrays.fill(root, -1);
        int cmd, a,b;
        for (int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (cmd == 0) union(a, b);
            else {
                if (find(a) == find(b)) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
    static int find(int v) {
        if (root[v] == -1) return v;
        return root[v] = find(root[v]);
    }
    static void union(int v1, int v2) {
        int x = find(v1);
        int y = find(v2);
        if (x == y) return;
        root[y] = x;
    }
}