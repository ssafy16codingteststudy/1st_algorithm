import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        int node = Integer.parseInt(br.readLine());
        sb.append(node).append("\n");
        maxHeap.offer(node);
        for (int i = 1; i < N; i++) {
            node = Integer.parseInt(br.readLine());

            /**
             * 작은 수를 maxHeap에 넣고, 큰 수를 minHeap에 넣는다.
             * 각 heap의 peek를 비교해보고 maxHeap에 peek가 minHeap의 peek보다 크면 서로 바꿔준다.
             * 
             * 작은 수들 중 가장 큰수, 큰 수들 중에서 가장 작은 수를 각 heap의 peek에서 볼수 있다.
             * 
             * 출력 하고자 하는 중간값은 maxHeap의 peek를 꺼내면 된다.
             * 입력 받은 수가 짝수 개여도 항상 maxHeap의 peek가 출력 대상이 된다.
             * 
             */
            if (maxHeap.size() != minHeap.size()) {
                minHeap.offer(node);
            } else {
                maxHeap.offer(node);
            }
            if (maxHeap.peek() > minHeap.peek()) {
                int temp = maxHeap.poll();
                maxHeap.offer(minHeap.poll());
                minHeap.offer(temp);
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
