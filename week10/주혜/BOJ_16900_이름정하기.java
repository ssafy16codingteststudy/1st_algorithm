import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] pattern = st.nextToken().toCharArray();
        long N = Integer.parseInt(st.nextToken());

        int pLength = pattern.length;
        long answer = pLength * N;

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

        System.out.println(answer - (pi[pLength - 1]) * (N-1));

    }
}
