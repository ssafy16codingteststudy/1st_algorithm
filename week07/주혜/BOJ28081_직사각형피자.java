import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		
		List<Integer> nums = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [] cutting_y = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cutting_y[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int [] cutting_x = new int [M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			cutting_x[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> row = new ArrayList<>();
		row.add(cutting_x[0]);
		for (int i = 1; i < M + 1; i++) {
			if (i == M) row.add(W - cutting_x[i-1]);
			else row.add(cutting_x[i] - cutting_x[i-1]);
		}
		
		List<Integer> col = new ArrayList<>();
		col.add(cutting_y[0]);
		for (int i = 1; i < N + 1; i++) {
			if (i == N) col.add(H-cutting_y[i-1]);
			else col.add(cutting_y[i] - cutting_y[i-1]);
		}
		
		Collections.sort(row);
		Collections.sort(col);
		
		long answer = 0;

		for (int i = 0; i < row.size(); i++) {
            int left = 0, right = col.size() - 1;
            int validCount = 0;

            while (left <= right) {
                int mid = (left + right) / 2;
                long area = (long) row.get(i) * col.get(mid);

                if (area <= K) {
                    validCount = mid + 1;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            answer += validCount;
            if (validCount == 0) break;
        }

		System.out.println(answer);
		
	}
}
