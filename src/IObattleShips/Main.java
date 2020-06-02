package IObattleShips;

class Main {
	Map			myMap;
	Map			opntMap;
	Interface	iface;
	Input		in;

	public Main() {
		myMap = new Map();
		opntMap = new Map();
		iface = new Interface();
		in = new Input();
	}
	public static void main(String[] args) {
		int	exit = 1;
		Main game = new Main();
		
		while (exit == 1) {
			exit = game.startGame();
		}
		game.iface.Parting();
	}

	private int startGame() {
		String cmd = "";

		iface.putMainMenu();

		while (cmd.length() < 1) {
			iface.putAnswer();
			cmd = in.getNext();
			
			switch(cmd) {
				case "2":
					myMap.createMap(iface, in);
					break;
				case "4":
					iface.putHelp();
					in.getNext();
					break;
				case "5":
					return 0;// exit game
				default:
					iface.putstr("wrong statemtent\n");
					cmd = "";
			}
		}
		return 1;
	} // method startGame end

} //class Main end
