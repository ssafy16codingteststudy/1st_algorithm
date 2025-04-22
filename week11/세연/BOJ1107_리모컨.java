package week11.세연;

import java.util.*;
import java.io.*;

public class BOJ1107_리모컨 {
    static int N, M, answer; 
    static boolean [] numbers = new boolean[10];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if (M == 0) {
            answer = Math.min(String.valueOf(N).length(), Math.abs(100-N));
            System.out.println(answer);
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        Arrays.fill(numbers, true);
        for (int m = 0; m<M; m++) numbers[Integer.parseInt(st.nextToken())] = false;
        
        answer = Math.abs(100 - N);
        if (answer == 0) {
            System.out.println(0);
            return;
        }
        dfs(0,0);
        
        System.out.println(answer);
    }

    static void dfs(int idx, int channel) {
        for (int i = 0; i < 10; i++){
            if (!numbers[i]) continue;
            int curr = channel * 10 + i;
            int cnt = Math.abs(N - curr) + String.valueOf(curr).length();
            answer = Math.min(answer, cnt);

            if (idx < 6) dfs(idx+1, curr);
        }
    }
}
