package IObattleShips;

import java.io.IOException;

class Interface {
	WinDrafts	winDrafts; // drafts of parts of window

	protected Interface() {
		winDrafts = new WinDrafts();
	}

	protected void battleMap(int[][] myMap, int[][] opMap) {
		clearScreen();
		putHead();

		putstr("\n\tBATTLE\n\n\t1) BACK TO MENU\n\n");
		putstr("\t 0 1 2 3 4 5 6 7 8 9 \t\t 0 1 2 3 4 5 6 7 8 9\n");
		for (int i = 0; i < 10; i++) { // cycle of lines
			//myMap putting
			putstr("\t" + Character.toString('A' + i)); // y coors A-J
			for (int j = 0; j < 10; j++) {
				if (myMap[i][j] <= 4)
					putstr(winDrafts.chars[0]);
				else if (myMap[i][j] == 5) // shotted ship
					putstr(winDrafts.chars[1]);
				else if (myMap[i][j] == 7) // shotted ship arround empty
					putstr(winDrafts.chars[3]); // cloud
				else
					putstr(winDrafts.chars[2]); // empty sea
			}
			//opMap putting
			putstr("\t\t" + Character.toString('A' + i)); // y coors A-J
			for (int j = 0; j < 10; j++) {
				if (opMap[i][j] == 5) // shotted ship
					putstr(winDrafts.chars[1]); // shotted ship
				else if (opMap[i][j] == 7) // shotted ship arround empty
					putstr(winDrafts.chars[2]); // empty sea
				else
					putstr(winDrafts.chars[3]); // cloud
			}
			putstr("\n"); // end of line
		}
		putstr("\n");
	}

	protected void putGameOver(String result, Input in) {
		clearScreen();
		putHead();
		if (result.equals("win"))
			putstr("\n\tyou win!\n");
		else
			putstr("\n\tyou lose!\n");
		putstr("\npress key\n");
		in.getNext();
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

	protected void putfillMapWithShips(int[][] map, int size, String map_name) {
		String[] help = new String[] {
			"\n",
			"\tWrite position for ship with size " + size + "\n",
			"\tExamples: \"A0-A2\"(size: 3)\n",
			"\t          \"B3-E3\"(size: 4)\n",
			"\t          \"A0-A0\"(size: 1)\n",
			"\n",
			"\n",
			"\t1) BACK TO MENU\n",
			"\t  (map do not be saved)\n",
			"\n"
		};
		clearScreen();
		putHead();
		
		putstr("\n\tFILL MAP WITH SHIPS\n");
		putstr("\tmap name: " + map_name + "\n");
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
	}

	protected void	clearScreen() {
		System.out.print("\033[H\033[2J");  
		System.out.flush();
		try {
			this.CLS();
		} catch (IOException e) {

		} catch (InterruptedException e) {
			// handle it
		}
	}
	private void CLS() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
