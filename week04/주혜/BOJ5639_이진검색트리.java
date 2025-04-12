import java.io.*;
import java.util.*;

class Main {
    static ArrayList<Integer> node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        node = new ArrayList<>();
        String input;

        while ((input = br.readLine()) != null) {
            node.add(Integer.parseInt(input));
        }

        postOrder(0, node.size() - 1);

    }

    static void postOrder(int start, int end) {
        if (start > end)
            return;
        
        int mid = start + 1;
        while (mid <= end && node.get(mid) < node.get(start))
            mid++;

        postOrder(start + 1, mid - 1);
        postOrder(mid, end);
        System.out.println(node.get(start));
    }
}