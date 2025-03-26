package week7.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ28081_직사각형피자 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long W = Long.parseLong(st.nextToken()); // 가로
        long H = Long.parseLong(st.nextToken()); // 세로
        long K = Long.parseLong(st.nextToken());

        int N = Integer.parseInt(br.readLine()); // 가로 방향
        int[] xCuts = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            xCuts[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine()); // 세로 방향
        int[] yCuts = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            yCuts[i] = Integer.parseInt(st.nextToken());
        }

        long[] heights = getLengths(xCuts, H);
        long[] widths = getLengths(yCuts, W);

        Arrays.sort(widths);
        Arrays.sort(heights);

        long answer = 0;
        for (long w : widths) {
            if (w > K) break;
            long limit = K / w; // w * h <= K -> h <= K / w
            long count = binarySearch(heights, limit);
            answer += count;
        }

        System.out.println(answer);
    }

    private static long[] getLengths(int[] cuts, long total) {
        long[] result = new long[cuts.length + 1];
        int prev = 0;
        for (int i = 0; i < cuts.length; i++) {
            result[i] = cuts[i] - prev;
            prev = cuts[i];
        }
        result[cuts.length] = total - prev;
        return result;
    }

    private static long binarySearch(long[] arr, long limit) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= limit) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result + 1L;
    }
}
