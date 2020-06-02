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

	protected void	clearScreen() {
	    putstr("\033[H\033[2J");
	    System.out.flush();
	}

	public void putstr(String str) {
		System.out.print(str);
	}

	public void Parting() {
		putstr(winDrafts.strParting);
	}
}
