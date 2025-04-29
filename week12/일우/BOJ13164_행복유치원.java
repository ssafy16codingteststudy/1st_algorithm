package 일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ13164_행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] children = new int[N];
        int[] diff = new int[N - 1];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(input[i]);
            if (i > 0) {
                diff[i - 1] = children[i] - children[i - 1];
            }
        }

        Arrays.sort(diff);

        int ans = 0;
        for (int i = 0; i < (N - 1) - (K - 1); i++) {
            ans += diff[i];
        }

        System.out.println(ans);

        /*
        * 여기서 1과 2의 차이를 가지도록 설정하고, 2와 4 는 차이를 가지지 않도록 처리해야합니다.

        그러므로, 2 와 4 는 합연산에 들어가면 안된다는 의미입낟.

        즉 1 + 2로 답은 3 입니다.



        여기서 우리가 구한 차이의 개수들은 N - 1 개입니다. 위의 예시에서는 N = 4이므로 차이의 개수는 3개입니다. ( 2 2 1 4 )

        우리가 구하고자하는 그룹의 개수는 3개입니다. ( 문제조건에서 K = 3 이라고 나와있습니다. )

        1 2 2 4 가 있을때 3개의 그룹으로 나눌려면, 2개의 구분선이 있으면 가능합니다.

        예시로는, 1 3 ★ 5 6 ★ 10   (여기서 별이 구분선 입니다. )

        보시다시피, 3개의 그룹을 만들려면 2개의 구분선이 필요한걸 알 수 있습니다. 즉, K - 1 개의 구분선이 필요한 것입니다.



        문제에서 구분선을 구하기 위해서는 우리가 구한

        키 차이 오름차순 정렬
        1 2 2 4
        4 개 중에서 2개를 정하면, 그게 최소의 비용일 것 입니다. ( 남은 2개는 구분선이 되어서 더해지지 않습니다. )
        즉, 위의 4 길이의 차이배열에서 결국 최소값의 2개만 선택한다는 의미입니다.

        수학적으로 보면,

        키차이의 개수를 구하면, N - 1 개입니다. (5개의 입력값에서는 4개의 차이의 배열이 나옵니다.)

        구분선의 개수를 구하면, K - 1 개입니다. (3개의 그룹을 가지려면, 2개의 구분선으로 그룹을 나눕니다.)

        즉, (N - 1) - ( K - 1 ) 개. N - K 개의 차이배열만 더해주면 해당 값이 최소입니다.
* */
    }
}
