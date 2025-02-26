package week3.예림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ30646_최대합순서쌍의개수 {

    private static int[] a;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine()); // 배열 크기

        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long[] prefixSum = computePrefixSum();
        Map<Integer, int[]> indexMap = buildIndexMap();
        long[] result = findMaxSumAndCount(prefixSum, indexMap);

        sb.append(result[0]).append(" ").append(result[1]);
        System.out.println(sb);
    }

    private static long[] computePrefixSum() {
        long[] prefixSum = new long[n];

        prefixSum[0] = a[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        return prefixSum;
    }

    private static Map<Integer, int[]> buildIndexMap() {
        Map<Integer, int[]> indexMap = new HashMap<>(); // 각 원소의 첫 인덱스, 마지막 인덱스 저장
        for (int i = 0; i < n; i++) {
            if (indexMap.containsKey(a[i])) {
                indexMap.get(a[i])[1] = i;
            } else {
                indexMap.put(a[i], new int[]{i, i});
            }
        }
        return indexMap;
    }

    private static long[] findMaxSumAndCount(long[] prefixSum, Map<Integer, int[]> indexMap) {
        long max = 0;
        int count = 0;

        for (int[] index : indexMap.values()) { // 최대값, 횟수 구하기
            int startIndex = index[0];
            int endIndex = index[1];

            long sum = prefixSum[endIndex] - (startIndex > 0 ? prefixSum[startIndex - 1] : 0);

            if (sum > max) {
                max = sum;
                count = 1;
            } else if (max == sum) {
                count++;
            }
        }

        return new long[]{max, count};
    }
}
