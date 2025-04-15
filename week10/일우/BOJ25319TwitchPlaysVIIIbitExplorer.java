package week10.일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ25319TwitchPlaysVIIIbitExplorer {

    // 좌표 정보를 저장할 클래스
    static class Pair {
        int r, c;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 던전의 행 수
        int M = Integer.parseInt(st.nextToken()); // 던전의 열 수
        int lenS = Integer.parseInt(st.nextToken()); // 아이디 S의 길이 (필요시 사용)

        // 던전 배열과, 각 알파벳이 등장하는 좌표들을 저장할 맵 구성
        char[][] dungeon = new char[N][M];
        Map<Character, Queue<Pair>> posMap = new HashMap<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            posMap.put(ch, new LinkedList<>());
        }
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = line.charAt(j);
                dungeon[i][j] = ch;
                posMap.get(ch).offer(new Pair(i, j));
            }
        }

        // 아이디 S 읽기
        String S = br.readLine().trim();

        // S에서 각 알파벳의 등장 횟수 계산
        int[] freqS = new int[26];
        for (int i = 0; i < S.length(); i++) {
            freqS[S.charAt(i) - 'a']++;
        }

        // 던전 전체에서 각 알파벳의 등장 횟수 (큐에 들어있는 원소 수로 확인)
        int[] freqGrid = new int[26];
        for (char ch = 'a'; ch <= 'z'; ch++) {
            freqGrid[ch - 'a'] = posMap.get(ch).size();
        }

        // 최대 강화 횟수 C_max 계산: 각 알파벳별 available / required 의 최솟값
        int C_max = Integer.MAX_VALUE;
        for (int c = 0; c < 26; c++) {
            if (freqS[c] > 0) {
                C_max = Math.min(C_max, freqGrid[c] / freqS[c]);
            }
        }
        if (C_max == Integer.MAX_VALUE) C_max = 0; // S가 공백인 경우(문제 조건상 없지만)

        // S를 C_max번 완성하기 위해, 각 픽(P) 동작에서 사용할 던전 내 셀의 위치를 미리 결정
        // S의 각 문자에 대해 posMap의 큐에서 하나씩 꺼내면 된다.
        List<Pair> pickPositions = new ArrayList<>();
        for (int rep = 0; rep < C_max; rep++) {
            for (int i = 0; i < S.length(); i++) {
                char letter = S.charAt(i);
                Pair p = posMap.get(letter).poll();
                pickPositions.add(p);
            }
        }

        // 행동 명령을 구성: 시작점 (0,0)에서부터 pickPositions 순서대로 이동하며 'P' 명령 실행 후
        // 모든 픽 이후 던전의 우측 하단 (N-1, M-1)까지 이동
        StringBuilder actions = new StringBuilder();
        int curR = 0, curC = 0;

        // 각 픽을 위한 이동 및 픽 행동 추가
        for (Pair target : pickPositions) {
            // 행 이동 (위/아래)
            while (curR < target.r) {
                actions.append("D");
                curR++;
            }
            while (curR > target.r) {
                actions.append("U");
                curR--;
            }
            // 열 이동 (왼쪽/오른쪽)
            while (curC < target.c) {
                actions.append("R");
                curC++;
            }
            while (curC > target.c) {
                actions.append("L");
                curC--;
            }
            // 해당 칸에 도착하면 아이템 줍기(P)
            actions.append("P");
        }

        // 모든 픽 후, 던전 우측 하단 (N-1, M-1)까지 이동
        while (curR < N - 1) {
            actions.append("D");
            curR++;
        }
        while (curR > N - 1) {
            actions.append("U");
            curR--;
        }
        while (curC < M - 1) {
            actions.append("R");
            curC++;
        }
        while (curC > M - 1) {
            actions.append("L");
            curC--;
        }

        // 최종 행동 문자열 및 총 길이
        String resultActions = actions.toString();
        int L = resultActions.length();

        // 출력: 첫 줄에 "C L", 둘째 줄에 행동 문자열.
        System.out.println(C_max + " " + L);
        System.out.println(resultActions);
    }
}
