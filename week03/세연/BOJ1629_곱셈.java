import java.io.*;
import java.util.*;

public class Main {
    static long A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.print(dnc(B));
    }
    static long dnc(long idx) {
        if (idx == 1) return A%C; // 재귀 탈출 조건
        long tmp = dnc(idx/2);
//        return ((idx%2==0? 1:A%C) * (tmp * tmp % C)) % C;
        return ((idx%2==0? 1:A) * (long)(Math.pow(dnc(idx/2),2))% C) % C;

        // 틀리는 케이스
        // return ((idx%2==0? 1:A) * tmp * tmp) % C;
        // return ((idx%2==0? 1:A) * tmp * tmp % C) % C;
        // return ((idx%2==0? 1:A) * (long)(Math.pow(dnc(idx/2),2))% C) % C;
        // 타입 오버플로우
        // 시간초과 케이스
        // return dnc(idx/2) * dnc(idx- idx/2) % C;
    }
}
