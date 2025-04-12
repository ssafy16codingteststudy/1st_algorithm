package week8.세연;

import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int from, to, weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class BOJ1197_최소스패닝트리 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        // 간선 리스트
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        // 간선 가중치 기준 정렬
        Collections.sort(edges);

        int totalCost = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.weight;
                count++;
                if (count == V - 1) break; // MST 완성
            }
        }

        System.out.println(totalCost);
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }
}
