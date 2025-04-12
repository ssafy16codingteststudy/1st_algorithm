import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		int max = 0;
		int min = 10000;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = (min > arr[i]) ? arr[i] : min;
			max = (max < arr[i]) ? arr[i] : max;
		}

		int left = 0;
		int right = max - min;
		int result = right;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (isOk(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
		}
		System.out.println(result);
	}

	static boolean isOk(int num) {
		
		int min = 10000;
		int max = 0;
		int count = 1;
		for (int i = 0; i < N; i++) {
			
			min = (min > arr[i]) ? arr[i] : min;
			max = (max < arr[i]) ? arr[i] : max;
			if (max - min > num) {
				count++;
				min = arr[i];
				max = arr[i];
			}
		}
		
		return count <= M;
	}

}
