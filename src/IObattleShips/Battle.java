package IObattleShips;
import static IObattleShips.MyFile.*;
import static IObattleShips.BattleShip.*;

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
		boolean amIfirst = true;
		if (myMap.time_of_creating.compareTo(opMap.time_of_creating) > 0) // if your time more then op time, u a nor the first
			amIfirst = false;
		while (myMap.shipsAlive() && opMap.shipsAlive()) { // its beginning of game
			iface.battleMap(myMap.map, opMap.map);
			if (amIfirst) {
				iface.putstr("\t\t\t\t\tYOU ARE ATTACK YOUR OPPONENT\n");
				iface.putstr("\t\t\t\t\tchoose the field you wanna attack\n");
			} else {
				iface.putstr("\tYOUR OPPONENT ARE ATTACK YOU\n");
				iface.putstr("\twrite the field that your opponent attack\n");
			}

			String field = "";

			while (field.length() < 1) {
				iface.putAnswer();
				field = in.getNext();

				if (field.equals("1")) {// Back to Menu
					return 1; // there must be: are you sure?
				}
				if (field.length() != 2)
					field = ".."; // put wrong pos
				if (isA_to_J(field.charAt(0)) && isDigit(field.charAt(1))) { // if right field. Ex.: (A1, D3, ...)
					if (amIfirst) {
						if (opMap.attack(field, iface) == 0) {
							amIfirst = false; // if all right then turn is not mine
						} else
							field = "";
					}
					else {
						if (myMap.attack(field, iface) == 0) {
							amIfirst = true; // if all right then turn is mine
						} else
							field = "";
					}
				} else { // wrong answer
					field = "";
				}
				if (field.equals(""))
					iface.putstr("wrong field try again\n");
			}
		} // game is end
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