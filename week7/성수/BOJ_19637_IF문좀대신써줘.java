import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_19637_IF문좀대신써줘 {

    static int N; // 칭호의 개수 1~100,000
    static int M; // 칭호를 출력할 캐릭터 수, 1~100,000
    static ArrayList<String> title;
    static ArrayList<Integer> ca;
    // static String[] title; //칭호 이름.
    // static int[] ca; //칭호 기준. 0~1,000,000,000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        title = new ArrayList<>();
        ca = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        title.add(st.nextToken());
        ca.add(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            int tem = Integer.parseInt(st.nextToken());
            if (ca.get(ca.size() - 1) == tem) {
                continue;
            }
            title.add(temp);
            ca.add(tem);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int m = Integer.parseInt(br.readLine());
            int end = ca.size() - 1;
            sb.append(title.get(solve(0, end, m))).append("\n");
        }
        System.out.print(sb);
    }

    public static int solve(int start, int end, int target) {
        if (start > end)
            return start;
        int mid = (start + end) / 2;

        if (ca.get(mid) == target) {
            return mid;
        } else if (ca.get(mid) < target) {
            return solve(mid + 1, end, target);
        } else {
            return solve(start, mid - 1, target);
        }
    }
}
