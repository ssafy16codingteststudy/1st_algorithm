class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int len = queue1.length;
        int[] nums = new int[len * 2];
        long total = 0;
        long curr = 0;

        for (int i = 0; i < len; i++) {
            nums[i] = queue1[i];
            nums[i + len] = queue2[i];
            total += queue1[i] + queue2[i];
            curr += queue1[i];
        }

        if (total % 2 != 0) return -1;
        long target = total / 2;

        int s = 0, e = len - 1;
        int maxOps = len * 2 + 2;
        int ops = 0;

        while (ops <= maxOps) {
            if (curr == target) return ops;

            if (curr < target) {
                e = (e + 1) % nums.length;
                curr += nums[e];
            } else {
                curr -= nums[s];
                s = (s + 1) % nums.length;
            }

            ops++;
        }

        return -1;
    }
}
