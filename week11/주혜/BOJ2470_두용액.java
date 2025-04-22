import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Long> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(list);

        int left = 0;
        int right = N - 1;

        long min = Long.MAX_VALUE;
        long ans1 = 0, ans2 = 0;

        while (left < right) {
            long a = list.get(left);
            long b = list.get(right);
            long sum = a + b;

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                ans1 = a;
                ans2 = b;
            }

            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}
