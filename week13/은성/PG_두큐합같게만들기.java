import java.util.*;

class PG_두큐합같게만들기 {
    /**
     * 두 큐 합 같게 만들기
     * 접근 : 두 큐를 합쳐서 하나의 배열을 만들고,
     *      누적합을 사용하여 합이 1/2 이 되는 구간을 구함 -> 구간을 통해 연산 횟수를 계산 후 비교
     *      이렇게 하면 단순히 큐에 지속적인 연산(poll/offer)을 하는 것 보다 한 연산당 비용이 적게 들어 시간상 효율이 좋다고 함(챗지피티 피셜)
     */
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        long sum = 0;

        long[] sumQueue = new long[n * 2 + 1];
        sumQueue[1] = queue1[0];

        for (int i = 1; i < n * 2; i++) {
            if(i < n) {
                sumQueue[i + 1] = sumQueue[i] + queue1[i];
            } else {
                sumQueue[i + 1] = sumQueue[i] + queue2[i - n];
            }
        }

        sum = sumQueue[n * 2];
        if(sum % 2 == 1) {
            return -1;
        }
        sum /= 2;

        int answer = Integer.MAX_VALUE;
        int start = 0;
        int end = 1;
        while(end <= n * 2) {
            long temp = sumQueue[end] - sumQueue[start];

            if(temp == sum) {
                int value = 0;
                if(end < n) {
                    value = (start + n) + (end);
                } else {
                    value = (start) + (end - n);
                }
                answer = Math.min(answer, value);
            }

            if(temp > sum) {
                start++;
            } else {
                end++;
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}