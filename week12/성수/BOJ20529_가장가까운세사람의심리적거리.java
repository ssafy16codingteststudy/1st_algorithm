import java.util.*;
import java.io.*;

//비둘기집 원리 : n+1 개의 물건을 n개의 상자에 넣으면 적어도 한 상자에는 2개 이상 물건이 들어 있다.
//k * n + 1 개의 물건을 n개의 상자에 넣으면 적어도 한 상자에는 k+1개의 물건이 들어 있을 것이다.
// -> 같은 물건이 최대한 없게 하려면 골고루 넣어야함 -> n 개의 상자에 하나씩, 하나씩 채워서 k * n 개를 다 넣으면 결국 한개는 중복이 될 수 밖에 없는 곳에 들어감.
//16*1+1명이 있으면 적어도 1+1명 이상은 mbti가 같을 것이다.
//16*2+1 명이 있으면 적어도 2+1명은 mbti가 같을 것이다.
//n >= 33 이면 3명이상이 무조건 같아서 심리적인 거리는 무조건 0;
//n <= 16 이면 3개의 조합으로 심리적 거리를 완탐
//16 < n <32 이면 무조건 같은 조합과 나머지 하나의 엠비티아이의 심리적 거리를 구함.

public class Main {

    static int N; //학생의 수(3~100,000)
    static String[] student;
    static int ans;
    static int[] selectIdx;
    static int AB; //심리적 거리 계산

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            ans = 22;
            selectIdx = new int[3];
            student = new String[N];
            st = new StringTokenizer(br.readLine());
            if(N < 33){
                for(int i=0; i<N; i++) {
                    student[i] = st.nextToken();
                }
                select(0, 0);
            } else {
                ans = 0;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
    public static void select(int idx, int cnt){
        if(ans == 0) return;

        if(cnt == 3){
            AB = 0;
            for(int i=0; i<3; i++){
                int n = selectIdx[i];
                int m = 0;
                for(int j=i+1; j<3; j++){
                    m = selectIdx[j];
                    for(int k=0; k<4; k++){
                        if(student[n].charAt(k) != student[m].charAt(k)){
                            AB += 1;
                        }
                    }
                }
            }
            ans = Math.min(ans, AB);
            return;
        }

        for(int i = idx; i<N; i++){
            selectIdx[cnt] = i;
            select(i+1, cnt+1);
        }
    }
}