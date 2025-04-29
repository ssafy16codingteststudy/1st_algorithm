import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main {
    static ArrayList<Node>[] graph;

    public static int Dijkstra(int n, int start, int end) {
        boolean[] check = new boolean[n + 1];
        int[] dist = new int[n + 1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if(check[nowVertex]) continue;
            check[nowVertex] = true;

            for(Node next : graph[nowVertex]) {
                if(dist[next.index] > dist[nowVertex]+ next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++)  graph[i] = new ArrayList<>();


        for(int i = 0 ; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[v].add(new Node(w, cost));
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, Dijkstra(n, i, x) + Dijkstra(n, x, i));
        }

        System.out.println(max);
    }
}
