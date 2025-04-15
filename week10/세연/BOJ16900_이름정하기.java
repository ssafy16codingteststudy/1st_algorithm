package week10.세연;

import java.util.*;
import java.io.*;

public class BOJ16900_이름정하기 {
    static String pattern;
    static int repeat, length;
    static int [] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        pattern = st.nextToken();
        repeat = Integer.parseInt(st.nextToken());
        length = pattern.length();
        table = new int [length];

        int matchLength = makeTable(pattern);

        System.out.println(length + (repeat - 1) * (long)(length - matchLength));
    }

    static int makeTable(String pattern) {
        int max = 0;
        int idx = 0;
        for(int i=1; i<length; i++) {
            while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx-1];
            }
            if(pattern.charAt(i) == pattern.charAt(idx)) {
                table[i] = ++idx;
                max = Math.max(max, table[i]);
            }
        }
        return table[length-1];
    }
}