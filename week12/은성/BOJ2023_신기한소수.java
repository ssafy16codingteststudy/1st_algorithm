package week12.은성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023_신기한소수 {

    private static int n;
    private static boolean[] arr;
    private static StringBuilder sb = new StringBuilder();
    // 2023_신기한소수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

//        // 에라토스테네스의 체로 소수 구하고
//        arr = new boolean[(int) Math.pow(10, n)];
//        arr[1] = true;
//        for (int i = 2; i < arr.length; i++) {
//            if (arr[i]) {
//                continue;
//            }
//
//            for (int j = i * 2; j <arr.length; j += i) {
//                arr[j] = true;
//            }
//        }

        // 백트래킹으로 자릿수 높여가며 판별
        backTracking(0, 0);
        System.out.println(sb);
    }

    private static void backTracking(int length, int value) {
        if (length == n) {
            sb.append(value).append("\n");
            return;
        }

        for (int i = 1; i < 10; i++) {
            // 메모리 요구량이 매우 작으므로, 그냥 소수 판별
            if(!isPrime(value * 10 + i)) {
                continue;
            }

            backTracking(length + 1, value * 10 + i);
        }
    }

    private static boolean isPrime(int value) {
        if (value == 1) {
            return false;
        }

        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}