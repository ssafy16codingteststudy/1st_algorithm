package 일우;

import java.util.Scanner;

public class BOJ2023 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dfs(0, n);
    }

    // 자릿수 깊이 탐색 num 숫자, n 자릿수
    static void dfs(int num, int n) {
        if(n==0) {
            System.out.println(num);
        }
        for (int i = 1; i < 10; i++) {
            int tmp = 10 * num + i; // 10 * num(체크해온 소수) + 1~9
            //7331 을 예시로 들면
            // 0 + 1~9
            // 7 + 1~9
            // 73 + 1~9
            // 733 + 1~9
            // 7331 n==0이 되니까 탈출
            if(n>0 && isPrime(tmp)) {
                dfs(tmp, n-1); //해당 숫자의 체크해야하는 자릿수를 감소시키면서 재귀호출
            }
        }
    }

    // 소수 판별
    static boolean isPrime(int num) {
        if(num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
