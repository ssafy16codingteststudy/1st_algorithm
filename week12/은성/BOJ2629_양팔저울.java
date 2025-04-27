package week12.은성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2629_양팔저울 {

    private static int n, k;
    private static int[] choo, balls;
    private static Set<Integer> possibleBall = new HashSet<>();
    /**
     * 2629 양팔저울
     * 백트래킹 접근했다가 알고리즘 분류 보고 해결
     * 왜 정렬해서 주는가? 에서 힌트를 얻었어야 함...
     * 이전에 나온 경우의 수를 활용하면 세 가지 경우의 수가 나옴
     * (현재 추 - 이전 값), (현재 추 + 이전 값), (-현재 추 + 이전 값)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        choo = new int[n];
        for (int i = 0; i < n; i++) {
            choo[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        balls = new int[k];
        for (int i = 0; i < k; i++) {
            balls[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            Set<Integer> newBall = new HashSet<>();

            int nowBall = choo[i];
            for (Integer ball : possibleBall) {
                newBall.add(nowBall + ball);

                if (nowBall - ball > 0) {
                    newBall.add(nowBall - ball);
                }

                if (ball - nowBall > 0) {
                    newBall.add(ball - nowBall);
                }
            }

            possibleBall.add(nowBall);
            possibleBall.addAll(newBall);
        }

        for (int ball : balls) {
            if (possibleBall.contains(ball)) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }

        System.out.println(possibleBall);
        System.out.println(sb);
    }
}