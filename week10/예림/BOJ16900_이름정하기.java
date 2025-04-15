import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    // 문자열 S가 최소 K번 등장하는 이름 중 가장 짧은 것의 길이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        
        int length = S.length();
        int[] pi = new int[length];
        int begin = 1;
        int matched = 0;

        while (begin + matched < length) {
            if (S.charAt(begin + matched) == S.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        int max = pi[length - 1];

        // S길이 + (S 길이 - pi 최대값) * K -1
        long answer = length + (long) (length - max) * (K - 1);
        System.out.println(answer);
    }
}
