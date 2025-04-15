package week10.세연;

import java.util.*;
import java.io.*;

public class BOJ18119_단어암기 {
    static int N, M, cmd, alphabet, cnt;
    static int[][] dictionary;
    static int [] knowledge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dictionary = new int[N][26];
        knowledge = new int[N];
        cnt = N;

        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            for (int i = 0; i < line.length(); i++) {
                dictionary[n][line.charAt(i) - 'a'] = 1;
            }
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            alphabet = st.nextToken().charAt(0) - 'a';

            if (cmd == 1) forget();
            else remember();

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void forget(){
        for (int n = 0; n < N; n++) {
            if (dictionary[n][alphabet] == 0) continue;
            if (knowledge[n]-- == 0) cnt--;
        }
    }

    static void remember(){
        for (int n = 0; n < N; n++) {
            if (knowledge[n] == 1) continue;
            if (dictionary[n][alphabet] == 0) continue;
            if (++knowledge[n] == 0) cnt++;
        }
    }
}