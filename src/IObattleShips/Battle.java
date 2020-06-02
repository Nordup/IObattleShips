package IObattleShips;
import static IObattleShips.MyFile.*;

class Battle {
	Map			myMap;
	Map			opMap;
	Input		in;
	Interface	iface;

	public Battle(Interface iface, Input in) {
		this.iface = iface;
		this.in = in;
		myMap = new Map();
		opMap = new Map();
	}

	public int startBattle() {
		if (initMaps() != 0)
			return 1;
		return 0;
	}

	private int initMaps() {
		boolean log = true;

		iface.clearScreen();
		iface.putHead();
		iface.putstr("\n\tCHOOSE YOUR MAP\n\t(write name of map)\n\n\tmaps:\n");
		pushList("./resources/maps", iface);
		iface.putstr("\n\t1) BACK TO MENU\n\n");
		while (log) {
			String filename;
			iface.putAnswer();
			filename = in.getNext();
			if (filename.equals("1"))
				return 1;
			if (myMap.readMap(filename) == 0)
				log = false;
		}
		log = true;
		iface.clearScreen();
		iface.putHead();
		iface.putstr("\n\tCHOOSE MAP OF YOUR OPPONENT\n\t(write name of map)\n\n\tmaps:\n");
		pushList("./resources/maps", iface);
		iface.putstr("\n\t1) BACK TO MENU\n\n");
		while (log) {
			String filename;
			iface.putAnswer();
			filename = in.getNext();
			if (filename.equals("1"))
				return 1;
			if (opMap.readMap(filename) == 0)
				log = false;
		}
		return 0;
	}
}