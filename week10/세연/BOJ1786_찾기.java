package week10.세연;

import java.io.*;

public class BOJ1786_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();

        KMP(T,P);
    }
    static void KMP(String parent, String pattern){
        int[] table = makeTable(pattern);

        int parentSize = parent.length(), patternSize = pattern.length();
        int idx = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < parentSize; i++) {
            while(idx > 0 && parent.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx-1];
            }

            if(parent.charAt(i) == pattern.charAt(idx)) {
                if(idx == patternSize-1) {
                    sb.append(i-idx+1).append(" ");
                    cnt++;
                    idx = table[idx];
                }else {
                    idx +=1;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(sb);

    }

    static int[] makeTable(String pattern) {
        int patternSize = pattern.length();
        int[] table = new int[patternSize];

        int idx = 0;
        for(int i=1; i<patternSize; i++) {
            while(idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx-1];
            }
            if(pattern.charAt(i) == pattern.charAt(idx)) {
                table[i] = ++idx;
            }
        }
        return table;
    }
}