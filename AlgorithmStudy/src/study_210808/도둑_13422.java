package study_210808;

/*

도둑 출처
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	1568	418	344	27.542%
문제
다음 그림과 같이 N개의 집이 순서대로 이웃하여 세워진 마을이 있다.



위 그림은 N = 8인 경우 마을의 모습이다. 
위 그림과 같이 각각의 집은 순서대로 서로 이웃해 있으며, 첫 번째 집과 마지막 집 또한 이웃해 있다. 
예를들면 3이 적힌 집은 9, 4가 적힌 집과 이웃해 있으며, 5가 적힌 집은 6, 7이 적힌 집과 이웃해 있다. 
이 마을 사람들은 각자 자신의 집에 돈을 보관한다. 
위 그림에서 각 집에 적혀 있는 숫자는 집마다 보관 중인 돈의 금액을 나타낸다. 
어느 날 이 마을에 도둑이 들었다. 
도둑은 이 마을에서 어떻게 도둑질을 할까 잠시 고민하다가, 
빠르게 돈을 훔치고 달아나기 위해 M개의 연속된 집에서 돈을 훔치되, 
돈을 훔칠 때는 각 집에 보관중인 돈을 전부 훔치기로 했다. 
예를 들어 M = 3일 때, 위 그림에서 순서대로 3, 4, 7의 돈을 보관중인 집에서 총 14의 돈을 훔친 후에는 더 이상 돈을 훔치지 않고 달아나야 한다. 
또 다른 예로는 순서대로 5, 6, 4의 돈을 보관중인 집에서 총 15의 돈을 훔친 후에는 달아나야 한다. 
그러나 만약 도둑이 이 마을에서 총 K원 이상의 돈을 훔친다면 자동 방범장치가 작동하여 도둑은 바로 붙잡히게 된다. 
예를 들어 M = 3, K = 15인 경우 위 그림에서 순서대로 3, 4, 7의 돈을 보관중인 집에서 도둑질을 한다면 총 도둑질한 돈이 14가 되어 붙잡히지 않고 달아날 수 있지만, 
순서대로 5, 6, 4의 돈을 보관중인 집에서 돈을 훔친다면 총 도둑질한 돈은 15가 되고, 방범장치가 작동하여 도둑은 붙잡히게 된다.

마을을 이루고 있는 집의 개수 N, 도둑이 돈을 훔쳐야 할 연속된 집의 개수 M, 자동 방범장치가 작동하는 최소 돈의 양 K와 각 집에서 보관 중인 돈이 순서대로 주어질 때, 
도둑이 붙잡히지 않고 무사히 마을을 빠져나가기 위해 돈을 훔칠 M개의 연속된 집을 고르는 방법의 수를 출력하는 프로그램을 작성하시오.

입력
입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 구성된다. 
입력의 첫째 줄에 테스트 케이스의 개수를 나타내는 자연수 T가 주어진다. 
각각의 테스트 케이스의 첫째 줄에 마을에 있는 집의 개수 N(1 ≤ N ≤ 100,000), 
도둑이 돈을 훔칠 연속된 집의 개수 M(1 ≤ M ≤ N), 
자동 방범 장치가 작동하는 최소 돈의 양 K(1 ≤ K ≤ 1,000,000,000, K는 정수)가 공백으로 구분되어 주어진다. 
테스트 케이스의 둘째 줄에는 N개의 집에서 각각 보관중인 돈의 양이 시계방향 순서대로 공백으로 구분되어 주어진다. 
돈의 양은 각 집이 이웃해 있는 순서대로 주어진다. 
첫 번째 집과 마지막 집 또한 이웃하기 위해 입력의 첫 번째와 마지막이 서로 연결되어 있다고 가정하며, 
각 집에서 보관 중인 돈의 양은 1이상 10,000이하의 정수이다.

출력
출력은 표준 출력을 사용한다. 
입력 받은 데이터에 대해, 도둑이 자동 방범장치에 붙잡히지 않도록 연속된 M개의 집에서 돈을 훔치는 방법의 가짓수를 한 줄에 1개씩 출력한다.

예제 입력 1 
2
8 3 15
3 4 7 5 6 4 2 9
2 1 5
4 5
예제 출력 1 
3
1

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도둑_13422 {
	private static int T;
	private static int N;
	private static int M;
	private static int K;
	private static int[] money;
	private static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder result = new StringBuilder();
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			money = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				money[i] = Integer.parseInt(st.nextToken());
			}
			cnt = 0;
			stealMoney();
			result.append(cnt).append("\n");
		}
		
		System.out.println(result.toString().trim());
		
		br.close();
	}
	
	private static void stealMoney() {
		int start = 0;
		int end = M - 1;
		int stolenMoney = getFirstStealMoney();
		
		while (start < N) {
			if (stolenMoney < K) {
				cnt++;
			}
			
			stolenMoney -= money[start];
			
			start++;
			end = (end + 1) % N;
			
			stolenMoney += money[end];
		}
		
		if (N == M && cnt > 0) {
			cnt = 1;
		}
	}
	
	private static int getFirstStealMoney() {
		int sum = 0;
		for (int i = 0; i < M; i++) {
			sum += money[i];
		}
		
		return sum;
	}
}
