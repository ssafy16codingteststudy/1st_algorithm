package week12.은성;

import java.io.*;
import java.util.*;

public class BOJ13164_행복유치원 {

    private static int n, k;
    private static int[] arr;
    /**
     * 13164 행복유치원
     * 서로 인접한 원생 끼리 K 개의 조를 구성 -> 각 원생들 사이에 K - 1 개의 경계를 설정
     * 어디에 k - 1 개의 경계를 세울 것인가? -> 가장 키 차이가 많이 나는 곳에 세움
     * 서로 인접한 원생들의 키 차이를 모두 구한뒤,
     * 경계를 세운 곳을 제외한 키 차이를 모두 더하면 이는 K개의 조에 대한 비용의 합과 같다
     * 구현 : 모든 인접 키차이를 구한 뒤, 정렬한 후 N - K 번째 까지의 합을 구하면 됨
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[n - 1];

        for (int i = 1; i < n; i++) {
            diff[i - 1] = arr[i] - arr[i - 1];
        }
        Arrays.sort(diff);

        int answer = 0;
        for (int i = 0; i < n - k; i++) {
            answer += diff[i];
        }
        System.out.println(answer);
    }
}
