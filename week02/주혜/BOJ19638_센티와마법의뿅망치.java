import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int H = sc.nextInt();
        int T = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            pq.add(sc.nextInt());
        }

        sc.close();

        if (pq.peek() < H) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= T; i++) {
            int max = pq.poll();

            if (max == 1) {
                System.out.println("NO");
                System.out.println(max);
                return;
            }

            if(max % 2 == 0) {
                max /= 2;
            } else if(max % 2 == 1) {
                max = (max - 1) / 2;
            }
            pq.add(max);

            if (pq.peek() < H) {
                System.out.println("YES");
                System.out.println(i);
                return;
            }
        }

        System.out.println("NO");
        System.out.println(pq.peek());
    }
}
