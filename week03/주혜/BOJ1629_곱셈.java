import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String [] str = s.split(" ");

        long A = Long.parseLong(str[0]);
        long B = Long.parseLong(str[1]);
        long C = Long.parseLong(str[2]);

        System.out.println(answer(A, B, C));
    }

    static long answer(long A, long B, long C){

        if (B == 0) return 1;
        if (B == 1) return A % C;
        long half = answer(A, B / 2, C);
        long result = half * half % C;
        if (B % 2 != 0) {
            result = result * A % C;
        }
        return result;
    }
}