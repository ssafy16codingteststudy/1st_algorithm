package week11.세연;

import java.util.*;
import java.io.*;

public class BOJ2470_두용액 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int [N];
        int cnt = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] < 0) cnt++;
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        if (cnt == 0 || cnt == N) { // 산성 용액만 있거나 염기성 용액만 있는 경우
            if (arr[0] > 0) sb.append(arr[0]).append(" ").append(arr[1]);
            else sb.append(arr[N-2]).append(" ").append(arr[N-1]);
            System.out.println(sb);
            return;
        }
        int start = 0, end = N-1;
        int val = Integer.MAX_VALUE;
        int [] answer = {0, N-1};
        while (start < end) {
            int tmp = arr[start] + arr[end];

            if (val > Math.abs(tmp)){
                val = Math.abs(tmp);
                answer[0] = arr[start];
                answer[1] = arr[end];
                if (val == 0) break;
            }

            if (tmp < 0) start++;
            else end--;
            
        }
        System.out.printf("%d %d", answer[0], answer[1]);
    }
}
