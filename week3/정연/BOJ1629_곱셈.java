import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        A = Long.parseLong(input[0]);
        B = Long.parseLong(input[1]);
        C = Long.parseLong(input[2]);

        System.out.println(POW(B));
    }

    static long POW(long b) {
        if (b == 1) {
            return A % C;
        }
        long x = POW(b / 2);
        if (b % 2 == 0) {
            return (x * x) % C;
        } else {
            return ((x * x) % C * A) % C;
        }
    }
}
// 10 2147483647 12
// => 4

// 2147483647 3 5
// => 3
