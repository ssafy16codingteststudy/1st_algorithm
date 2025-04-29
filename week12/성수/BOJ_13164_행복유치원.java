import java.io.*;
import java.util.*;
//1 3 5 6 10
//1 3/ 5 6 10
//1 3/ 5 6/ 10

// 2 2 1 4  차이가 큰 거 부터 k-1 개를 자르면 된다
// 2개의 차이가 가장 크다는 것은 그 두개를 포함한 그룹은 그거의 비용 이상이 들기 때문.
public class BOJ_13164_행복유치원 {

    static int N, K; //원생 수(1~300,000), 나눌 그룹 수
    static int[] height; //원생들 키(1~1,000,000,000)
    static int[] diff; //연속된 원생들의 키차이
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        height = new int[N];
        diff = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<N; i++){
            diff[i] = height[i] - height[i-1];
        }
        Arrays.sort(diff);
        for(int i=1; i<=N-K; i++){ //차이가 커서 잘린 부분(여기는 비용 0)을 제외한 차이들의 합
            //헷갈렸던 것은 (1 2 3 4) 이런식으로 잘리고 남은 부분은 원소가 3개 이상인데
            //2개짜리 차이의 합으로 구해도 되나싶었다.
            //하지만 1 2 차이, 2 3 차이, 3 4 차이의 합이 1 4의 차이와 같기 때문에 문제 없다.
            ans += diff[i];
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
