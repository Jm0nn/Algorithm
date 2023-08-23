import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RPG 게임
public class Main {

	// 플레이어 클래스
	static class Player {
		int r, c; // 위치
		int lv; // 레벨
		int hp; // 체력
		int hpMax; // 최대 체력
		int att; // 공격력
		int def; // 방어력
		int exp; // 현재 경험치
		int expReq; // 요구 경험치

		int wAtt; // 장착 무기 공격력
		int aDef; // 장착 방어구 공격력
		String acc; // 장착 장신구
		int accCnt; // 장착 장신구 개수

		Player(int r, int c) {
			this.r = r;
			this.c = c;
			this.lv = 1;
			this.hpMax = 20;
			this.hp = this.hpMax;
			this.att = 2;
			this.def = 2;
			this.expReq = 5 * this.lv;

			this.acc = "";
		}
	}

	// 몬스터 클래스
	static class Monster {
		String name; // 이름
		int att; // 공격력
		int def; // 방어력
		int hp; // 체력
		int exp; // 얻을 수 있는 경험치

		Monster(String name, int att, int def, int hp, int exp) {
			this.name = name;
			this.att = att;
			this.def = def;
			this.hp = hp;
			this.exp = exp;
		}
	}

	// 상자 클래스
	static class Box {
		String type; // 보상 종류
		int ad; // 장비 수치
		String effect; // 효과

		Box(String type, int ad) {
			this.type = type;
			this.ad = ad;
		}

		Box(String type, String effect) {
			this.type = type;
			this.effect = effect;
		}
	}

	static int N, M; // 맵의 크기
	static int sr, sc; // 플레이어 시작 지점

	static char[][] map; // 맵 배열
	static Player player; // 플레이어
	static Monster[][] monster; // 몬스터 배열
	static Box[][] box; // 상자 배열

	static String cod; // 사망 원인
	static boolean gameOver; // 게임 종료 여부
	static boolean youDied; // 플레이어 사망 여부
	static boolean killBoss; // 보스 처치 여부
	static boolean canRevive; // 부활 가능 여부

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N + 1][M + 1];
		monster = new Monster[N + 1][M + 1];
		box = new Box[N + 1][M + 1];

		int k = 0; // 몬스터의 수
		int l = 0; // 상자의 수

		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++) {
				char c = s.charAt(j - 1);

				if (c == '@') { // 플레이어 설정
					player = new Player(i, j);
					sr = i;
					sc = j;
					c = '.';
				} else if (c == '&' || c == 'M') {
					k++;
				} else if (c == 'B') {
					l++;
				}

				map[i][j] = c;
			}
		}

		char[] command = br.readLine().toCharArray(); // 플레이어 이동 입력

		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			String name = st.nextToken();
			int att = Integer.parseInt(st.nextToken());
			int def = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			int exp = Integer.parseInt(st.nextToken());

			monster[r][c] = new Monster(name, att, def, hp, exp);
		}

		while (l-- > 0) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			String type = st.nextToken();

			if (type.equals("O")) {
				String effect = st.nextToken();
				box[r][c] = new Box(type, effect);
			} else {
				int ad = Integer.parseInt(st.nextToken());
				box[r][c] = new Box(type, ad);
			}
		}

		int T = 0; // 게임 진행 턴 수

		// 입력 받은 커맨드만큼 게임 진행
		for (int i = 0; i < command.length; i++, T++) {
			char cmd = command[i]; // 입력

			int nr = player.r;
			int nc = player.c;

			// 입력받은 방향으로 이동
			switch (cmd) {
			case 'L':
				nc--;
				break;

			case 'R':
				nc++;
				break;

			case 'U':
				nr--;
				break;

			case 'D':
				nr++;
				break;
			}

			// 맵 밖으로 이동하려 할 경우 움직이지 않음
			if (1 > nr || nr > N || 1 > nc || nc > M) {
				nr = player.r;
				nc = player.c;
			}

			char pos = map[nr][nc];

			switch (pos) {
			case '#': // 벽
				nr = player.r;
				nc = player.c;
				pos = map[nr][nc];
				break;

			case 'B': // 상자
				getBox(nr, nc);
				break;

			case '&': // 몬스터
				fight(nr, nc);
				break;

			case 'M': // 보스
				fightBoss(nr, nc);
				break;
			}

			if (pos == '^') { // 가시
				// Dexterity 장신구를 착용했을 경우 가시 데미지 1만 받음
				if (player.acc.contains("DX"))
					player.hp--;
				// 그렇지 않을 경우 가시 데미지 5
				else
					player.hp -= 5;

				// 체력이 0 이하가 되면 플레이어 사망
				if (player.hp <= 0) {
					player.hp = 0;
					cod = "SPIKE TRAP";

					gameOver = true;
					youDied = true;
				}
			}

			// 플레이어 위치 설정
			player.r = nr;
			player.c = nc;

			if (gameOver) { // 게임 종료 시
				if (youDied && canRevive) { // 플레이어 사망 및 부활 가능
					// 플레이어 시작지점에서 부활
					player.r = sr;
					player.c = sc;
					player.hp = player.hpMax;

					// 모든 상태 초기화
					gameOver = false;
					youDied = false;
					canRevive = false;
					player.acc = player.acc.replace("RE ", "");
					player.accCnt--;
				} else { // 다른 상황에서는 게임 종료
					T++;
					break;
				}
			}
		}

		// 사망하지 않았을 경우 맵에 플레이어 위치 표시
		if (!youDied)
			map[player.r][player.c] = '@';

		// 게임이 끝난 시점의 상황 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++)
				sb.append(map[i][j]);
			sb.append('\n');
		}

		sb.append("Passed Turns : ").append(T).append('\n'); // 진행된 턴 수 출력
		sb.append("LV : ").append(player.lv).append('\n'); // 플레이어 레벨 출력
		sb.append("HP : ").append(player.hp).append('/').append(player.hpMax).append('\n'); // 플레이어 체력 출력
		sb.append("ATT : ").append(player.att).append('+').append(player.wAtt).append('\n'); // 플레이어 공격력 출력
		sb.append("DEF : ").append(player.def).append('+').append(player.aDef).append('\n'); // 플레이어 방어력 출력
		sb.append("EXP : ").append(player.exp).append('/').append(player.expReq).append('\n'); // 플레이어 경험치 출력
		if (killBoss) // 보스를 잡았을 경우
			sb.append("YOU WIN!");
		else if (youDied) // 사망했을 경우
			sb.append("YOU HAVE BEEN KILLED BY ").append(cod).append("..");
		else // 게임이 계속 진행중일 경우
			sb.append("Press any key to continue.");

		System.out.println(sb);
	}

	// 상자를 열 때
	static void getBox(int r, int c) {
		Box reward = box[r][c]; // 현재 위치 상자
		String type = reward.type; // 보상 종류

		if (type.equals("O")) { // 장신구
			String effect = reward.effect; // 장신구 효과

			// 장신구는 최대 4개 까지만 착용 가능, 동일한 효과의 장신구는 중복 착용 불가
			if (player.accCnt < 4 && !player.acc.contains(effect)) {
				if (effect.equals("RE")) // 부활 장신구를 얻을 경우
					canRevive = true; // 사망시 부활 가능

				player.acc += effect + " "; // 장신구 착용
				player.accCnt++; // 장신구 착용 개수 증가
			}
		} else {
			int ad = reward.ad; // 장비 수치

			if (type.equals("W")) // 장비가 무기일 경우
				player.wAtt = ad; // 무기 장착
			else if (type.equals("A")) // 장비가 방어구일 경우
				player.aDef = ad; // 방어구 장착
		}

		map[r][c] = '.'; // 상자 오픈
	}

	// 몬스터와 전투
	static void fight(int r, int c) {
		Monster enemy = monster[r][c]; // 현재 위치 몬스터

		int pAtt = player.att + player.wAtt; // 플레이어 공격력
		int pDef = player.def + player.aDef; // 플레이어 방어력
		int mAtt = enemy.att; // 몬스터 공격력
		int mDef = enemy.def; // 몬스터 방어력
		int mHp = enemy.hp; // 몬스터 체력

		boolean firstAtt = false; // 첫 번째 공격 여부
		boolean killMonster = false; // 몬스터 사망 여부

		// Courage 장신구를 착용했을 경우 첫 번째 공격 데미지 2배로 증가
		if (player.acc.contains("CO"))
			firstAtt = true;

		while (true) {
			if (firstAtt) { // 첫 번째 공격일 경우
				int fAtt = pAtt; // 적용할 데미지

				// Dexterity 장신구를 착용했을 경우 데미지 세배
				if (player.acc.contains("DX"))
					fAtt *= 3;
				else
					fAtt *= 2;

				mHp -= Math.max(1, fAtt - mDef); // 몬스터 공격

				firstAtt = false;
			} else {
				mHp -= Math.max(1, pAtt - mDef); // 몬스터 공격
			}

			// 몬스터 사망 시 전투 종료
			if (mHp <= 0) {
				killMonster = true;
				break;
			}

			player.hp -= Math.max(1, mAtt - pDef); // 몬스터 공격

			// 플레이어 사망 시 전투 종료
			if (player.hp <= 0) {
				player.hp = 0;
				cod = enemy.name;

				gameOver = true;
				youDied = true;
				break;
			}
		}

		// 전투 승리 시 경험치 획득
		if (killMonster) {
			// HP Regeneration 장신구를 착용했을 경우 전투 승리시 체력 3 회복
			if (player.acc.contains("HR")) {
				player.hp += 3;

				if (player.hp > player.hpMax)
					player.hp = player.hpMax;
			}

			map[r][c] = '.'; // 몬스터 사망
			getExp(enemy.exp);
		}
	}

	// 보스와 전투
	static void fightBoss(int r, int c) {
		Monster boss = monster[r][c]; // 보스 몬스터

		int pAtt = player.att + player.wAtt; // 플레이어 공격력
		int pDef = player.def + player.aDef; // 플레이어 방어력
		int bAtt = boss.att; // 보스 공격력
		int bDef = boss.def; // 보스 방어력
		int bHp = boss.hp; // 보스 체력

		boolean firstAtt = false; // 플레이어의 첫 번째 공격 여부

		// Courage 장신구를 착용했을 경우 첫 번째 공격 데미지 2배로 증가
		if (player.acc.contains("CO"))
			firstAtt = true;

		boolean firstDef = false; // 보스의 첫 번째 공격 여부

		// Hunter 장신구를 착용했을 경우 체력을 최대치까지 회복
		if (player.acc.contains("HU")) {
			player.hp = player.hpMax;
			firstDef = true;
		}

		while (true) {
			if (firstAtt) { // 플레이어의 첫 번째 공격일 경우
				int fAtt = pAtt; // 적용할 데미지

				// Dexterity 장신구를 착용했을 경우 데미지 세배
				if (player.acc.contains("DX"))
					fAtt *= 3;
				else
					fAtt *= 2;

				bHp -= Math.max(1, fAtt - bDef); // 플레이어의 공격

				firstAtt = false;
			} else {
				bHp -= Math.max(1, pAtt - bDef); // 플레이어의 공격
			}

			// 보스 사망 시 전투 종료
			if (bHp <= 0) {
				gameOver = true;
				killBoss = true;
				break;
			}

			if (firstDef) { // 보스의 첫 번째 공격일 경우
				firstDef = false; // 보스에 의한 데미지 0
			} else {
				player.hp -= Math.max(1, bAtt - pDef); // 보스의 공격
			}

			// 플레이어 사망 시 전투 종료
			if (player.hp <= 0) {
				player.hp = 0;
				cod = boss.name;

				gameOver = true;
				youDied = true;
				break;
			}
		}

		// 전투 승리 시 경험치 획득
		if (killBoss) {
			// HP Regeneration 장신구를 착용했을 경우 전투 승리시 체력 3 회복
			if (player.acc.contains("HR")) {
				player.hp += 3;

				if (player.hp > player.hpMax)
					player.hp = player.hpMax;
			}

			map[r][c] = '.'; // 보스 사망
			getExp(boss.exp);
		}
	}

	// 경험치 획득
	static void getExp(int exp) {
		// Experience 장신구를 착용했을 경우 획득 경험치 1.2배
		if (player.acc.contains("EX"))
			exp = (int) (exp * 1.2);

		player.exp += exp;

		// 레벨업
		if (player.exp >= player.expReq) {
			player.lv++; // 레벨 증가

			player.hpMax += 5; // 최대 체력 5 증가
			player.hp = player.hpMax; // 체력 모두 상승

			// 공격력, 방어력 2씩 증가
			player.att += 2;
			player.def += 2;

			// 현재 경험치 0, 요구 경험치 증가
			player.exp = 0;
			player.expReq = player.lv * 5;
		}
	}

}