import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {
    public static List<Integer>[] arr;
    public static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N + 1];
        result = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            arr[a].add(b);
            arr[b].add(a);

        }
        tree(1);
        for (int i = 2; i <= N; i++) {
            System.out.println(result[i]);
        }

    }

    public static void tree(int parent) {
        for (int child : arr[parent]) {
            if (result[child] != 0)
                continue;
            result[child] = parent;
            tree(child);
        }
    }

}