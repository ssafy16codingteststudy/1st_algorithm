import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스의 수 T 읽기
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            // 현재 테스트 케이스의 파일 개수 K
            int K = Integer.parseInt(br.readLine());

            // 파일 크기 입력
            st = new StringTokenizer(br.readLine());
            // 파일 크기를 저장할 우선순위 큐(PriorityQueue)를 long 타입으로 선언
            PriorityQueue<Long> pq = new PriorityQueue<>(K);
            for (int i = 0; i < K; i++) {
                long fileSize = Long.parseLong(st.nextToken());
                pq.offer(fileSize);
            }

            // 두 개씩 합쳐나가며 최소 비용 계산 (Huffman 방식)
            long totalCost = 0;
            while(pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                long sum = a + b;
                totalCost += sum;
                pq.offer(sum);
            }
            sb.append(totalCost).append("\n");
        }
        System.out.print(sb.toString());
    }
}
