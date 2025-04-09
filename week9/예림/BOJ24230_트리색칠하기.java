import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<Integer>[] temp;
    private static List<Integer>[] tree;
    private static int count;
    private static int[] colors, currentColors;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 정점 수 (1 ~ N)

        st = new StringTokenizer(br.readLine());
        colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
        }

        temp = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            temp[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            temp[a].add(b);
            temp[b].add(a);
        }

        visited = new boolean[N + 1];
        makeTree(1);

        currentColors = new int[N + 1];
        count = 0;
        paintColor(1, 0);

        System.out.println(count);
    }

    private static void makeTree(int now) {
        visited[now] = true;
        for (int next : temp[now]) {
            if (visited[next]) {
                continue;
            }
            tree[now].add(next);
            makeTree(next);
        }
    }

    private static void paintColor(int now, int parentColor) {
        if (colors[now] != parentColor) {
            count++;
        }
        for (int next : tree[now]) {
            paintColor(next, colors[now]);
        }
    }
}
