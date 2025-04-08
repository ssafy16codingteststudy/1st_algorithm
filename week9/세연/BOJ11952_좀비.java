package week9.세연;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K, S, p, q;
    static List<Integer>[] map;
    static int[] cityStatus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        map = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) map[i] = new ArrayList<>();
        cityStatus = new int[N + 1]; // 0: 안전, 1: 위험, -1: 좀비

        // 좀비 도시 입력 및 큐 준비
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int city = Integer.parseInt(br.readLine());
            cityStatus[city] = -1;
            queue.add(new int[]{city, 0});
        }

        // 도로 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a].add(b);
            map[b].add(a);
        }

        // 위험 도시 마킹
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int city = tmp[0], dist = tmp[1];
            if (dist >= S) continue;
            for (int next : map[city]) {
                if (cityStatus[next] == 0 && next != 1 && next != N) {
                    cityStatus[next] = 1;
                    queue.add(new int[]{next, dist + 1});
                }
            }
        }
        
        // 다익스트라
        Long[] dist = new Long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0L;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, 1});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long cost = curr[0] ;
            int city = (int)curr[1];
            if (city == N) {
                System.out.println(cost);
                break;
            }
            if (cost > dist[city]) continue;

            for (int next : map[city]) {
                if (cityStatus[next] == -1) continue;
                int add = (next == N) ? 0 : (cityStatus[next] == 1 ? q : p);
                if (dist[next] > dist[city] + add) {
                    dist[next] = dist[city] + add;
                    pq.add(new long[]{dist[next], next});
                }
            }
        }

    }
}
