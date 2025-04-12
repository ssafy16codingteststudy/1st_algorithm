import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] map;
    static int size = 2, time = 0, eatAmount = 0;
    static int sharkX, sharkY;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        // input 받기
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }
        // 다음으로 먹을 물고기(nextPrey) 탐색
        int[] nextPrey;
        while ((nextPrey = getNextPrey()) != null) { // 다음에 먹을 물고기가 없을 때까지 반복
            time += nextPrey[2]; // 다음 물고기 먹으러 가는데 이동해야하는 거리(dst) 더하기
            sharkX = nextPrey[0];
            sharkY = nextPrey[1];
            map[nextPrey[0]][nextPrey[1]] = 0; // 먹은 물고기 처리
            if (++eatAmount == size) {
                size++;
                eatAmount = 0;
            }
        }
        System.out.println(time);
    }

    static int[] getNextPrey() {
        int [][] visited = new int [n][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.add(new int[] {21*sharkX+sharkY, 0, sharkX, sharkY}); // 같은 거리의 경우 물고기 우선순위는 (x,y)가 작은 순서
        visited[sharkX][sharkY] = 1;

        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int dst = curr[1], x = curr[2], y = curr[3];

            if (map[x][y] >0 && map[x][y] < size) return new int[]{x,y,dst}; // 먹을 수 있는 물고기 위치에 도달하면 return

            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >=0 && mx < n && my >= 0 && my < n && visited[mx][my] == 0) {
                    if (map[mx][my] <= size) {
                        visited[mx][my] = 1;
                        queue.add(new int[] {(dst+1)*25*n+21*mx+my, dst+1, mx,my}); // 상어와의 거리(dst)가 작은 물고기의 우선순위가 가장 높음, 거리가 같을 경우 우선순위는 (x,y)가 작은 순서
                    }
                }
            }
        }
        return null;
    }
}