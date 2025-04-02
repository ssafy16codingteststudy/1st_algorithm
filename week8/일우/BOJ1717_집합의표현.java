package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1717 {
    static class Edge {
        int key, from, to;
        Edge (int k, int from, int to) {
            this.key = k;
            this.from = from;
            this.to = to;
        }
    }

    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        make();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int from = Integer.parseInt(input[1]);
            int to = Integer.parseInt(input[2]);
            edges.add(new Edge(k, from, to));
        }

        for(Edge e : edges) {
            if(e.key == 0) {
                union(e.from, e.to);
            } else if(e.key == 1)
                check(e.from, e.to);
        }
    }

    static void make() {
        parents = new int[N+1];
        for(int i = 0; i <= N; i++)
            parents[i] = i; //부모 노드를 자기 자신의 노드로
    }

    static int find(int a) {
        if(a == parents[a]) return a; //본인이 루트 노드이면 루트노드 반환
        return parents[a] = find(parents[a]); //경로 압축 하면서
    }

    static void check(int a, int b) {
        if(find(a) == find(b)) System.out.println("YES");
        else System.out.println("NO");
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return true; //같은 집합에 속해있는 것

        //그게 아니라면
        parents[rootB] = rootA; //B집합의 루트노드를 A집합의 루트노드로 교체해서 집합을 합침
        return false;
    }
}
