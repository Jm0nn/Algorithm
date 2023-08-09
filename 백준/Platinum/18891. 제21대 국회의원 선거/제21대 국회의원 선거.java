import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 국회의원선거에서 얻은 의석수를 기준으로 내림차순으로 정렬해 정당을 출력하는 문제
public class Main {

	static final int NATIONAL_ASSEMBLY_MEMBER = 300; // 의원정수
	static final int CONSTITUENCY_MEMBER = 253; // 지역구국회의원
	static final int PROPORTIONAL_REPRESENTATION = 47; // 비례대표국회의원

	// 정당 클래스
	static class Party implements Comparable<Party> {
		int i; // 기호
		String name; // 정당명
		int r; // 지역구의석수
		int vote; // 비례대표국회의원선거 득표수

		int pr; // 비례대표의석수
		int s; // 연동배분의석수
		double p; // 의석할당정당 간 비례대표국회의원선거 득표비율
		boolean isAllocated; // 의석할당정당인지?

		double s_cal; // 연동배분의석수 계산용

		public Party(int i, String name, int r, int vote) {
			this.i = i;
			this.name = name;
			this.r = r;
			this.vote = vote;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(this.name).append(' ').append(this.r + this.pr);
			return sb.toString();
		}

		// 의석수 기준으로 내림차순으로 정렬 (의석수 = 지역구 + 비례대표)
		// 의석 수가 같을 경우 정당명이 사전 순으로 앞서는 순으로 정렬
		@Override
		public int compareTo(Party o) {
			int result = (o.r + o.pr) - (this.r + this.pr);
			if (result != 0)
				return result;
			else
				return this.name.compareTo(o.name);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken()); // 정당 수
		int V = Integer.parseInt(st.nextToken()); // 총 투표자 수
		Party[] party = new Party[P]; // 정당 배열

		int allocated = 0; // 의석할당정당의 지역구 당선인 수
		int voteAlloc = 0; // 모든 의석할당정당의 득표수

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());

			// 정당 기호, 정당명, 지역구 의석 수, 비례대표국회의원선거 득표수 입력
			party[i] = new Party(i + 1, st.nextToken(), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));

			// 지역구국회의원선거에서 5석 이상의 의석을 차지했거나, 비례대표국회의원선거에서 유효투표총수의 3% 이상을 득표했을 경우 의석할당정당으로 지정
			if (party[i].r >= 5 || 1.0 * party[i].vote / V >= 0.03) {
				party[i].isAllocated = true; // 의석할당정당 지정
				allocated += party[i].r; // 의석할당정당 지역구 당선인 수 계산
				voteAlloc += party[i].vote; // 의석할당정당 득표수 계산
			}
		}

		// 의석할당정당이 아닌 정당의 지역구 당선인 수 총합 + 무소속 지역구 당선인 수
		int notAlloc = CONSTITUENCY_MEMBER - allocated;

		int sSum = 0; // 연동배분의석수 합

		for (int i = 0; i < P; i++) {
			// 의석할당정당이 아닌 경우 넘어감
			if (!party[i].isAllocated)
				continue;

			// 의석할당정당의 비례대표국회의원선거 득표비율 계산
			party[i].p = 1.0 * party[i].vote / voteAlloc;

			// 정당별 연동배분의석수 산정
			double tmp = (((NATIONAL_ASSEMBLY_MEMBER - notAlloc) * party[i].p) - party[i].r) * 0.5;

			if (tmp < 1) // 결과값이 1보다 작을 경우 0
				party[i].s = 0;
			else // 소수점 첫째 자리에서 반올림
				party[i].s = (int) Math.round(tmp);

			sSum += party[i].s; // 연동배분의석수 합 계산
		}

		if (sSum < 30) { // 계산된 연동배분의석수가 30석보다 작을 경우
			for (int i = 0; i < P; i++) {
				// 의석할당정당이 아닌 경우 넘어감
				if (!party[i].isAllocated)
					continue;

				party[i].s_cal = party[i].s + (30 - sSum) * party[i].p;
			}

			int nSum = 0;

			for (int i = 0; i < P; i++) {
				// 의석할당정당이 아닌 경우 넘어감
				if (!party[i].isAllocated)
					continue;

				// 계산된 값의 정수부분을 먼저 배분
				party[i].s = (int) Math.floor(party[i].s_cal);
				nSum += party[i].s;
				party[i].s_cal -= party[i].s;
			}

			// 연등배분의석수 계산값의 소수점에 따라 내림차순으로 정렬
			Arrays.sort(party, new Comparator<Party>() {

				@Override
				public int compare(Party o1, Party o2) {
					double result = o2.s_cal - o1.s_cal;

					if (result > 0)
						return 1;
					else if (result < 0)
						return -1;
					else
						return o1.i - o2.i;
				}

			});

			int idx = 0; // 정당 인덱스
			// 총 연동배분의석수가 30석이 될 때까지 소수점 이하의 수가 큰 순서대로 배분
			while (nSum < 30) {
				if (!party[idx].isAllocated) {
					idx = (idx + 1) % P;
					continue;
				}

				party[idx].s++;
				nSum++;
				idx = (idx + 1) % P;
			}

		} else if (sSum > 30) { // 계산된 연동배분의석수가 30석보다 클 경우
			for (int i = 0; i < P; i++) {
				// 의석할당정당이 아닌 경우 넘어감
				if (!party[i].isAllocated)
					continue;

				party[i].s_cal = 30.0 * party[i].s / sSum;
			}

			int nSum = 0;

			for (int i = 0; i < P; i++) {
				// 의석할당정당이 아닌 경우 넘어감
				if (!party[i].isAllocated)
					continue;

				// 계산된 값의 정수부분을 먼저 배분
				party[i].s = (int) Math.floor(party[i].s_cal);
				nSum += party[i].s;
				party[i].s_cal -= party[i].s;
			}

			// 연등배분의석수 계산값의 소수점에 따라 내림차순으로 정렬
			Arrays.sort(party, new Comparator<Party>() {

				@Override
				public int compare(Party o1, Party o2) {
					double result = o2.s_cal - o1.s_cal;

					if (result > 0)
						return 1;
					else if (result < 0)
						return -1;
					else
						return o1.i - o2.i;
				}

			});

			int idx = 0; // 정당 인덱스
			// 총 연동배분의석수가 30석이 될 때까지 소수점 이하의 수가 큰 순서대로 배분
			while (nSum < 30) {
				if (!party[idx].isAllocated) {
					idx = (idx + 1) % P;
					continue;
				}

				party[idx].s++;
				nSum++;
				idx = (idx + 1) % P;
			}
		}

		int remain = 0; // 나머지 17석

		for (int i = 0; i < P; i++) {
			if (!party[i].isAllocated)
				continue;

			party[i].s_cal = 17 * party[i].p;

			// 계산 값의 정수 부분 먼저 배분
			party[i].s += Math.floor(party[i].s_cal);
			remain += Math.floor(party[i].s_cal);
			party[i].s_cal -= Math.floor(party[i].s_cal);
		}

		// 연등배분의석수 계산값의 소수점에 따라 내림차순으로 정렬
		Arrays.sort(party, new Comparator<Party>() {

			@Override
			public int compare(Party o1, Party o2) {
				double result = o2.s_cal - o1.s_cal;

				if (result > 0)
					return 1;
				else if (result < 0)
					return -1;
				else
					return o1.i - o2.i;
			}

		});

		int idx = 0; // 정당 인덱스
		// 나머지 연동배분의석수가 17이 될 때까지 소수점 이하의 수가 큰 순서대로 배분
		while (remain < 17) {
			if (!party[idx].isAllocated) {
				idx = (idx + 1) % P;
				continue;
			}

			party[idx].s++;
			remain++;
			idx = (idx + 1) % P;
		}

		// 비례대표의석수 할당
		for (int i = 0; i < P; i++)
			party[i].pr = party[i].s;

		Arrays.sort(party); // 총 의석수를 기준으로 내림차순 정렬

		// 출력
		for (int i = 0; i < P; i++)
			sb.append(party[i]).append('\n');

		System.out.println(sb);

		br.close();
	}
}