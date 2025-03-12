import java.util.*;
import java.io.*;

public class Main {

	private static int A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		sb.append(dnc(B));

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	private static long dnc(int power) {
		if (power == 1)
			return A % C;

		long v1 = dnc(power / 2) % C;

		long result = (v1 * v1) % C;
		if (power % 2 == 1)
			result = (result * A) % C;
		
		return result;
	}

}
