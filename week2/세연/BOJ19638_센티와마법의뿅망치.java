import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Queue<Integer> giants = new PriorityQueue<>(Comparator.reverseOrder()); // 최대 우선순위 큐
        for (int i = 0; i < n; i++){
            giants.add(Integer.parseInt(br.readLine()));
        }
        int attack, g;
        for (attack = 0; attack<t;attack++){ // 가능한 공격 횟수만큼
            g = giants.poll(); // 가장 큰 거인 뽑기
            if (h > g) { // 가장 큰 거인이 센티보다 작음 -> 중단
                System.out.printf("YES\n%d",attack);
                break;
            }
            else if (g==1){ // 가장 큰 거인이 1 -> 중단 
                System.out.println("NO\n1"); // 위의 조건에서 걸리지 않았으므로 센티보다 거인이 큼
                break;
            }
            giants.add((int)g/2); // 종료 조건에 해당하지 않는 경우 거인의 키를 변경해서 다시 큐에 넣음
        }
        if (attack==t){ // 만일 공격 횟수를 모두 채운 경우
            int biggest = giants.poll();
            if (h > biggest) System.out.printf("YES\n%d",attack);
            else System.out.printf("NO\n%d",biggest);
        }
    }
}
