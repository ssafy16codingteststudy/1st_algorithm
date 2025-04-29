package 일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


class Time implements Comparable<Time> {
    int start;
    int end;

    Time(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        if (this.start == o.start) return this.end - o.end; //시작시간이 같으면 종료시간으로 정렬
        else return this.start - o.start;
    }
}

public class BOJ19598_최소_회의실_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Time[] times = new Time[N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);

            times[i] = new Time(start, end);
        }

        Arrays.sort(times);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(times[0].end);
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= times[i].start) { // 끝나는 시간이 다음 회의시간보다 빠르거나 같으면 회의실 1개로 가능하나다는 뜻
                pq.poll(); // q에서 빼냄
            }
            pq.offer(times[i].end); // 끝나는 시간을 넣음
        }

        System.out.println(pq.size()); // 모든 회의를 위와같은 과정을 거치면 q에 남아있는 사이즈가 회의실 사용 숫자
    }
}
