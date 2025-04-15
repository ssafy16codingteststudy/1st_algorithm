package 용범;

import java.io.*;
import java.util.*;

public class BOJ16900_이름정하기 {

  static String S;
  static int K;

  public static void main(String[] args) throws IOException {
    init();
    solve();
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    st = new StringTokenizer(br.readLine());
    S = st.nextToken();
    K = Integer.parseInt(st.nextToken());

    br.close();
  }

  private static void solve() {

    int N = S.length();
    int[] table = makeTable();

    long res = N + (K - 1L) * (N - table[N - 1]);
    System.out.println(res);
  }

  private static int[] makeTable() {

    int patternSize = S.length();
    int[] table = new int[patternSize];

    int j = 0;
    for (int i = 1; i < patternSize; i++) {
      while (j > 0 && S.charAt(i) != S.charAt(j)) {
        j = table[j - 1];
      }

      if (S.charAt(i) == S.charAt(j)) {
        table[i] = j + 1;
        j++;
      }
    }

    return table;
  }
}
