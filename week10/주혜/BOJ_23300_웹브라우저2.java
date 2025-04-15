import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Stack<Integer> back = new Stack<>();
        Stack<Integer> front = new Stack<>();

        int current = 0;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String work = st.nextToken();

            switch(work) {
                case "B" :
                    if (back.isEmpty()) continue;
                    else {
                        front.add(current);
                        current = back.pop();
                    }
                    continue;
                case "F" :
                    if (front.isEmpty()) continue;
                    else {
                        back.add(current);
                        current = front.pop();
                    }
                    continue;
                case "A" :
                    int webNum = Integer.parseInt(st.nextToken());
                    if (current != 0) {
                        back.add(current);
                        current = webNum;
                        front.clear();
                    } else {
                        current = webNum;
                    }
                    continue;
                case "C" :
                    Stack<Integer> temp = new Stack<>();
                    int prev = 0;
                    int size = back.size();

                    for (int j = 0; j < size; j++) {

                        if (temp.isEmpty()) {
                            temp.add(back.pop());
                            prev = temp.peek();
                        }
                        else {
                            if (prev == back.peek()) back.pop();
                            else {
                                temp.add(back.pop());
                                prev = temp.peek();
                            }
                        }
                    }

                    size = temp.size();
                    for (int j = 0; j < size; j++) {
                        back.add(temp.pop());
                    }
            }

        }

        System.out.println(current);
        if (back.isEmpty()) System.out.println(-1);
        else {
            int size = back.size();
            for (int i = 0; i < size; i++) {
                System.out.print(back.pop() + " ");
            }
            System.out.println();
        }

        if (front.isEmpty()) System.out.println(-1);
        else {
            int size = front.size();
            for (int i = 0; i < size; i++) {
                System.out.print(front.pop() + " ");
            }
        }
    }
}
