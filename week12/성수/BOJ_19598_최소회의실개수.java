package week12.성수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_19598_최소회의실개수 {
    static int N; //회의 수(1~100,000)
    static int[][] meeting;
    static int ans = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        meeting = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            meeting[i][0] = s;
            meeting[i][1] = e;
        }
        Arrays.sort(meeting, (m1, m2)->{
            return Integer.compare(m1[0], m2[0]); //시작이 빠른 거 부터 큐에 들어갈 예정
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((m1, m2) -> {
            return Integer.compare(m1, m2); //빨리 끝난는 걸 꺼냄
        });
        for(int i=0; i<N; i++){
            int s = meeting[i][0];
            int e = meeting[i][1];

            while(!pq.isEmpty() && pq.peek() <= s){
                pq.poll(); //새롭게 회의가 시작됐을 때 이미 시작한 회의가 끝났으면 pq 에서 빼면서 회의를 끝냄
            }
            pq.add(e);
            ans = Math.max(ans, pq.size()); //pq의 사이즈는 진행 중인 회의 수 -> 모든 회의를 끝냈을 때 가지 동시에 진행된 회의가 가장
            //많은 순간이 최대로 필요한 회의실 수.
        }

        System.out.print(ans);
        br.close();
    }
}
