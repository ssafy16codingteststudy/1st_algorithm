import java.io.*;

public class BOJ_2023 {
	
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[] singleDigit = {2, 3, 5, 7};
		for(int nxt : singleDigit) {
			dfs(nxt, 1);	// 현재 숫자, 자릿수
		}
	}
	
	static void dfs(int num, int length) {
		if(length == n) {
			System.out.println(num);
			return;
		}
		
		for(int i = 1; i <= 9; i += 2) {	// 짝수는 모두 스킵
			int next = num * 10 + i;
			if(isPrime(next)) {
				dfs(next, length + 1);
			}
		}
	}

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
