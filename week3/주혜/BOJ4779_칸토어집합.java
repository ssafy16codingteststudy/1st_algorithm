import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);

            System.out.println(kantor(N));
        }
    }

    static String kantor(int n) {
        if (n == 0) return "-"; 
        if (n == 1) return "- -";
        String space = " ";
        return kantor(n-1) + space.repeat((int) Math.pow(3, n-1)) + kantor(n-1);
    }
}