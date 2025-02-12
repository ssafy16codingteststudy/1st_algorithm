import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int t = 1;
    static int n;
    static int [][] graph;
    static int [][] visited;
    static int inf;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        while ((n=Integer.parseInt(br.readLine())) != 0) {
            graph = new int[n][n];
            visited = new int[n][n];
            inf = 10 * (int)Math.pow(n, 2);
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = inf;
                }
            }
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            heap.add(new int[] {graph[0][0], 0, 0});
            visited[0][0] = graph[0][0];
            while (!heap.isEmpty()) {
                int[] item = heap.poll();
                if (visited[item[1]][item[2]] < item[0]) continue;
                for (int i = 0; i < 4; i++) {
                    int mx = item[1] + dx[i], my = item[2] + dy[i];
                    if (mx >= 0 && mx < n && my >= 0 && my < n && visited[mx][my] > item[0] + graph[mx][my]) {
                        heap.add(new int[]{item[0] + graph[mx][my], mx, my});
                        visited[mx][my] = item[0] + graph[mx][my];
                    }
                }
            }
            System.out.printf("Problem %d: %d\n",t++, visited[n-1][n-1]);
        }
    }
}
