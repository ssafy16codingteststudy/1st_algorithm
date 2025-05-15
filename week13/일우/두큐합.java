package week13.일우;

public class 두큐합 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            int n = queue1.length; //q의 길이
            long[] sumq = new long[2 * n]; //1q 2q 합한 원형큐
            long sum1 = 0, sum2 = 0;

            for(int i = 0; i < n; i++) {
                sumq[i] = queue1[i];
                sumq[i+n] = queue2[i];
                sum1 += queue1[i];
                sum2 += queue2[i];
            }

            // 합이 홀수면 같게 못하므로 return;
            long total = sum1+sum2;
            if (total % 2 != 0) return -1;

            long target = total / 2;
            // 투 포인터, 작업 횟수
            int p1 = 0;
            int p2 = n;
            long curr = sum1;
            int ops = 0;
            int maxOps = 4 * n;

            // 큐에서 숫자를 다 뺀 경우의 수 전까지
            while(ops<= maxOps && p1 < 2*n && p2 < 2*n) {
                if(curr == target) return ops;

                if(curr > target) { // 1번 큐의 합이 타겟넘버보다 크니까 1번 큐에서 빼고 2번큐에 넣음
                    curr -= sumq[p1];
                    p1++;
                } else if(curr < target) {
                    curr += sumq[p2];
                    p2++;
                }
                ops++;
            }

            return -1;
        }
    }
}
