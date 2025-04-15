import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int tLength = text.length;
        int pLength = pattern.length;

        int[] pi = new int[pLength];
        for (int i = 1, j = 0; i < pLength; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            } else {
                pi[i] = 0;
            }
        }

        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0, j = 0; i < tLength; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (text[i] == pattern[j]) {
                if (j == pLength - 1) {
                    ++cnt;
                    list.add(i-j);
                    j = pi[j];
                } else {
                    ++j;
                }
            }
        }

        System.out.println(cnt);
        if (cnt > 0) {
           for (int i = 0; i < list.size(); i++) {
               System.out.println(list.get(i) + 1);
           }
        }
    }
}
