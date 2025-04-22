package April_week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_25947_선물할인 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 모든 수를 오름차순 정렬
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < n; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		long curCost = 0;
		int count = 0;
		int halfCount = 0;
		PriorityQueue<Integer> nonhalfList = new PriorityQueue<>(Collections.reverseOrder()); //할인 받지 않은 제품을 내림차순 정렬
		PriorityQueue<Integer> halfList = new PriorityQueue<>();	//할인 받은 제품을 오름차순 정렬
		//1. 가장 낮은 가격의 제품부터 구매 리스트에 넣음
		//2. 이번 순서의 제품을 구매하게 될 경우 예산을 오버하게 된다면
		//2-1 할인 쿠폰이 남아 있지 않다면
		//2-1-1 할인 쿠폰이 없는데 할인 받은 제품도 없다면 -> 애초에 쿠폰이 0개라는 소리 => 종료
		//2-1-2 할인 받은 제품이 있다면 -> 할인 받은 제품 중 가장 싼 제품의 할인을 취소 & 현재 제품에 할인 적용
		//		=> 예산보다 작다면 진행 or 아니라면 더이상 구매 못함(현재 제품 뒤의 것들은 현재 제품보다 가격이 더 비싸기 떄문)
		//2-2 할인 쿠폰이 남아 있다면
		//2-1-1 현재 제품을 할인 받았을 때, 예산 안에서 구매 가능한지 확인
		//2-1-1-1 구매 가능하면 할인 쿠폰을 하나 사용해서 구매
		//2-1-1-2 불가능하면 할인 받지 않고 구매한 제품중에 가장 비싼 제품에 할인 적용 -> 현재 제품에 대해 1부터 다시 시작
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			if(curCost + cur <= b) {
				curCost += cur;
				count++;
				nonhalfList.add(cur);
			}else {
				if(halfCount == a) {
					if(halfList.isEmpty()) {
						break;
					}
					int top = halfList.poll();
					int half = cur / 2;
					long newCost = curCost + top/2 + half;
					if(newCost <= b) {
						curCost = newCost;
						halfList.add(cur);
						nonhalfList.add(top);
						count++;
					}else {
						break;
					}
				}else {
					int half = cur / 2;
					if(curCost + half <= b) {
						curCost += half;
						count++;
						halfCount++;
						halfList.add(cur);
					}else {
						if(nonhalfList.isEmpty()) {
							break;
						}
						int top = nonhalfList.poll();
						curCost -= top/2;
						pq.add(cur);
						halfCount++;
						halfList.add(top);
					}
				}
			}
		}
		System.out.println(count);
		bf.close();
		
	}
	
	
}
