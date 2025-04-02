package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16562_UNION {
    static int[] cost;
    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        cost = new int[N+1];
        parent = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) cost[i] = Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++) parent[i] = i;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a > b) union(b,a);
            else union(a,b);
        }

        long cnt = 0;
        boolean[] check = new boolean[N+1];
        for(int i=1;i<=N;i++){
            int idx = find(i);

            if(!check[idx]){
                cnt += cost[idx];
                check[idx] = true;
            }

            check[i] = true;
        }

        if(cnt > k) System.out.println("Oh no");
        else System.out.println(cnt);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(cost[a] > cost[b]) parent[a] = b;
        else parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}
