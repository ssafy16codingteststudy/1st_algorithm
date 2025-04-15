import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String text = br.readLine();
        String pattern = br.readLine();

        List<Integer> result = kmpSearch(text, pattern);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");

        for (int index : result) {
            sb.append(index).append(" ");
        }

        System.out.println(sb);
    }

    private static List<Integer> kmpSearch(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        int[] pi = getPi(pattern);

        List<Integer> result = new ArrayList<>();

        int begin = 0, matched = 0;

        while (begin <= textLength - patternLength) {
            if (matched < patternLength && text.charAt(begin + matched) == pattern.charAt(matched)) {
                matched++;
                if (matched == patternLength) {
                    result.add(begin + 1);
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return result;
    }

    private static int[] getPi(String pattern) {
        int length = pattern.length();
        int[] pi = new int[length];
        int begin = 1; // 비교 시작 위치
        int matched = 0; // 접두사 == 접미사 길이

        // 비교 구간 -> pattern[begin + matched] vs pattern[matched]
        while (begin + matched < length) {
            if (pattern.charAt(begin + matched) == pattern.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else { // 불일치
                if (matched == 0) {
                    begin++; // 다음 위치부터 시작
                } else {
                    begin += matched - pi[matched - 1]; // 점프
                    matched = pi[matched - 1]; // 이전 값 활용
                }
            }
        }

        return pi;
    }
}
