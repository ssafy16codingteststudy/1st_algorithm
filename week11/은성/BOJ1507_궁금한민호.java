import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1507_궁금한민호 {

    private static int N;
    private static int[][] arr;

    /**
     * 1507 궁금한 민호
     * 플로이드의 역 계산
     * 일단 기존 플로이드 알고리즘을 돌면서 값의 갱신이 일어나면 -1 -> 이는 완성된 플로이드가 아니므로, 값을 구할 수 없음
     * 그리고 arr[i][j] == arr[i][k] + arr[k][j] 를 만족하는 i-j 도로는 굳이 필요 없는 도로임 -> 이 if가 아닌 것만 가중치를 더함
     * 이 때, 최단 비용 갱신이 아닌, 각 (i,j)에 대해 모든 k를 돌면서 “경유해서 같은 거리인지”를 체크해야하기 때문에 k가 제일 안쪽 for 문으로 두어야 함
     */
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(checkAndGet());
    }

    private static int checkAndGet() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                boolean flag = true;
                for (int k = 0; k < N; k++) {
                    if (k == i || k == j) continue;

                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        return -1;
                    }

                    // “i–j 간선을 안 써도 k를 거쳐 같은 거리” → 불필요
                    if (arr[i][j] == arr[i][k] + arr[k][j]) {
                        flag = false;
                        break;
                    }
                }

                // 어딜 거쳐갈 수도 없이 이 간선이 최소라면 이 도로는 필요하다.
                if (flag) {
                    sum += arr[i][j];
                }
            }
        }
        return sum;
    }

    private static boolean check() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(i == k) continue;
                for (int j = i + 1; j < N; j++) {
                    if(j == i || j == k) continue;

                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int getAnswer() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                boolean flag = true;
                for (int k = 0; k < N; k++) {
                    if (k == i || k == j) continue;
                    // “i–j 간선을 안 써도 k를 거쳐 같은 거리” → 불필요
                    if (arr[i][j] == arr[i][k] + arr[k][j]) {
                        flag = false;
                        break;
                    }
                }

                // 어딜 거쳐갈 수도 없이 이 간선이 최소라면 이 도로는 필요하다.
                if (flag) {
                    sum += arr[i][j];
                }
            }
        }
        return sum;
    }
}