import java.io.*;
import java.util.*;

public class Main {
    static Map<Long, Long> dp = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());
        long Q = Long.parseLong(st.nextToken());
        dp.put(0L,1L);
        System.out.println(getVal(N,P,Q));
    }
    static long getVal(long idx, long P, long Q){
        if (!dp.containsKey(idx)) { // DP에 값이 없을 경우 재귀적으로 해당 DP 값 구하기
            dp.put(idx, getVal(idx/P,P,Q) + getVal(idx/Q,P,Q));
        }
        return dp.get(idx);

    }
}