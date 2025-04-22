import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int target = Integer.parseInt(br.readLine()); // 이동하려는 채널
        int errorCount = Integer.parseInt(br.readLine()); // 버튼 수

        broken = new boolean[10];
        if (errorCount > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < errorCount; i++) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }
        }

        // +, - 버튼만 사용하는 경우
        int minPress = Math.abs(target - 100);

        // 숫자 버튼과 +, - 버튼을 사용하는 경우
        for (int num = 0; num < 1000000; num++) {
            if (canPress(num)) {
                int pressCount = String.valueOf(num).length() + Math.abs(num - target);
                minPress = Math.min(minPress, pressCount);
            }
        }

        // 최소 버튼 누른 수
        System.out.println(minPress);
    }

    private static boolean canPress(int num) {
        for (char c : String.valueOf(num).toCharArray()) {
            if (broken[c - '0']) {
                return false;
            }
        }
        return true;
    }
}
