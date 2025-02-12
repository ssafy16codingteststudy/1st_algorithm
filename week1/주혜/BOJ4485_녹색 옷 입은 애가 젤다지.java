import java.util.*;

class Main{
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int [][] cost;
    static int [][] cave;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testcase = 1;
        while(true){
            int N = sc.nextInt();
            if (N == 0) return;
            cave = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cave[i][j] = sc.nextInt();
                }
            }

            find(N);

            System.out.println("Problem " + testcase + ": " + cost[N-1][N-1]);
            testcase++;
        }
    }

    static void find(int n) {
        cost = new int[n][n];
        for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        cost[0][0] = cave[0][0];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    int newCost = cost[x][y] + cave[nx][ny];

                    if (newCost < cost[nx][ny]) {
                        cost[nx][ny] = newCost;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}