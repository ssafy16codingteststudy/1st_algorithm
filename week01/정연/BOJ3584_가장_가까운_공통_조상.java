import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            List<Integer> AA = new ArrayList<>();
            List<Integer> AB = new ArrayList<>();
            tree = new Node[N + 1];
            for (int i = 0; i <= N; i++) {
                tree[i] = new Node();
            }

            for (int i = 1; i <= N - 1; i++) {
                String[] nodes = br.readLine().split(" ");
                int p = Integer.parseInt(nodes[0]);
                int c = Integer.parseInt(nodes[1]);
                tree[p].children.add(c);
                tree[c].parent = p;
            }
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            findAncester(a, AA);
            findAncester(b, AB);

            int result = 0;
            for (int i = 0; i < N; i++) {
                /**
                 * Wrapper 클래스(여기선 Integer)의 자동 언박싱 범위가 존재한다. Wrapper 클래스끼리 비교할 때 == 연산자로 비교 시
                 * 127까지 비교 가능하지만, 그 이상부터는 인스턴스로 취급하기 때문에 int 가 아니고 Integer로 비교하기 때문에 equals로
                 * 비교해야한다.
                 * 
                 */
                if (AA.size() == i || AB.size() == i || !AA.get(i).equals(AB.get(i))) {
                    break;
                }
                result = AA.get(i);
            }
            System.out.println(result);
        }

    }

    static void findAncester(int cur, List<Integer> l) {
        int p = tree[cur].parent;
        if (p != 0)
            findAncester(p, l);
        l.add(cur);
    }

}

class Node {
    List<Integer> children;
    int parent;

    public Node() {
        children = new ArrayList<>();
        parent = 0;
    }

}
