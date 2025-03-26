import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] request = new int[N], sum = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) request[i] = Integer.parseInt(st.nextToken());
        int totalAmt = Integer.parseInt(br.readLine());

        Arrays.sort(request);
        sum[0] = request[0];
        for (int i = 1; i < N; i++) sum[i] = request[i] + sum[i - 1];

        int low = 0, high = N-1, mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (sum[mid] + (N-1-mid)*request[mid] <= totalAmt) low = mid+1;
            else high = mid;
        }

        if (low == 0) System.out.println(totalAmt/N);
        else if (low == N-1 && sum[low] <= totalAmt) System.out.println(request[low]);
        else System.out.println((totalAmt-sum[low-1])/(N-low));
    }
}