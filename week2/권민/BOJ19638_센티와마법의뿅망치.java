import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			q.add(n);
		}

		int tmp = 0;
		int count = 0;
		// 뿅망치를 가장 효율적으로 사용하기 위해서는 키가 가장 큰 사람을 반으로 줄여야 한다.
		while ((tmp = q.poll()) >= H && count++ < T) {
			if (tmp != 1)
				tmp /= 2;
			q.add(tmp);
		}

		StringBuilder sb = new StringBuilder();
		if (count > T || tmp >= H) {
			sb.append("NO\n").append(tmp);
		} else {
			sb.append("YES\n").append(count);
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		bw.close();
	}
}
