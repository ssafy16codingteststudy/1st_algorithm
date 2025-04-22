package week11.세연;

import java.util.*;
import java.io.*;

public class BOJ25947_선물할인 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int b  = Integer.parseInt(st.nextToken());
        int a  = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] gifts = new int[n];
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) gifts[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(gifts);
        sum[0] = gifts[0];
        for (int i = 1; i < n; i++) sum[i] = gifts[i] + sum[i-1];

        if (n == a && (double)sum[n-1]/2.0 <= b) {
            System.out.println(n);
            return;
        } 
        int start = 0, end = n-a-1, mid, ans = 0;
        
        while (start <= end) {
            mid = (start + end) / 2;

            double cost = sum[mid] + (double)(sum[mid + a] - sum[mid]) / 2.0;
            if (cost > b){end = mid - 1;}
            else {
                ans = Math.max(ans, mid + a + 1);
                if (cost == b) break;
                start = mid+1;
            }
        }

        start = 0; end = a-1;
        while (start <= end){
            mid = (start + end) / 2;
            double cost = sum[mid];
            if (cost > b){end = mid - 1;}
            else {
                ans = Math.max(ans, mid + 1);
                if (cost == b) break;
                start = mid+1;
            }
        }

        System.out.println(ans);
    }
}
