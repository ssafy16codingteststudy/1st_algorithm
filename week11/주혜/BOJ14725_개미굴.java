import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    Map<String, Node> children = new TreeMap<>();
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node head = new Node();
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" ");
            int K = Integer.parseInt(arr[0]);
            Node cur = head;

            for (int k = 1; k <= K; k++) {
                if (!cur.children.containsKey(arr[k])) {
                    cur.children.put(arr[k], new Node());
                    cur = cur.children.get(arr[k]);
                    cnt++;
                }
                else {
                    cur = cur.children.get(arr[k]);
                }
            }
        }

        print(head, 0);

    }

    public static void print(Node node, int depth) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(key);
            print(node.children.get(key), depth + 1);
        }
    }

}
