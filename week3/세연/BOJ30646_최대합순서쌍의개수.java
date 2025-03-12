import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N + 1];
        long[] prefixSum = new long[N + 1]; // 누적 합 저장하는 배열
        Map<Long, Integer> firstIdx = new HashMap<>(); // 특정 값이 등장한 가장 첫번째 인덱스 저장하는 해시맵
        Map<Long, Integer> lastIdx = new HashMap<>(); // 특정 값이 등장한 가장 마지막 인덱스 저장하는 해시맵

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + arr[i]; // 누적합 저장
            if (!firstIdx.containsKey(arr[i])) { firstIdx.put(arr[i], i); } // 만일 저장된 첫번째 인덱스 값이 없을 경우 값 등록
            lastIdx.put(arr[i], i);
        }

        int s, e, ansCnt = 0;
        long ansSum = -1, sum;
        for (long x : firstIdx.keySet()){
            s = firstIdx.get(x); e = lastIdx.get(x);
            sum = prefixSum[e] - prefixSum[s-1];

            if (sum > ansSum) { // 같은 수 안의 배열의 합이 가장 큰 값보다 큰 경우
                ansSum = sum; // 답으로 반환하는 최대합 값을 업데이트
                ansCnt = 1; // 해당 값을 만족하는 순서쌍 개수도 초기화
            }
            else if (sum == ansSum) { // 배열의 합이 최대값과 같은 경우
                ansCnt++; // 해당 값을 만족하는 순서쌍 개수 업데이트
            }
        }
        System.out.printf("%d %d\n", ansSum, ansCnt);
    }
}