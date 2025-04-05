import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ24230_트리색칠하기 {

    private static int N;
    private static int[] color;
    private static List<Integer>[] lists;

    /**
     * 24230. 트리 색칠하기
     * 첫 번째 접근 : 초기 color 값을 0으로 전부 세팅해두고,
     * 목표 색깔과 다를 경우 밑에 노드를 모두 색칠하는식 -> 시간초과
     * 두 번쨰 접근 : 생각해보니 그럴 필요 없이 이전 노드와 색깔이 다르면 count++ 해주면 되는거였음
     *
     * + 반례 : 첫 노드가 0 이 아니면 + 1 해줘야 함, 문제를 꼼꼼히 잘 읽자... 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        color = new int[N + 1];
        lists = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lists[a].add(b);
            lists[b].add(a);
        }

        System.out.println(checkColor(1));
    }

    private static int checkColor(int start) {
        Deque<Integer[]> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        deque.add(new Integer[]{start, start});
        visited[start] = true;

        int count = color[start] != 0 ? 1 : 0;
        while (!deque.isEmpty()) {
            Integer[] poll = deque.poll();
            int now = poll[0];
            int last = poll[1];

            if (color[now] != color[last]) {
                count++;
            }

            for (Integer next : lists[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    deque.add(new Integer[]{next, now});
                }
            }
        }
        return count;
    }
}