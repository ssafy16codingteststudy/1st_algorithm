import java.io.*;
import java.util.Arrays;

public class Main {

    static int[][] tree = new int[10000 * 10 + 1][10];
    static boolean[] isEnd = new boolean[10000 * 10 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int current = 1;

            for (int i = 0; i <= N * 10; i++) {
                Arrays.fill(tree[i], -1);
            }
            Arrays.fill(isEnd, false);

            boolean answer = true;

            for (int i = 0; i < N; i++) {
                int nxt = 0;
                String[] arr = br.readLine().split("");
                if (answer == false) continue;
                for (int j = 0; j < arr.length; j++) {
                    if (tree[nxt][Integer.parseInt(arr[j])] == -1) {
                        tree[nxt][Integer.parseInt(arr[j])] = current;
                        nxt = current++;
                        answer = true;
                    } else {
                        nxt = tree[nxt][Integer.parseInt(arr[j])];
                        answer = false;
                        if (isEnd[nxt] == true) break;
                    }
                }
                isEnd[nxt] = true;
            }

            if (answer == true) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
