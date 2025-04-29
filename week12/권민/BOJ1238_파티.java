import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private static int N, M, X;
    private static final int MAX = (int) 2e9;
    private static List<int[]>[] m1 = new ArrayList[1001];
    private static List<int[]>[] m2 = new ArrayList[1001];
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            m1[i] = new ArrayList<>();
            m2[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            m1[start].add(new int[]{end, distance});
            m2[end].add(new int[]{start, distance});
        }

        dist = new int[N + 1];
        Arrays.fill(dist, MAX);
        dijkstra(X, m1);
        int[] tmp = dist.clone();

        dist = new int[N + 1];
        Arrays.fill(dist, MAX);
        dijkstra(X, m2);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] < MAX && tmp[i] < MAX) {
                answer = Math.max(answer, dist[i] + tmp[i]);
            }
        }

        sb.append(answer).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra(int start, List<int[]>[] graph) {
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, start}); // {cur_dist, cur_node}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curDist = current[0];
            int curNode = current[1];

            for (int[] next : graph[curNode]) {
                int nextNode = next[0];
                int nextDist = next[1] + curDist;

                if (dist[nextNode] > nextDist) {
                    dist[nextNode] = nextDist;
                    pq.offer(new int[]{nextDist, nextNode});
                }
            }
        }
    }
}
