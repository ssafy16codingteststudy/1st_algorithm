import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int MAX = 100_001;
    private static int[] previous;
    private static int start, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        sb.append(bfs()).append("\n");

        List<Integer> path = new ArrayList<>();
        for (int i = target; i != start; i = previous[i]) {
            path.add(i);
        }
        path.add(start);

        Collections.reverse(path);
        for (int p : path) {
            sb.append(p).append(" ");
        }

        System.out.println(sb);
    }

    private static int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX];
        previous = new int[MAX];

        queue.add(new int[] {start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[0] == target) {
                return now[1];
            }

            for (int next : new int[]{now[0] - 1, now[0] + 1, now[0] * 2}) {
                if (next < 0 || next >= MAX || visited[next]) {
                    continue;
                }
                queue.add(new int[] {next, now[1] + 1});
                visited[next] = true;
                previous[next] = now[0];
            }
        }
        return -1;
    }
}
