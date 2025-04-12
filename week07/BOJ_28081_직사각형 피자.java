import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int W, H, rCnt, cCnt;

	static long K;
	static int[] rowCut, colCut;
	static final int MAX_SLICE = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		rCnt = Integer.parseInt(br.readLine());
		rowCut = new int[rCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < rCnt; i++) {
			rowCut[i] = Integer.parseInt(st.nextToken());
		}
		cCnt = Integer.parseInt(br.readLine());
		colCut = new int[cCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cCnt; i++) {
			colCut[i] = Integer.parseInt(st.nextToken());
		}
		int[] rows = new int[rCnt + 1];
		double[] cols = new double[cCnt + 1];

		for (int i = 1; i < rCnt; i++) {
			rows[i] = rowCut[i] - rowCut[i - 1];
		}
		rows[0] = rowCut[0];
		rows[rCnt] = H - rowCut[rCnt - 1];
		for (int i = 1; i < cCnt; i++) {
			cols[i] = colCut[i] - colCut[i - 1];
		}
		Arrays.sort(rows);
		cols[0] = colCut[0];
		cols[cCnt] = W - colCut[cCnt - 1];
		Arrays.sort(cols);

		long result = 0;
		for (int r = 0; r < rCnt + 1; r++) {
			if (rows[r] > K)
				break;
			if (K / rows[r] > MAX_SLICE) {
				result += cCnt + 1;
				continue;
			}
			int rr = Arrays.binarySearch(cols, K / rows[r] + 0.1);
//			System.out.println(Arrays.toString(cols));
//			System.out.println("rr :" + (rr + 1) * -1);
			result += (rr + 1) * -1;
		}
		System.out.println(result);

	}
}
//7 6 6 
//1
//3
//2
//1 5

//2 2 2
//1
//1
//1
//1
