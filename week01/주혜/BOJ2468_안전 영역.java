import java.util.*;

class Main{

    static int[][] area;
    static int N;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int minHeight = 101;
        int maxHeight = 0;
        
        area = new int[N][N];
        // 입력
        for (int i = 0; i < N; i++) { 
            for (int j = 0; j < N; j++) {
                int n = sc.nextInt();
                area[i][j] = n;
                if (minHeight > n) minHeight = n;
                if (maxHeight < n) maxHeight = n;
            }
        }
        
        int answer = 1;

        for (int i = minHeight; i <= maxHeight; i++) {
            int count = find(i);
            if(answer < count) answer = count;
        }
        System.out.println(answer);
    }

    static int find(int k) {

        visited = new boolean[N][N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (area[i][j] > k && !visited[i][j]) {
                    bfs(i, j, k);
                    count++;
                }
            }
        }

        return count;
    }

    static void bfs(int x, int y, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && area[nx][ny] > k) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}