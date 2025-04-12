package week3.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4779_칸토어집합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int n = Integer.parseInt(input);
            sb.append(solution(n)).append("\n");
        }

        System.out.println(sb);
    }

    private static String solution(int n) {
        if (n == 0) {
            return "-";
        }
        String side = solution(n - 1);
        String space = " ".repeat(side.length());
        return side + space + side;
    }
}
