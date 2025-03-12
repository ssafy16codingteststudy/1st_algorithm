import java.io.*;

public class Main {

	private static int v;
	private static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = "";
		while ((input = br.readLine()) != null) {
			v = Integer.parseInt(input);
			cantor(0, true);
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	private static void cantor(int depth, boolean isPrint) {
		if (depth == v) {
			if (isPrint) {
				sb.append("-");
			} else {
				sb.append(" ");
			}
			return;
		}

		// 세 부분으로 나뉘고, 가운데가 출력이 안 됩니다.
		// 다음 집합에서 출력이 안 되는 부분을 누가 호출했나 생각해보니
		// 이전에 출력이 안 됐던 부분에서 이어진다고 생각했습니다.
		if(isPrint) {
			cantor(depth + 1, true);
			cantor(depth + 1, false);
			cantor(depth + 1, true);
		} else {
			cantor(depth + 1, false);
			cantor(depth + 1, false);
			cantor(depth + 1, false);
		}
	}

}
