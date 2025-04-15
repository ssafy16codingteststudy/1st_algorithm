package week10.일우;

import java.io.*;
import java.util.*;

public class BOJ25289_지각하면안돼 {

    public static void main(String[] args) throws IOException {
        // 입력 읽기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // A 수열 입력 및 각 숫자가 등장하는 위치 저장
        // 1부터 100까지의 숫자에 대해 인덱스를 저장할 리스트 배열 생성 (index 1부터 사용)
        List<Integer>[] positions = new ArrayList[101];
        for (int i = 1; i <= 100; i++) {
            positions[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            positions[num].add(i);
        }

        int answer = 1;  // 최소 1개는 있으므로

        // 1) d = 0 인 경우: 같은 숫자만 뽑아 만들 수 있는 등차 수열의 최대 길이
        for (int i = 1; i <= 100; i++) {
            answer = Math.max(answer, positions[i].size());
        }

        // 2) d != 0 인 경우: 공차 d가 -99 ~ 99 (0 제외)
        for (int d = -99; d <= 99; d++) {
            if (d == 0) continue;  // d=0은 이미 처리

            // 시작 숫자 a: 1부터 100
            for (int a = 1; a <= 100; a++) {
                if (positions[a].isEmpty()) continue;  // 수열에 a가 없으면 넘어감

                int count = 0;
                int lastIndex = -1;   // 아직 아무 인덱스도 선택하지 않음
                int current = a;      // 현재 찾을 값

                // current가 유효한 범위 내에 있을 때(1 ≤ current ≤ 100) 시뮬레이션 진행
                while (current >= 1 && current <= 100) {
                    // positions[current]에서 lastIndex보다 큰 첫 번째 인덱스를 찾기 위해 순차적으로 검사
                    boolean found = false;
                    for (int pos : positions[current]) {
                        if (pos > lastIndex) {
                            lastIndex = pos;  // 이 위치로 갱신
                            count++;          // 등차 수열에 한 항 추가
                            found = true;
                            break;            // 첫 번째 찾은 인덱스만 사용하고 다음 단계로
                        }
                    }
                    if (!found) break;  // 현재 값 current를 더 이상 찾을 수 없음 → 종료
                    // 다음 값: current에 d를 더한다.
                    current += d;
                }
                answer = Math.max(answer, count);
            }
        }

        // 최종 결과 출력
        System.out.println(answer);
    }
}

