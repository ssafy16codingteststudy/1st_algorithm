import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {
    static StringTokenizer st;
    static int N, time, size, eatStack;
    static int[][] arr;
    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        size = 2;
        PriorityQueue<Shark> queue = new PriorityQueue<>();
        Shark currentShark = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    currentShark = new Shark(0, j, i, 0);
                    arr[i][j] = 0;
                }

            }
        }
        while (true) {
            // 먹이를 먹고 끝났는지 확인 flag
            boolean isHungry = true;
            // 시작점 초기화
            queue = new PriorityQueue<>();
            queue.offer(new Shark(0, currentShark.x, currentShark.y, 0));
            // 재방문 확인 flag 초기화
            boolean[][] visit = new boolean[N][N];
            visit[currentShark.y][currentShark.x] = true;
            // 아무거나 하나 먹을 때까지 bfs
            while (!queue.isEmpty()) {
                currentShark = queue.poll();
                // 움직인 칸에 먹을 수 있는 먹이가 있을 때
                if (eatable(currentShark)) {
                    // System.out.println(currentShark);
                    arr[currentShark.y][currentShark.x] = 0; // 먹이 지도에서 삭제
                    eatStack++; // 먹은 횟수 +1
                    time += currentShark.d; // 이동에 따라 시간 소요
                    isHungry = false; // 먹이를 먹었음을 확인 flag
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentShark.x;
                    int ny = dy[i] + currentShark.y;

                    // delta 탐색, 재방문여부, 크기에 따른 지나갈 수 있는지 여부
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx] || arr[ny][nx] > size)
                        continue;
                    visit[ny][nx] = true;
                    queue.offer(new Shark(arr[ny][nx], nx, ny, currentShark.d + 1));
                }
            }
            // 아무것도 못먹고 끝남 -> 프로그램종료
            if (isHungry)
                break;
            // 성장
            if (eatStack == size) {
                eatStack = 0;
                size++;
            }
        }
        System.out.println(time);
    }

    static boolean eatable(Shark s) {
        if (s.v == 0 || s.v >= size)
            return false;
        return true;
    }
}

class Shark implements Comparable<Shark> {
    int v;
    int x; // 수평성분
    int y; // 수직성분
    int d;

    public Shark(int v, int x, int y, int d) {
        this.v = v;
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Shark [v=" + v + ", x=" + x + ", y=" + y + ", d=" + d + "]";
    }

    // 거리 짧은순,
    @Override
    public int compareTo(Shark o) {
        if (this.d != o.d)
            return Integer.compare(this.d, o.d);
        else if (this.y != o.y)
            return Integer.compare(this.y, o.y);
        else
            return Integer.compare(this.x, o.x);
    }

}
