import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = br.readLine();
            if (input == null)
                break;
            solve(input);
        }
    }

    static void solve(String input) {
        int N = Integer.parseInt(input);
        ct(N, 0);
        System.out.println(sb.toString());
        sb.setLength(0);
    }

    static void ct(int n, int idx) {
        if (idx == 1) {
            sb.append(" ".repeat((int) Math.pow(3, n)));
            return;
        }
        if (n == 0) {
            sb.append("-");
            return;
        }
        for (int i = 0; i < 3; i++) {
            ct(n - 1, i);
        }
    }
}
