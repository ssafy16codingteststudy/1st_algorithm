package week8.세연;

import java.util.*;
import java.io.*;

public class BOJ16562_친구비 {
    static int[] cost, root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int totCost = 0;
        cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {cost[i] = Integer.parseInt(st.nextToken());}
        root = new int[N];
        Arrays.fill(root, -1);
        for(int m = 0 ; m < M ; m++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
        }
        for(int i = 0 ; i < N ; i++) {
            if (root[i] == -1) {
                totCost += cost[i];
            }
        }
        if (totCost > K) System.out.println("Oh no");
        else System.out.println(totCost);
    }
    static int find(int v) {
        if (root[v] == -1) return v;
        return root[v] = find(root[v]);
    }
    static void union(int v1, int v2) {
        int x = find(v1);
        int y = find(v2);
        if (x == y) return;
        if (cost[x] < cost[y]) {
            root[y] = x;
        }
        else {
            root[x] = y;
        }
    }
}