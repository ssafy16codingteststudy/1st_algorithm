import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1305_광고 {

    /**
     * 1305 광고
     * 문자열 S가 최소 주기 P를 가질때,
     * KMP 실패 함수의 마지막 값 K = fail[N - 1] 에 대해서 다음과 같은 식을 가진다.
     * (fail[N - 1] : S의 가장 긴 접두사이자 접미사의 길이)
     * P = N - K
     *
     * 이는 도형으로 이해하면 좀 더 이해가 쉬움!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] text = br.readLine().toCharArray();
        int[] pi = new int[n];

        for (int i = 1, j = 0; i < n; i++) {

            while(j > 0 && text[i] != text[j]) {
                j = pi[j - 1];
            }

            if (text[i] == text[j]) {
                pi[i] = ++j;
            }
        }

        // P = N - K
        System.out.println(n - pi[n-1]);
    }
}