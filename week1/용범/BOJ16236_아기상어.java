package 용범;

import java.io.*;
import java.util.*;

public class BOJ16236_아기상어 {

    static class Fish implements Comparable<Fish> {
        int y, x, size, dist;

        Fish(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            // 거리 기준 -> 오름차순
            if (this.dist != o.dist)
                return this.dist - o.dist;
            // 거리가 같다면 -> y 기준 오름차순
            if (this.y != o.y)
                return this.y - o.y;
            // y값이 같다면 -> x 기준 오름차순
            return this.x - o.x;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, sy, sx, nxtY, nxtX, size, dist, cnt, time;
    static int[][] map;
    static boolean[][] visited;
    static boolean flag;
    static int[] dys = {-1, 0, 1, 0}, dxs = {0, -1, 0, 1}; // 상, 좌, 하, 우
    static ArrayDeque<int[]> dq;
    static PriorityQueue<Fish> pq;

    public static void main(String[] args) throws IOException {

        init();

        solve();

    }

    private static void solve() {

        while (true) {
            // 1. 먹을 수 있는 물고기들을 탐색(BFS)하여 정렬 기준에 맞춰 우선순위 큐에 저장
            fishSave(sy, sx);

            // 2. 사냥 및 결과 반환
            flag = moveAndEat();

            // 3. 더 이상 먹을 먹이가 없다면 -> 엄마 상어를 부르고 함수를 종료
            if (!flag) {
                System.out.println(time); // 정답 출력
                break;
            }
        }
    }

    private static void fishSave(int y, int x) {

        visited = new boolean[N][N];
        visited[y][x] = true; // 시작 위치 방문 처리

        dq = new ArrayDeque<>();
        pq = new PriorityQueue<>();
        dq.offer(new int[]{y, x, 0});

        while (!dq.isEmpty()) {

            int[] curr = dq.poll();

            dist = curr[2];
            for (int i = 0; i < 4; i++) {
                nxtY = curr[0] + dys[i];
                nxtX = curr[1] + dxs[i];
                //  범위를 넘지 않고, 방문 가능하고, 자신의 크기보다 작거나 같은 물고기 혹은 빈칸인 경우 -> 탐색 진행
                if (inRange(nxtY, nxtX) && !visited[nxtY][nxtX] && map[nxtY][nxtX] <= size) {
                    visited[nxtY][nxtX] = true; // 방문 처리
                    dq.offer(new int[]{nxtY, nxtX, dist + 1});
                    // 먹을 수 있는 물고기만 저장 -> 1. 거리 순 / 2. y값 / 3. x값
                    if (0 < map[nxtY][nxtX] && map[nxtY][nxtX] < size) {
                        pq.offer(new Fish(nxtY, nxtX, dist + 1));
                    }
                }
            }
        }
    }

    private static boolean moveAndEat() {
        // 먹을 수 있는 먹이가 없는 경우 -> 엄마 상어를 불러야 한다.
        if (pq.isEmpty())
            return false;

        Fish fish = pq.poll();
        map[fish.y][fish.x] = 0; // 물고기를 먹으면 빈칸이 된다.
        time += fish.dist; // 움직인 거리만큼 시간 증가
        cnt++; // 먹은 숫자 증가
        sy = fish.y;
        sx = fish.x;
        // 만약, 자신의 크기만큼 물고기를 먹었다면 -> 아기 상어 크기 증가
        if (cnt == size) {
            size++;
            cnt = 0;
        }

        return true;
    }

    private static boolean inRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static void init() throws IOException {

        // 입력 부분
        N = Integer.parseInt(br.readLine());
        size = 2; // 처음 아기 상어의 크기
        time = 0; // 아기 상어가 엄마 상어를 부르기 전까지 걸리는 시간
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아기 상어의 시작 위치 저장
                if (map[i][j] == 9) {
                    map[i][j] = 0; // 시작점 초기화
                    sy = i;
                    sx = j;
                }
            }
        }

        br.close();
    }
}
