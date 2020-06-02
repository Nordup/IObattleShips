package IObattleShips;

class Main {
	Battle		battle;
	Map			someMap;
	Interface	iface;
	Input		in;

	public Main() {
		someMap = new Map();
		iface = new Interface();
		in = new Input();
		battle = new Battle(iface, in);
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
				case "1":
					battle.startBattle();
					break;
				case "2":
					someMap.createMap(iface, in);
					break;
				case "3":
					someMap.importMap(iface, in);
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
