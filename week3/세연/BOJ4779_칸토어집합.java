import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                int N = Integer.parseInt(br.readLine());
                sb.append(dnc(N)).append("\n");
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(sb);
    }

    static String dnc(int N) {
        if (N == 0) return "-"; // 재귀 탈출 조건
        StringBuilder sb = new StringBuilder();
        String tmp = dnc(N-1); // 재귀 두번 호출하는 것 보다는 이 방식이 속도가 좀 더 빠름
        return sb.append(tmp).append(" ".repeat((int)(Math.pow(3, N-1)))).append(tmp).toString();
    }
}
// 실험! 과거에 계산했던 것을 기록하는 방식
public class Main {
    static String[] history = new String[13];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        history[0] = "-";
        while (true) {
            try {
                int N = Integer.parseInt(br.readLine());
                sb.append(dnc(N)).append("\n");
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(sb);
    }

    static String dnc(int N) {
        if (N == 0) return history[0]; // 재귀 탈출 조건
        StringBuilder sb = new StringBuilder();
        if (history[N-1] == null) history[N-1] = dnc(N-1); // 한번 계산한 건 기록해서 가져와볼까 했으나 시간 차이 별로 없음
        return sb.append(history[N-1]).append(" ".repeat((int)(Math.pow(3, N-1)))).append(history[N-1]).toString();
    }
}