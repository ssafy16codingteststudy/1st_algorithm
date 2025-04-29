import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] starts = new int[N];
        int[] ends = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            starts[i] = Integer.parseInt(st.nextToken());
            ends[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int answer = 0;
        int sIndex = 0, eIndex = 0;

        while (sIndex < N) {
            if (starts[sIndex] < ends[eIndex]) {
                rooms++;
                answer = Math.max(answer, rooms);
                sIndex++;
            } else {
                rooms--;
                eIndex++;
            }
        }

        System.out.println(answer);
    }
}
