package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939중량제한 {

    /**
     * 1939 중량제한
     * PG_등산 코스 정하기와 유사한 문제라 생각하여 다익스트라로 풀이
     * 내가 지나온 과정 중에서는 가장 작은 값을 선택해야 하고 -> newCost 결정 원리
     * 그 값들중 큰 값들을 기록해 나아가야 함 -> dist[] 배열 갱신 원리
     * 그 후 목표에 도달하면 즉시 탈출 -> 우선순위 큐이므로 최적임을 보장
     */
    private static int n;
    private static List<Integer[]>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        lists = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            lists[a].add(new Integer[]{b, cost});
            lists[b].add(new Integer[]{a, cost});
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(a, b));
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(new LineComparator());
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        queue.add(new Integer[]{start, Integer.MAX_VALUE});

        while (!queue.isEmpty()) {
            Integer[] poll = queue.poll();
            int now = poll[0];
            int cost = poll[1];

            if (now == end) {
                return cost;
            }

            if (dist[now] > cost) {
                continue;
            }

            for (Integer[] near : lists[now]) {
                int next = near[0];
                int newCost = Math.min(cost, near[1]);

                if (dist[next] < newCost) {
                    dist[next] = newCost;
                    queue.add(new Integer[]{next, newCost});
                }
            }
        }

        return -1;
    }

    private static class LineComparator implements Comparator<Integer[]> {

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return o2[1] - o1[1];
        }
    }
}