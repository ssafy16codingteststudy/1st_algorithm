package week11.세연;

import java.util.*;
import java.io.*;

public class BOJ1563_개근상 {
    static final int MOD = 1_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] attendance = new int[N+1]; // L이 없고 AAA 없는 출석부
        attendance[0] = 1;
        if (N >= 1) attendance[1] = 2; 
        if (N >= 2) attendance[2] = 4;
        for (int i = 3; i <= N; i++) {
            attendance[i] = ((attendance[i-1] + attendance[i-2]) % MOD + attendance[i-3]) % MOD;
        }
        // 지각 아예 안하고 개근상 받는 경우
        int ans = attendance[N];
        // 지각 1번 하고 개근상 받는 경우 추가
        for (int i = 0; i <= N-1; i++) {
            long ways = (long)attendance[i] * attendance[N-1-i] % MOD;
            ans = (ans + (int)ways) % MOD;
        }
        System.out.println(ans);
    }
}
