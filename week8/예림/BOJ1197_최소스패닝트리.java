import java.io.*;
import java.util.*;

public class Main {
    
    private static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }
        edges.sort(Comparator.comparing(e -> e.w));

        long total = 0;
        int count = 0;
        for (Edge edge : edges) {
            if (find(edge.u) == find(edge.v)) {
                continue;
            }
            
            union(edge.u, edge.v);
            total += edge.w;
            count++;

            if (count == V - 1) {
                break;
            }
        }

        System.out.println(total);
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootB] = rootA;
        }
    }
}
