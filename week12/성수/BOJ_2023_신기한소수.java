package week12.성수;

import java.util.*;
import java.io.*;

public class BOJ_2023_신기한소수 {
    static int N; //자리수 (1~8)
    static int[] prime1 = {2, 3, 5, 7}; //한자리 수 소수
    static int[] lastNum = {1, 3, 7, 9}; //두자리수 이상의 소수는 마지막에 1, 3, 7, 9 만 올 수 있음.
    static List<Integer> primeNum; //제곱근 num 까지의 소수를 미리 구해두기
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        if(N == 1) for(int i=0; i<4; i++) sb.append(prime1[i]).append("\n");
        else {
            makePrimeNum(N);
            for(int i=0; i<4; i++) findPrimeNum(1, prime1[i]);
        }
        System.out.print(sb);
        br.close();
    }
    public static void findPrimeNum(int cnt, int num){
        if(cnt == N && isPrime(num)){
            sb.append(num).append("\n");
            return;
        }

        if(isPrime(num)){
            for(int last : lastNum){
                findPrimeNum(cnt + 1, num * 10 + last);
            }
        }
    }
    public static boolean isPrime(int num){
        for(int p : primeNum){
            if(p*p > num) break;
            if(num % p == 0) return false;
        }
        return true;
    }

    public static void makePrimeNum(int n){
        primeNum = new ArrayList<>();
        int max = (int)(Math.pow(10, n+1)-1);
        int limit = (int)(Math.sqrt(max));
        boolean[] eratosthenes = new boolean[limit+1]; //작은 소수부터 배수를 지워나가기, 제곱근 max 까지만 해도 됨 -> max 이전의 숫자들의 배수들은 이미 제거됨 그래서
         //true가 제거된 거  //limit 까지 왔으면 배수가 자기 자신과의 곱밖에 없고 그 보다 큰 숫자들의 배수는 max 보다 커지기 때문에 의미 없음

        for(int i=2; i*i<=limit; i++){
            if(!eratosthenes[i]){ //제거된 숫자가 아닐 때
                for(int j=i*i; j<=limit; j+=i){
                    eratosthenes[j] = true; //p의 배수 제거 -> 제곱부터 시작하는 이유 p보다 작은 수들의 배수는 이미 처리함
                }
            }
        }
        for(int i=2; i<=limit; i++){
            if(!eratosthenes[i]) primeNum.add(i);
        }
    }
}
