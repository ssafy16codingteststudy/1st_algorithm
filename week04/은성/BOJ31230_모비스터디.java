package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ31230_모비스터디 {

    private static int N;
    private static List<City>[] lists;
    private static long[] distA;
    private static long[] distB;
    private static int A;
    private static int B;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        lists = new List[N + 1];
        distA = new long[N + 1];
        distB = new long[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
            distA[i] = distB[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            lists[a].add(new City(b, value));
            lists[b].add(new City(a, value));
        }

        // 다익스트라 탐색으로 A -> B 에 대한 모든 최단경로 파악
        dijkstra(A, distA);
        // B -> A 에 대한 모든 최단 경로 파악
        dijkstra(B, distB);

        long minRoute = distA[B];
        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            // 각각 두 도시에서 해당 도시와의 최단거리 합이 A ~ B 까지의 거리이면 해당 도시는 경유 도시
            if (distA[i] + distB[i] == minRoute) {
                sb.append(i).append(" ");
                count++;
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }

    private static void dijkstra(int start, long[] dist) {
        PriorityQueue<City> queue = new PriorityQueue<>();
        queue.add(new City(start, 0L));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            City poll = queue.poll();

            if (dist[poll.now] < poll.value) {
                continue;
            }

            for (City city : lists[poll.now]) {

                long newValue = dist[poll.now] + city.value;
                if (newValue < dist[city.now]) {
                    dist[city.now] = newValue;
                    queue.add(new City(city.now, newValue));
                }
            }
        }
    }

    private static class City implements Comparable<City> {
        int now;
        long value;

        public City(int now, long value) {
            this.now = now;
            this.value = value;
        }

        @Override
        public int compareTo(City o) {
            return Long.compare(this.value, o.value);
        }
    }
}