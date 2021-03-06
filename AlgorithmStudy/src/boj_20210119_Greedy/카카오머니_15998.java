package boj_20210119_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*

카카오머니 스페셜 저지출처분류
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
5 초 (추가 시간 없음)	256 MB	2922	342	168	9.929%
문제
카카오페이는 카카오톡을 통해 송금, 결제 등을 할 수 있는 핀테크 서비스이다. 
카카오페이에는 원하는 만큼 현금을 충전하고 사용할 수 있는 카카오머니라는 서비스가 있다. 
무지는 오늘부터 현금을 간편하게 사용할 수 있는 카카오머니를 사용해 보기로 하였다. 
무지는 좀 더 편리하게 서비스를 이용하기 위해 잔액이 10100 원인 자신의 계좌와 카카오머니 계정을 연결하였다.

처음에 무지의 카카오머니 잔액은 0원이다. 
무지가 자신의 통장에서 잔액을 충전하거나 타인에게 송금을 받을 경우 카카오머니 잔액이 증가하며, 이러한 경우를 입금이라고 한다. 
또한, 무지가 카카오머니로 결제를 하거나 타인에게 송금을 할 경우 카카오머니 잔액이 감소하며, 이러한 경우를 출금이라고 한다. 
이 문제에서는 입금 또는 출금할 때 액수가 1원 단위여야 한다는 것 외의 별다른 제약이 없다고 가정하자. 
즉, 실제 카카오머니의 제약사항인 잔액 200만 원 이하, 송금은 1일에 50만 원 한도 등은 무시한다.

x 원이 입금될 경우, 무지의 카카오머니 잔액은 x 원만큼 증가한다. 
그러나, x 원을 출금할 때는 상황이 다르다. 만약 잔액이 x 원 이상이라면, 잔액에서 x 원을 차감하면 된다. 
그러나, 잔액이 x 원 미만이라면 카카오머니 내부에서 금액을 충당할 수 없기 때문에, 연결된 통장에서 돈을 가져올 필요가 있다. 
카카오는 이를 위해 최소 충전 단위 M 을 두어서, 잔액이 x 원 이상이 되기 전까지 M 원을 통장에서 가져오다가, 잔액이 x 원 이상이 되면 x 원을 잔액에서 차감한다. M 은 양의 정수이다.

예를 들어, M = 10,000 이고 무지의 잔액이 1,500원일 때, x = 17,000원을 출금하려는 상황을 가정하여 보자. 
무지의 잔액으로는 x = 17,000원을 만들 수 없기 때문에, 카카오머니는 우선 무지의 계좌에서 M = 10,000원을 가져와 잔액을 11,500원으로 만든다. 
그러나, 11,500원으로도 x = 17,000원을 만들 수 없기 때문에, 카카오머니는 무지의 계좌에서 또 M = 10,000원을 가져와 잔액을 21,500원으로 만든다. 
이제는 17,000원을 출금할 수 있으므로, 잔액에서 x = 17,000원을 차감한다. 최종적으로, 무지의 카카오머니 잔액은 21,500 - 17,000 = 4,500원이 된다.

카카오머니에 남는 입출금 내역과는 별개로, 무지는 카카오머니를 이용하기 시작할 때부터 자신만의 입출금 로그를 작성하였다. 
이 로그는 N 개의 두 정수 쌍 (ai, bi)로 이루어져 있으며, 시간 순서대로 저장되어 있다. 
무지는 꼼꼼하기 때문에 입금 또는 출금 내역을 로그에서 하나도 빠뜨리지 않았다고 생각한다. 각 쌍의 의미는 아래와 같다.

ai > 0이라면, 무지의 카카오머니에 ai 원이 입금되었다. 입금 결과, 잔액은 bi 원이었다.
ai < 0이라면, 무지의 카카오머니에서 -ai 원이 출금되었다. 출금 결과, 최종적으로 잔액은 bi 원이었다.
ai = 0인 경우는 없다.
위에 언급된 예시의 경우, 무지의 입출금 로그에 (-17,000, 4,500)이 추가되었을 것이다.

그러나 무지는 자신이 제대로 로그를 관리하고 있는지에 대한 걱정이 들기도 해서, 간단하게 로그에 모순이 없는지를 점검해 보고자 한다. 
무지가 생각한 방법은, 입출금 로그만 보고 유효한, 즉 로그에 모순이 생기지 않도록 하는 최소 충전 단위 M 이 존재하는지, 존재한다면 값이 얼마인지 확인하는 것이다. 
무지를 도와, 이 일을 대신해 줄 프로그램을 작성하라.

입력
첫 번째 줄에 무지의 입출금 로그에 있는 쌍의 수 N(1 ≤ N ≤ 300,000)이 주어진다.

다음 N 개 줄에는 무지가 작성한 입출금 로그가 주어진다. 
이 중 i(1 ≤ i ≤ N)번째 줄에는 두 개의 정수 ai 와 bi(-1018 ≤ ai ≤ 1018, ai ≠ 0, 0 ≤ bi ≤ 1018)가 공백 하나를 사이로 두고 주어진다.

출력
만약 유효한 최소 충전 단위 M(1 ≤ M ≤ 9 * 1018)이 존재한다면, 첫 번째 줄에 M 을 출력한다. 
가능한 값이 여러 가지 있다면, 그중 9 * 1018 이하인 것을 아무거나 하나 출력한다.

존재하지 않는다면 -1을 출력한다.

예제 입력 1 
5
1500 1500
-17000 4500
1200 5700
-5600 100
-200 9900
예제 출력 1 
10000

최소 충전 단위 M = 10,000일 때, 무지의 카카오머니 잔액은 아래와 같이 변한다.

처음에 잔액은 0원이다.
i = 1: 1,500원이 입금되어, 잔액이 1,500원이 되었다.
i = 2: 17,000원을 출금하기 위해, 문제 본문에 설명된 과정을 거쳐 잔액이 4,500원이 되었다.
i = 3: 1,200원이 입금되어, 잔액이 5,700원이 되었다.
i = 4: 5,600원이 출금되어, 잔액이 100원이 되었다.
i = 5: 200원을 출금하려고 한다. 잔액이 200원보다 적으므로, 카카오머니는 무지의 통장에서 M = 10,000원을 가져와 잔액을 10,100원으로 만든다. 
	      이제 잔액이 200원 이상이기 때문에, 잔액에서 200원을 차감하여 최종 잔액은 9,900원이 되었다.
	      
예제 입력 2 
2
-5 0
-6 1
예제 출력 2 
-1
입출금 로그를 모두 만족시키는 최소 출금 단위 M이 존재하지 않는다. 그 이유는,

i = 1: 잔액이 0원인 상황에서, 5원을 출금하려는 상황이다. 최종 잔액이 0원이 되도록 하는 M 은 1원 또는 5원이다.
i = 2: 잔액이 0원인 상황에서, 6원을 출금하려는 상황이다. M = 1원이었다면, 6원을 출금한 이후 최종 잔액이 0원이어야 한다. 
	   M = 5원이었다면, 6원을 출금한 이후 최종 잔액이 4원이어야 한다. 
	      두 경우 모두 최종 잔액이 b2 = 1원이 아니므로, 입출금 로그를 만족시키지 않는다.

*/

//# 요약
//1. M은 출금 했을 경우 남은 돈 보다는 크다 -> 충전을 하고 뺀 남은 돈이니까
//2. 출금할 금액이 남은 금액보다 클 경우
// 출금할 금액 - 남은 금액 + 출금 후 남은 금액 = M의 최대 값
//즉, M의 최대 값의 약수들 중 1번의 조건을 만족하는 최소 값이 답
//M의 약수들인 것은 돈이 부족할 때 최소 충전 단위인 M을 계속 더해주기 때문에

//답을 유추하기 위한 순서
//1) M의 최대값을 구한다음 약수들을 구한다. 
//2) 그 약수들이 출금 후 남은 금액보다 큰 수들을 뽑는다.
//3) 뽑은 수들을 정상적으로 입출력 로그를 돌렸을 떄 값이 제대로 나온다면 그것이 답

//-> 시간 초과
//-------------------------------------------------------

// 최대공약수를 이용해야한다.

public class 카카오머니_15998 {
	private static int N;
	private static long max = -1;
	private static boolean flag = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		long a = 0;
		long b = 0;
		long preB = 0;
		long M = 0;
		long gcd = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			
			// 출금해야하는 상황이면서 충전해야하는 상황
			if(a < 0 && preB + a < 0) {
				M = b - a - preB;
				// 최대공약수를 구한다. (유클리드 호제법)
				gcd = gcd(M, gcd);
				// 최소 충전 금액이 남은 금액보다 적으면 안되므로
				// max에 남은 금액의 최대값을 넣어준다.
				max = Math.max(max, b);
			
			// 계산이 맞지 않을 떄
			}else if(preB + a != b) {
				gcd = -1;
				break;
			}
			preB = b;
			
		}
		
		// 최대공약수가 없다면 금액이 딱 떨어지는 것
		// 즉, 1이 아니라 무슨 자연수가 오더라도 된다.
		if(gcd == 0) {
			gcd = 1;
		}
		
		// 최소 충전 금액이 남은 금액보다 크다면
		if(gcd > max) {
			System.out.println(gcd);
		}else {
			System.out.println(-1);
		}
		
		
		br.close();
	}
	
	// 유클리드 호제법
	private static long gcd(long a, long b) {
		if(b == 0) return a;
		else return gcd(b, a%b);
	}
	
	
}
