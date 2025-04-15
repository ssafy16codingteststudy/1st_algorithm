package week10.일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ18119_enhance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // 각 단어들을 저장할 리스트
        String[] words = new String[N];

        // 각 단어의 잊어버린 글자 수(처음에는 모두 0)
        int[] forgotCount = new int[N];

        // 각 단어가 포함하는 글자를 저장한 boolean 배열 a~z 까지 26개
        boolean[][] wordLetter = new boolean[N][26];

        List<Integer>[] letterwords = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            letterwords[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine().trim();
            words[i] = word;
            boolean[] letters = new boolean[26];
            for (int j = 0; j < word.length(); j++) {
                int c = word.charAt(j) - 'a';
                if(!letters[c]) {
                    letters[c] = true;
                    letterwords[c].add(i);
                }
            }
            wordLetter[i] = letters;
        }

        boolean[] letterState = new boolean[26];
        Arrays.fill(letterState, true);

        // 시작시 모든 단어는 기억 가능한 단어이므로 validCount = N
        int validCount = N;

        // 쿼리 처리
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int type = Integer.parseInt(input[0]);
            int idx = input[1].charAt(0) - 'a';

            if (type == 1) { // 잊어버리기
                if (letterState[idx]) { // 이미 기억 중이면 처리
                    letterState[idx] = false;
                    // 이 글자를 포함한 모든 단어에 대해 forgottenCount 증가
                    for (int wordIdx : letterwords[idx]) {
                        // 단어에 포함되어 있다면 forgottenCount 증가 (이미 포함된 것이 확실)
                        if (forgotCount[wordIdx] == 0) {
                            validCount--; // 기억 가능한 단어에서 제외됨
                        }
                        forgotCount[wordIdx]++;
                    }
                }
            } else if (type == 2) { // 기억하기
                if (!letterState[idx]) { // 현재 잊어버린 상태면 처리
                    letterState[idx] = true;
                    // 이 글자를 포함한 모든 단어에 대해 forgottenCount 감소
                    for (int wordIdx : letterwords[idx]) {
                        forgotCount[wordIdx]--;
                        if (forgotCount[wordIdx] == 0) {
                            validCount++; // 다시 기억 가능한 단어로 복귀
                        }
                    }
                }
            }
            System.out.println(validCount);
        }


    }
}
