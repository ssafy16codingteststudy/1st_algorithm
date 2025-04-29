import java.io.*;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        answer(2, 1);
        answer(3, 1);
        answer(5, 1);
        answer(7, 1);

        System.out.println(sb);
    }

    static void answer(int prime, int i) {
        if (i == n) {
            sb.append(prime);
            sb.append("\n");
            return;
        }
        for (int x = 0; x <= 9; x++) {
            int num = prime * 10 + x;
            if (isPrime(num)) {
                answer(num, i + 1);
            }
        }
    }

    static boolean isPrime(int num) {

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
