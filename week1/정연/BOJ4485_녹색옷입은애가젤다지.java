import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {

    int v;
    int x;
    int y;

    public Node(int v, int x, int y) {
        this.v = v;
        this.x = x;
        this.y = y;
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.v - o2.v;
    }
}

public class Main {

    private static int[][] grid;
    private static int[][] dist;
    private static int[] dx = { 0, 0, 1, -1 };
    private static int[] dy = { 1, -1, 0, 0 };
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = 1;

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            grid = new int[n][n];
            dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dijk();
            System.out.printf("Problem %d: %d\n", testCase++, dist[n - 1][n - 1]);

        }
    }

    public static void dijk() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        // set start
        dist[0][0] = grid[0][0];
        // root node append!
        pq.offer(new Node(dist[0][0], 0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int v = node.v;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (dist[nx][ny] > v + grid[nx][ny]) {
                    dist[nx][ny] = v + grid[nx][ny];
                    pq.offer(new Node(dist[nx][ny], nx, ny));
                }
            }
        }
    }
}
