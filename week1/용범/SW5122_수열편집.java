package 용범;

import java.io.*;
import java.util.*;

public class SW5122_수열편집 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static LinkedList<Integer> lst;
    static int T, N, M, L, n1, n2;
    static char command;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            input();

            solve(tc);
        }

        br.close();
        bw.close();
    }

    private static void solve(int tc) throws IOException {

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken().charAt(0);

            switch (command) {
                case 'I':
                    n1 = Integer.parseInt(st.nextToken());
                    n2 = Integer.parseInt(st.nextToken());
                    lst.add(n1, n2);
                    break;

                case 'D':
                    n1 = Integer.parseInt(st.nextToken());
                    lst.remove(n1);
                    break;

                case 'C':
                    n1 = Integer.parseInt(st.nextToken());
                    n2 = Integer.parseInt(st.nextToken());
                    lst.remove(n1);
                    lst.add(n1, n2);
                    break;
            }
        }

        if (lst.size() < L + 1) bw.write(String.format("#%d %d\n", tc, -1));
        else bw.write(String.format("#%d %d\n", tc, lst.get(L)));
    }

    private static void input() throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N: 수열의 길이
        M = Integer.parseInt(st.nextToken()); // M: 추가 횟수
        L = Integer.parseInt(st.nextToken()); // L: 출력할 인덱스

        st = new StringTokenizer(br.readLine());
        lst = new LinkedList<>();

        for (int i = 0; i < N; i++)
            lst.add(Integer.parseInt(st.nextToken()));
    }


}
