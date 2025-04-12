import java.io.*;

public class Main {
    static int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long[][] arr = {{1,1},{1,0}};
        long[][] result = powMatrix(arr, N);	//함수 실행
        System.out.println((result[1][0] % MOD));
    }

    static long[][] powMatrix(long[][] A, long N){
        if(N==1) return A;
        long[][] temp = powMatrix(A,N/2);
        return N%2==0? mulMatrix(temp,temp) : mulMatrix(mulMatrix(temp, temp), A);
    }

    public static long[][] mulMatrix(long[][] A, long[][] B){
        long[][] result = new long[2][2];
        for(int i=0;i<2;i++) {
            for(int j=0;j<2;j++) {
                for(int k=0;k<2;k++) {
                    result[i][j] += A[i][k] * B[k][j];
                    result[i][j] %= MOD;
                }
            }
        }
        return result;
    }
}