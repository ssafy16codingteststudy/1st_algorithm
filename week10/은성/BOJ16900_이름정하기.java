import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16900_이름정하기 {

    /**
     * 16900 이름 정하기
     * 문자열 s 에 대해 실패 함수(부분 일치 테이블)을 구한 뒤 pi[len - 1] 값을 사용
     * (문자열 s 길이) + (k - 1) * (문자열 s 길이 - (접미사와 접두사 겹치는 길이))
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        char[] pattern = split[0].toCharArray();
        int len = pattern.length;
        int n = Integer.parseInt(split[1]);

        // 실패 함수를 구함
        // pi[k] : 인덱스가 k 까지인 부분 문자열에서, 접두사와 접미사가 일치하는 개수
        int[] pi = new int[len];
        for (int i = 1, j = 0; i < len; i++) {
            // i: 가만있는 문자열의 현재 비교 인덱스, j : 움직이는 문자열의 비교 인덱스

            // j 값을 조정해가며, i와 j가 값이 일치하는 부분을 찾음
            while(j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if (pattern[i] == pattern[j]) {
                // 값이 일치한다면 j 가 인덱스이므로 j + 1 은 개수를 의미,
                pi[i] = j + 1;
                // 일치했으므로 다음 비교를 위해 인덱스 이동
                j++;
            }
        }

        // 제에에에발 long 좀 생각하자 은성아
        System.out.println(len + ((long)(len - pi[len - 1]))*(n - 1));
    }
}