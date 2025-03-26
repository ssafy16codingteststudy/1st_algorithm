import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] names = new String[N];
        int[] power = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }
        
        StringBuilder str = new StringBuilder("");

        for (int i = 0; i < M; i++) {
            int val = Integer.parseInt(br.readLine());

            // binary search
            int low = 0;
            int high = N - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (val <= power[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            str.append(names[low])
            .append("\n");
        }
        
        System.out.println(str.toString());
    }
}

