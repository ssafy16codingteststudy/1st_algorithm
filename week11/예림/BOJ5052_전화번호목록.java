import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        boolean insert(String string) {
            Node node = this.root;
            for (char c : string.toCharArray()) {
                if (node.isEndOf) {
                    return false;
                }
                node.children.putIfAbsent(c, new Node());
                node = node.children.get(c);
            }
            if (!node.children.isEmpty()) {
                return false;
            }
            node.isEndOf = true;
            return true;
        }
    }

    private static class Node {
        Map<Character, Node> children;
        boolean isEndOf;

        Node() {
            this.children = new HashMap<>();
            this.isEndOf = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            boolean isConsistent = true;
            for (int i = 0; i < n; i++) {
                String string = br.readLine();
                if (!trie.insert(string)) {
                    isConsistent = false;
                    for (int j = i + 1; j < n; j++) {
                        br.readLine();
                    }
                    break;
                }
            }
            sb.append(isConsistent ? "YES\n" : "NO\n");
        }
        System.out.println(sb);
    }
}
