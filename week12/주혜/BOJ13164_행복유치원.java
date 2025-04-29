import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] diff = new int[n];
        int answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i != 0){
                diff[i] = arr[i] - arr[i-1];
                answer += diff[i];
            }
        }

        Arrays.sort(diff);

        for (int i = 1; i < k; i++) {
            answer -= diff[n - i];
        }

        System.out.println(answer);
    }

}
