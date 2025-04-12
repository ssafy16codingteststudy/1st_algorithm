import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Main {
    public static void main(String[] args) throws IOException {
        // 코드 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();

        HashMap <Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
        String [] A = str.split(" ");
        int [] a = new int[N];

        // 키 : 값 / 벨류 : 인덱스들 어레이 리스트
        for (int i = 0; i < N; i++){
            a[i] = Integer.parseInt(A[i]);
            if (!hm.containsKey(a[i])){
                hm.put(a[i], new ArrayList<>(Arrays.asList(i)));
                
            } else {
                hm.get(a[i]).add(i);
            }
        }

        // 각 인덱스별 모두 합한 값
        Long [] result = new Long[N];
        result[0] = Long.valueOf(a[0]);
        for (int i = 1; i < N; i++) {
            result[i] = result[i - 1] + Long.valueOf(a[i]);
        }

        // 정답으로 제출할 것들
        Long Max = 0L;
        int count = 0;

        // 해쉬맵 돌면서 
        for (int n : hm.keySet()){
            Long check = 0L;
            // 해당 키에 대한 a[i]의 첫번째 인덱스 번호
            int start = hm.get(n).get(0);
            // 해당 키에 대한 a[i]의 마지막 인덱스 번호
            int end = hm.get(n).get(hm.get(n).size()-1);

            // 첫번째랑 마지막이 같다 == 하나 밖에 없다
            if (start == end) {
                check = Long.valueOf(a[start]); 
            }

            // 값이 2개 이상 들어있을 때
            else {
                // start가 0이면 그냥 end에 해당하는 result값
                if (start == 0) check = result[end];
                else {
                    // start가 1 이상이면 end에 해당하는 result값 - start 전 값
                    check = result[end] - result[start - 1];
                }
            }

            if (Max.equals(check)) count++;
            else if (Max < check) {
                Max = check;
                count = 1;
            }
        }
        System.out.println(Max + " " + count);
    }
}