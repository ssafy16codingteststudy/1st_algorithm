package April_week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2470_두용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(bf.readLine());
		List<Integer> plusList = new ArrayList();
		List<Integer> minusList = new ArrayList();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num >= 0) {
				plusList.add(num);
			}else {
				minusList.add(num);
			}
		}
		Collections.sort(plusList);
		Collections.sort(minusList, Collections.reverseOrder());
		int num1 = 0;
		int num2 = 0;
		int sum = 0;
		int newNum1 = 0;
		int newNum2 = 0;
		int newSum = 0;
		if(plusList.size() > 1) {
			num1 = plusList.get(0);
			num2 = plusList.get(1);
			sum = num1 + num2;
			if(minusList.size() > 1) {
				newNum1 = minusList.get(0);
				newNum2 = minusList.get(1);
				newSum = Math.abs(newNum1 + newNum2);
				if(Math.abs(sum) > Math.abs(newSum)) {
					sum = newSum;
					num1 = newNum1;
					num2 = newNum2;
				}
			}
		}else if(minusList.size() > 1){
			num1 = minusList.get(0);
			num2 = minusList.get(1);
			sum = num1 + num2;
		}
		
		
		int plusIdx = 0;
		int minusIdx = 0;
		int plusSize = plusList.size();
		int minusSize = minusList.size();
		while(plusIdx < plusSize && minusIdx < minusSize) {
			newNum1 = plusList.get(plusIdx);
			newNum2 = minusList.get(minusIdx);
			newSum = newNum1 + newNum2;
			if(Math.abs(sum) > Math.abs(newSum)) {
				sum = newSum;
				num1 = newNum1;
				num2 = newNum2;
			}
			if(newSum >= 0) {
				minusIdx++;
			}else {
				plusIdx++;
			}
		}
		if(num1 > num2) {
			System.out.println(num2 + " " + num1);
		}else {
			System.out.println(num1 + " " + num2);
		}

		
		bf.close();
	}
}
