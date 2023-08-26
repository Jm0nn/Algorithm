import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

// BOJ 대회의 스코어보드를 출력하는 문제
public class Main {

	// 문제 클래스
	static class Problem implements Comparable<Problem> {
		int id; // 번호
		int order; // 순서
		long pscore; // 배점

		Problem(int id, int order, long pscore) {
			this.id = id;
			this.order = order;
			this.pscore = pscore;
		}

		@Override
		public int compareTo(Problem o) {
			return this.order - o.order;
		}
	}

	// 문제 리스트 클래스
	static class ProblemList {
		Problem[] problem; // 문제 리스트
		int size; // 문제 수

		ProblemList(int size) {
			this.size = size;

			problem = new Problem[size];
		}

		int indexOf(int id) {
			for (int i = 0; i < size; i++) {
				if (problem[i].id == id)
					return i;
			}
			return -1;
		}

		Problem getProblem(int id) {
			return problem[indexOf(id)];
		}

		void sort() {
			Arrays.sort(problem);
		}
	}

	// 유저 클래스
	static class User implements Comparable<User> {
		String uid; // 아이디

		long totalPoint; // 총 획득 점수
		long totalPenalty; // 총 획득 페널티

		// 각 문제당 정보
		int[] submitCnt; // 제출 횟수
		int[] trialCnt; // 좋은 제출까지 시도 횟수
		long[] point; // 점수
		long[] penalty1; // 페널티1
		long[] penalty2; // 페널티2
		boolean[] goodSubmit; // 좋은 제출 유무

		int lastGoodSubmitId; // 마지막 좋은 제출
		int lastSubmitId; // 마지막 제출

		User(String uid, int probCnt) {
			this.uid = uid;

			submitCnt = new int[probCnt];
			trialCnt = new int[probCnt];
			point = new long[probCnt];
			penalty1 = new long[probCnt];
			penalty2 = new long[probCnt];
			goodSubmit = new boolean[probCnt];
		}

		// 우선 순위
		// 1. 획득한 점수가 높은 순
		// 2. 페널티가 적은 순
		// 3. 마지막으로 맞은 제출한 번호가 작은 순
		// 4. 마지막으로 제출한 제출 번호가 작은 순
		// 5. 유저의 아이디가 사전 순으로 앞서는 순
		@Override
		public int compareTo(User o) {
			return this.totalPoint != o.totalPoint ? Long.compare(o.totalPoint, this.totalPoint)
					: this.totalPenalty != o.totalPenalty ? Long.compare(this.totalPenalty, o.totalPenalty)
							: this.lastGoodSubmitId != o.lastGoodSubmitId
									? Integer.compare(this.lastGoodSubmitId, o.lastGoodSubmitId)
									: this.lastSubmitId != o.lastSubmitId
											? Integer.compare(this.lastSubmitId, o.lastSubmitId)
											: this.uid.compareTo(o.uid);
		}
	}

	// 유저 리스트 클래스
	static class UserList {
		User[] user; // 유저 리스트
		int size; // 유저 수

		UserList(int size) {
			this.size = size;

			user = new User[size];
		}

		boolean contains(String name) {
			return indexOf(name) != -1 ? true : false;
		}

		int indexOf(String name) {
			for (int i = 0; i < size; i++) {
				if (user[i].uid.equals(name))
					return i;
			}
			return -1;
		}

		User getUser(String name) {
			return user[indexOf(name)];
		}

		void sort() {
			Arrays.sort(user);
		}
	}

	// 대회 정보
	static long penalty; // 대회의 페널티
	static String start; // 대회 시작 시간
	static int last; // 마지막 페널티
	static int ce; // 컴파일 에러 제외 여부 (제외 1, 아니면 0) (1이면 컴파일 에러 제출은 없는 제출)
	static int cscore; // 점수 대회 여부 (점수 대회 1, 아니면 0) (0이면 문제의 배점 무시)
	static int format; // 페널티 포맷 (분 0, 시:분 1) (1이면서 분이 1자리면 0을 채워 2자리로 만듦)

	static int N, M, S; // 문제 수, 유저 수, 제출 수
	static ProblemList problemList; // 문제 리스트
	static UserList userList; // 유저 리스트

	// 제출 정보
	static int sid; // 제출 번호
	static int pid; // 문제 번호
	static String uid; // 유저 아이디
	static int result; // 채점 결과
	static int presult; // 부분 성공 여부
	static long score; // 점수
	static String date; // 제출 시간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 대회 정보 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		penalty = Long.parseLong(st.nextToken());
		start = st.nextToken() + " " + st.nextToken();
		last = Integer.parseInt(st.nextToken());
		ce = Integer.parseInt(st.nextToken());
		cscore = Integer.parseInt(st.nextToken());
		format = Integer.parseInt(st.nextToken());

		// 문제 수 입력
		N = Integer.parseInt(br.readLine());
		problemList = new ProblemList(N);

		// 문제 값 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int id = Integer.parseInt(st.nextToken());
			int order = Integer.parseInt(st.nextToken());
			long pscore = Long.parseLong(st.nextToken());

			problemList.problem[i] = new Problem(id, order, pscore);
		}

		problemList.sort(); // 문제 순서의 오름차순으로 정렬

		// 유저 수 입력
		M = Integer.parseInt(br.readLine());
		userList = new UserList(M);

		// 유저 아이디 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			userList.user[i] = new User(st.nextToken(), N);

		// 제출 수 입력
		S = Integer.parseInt(br.readLine());

		// 제출 정보 입력
		for (int s = 0; s < S; s++) {
			st = new StringTokenizer(br.readLine());
			sid = Integer.parseInt(st.nextToken());
			pid = Integer.parseInt(st.nextToken());
			uid = st.nextToken();
			result = Integer.parseInt(st.nextToken());
			presult = Integer.parseInt(st.nextToken());
			score = Long.parseLong(st.nextToken());
			date = st.nextToken() + " " + st.nextToken();

			// 유저 아이디가 없거나 result가 13이면 유효하지 않은 제출
			if (!userList.contains(uid) || result == 13)
				continue;

			// 컴파일 에러를 제출에서 제외할 때 현재 제출이 컴파일 에러라면 유효하지 않은 제출
			if (ce == 1 && result == 11)
				continue;

			Problem curProblem = problemList.getProblem(pid); // 현재 제출한 문제
			User curUser = userList.getUser(uid); // 현재 제출한 유저
			int idx = problemList.indexOf(pid); // 현재 제출한 문제의 인덱스

			curUser.submitCnt[idx]++; // 제출 횟수 증가
			curUser.lastSubmitId = sid; // 최근에 제출한 제출 번호 갱신

			// 성공 또는 부분 성공인 경우
			if (result == 4) {
				curUser.lastGoodSubmitId = sid; // 마지막으로 맞은 제출 번호 갱신

				// 일반 대회일 경우
				if (cscore == 0) {

					// 성공한 문제이며 한번도 성공한 적 없는 문제일 경우
					if (presult == 0 && !curUser.goodSubmit[idx]) {
						curUser.goodSubmit[idx] = true; // 좋은 제출 유무 갱신
						curUser.trialCnt[idx] = curUser.submitCnt[idx]; // 시도 횟수 갱신
						curUser.point[idx] = 1; // 점수 획득
						curUser.penalty1[idx] = (curUser.submitCnt[idx] - 1) * penalty; // 페널티1
						curUser.penalty2[idx] = passedTime(); // 페널티2
					}
				}

				// 점수 대회일 경우
				else if (cscore == 1) {

					// 일반 문제이면서 성공한 문제이며 한번도 성공한 적 없는 문제일 경우
					if (score == 0 && presult == 0 && !curUser.goodSubmit[idx]) {
						curUser.goodSubmit[idx] = true; // 좋은 제출 유무 갱신
						curUser.trialCnt[idx] = curUser.submitCnt[idx]; // 시도 횟수 갱신
						curUser.point[idx] = curProblem.pscore; // 점수 획득
						curUser.penalty1[idx] = (curUser.submitCnt[idx] - 1) * penalty; // 페널티1
						curUser.penalty2[idx] = passedTime(); // 페널티2
					}

					// 점수 문제이며 기존 점수를 갱신할 경우
					else if (curUser.point[idx] < score) {
						curUser.goodSubmit[idx] = true; // 좋은 제출 유무 갱신
						curUser.trialCnt[idx] = curUser.submitCnt[idx]; // 시도 횟수 갱신
						curUser.point[idx] = score; // 최고점 갱신
						curUser.penalty1[idx] = (curUser.submitCnt[idx] - 1) * penalty; // 페널티1
						curUser.penalty2[idx] = passedTime(); // 페널티2
					}
				}
			}
		}

		// 유저들의 획득한 점수, 패널티 계산
		for (int i = 0; i < M; i++) {
			User curUser = userList.user[i];

			long max = 0; // 페널티2의 최댓값

			for (int j = 0; j < N; j++) {
				curUser.totalPoint += curUser.point[j];
				curUser.totalPenalty += curUser.penalty1[j];

				// 마지막 페널티를 사용하지 않는 경우
				if (last == 0)
					curUser.totalPenalty += curUser.penalty2[j];

				// 마지막 페널티를 사용하는 경우
				else if (last == 1 && max < curUser.penalty2[j])
					max = curUser.penalty2[j];
			}

			// 마지막 페널티를 사용하는 경우 페널티2의 최댓값만 사용
			if (last == 1)
				curUser.totalPenalty += max;
		}

		userList.sort(); // 유저 리스트 정렬

		long curPoint = Long.MAX_VALUE;
		long curPenalty = Long.MAX_VALUE;
		int cnt = 0;
		int rank = 0;

		// 결과 출력
		for (int i = 0; i < M; i++) {
			User curUser = userList.user[i];

			if (curPoint > curUser.totalPoint) {
				curPoint = curUser.totalPoint;
				curPenalty = curUser.totalPenalty;
				rank = ++cnt;
			} else if (curPoint == curUser.totalPoint && curPenalty < curUser.totalPenalty) {
				curPenalty = curUser.totalPenalty;
				rank = ++cnt;
			} else {
				cnt++;
			}

			// 유저의 등수, 아이디 출력
			sb.append(rank).append(',').append(curUser.uid).append(',');

			// 문제에 대한 결과 출력
			for (int j = 0; j < N; j++) {

				// 일반 대회인 경우
				if (cscore == 0) {

					// 맞은 경우
					if (curUser.goodSubmit[j]) {
						sb.append("a/").append(curUser.trialCnt[j]).append('/');

						// 마지막 페널티를 사용하지 않을 경우
						if (last == 0) {

							// 포맷이 분일 경우
							if (format == 0)
								sb.append(curUser.penalty1[j] + curUser.penalty2[j]).append(',');

							// 포맷이 시:분일 경우
							else if (format == 1) {
								long p = curUser.penalty1[j] + curUser.penalty2[j];
								long h = p / 60L;
								long m = p % 60L;

								sb.append(h).append(':');

								if (m / 10 == 0)
									sb.append(0);

								sb.append(m).append(',');
							}
						}

						// 마지막 페널티를 사용할 경우
						else if (last == 1) {

							// 포맷이 분일 경우
							if (format == 0)
								sb.append(curUser.penalty2[j]).append(',');

							// 포맷이 시:분일 경우
							else if (format == 1) {
								long p = curUser.penalty2[j];
								long h = p / 60L;
								long m = p % 60L;

								sb.append(h).append(':');

								if (m / 10 == 0)
									sb.append(0);

								sb.append(m).append(',');
							}
						}
					}

					// 시도는 했으나 맞지 못한 경우
					else if (curUser.submitCnt[j] > 0)
						sb.append("w/").append(curUser.submitCnt[j]).append("/--,");

					// 시도를 한 적 없는 문제인 경우
					else
						sb.append("0/--,");
				}

				// 점수 대회인 경우
				else if (cscore == 1) {

					// 맞은 경우
					if (curUser.goodSubmit[j]) {

						// 성공한 경우
						if (curUser.point[j] == problemList.problem[j].pscore)
							sb.append("a/");

						// 부분 성공한 경우
						else
							sb.append("p/");

						sb.append(curUser.point[j]).append('/').append(curUser.trialCnt[j]).append('/');

						// 마지막 페널티를 사용하지 않을 경우
						if (last == 0) {

							// 포맷이 분일 경우
							if (format == 0)
								sb.append(curUser.penalty1[j] + curUser.penalty2[j]).append(',');

							// 포맷이 시:분일 경우
							else if (format == 1) {
								long p = curUser.penalty1[j] + curUser.penalty2[j];
								long h = p / 60L;
								long m = p % 60L;

								sb.append(h).append(':');

								if (m / 10 == 0)
									sb.append(0);

								sb.append(m).append(',');
							}
						}

						// 마지막 페널티를 사용할 경우
						else if (last == 1) {

							// 포맷이 분일 경우
							if (format == 0)
								sb.append(curUser.penalty2[j]).append(',');

							// 포맷이 시:분일 경우
							else if (format == 1) {
								long p = curUser.penalty2[j];
								long h = p / 60L;
								long m = p % 60L;

								sb.append(h).append(':');

								if (m / 10 == 0)
									sb.append(0);

								sb.append(m).append(',');
							}
						}
					}

					// 시도는 했으나 좋은 제출이 없는 경우
					else if (curUser.submitCnt[j] > 0)
						sb.append("w/").append(curUser.submitCnt[j]).append("/--,");

					// 시도를 한 적 없는 문제인 경우
					else
						sb.append("0/--,");
				}
			}

			// 유저의 최종 점수 출력
			sb.append(curUser.totalPoint).append('/');

			// 유저의 최종 페널티 출력
			// 포맷이 분일 경우
			if (format == 0)
				sb.append(curUser.totalPenalty).append('\n');

			// 포맷이 시:분일 경우
			else if (format == 1) {
				long p = curUser.totalPenalty;
				long h = p / 60L;
				long m = p % 60L;

				sb.append(h).append(':');

				if (m / 10 == 0)
					sb.append(0);

				sb.append(m).append('\n');
			}
		}

		System.out.println(sb);
	}

	// 대회 시작부터 현재 제출까지 걸린 시간 계산
	static long passedTime() throws Exception {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date st = dtFormat.parse(start);
		Date su = dtFormat.parse(date);

		return (su.getTime() - st.getTime()) / 60000L;
	}

}