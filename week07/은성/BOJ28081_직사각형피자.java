package codingTestStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ28081_직사각형피자 {

    /**
     * 28081 직사각형 피자
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());    // long 주의합시다...

        // 가로 방향 커팅
        int n = Integer.parseInt(br.readLine());
        int[] arrY = new int[n + 1];

        int last = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int cutting = Integer.parseInt(st.nextToken());
            arrY[i] =  cutting - last;
            last = cutting;
        }
        arrY[n] = y - last;

        // 세로 방향 커팅
        int m = Integer.parseInt(br.readLine());
        int[] arrX = new int[m + 1];

        last = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int cutting = Integer.parseInt(st.nextToken());
            arrX[i] =  cutting - last;
            last = cutting;
        }
        arrX[m] = x - last;

        // 커팅 크기 정렬
        Arrays.sort(arrY);
        Arrays.sort(arrX);

        long count = 0; // long 주의합시다... 2
        for (int num : arrY) {
            // 이미 최소 크기를 넘어버렸으면 그대로 종료
            if (num > k) {
                break;
            }

            // k 값을 처음 초과하는 인덱스를 반환하므로 그대로 더해줌
            count += upperBound(num, k, arrX);
        }
        System.out.println(count);
    }

    private static int upperBound(int num, long k, int[] arrX) {
        int start = 0;
        int end = arrX.length;
        int mid = (start + end) / 2;

        while (start < end) {
            if ((long) num * arrX[mid] <= k) {
                start = mid + 1;
            } else {
                end = mid;
            }
            mid = (start + end) / 2;
        }

        return mid;
    }
}