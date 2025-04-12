
import java.util.*;

public class Main {
	
    static int checkAnagramSubstring(String SA, String SB, int len) {
        int N = SA.length(), M = SB.length();
        if (len > N || len > M) return 0; // 길이가 초과하면 비교 불가
        
        int[] SA_freq = new int[26]; // SA의 알파벳 빈도
        int[] SB_freq = new int[26]; // SB의 알파벳 빈도
        
        
        // 첫 번째 윈도우 초기화
        for (int i = 0; i < len; i++) {
            SA_freq[SA.charAt(i) - 'a']++;
            SB_freq[SB.charAt(i) - 'a']++;
        }
        
        // 초기 윈도우가 같은 경우
        if (Arrays.equals(SA_freq, SB_freq)) return len;
        
        // 슬라이딩 윈도우 적용
        for (int i = len; i <= N; i++) {
            SB_freq = new int[26];
            for (int k = 0; k < len; k++) {
                SB_freq[SB.charAt(k) - 'a']++;
            }
            for (int j = len; j < M; j++) {
                SB_freq[SB.charAt(j - len) - 'a']--; // 이전 문자 제거
                SB_freq[SB.charAt(j) - 'a']++; // 새로운 문자 추가
                if (Arrays.equals(SA_freq, SB_freq)) return len;
            }
            if (i == N) break;
            SA_freq[SA.charAt(i - len) - 'a']--; // 이전 문자 제거
            SA_freq[SA.charAt(i) - 'a']++; // 새로운 문자 추가
        }
        return 0;
    }
    // "computersys"tem
    // se"systuercomp"lexity

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String SA = sc.next();
        String SB = sc.next();

        int maxLen = 0;
        for (int len = 1; len <= Math.min(SA.length(), SB.length()); len++) {
        	if (checkAnagramSubstring(SA, SB, len) > 0) {
            	
                maxLen = Math.max(maxLen, len);
            }
        }
        
        System.out.println(maxLen);
    }
}
