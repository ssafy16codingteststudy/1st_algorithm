package week8.세연;

import java.util.*;
import java.io.*;

public class BOJ20040_사이클게임 {
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        root = new int[N];
        int answer = 0;

        for (int i = 0; i < N; i++) root[i] = i;
        for(int m = 0 ; m < M ; m++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (union(v1, v2)) {
                answer = m+1;
                break;
            }
        }
        System.out.println(answer);
    }
    static int find(int v) {
        if (root[v] == v) return v;
        return root[v] = find(root[v]);
    }
    static boolean union(int v1, int v2) {
        int x = find(v1);
        int y = find(v2);
        if (x != y) {
            root[y] = x;
            return false;
        }
        return true;
    }

}