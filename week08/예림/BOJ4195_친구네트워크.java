import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int SIZE = 200_000;
    private static Map<String, Integer> people; // 이름, 인덱스
    private static int[] parents;
    private static int[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            people = new HashMap<>();
            parents = new int[SIZE];
            sizes = new int[SIZE];

            int F = Integer.parseInt(br.readLine());
            int index = 0;
            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!people.containsKey(a)) {
                    people.put(a, index);
                    parents[index] = index;
                    sizes[index++] = 1;
                }
                if (!people.containsKey(b)) {
                    people.put(b, index);
                    parents[index] = index;
                    sizes[index++] = 1;
                }

                union(people.get(a), people.get(b));

                int size = sizes[find(people.get(a))];
                sb.append(size).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootB] = rootA;
            sizes[rootA] += sizes[rootB];
        }
    }
}
