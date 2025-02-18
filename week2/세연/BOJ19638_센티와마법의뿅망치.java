import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Queue<Integer> giants = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++){
            giants.add(Integer.parseInt(br.readLine()));
        }
        int attack, g;
        for (attack = 0; attack<t;attack++){
            g = giants.poll();
            if (h > g) {
                System.out.printf("YES\n%d",attack);
                break;
            }
            else if (g==1){
                System.out.println("NO\n1");
                break;
            }
            giants.add((int)g/2);
        }
        if (attack==t){
            int biggest = giants.poll();
            if (h > biggest) System.out.printf("YES\n%d",attack);
            else System.out.printf("NO\n%d",biggest);
        }
    }
}
