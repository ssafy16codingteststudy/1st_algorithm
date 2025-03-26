package week7.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19637_IF문좀대신써줘 {

    private static int N, M;
    private static String[] names;
    private static int[] standards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 칭호 개수
        M = Integer.parseInt(st.nextToken()); // 캐릭터 개수

        names = new String[N];
        standards = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();
            standards[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int index = binarySearch(Integer.parseInt(br.readLine()));
            sb.append(names[index]).append("\n");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int target) {
        int left = 0, right = N ;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (standards[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
