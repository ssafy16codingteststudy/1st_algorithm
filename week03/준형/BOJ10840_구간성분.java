package main;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // N : 첫번째 String의 길이, M: 두번째 String의 길이
    static int[][] prefixA; // A의 누적빈도 배열
    static int[][] prefixB; // B의 누적빈도 배열
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sA = br.readLine();
        String sB = br.readLine();
        N = sA.length();
        M = sB.length();
        
        prefixA = buildPrefix(sA); 
        prefixB = buildPrefix(sB); 
        
        int maxLen = Math.min(N, M); // N과 M 중에 최소 길이가 가장 긴 탐색 길이
        int answer = 0;
        
        // 모든 가능한 길이에 대해 검사
        for (int L = 1; L <= maxLen; L++) {
            if (hasCommonComponent(L)) {
                answer = L;  // 조건을 만족하는 길이 L가 있으면 answer 갱신
            }
        }
        System.out.println(answer);
    }
    
    // 문자열 s의 누적 빈도 배열 생성
    // prefix[i][c] -> 0 ~ i번째 문자열에서의 c의 빈도값을 의미
    static int[][] buildPrefix(String s) {
        int n = s.length();
        int[][] prefix = new int[n][26]; // 알파벳 26개
        prefix[0][s.charAt(0) - 'a'] = 1; // 초기값, s.charAt(0) - 'a'은 알파벳 숫자에 따른 인덱스로 바꾸는 로직
        for (int i = 1; i < n; i++) {
            for (int c = 0; c < 26; c++) {
                prefix[i][c] = prefix[i - 1][c]; // 이전 값 누적
            }
            prefix[i][s.charAt(i) - 'a']++; // 이번 idx 값 추가
        }
        return prefix;
    }
    
    // 길이 L인 구간에 대해, 문자열 A와 B 중에 공통된 성분이 존재하는지 검사
    static boolean hasCommonComponent(int L) {
        HashSet<String> set = new HashSet<>(); // 존재 여부만 파악하면 되기 때문에 HashSet 자료구조를 사용
        // 문자열 A의 모든 길이 L 구간의 빈도 벡터를 집합에 저장
        for (int i = 0; i <= N - L; i++) {
            String key = getKey(prefixA, i, L);
            set.add(key);
        }
        // 문자열 B의 각 길이 L 구간에 대해 집합에 존재하는지 확인
        for (int j = 0; j <= M - L; j++) {
            String key = getKey(prefixB, j, L);
            if (set.contains(key)) {
                return true;
            }
        }
        return false;
    }
    
    // 주어진 구간 [start, start+L-1]의 빈도 벡터를 StringBuilder를 이용해 문자열로 변환
    static String getKey(int[][] prefix, int start, int L) {
        int end = start + L - 1;
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < 26; c++) {
            int count = prefix[end][c] - (start > 0 ? prefix[start - 1][c] : 0);
            sb.append(count).append('_'); // '_'를 구분자로 사용
        }
        return sb.toString();
    }
}

