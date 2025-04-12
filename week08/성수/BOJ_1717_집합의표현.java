package problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {

    static int n; // {0}, {1}, ..., {n} 초기 집합, 1,000,000
    static int m; // 연산의 개수, 100,000
    static int[] p; // 집합의 상태, 양수면 부모 정점, 음수면 부모가 없음을 나타내고 그 크기에 따라 랭크를 의미.
    static int cal; // 연산 정보, 0이면 유니온 연산.
    static int a, b;
    // 입력: 0, a, b -> a가 포함된 집합과 b가 포함된 집합을 합침
    // 입력: 1, a, b -> a와 b가 같은 집합에 포함이면 YES 아니면 NO 출력.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n + 1];
        Arrays.fill(p, -1);
        // 부모가 없는 정점 기준(루트노드) - |p[x]| = rank; 1 <- 2 , 3 <- 4 <- 5 이면 p[1] = -2, p[3] =
        // -3
        // 1. 최적화 기법: Union by rank
        // find 과정에서 부모를 찾는(find) 시간 복잡도는 O(N) -> 정점의 깊이가 깊을수록 효율 떨어짐.
        // 그래서 합칠 때(union) 랭크가 큰 집합에 작은 집합을 합침으로써 집합의 랭크를 낮게 유지 할 수 있음.
        // p[u] < p[v] -> u가 부모로 있는 집합의 랭크가 더 큼
        // 큰 랭크 집합에서 작은 랭크 집합을 합치면 랭크 변화 없음(p[v] = u)
        // 작은 랭크 집합에서 큰 랭크 집합을 합치면 랭크 커짐(p[v] = p[u] - 1, p[u] = v)
        // 시간 복잡도 log(n)으로 줄어듦

        // 2. 최적화 기법: 경로 압축(Path compression)
        // 6 <- 5 <- 4 <- 3 <- 2 <- 1 인 집합이 있다 가정하고 find(3)을 했을 때
        // 3부터 4 5 를 타고 끝인 6에 도달함 그래서 3, 4를 6의 자식으로 바꿔줌 (p[3] = 6, p[4] = 6)
        // 같은 집합인건 변화 없지만 깊이(랭크)를 줄일 수 있음.
        // 시간 복잡도 log(n)으로 줄어듦

        // 코테 수준에서 둘 다 적용하지 않아도 충분하지만 둘 다 적용하면 log(a(n))의 시간 복잡도를 가짐
        // a(n)은 아커만 함수의 역함수, n이 아무리 커져도 5를 넘기지 않아서 사실상 상수의 시간 복잡도로 작용함.

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            cal = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (cal == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.print(sb);
        br.close();
    }

    public static int find(int x) {
        if (p[x] < 0)
            return x; // 더 이상 부모가 없는 노드, 루트노드 도달, 자기 자신을 반환.

        return p[x] = find(p[x]); // 부모의 부모를 타고 찾으러 가면서 지난 노드들의 부모를 루트노드로 바꿈(경로 압축)

    }

    public static void union(int x, int y) {
        int u = find(x); // u가 속한 집합의 루트 노드 저장.
        int v = find(y); // v가 속한 집합의 루트 노드 저장.

        if (u == v) // x와 y가 같은 집합에 존재
            return; // 합칠 필요가 없음.

        // Union by rank를 적용.
        if (p[v] < p[u]) { // v의 집합의 랭크가 더 큼
            int temp = u;
            u = v;
            v = temp; // u의 랭크가 항상 크거나 같은 집합으로 고정해서 생각하려고 스왑 (나눠서 생각해도 되지만 코드가 길어짐)
            // 밑에서 u집합에 v집합을 합치는 코드 하나로 고정할 수 있음.
        }
        if (p[v] == p[u])
            p[u]--; // 둘 다 랭크가 같다면 합쳐지면서 랭크가 하나 늘어남.

        p[v] = u; // v의 부모를 u로 바꿈. 둘의 랭크가 같지 않다면 랭크가 큰 집합의 랭크는 변하지 않음.
    }
}
