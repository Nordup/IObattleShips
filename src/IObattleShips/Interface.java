package IObattleShips;

class Interface {
	WinDrafts	winDrafts; // drafts of parts of window

	protected Interface() {
		winDrafts = new WinDrafts();
	}

	protected void putMainMenu() {
		clearScreen();
		putHead();
		putstr(winDrafts.strMenu);
	}

	protected void putHelp() {
		clearScreen();
		putHead();
		putstr(winDrafts.strHelp);
		putAnswer();
	}

	protected void putHead() {
		putstr(winDrafts.strHead);
	}

	protected void putAnswer() {
		putstr("	Put answer: ");
	}

	protected void putCreatingOfMap() {
		clearScreen();
		putHead();
		putstr(winDrafts.strCreatingOfMap);
	}

	protected void putfillMapWithShips(int[][] map, int size) {
		String[] help = new String[] {
			"\n",
			"\tPUT THERE SOME HELP INFORMATION\n",
			"\n",
			"\n",
			"\n",
			"\n",
			"\n",
			"\n",
			"\n",
			"\n"
		};
		clearScreen();
		putHead();
		
		putstr("\n\tFILL MAP WITH SHIPS\n");
		putstr("\n\t 0 1 2 3 4 5 6 7 8 9\n");
		for (int i = 0; i < 10; i++) {
			putstr("\t" + Character.toString('A' + i));
			for (int j = 0; j < 10; j++) {
				if (map[i][j] <= 4)
					putstr(winDrafts.chars[0]);
				else if (map[i][j] == 5)
					putstr(winDrafts.chars[1]);
				else
					putstr(winDrafts.chars[2]);
			}
			putstr(help[i]);
		}
		putstr("\n");

		putAnswer();
	}

	protected void	clearScreen() {
	    putstr("\033[H\033[2J");
	    System.out.flush();
	}

	public void putstr(String str) {
		System.out.print(str);
	}

	protected void Parting() {
		clearScreen();
		putHead();
		putstr(winDrafts.strParting);
	}
}
