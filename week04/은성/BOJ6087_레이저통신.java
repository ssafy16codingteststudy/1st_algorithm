package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6087_레이저통신 {

    private static int Y;
    private static int X;
    private static char[][] arr;
    private static int[][][] dp;        // 현재 방향까지 고려한 dp 구성 필요
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        arr = new char[Y][X];
        dp = new int[Y][X][4];

        // 이중포문 사용했는데 이거보다 더 빠른 초기화가 있나?
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                Arrays.fill(dp[i][j], Y * X);
            }
        }

        List<Integer[]> laser = new ArrayList<>();
        for (int i = 0; i < Y; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < X; j++) {
                if (arr[i][j] == 'C') {
                    laser.add(new Integer[]{i, j});
                }
            }
        }

        bfs(laser.get(0)[0], laser.get(0)[1], laser.get(1)[0], laser.get(1)[1]);
    }

    private static void bfs(int startY, int startX, int endY, int endX) {
        // 우선순위 큐 사용 : 조금의 시간 절약 가능 (320 -> 220)
        PriorityQueue<Integer[]> deque = new PriorityQueue<>(new LaserComparator());

        // 초기 방향 설정
        for (int i = 0; i < 4; i++) {
            int newY = startY + dy[i];
            int newX = startX + dx[i];

            if (isPossible(newY, newX)) {
                // 큐에 넣는 데이터 정보 (y, x, 꺾은 수, 현재 방향)
                deque.add(new Integer[]{newY, newX, 0, i});
            }
        }

        while (!deque.isEmpty()) {
            Integer[] poll = deque.poll();
            int nowY = poll[0];
            int nowX = poll[1];
            int count = poll[2];
            int side = poll[3];

            // 기존에 저장해둔 값보다 더 큰값이라면 패스
            if (dp[nowY][nowX][side] <= count) {
                continue;
            }

            dp[nowY][nowX][side] = count;

            for (int i = -1; i <= 1; i++) {
                // 현재 방향에서 왼쪽, 직진, 오른쪽으로만 꺾는 경우의 수를 따져야 함
                int newSide = (side + i + 4) % 4;
                int newY = nowY + dy[newSide];
                int newX = nowX + dx[newSide];

                if (isPossible(newY, newX)) {
                    if (side != newSide) {
                        // 꺾었으면 카운트 + 1
                        deque.add(new Integer[]{newY, newX, count + 1, newSide});
                    } else {
                        deque.add(new Integer[]{newY, newX, count, newSide});
                    }
                }
            }
        }

        int answer = Y * X;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, dp[endY][endX][i]);
        }
        System.out.println(answer);
    }

    private static boolean isPossible(int y, int x) {
        return !isOut(y, x) && arr[y][x] != '*';
    }

    private static boolean isOut(int h, int w) {
        return (0 > h || h >= Y) || (0 > w || w >= X);
    }

    private static class LaserComparator implements Comparator<Integer[]> {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return Integer.compare(o1[2], o2[2]);
        }
    }
}