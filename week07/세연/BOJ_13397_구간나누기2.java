import java.util.*;
import java.io.*;

class Main {
    static int[] arr;
    static int answer = 10001;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int low = 0, high = 0, mid;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            high = Math.max(arr[i], high);
        }
        while (low < high) {
            mid = (low + high) / 2;
            if (count(mid) <= M) high = mid;
            else {
                low = mid + 1;
            }
        }

        System.out.println(high);
    }

    static int count(int mid){
        int cnt = 1;
        int min = 10001;
        int max = 0;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if (max - min > mid) {
                cnt++;
                min = 10001; max = 0;
                i--;
            }
        }
        return cnt;
    }
}