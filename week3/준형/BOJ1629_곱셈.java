import java.util.*;
import java.io.*;

public class Main {

	static int C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		System.out.println(pow(A, B));
	}

	static long pow(long A, long exponent) {

		if (exponent == 1) {
			return A % C;
		}

		long temp = pow(A, exponent / 2);

		if (exponent % 2 == 1) {
			return (temp * temp % C) * A % C;
		}
		return temp * temp % C;
	}
}
