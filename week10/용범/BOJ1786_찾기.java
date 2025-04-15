package 용범;

import java.io.*;
import java.util.*;

public class BOJ1786_찾기 {

  static String text, pattern;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    text = br.readLine();
    pattern = br.readLine();
    br.close();
  }

  private static void solve() {

    StringBuilder sb = new StringBuilder();

    int[] table = makeTable();

    int textSize = text.length();
    int patternSize = pattern.length();
    int idx = 0;
    int cnt = 0;

    for (int i = 0; i < textSize; i++) {
      while (idx > 0 && text.charAt(i) != pattern.charAt(idx)) {
        idx = table[idx - 1];
      }

      if (text.charAt(i) == pattern.charAt(idx)) {
        if (idx == patternSize - 1) {
          cnt++;
          sb.append(i - idx + 1).append("\n");
          idx = table[idx];
        } else {
          idx++;
        }
      }
    }

    System.out.println(cnt);
    System.out.print(sb);
  }

  private static int[] makeTable() {

    int patternSize = pattern.length();
    int[] table = new int[patternSize];

    int j = 0;
    for (int i = 1; i < patternSize; i++) {
      while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
        j = table[j - 1];
      }

      if (pattern.charAt(i) == pattern.charAt(j)) {
        table[i] = j + 1;
        j++;
      }
    }

    return table;
  }

}
/*

abacaabaccabacabaa
abacab

*/