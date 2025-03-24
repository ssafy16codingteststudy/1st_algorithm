package week7.정연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_19637_IF문좀대신써줘 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Name> arr = new ArrayList<>();
        Set<Integer> s = new TreeSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int range = Integer.parseInt(st.nextToken());
            if (s.contains(range))
                continue;
            s.add(range);
            arr.add(new Name(range, name));
        }
        Collections.sort(arr, (o1, o2) -> o1.v - o2.v);

        for (int i = 0; i < M; i++) {
            int player = Integer.parseInt(br.readLine());
            int position = Collections.binarySearch(arr, new Name(player, ""), (o1, o2) -> o1.v - o2.v);
            if (position >= 0) {
                sb.append(arr.get(position).n);
            } else {
                sb.append(arr.get(position * (-1) - 1).n);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

class Name {
    int v;
    String n;

    public Name(int v, String n) {
        super();
        this.v = v;
        this.n = n;
    }

    @Override
    public String toString() {
        return "Name [v=" + v + ",  n=" + n + "]";
    }

}