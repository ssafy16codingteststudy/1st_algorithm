import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_6549_히스토그램에서_가장_큰_직사각형 {
    // 전역 변수: 토크나이저, 막대 개수, 히스토그램 높이 배열, 최대 직사각형 넓이 결과
    static StringTokenizer st;
    static int N;
    static int[] arr;
    static long result;

    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 여러 테스트 케이스를 처리하기 위한 무한 반복문 (0이 입력되면 종료)
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 첫 토큰은 직사각형(막대)의 개수
            if (N == 0)
                break; // 0이 입력되면 프로그램 종료

            result = 0l; // 최대 넓이를 저장할 변수 초기화
            arr = new int[N]; // 히스토그램의 각 막대 높이를 저장하는 배열
            // 히스토그램 막대의 높이를 배열에 저장
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 스택 선언: 각 원소는 int 배열 {높이, 해당 높이의 시작 인덱스}로 구성
            // 이 방식을 사용하면, 현재 막대를 기준으로 이전에 등장한 높이들이 연속 구간을 형성할 때
            // 그 시작 인덱스를 기억하여 넓이를 효율적으로 계산할 수 있음.
            ArrayDeque<int[]> stack = new ArrayDeque<>();

            // 모든 막대를 순회하면서 처리
            for (int i = 0; i < N; i++) {
                // 만약 스택이 비어있거나 현재 막대의 높이가 스택의 최상단 막대보다 큰 경우,
                // 현재 막대의 높이와 인덱스를 스택에 추가
                if (stack.isEmpty() || arr[i] > stack.peek()[0])
                    stack.push(new int[] { arr[i], i });
                // 현재 막대의 높이가 스택의 최상단 막대와 동일하면, 특별한 처리가 필요없으므로 건너뜀
                else if (arr[i] == stack.peek()[0])
                    continue;
                else {
                    // 현재 막대의 높이가 스택의 최상단 막대보다 낮은 경우,
                    // 스택에서 팝하면서 직사각형의 넓이를 계산해야 함.
                    // 현재 스택의 top 원소의 시작 인덱스를 임시 변수에 저장.
                    int startpoint = stack.peek()[1];
                    // 스택에서 현재 막대보다 높이가 큰 원소들을 처리하기 위한 반복문
                    while (true) {
                        // 만약 스택이 비어있거나, 현재 막대의 높이가 스택 최상단 원소의 높이보다 크다면,
                        // 현재 막대를 스택에 {현재 높이, startpoint} 형태로 push하고 종료
                        if (stack.isEmpty() || arr[i] > stack.peek()[0]) {
                            stack.push(new int[] { arr[i], startpoint });
                            break;
                        }
                        // 현재 막대의 높이가 스택 최상단 원소의 높이와 같다면, 추가 처리가 필요 없으므로 종료
                        else if (arr[i] == stack.peek()[0]) {
                            break;
                        }
                        // 스택의 최상단 원소를 꺼내 현재 막대에 의해 직사각형이 종료된 영역의 넓이를 계산
                        int[] current = stack.pop();
                        // 넓이 계산: 현재 막대의 인덱스 i에서 해당 막대가 시작한 인덱스 current[1]까지의 너비와,
                        // 해당 막대의 높이(current[0])를 곱함
                        long recSize = ((long) i - current[1]) * current[0];
                        // 최대 넓이를 업데이트
                        result = Math.max(result, recSize);
                        // pop한 원소의 시작 인덱스를 보존하여, 이후 계산될 직사각형의 시작점으로 사용
                        startpoint = current[1];
                    }
                }
            }
            // 모든 막대를 순회한 후, 스택에 남은 원소들에 대해 직사각형 넓이를 계산
            while (!stack.isEmpty()) {
                int[] current = stack.pop();
                // 계산: 총 막대 수 N에서 해당 막대가 시작한 인덱스까지의 너비에 막대 높이를 곱함
                long recSize = ((long) N - current[1]) * current[0];
                result = Math.max(result, recSize);
            }
            // 해당 테스트 케이스의 최대 직사각형 넓이 출력
            System.out.println(result);
        }
    }
}
