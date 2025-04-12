package 용범;

import java.io.*;
import java.util.*;

public class BOJ1655_가운데를말해요 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();

  static int N, num;
  static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
  static PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

  public static void main(String[] args) throws IOException {

    N = Integer.parseInt(br.readLine()); // N: 외치는 숫자 개수

    // maxHeap.peek() <= minHeap.peek() 을 만족해야한다.
    for (int i = 1; i <= N; i++) {
      num = Integer.parseInt(br.readLine()); // -10_000 <= num <= 10_000

      // 초기값 설정
      if (maxHeap.isEmpty()) {
        maxHeap.offer(num);
        sb.append(num).append("\n");
        continue;
      } else {
        // 입력값이 maxHeap.peek() 보다 작은 경우 -> maxHeap 저장
        if (num < maxHeap.peek()) {
          maxHeap.offer(num);
        }
        // 입력값이 maxHeap.peek() 보다 크거나 같은 경우 -> minHeap 저장
        else {
          minHeap.offer(num);
        }

        if (Math.abs(minHeap.size() - maxHeap.size()) >= 2) {
          makeSameHeight(); // 높이 조정
        }

        // 들어온 숫자의 개수가 짝수 개인 경우 -> 두 Heap 의 peek() 중에서 작은 수 저장
        if (i % 2 == 0) {
          sb.append(maxHeap.peek())
              .append("\n");
        }
        // 들어온 숫자의 개수가 홀수 개인 경우 -> 홀수 개의 원소를 가진 Heap의 peek() 저장
        else {
          sb.append(maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek())
              .append("\n");
        }
      }
    }

    // 정답 출력
    System.out.println(sb);
    br.close();
  }

  private static void makeSameHeight() {

    // minHeap 원소가 2개 더 많은 경우 -> 1개를 maxHeap 이동
    if (maxHeap.size() + 1 < minHeap.size()) {
      maxHeap.offer(minHeap.poll());
    }
    // maxHeap 원소가 2개 더 많은 경우 -> 1개를 minHeap 이동
    else if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    }

  }
}
