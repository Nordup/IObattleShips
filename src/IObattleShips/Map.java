package IObattleShips;
import static IObattleShips.MyFile.*;
import static IObattleShips.Time.*;
import static IObattleShips.BattleShip.*;

class Map {
	String			map_name;
	String			time_of_creating;
	private int[][]	map;// 1 to 4 - ship id, 5 - shotted ship, 6 - empty fill
	private BattleShip[]	ship = new BattleShip[10];

	public void readMap() {

	}

	public int createMap(Interface iface, Input in) {
		if (createMapName(iface, in) == 1)
			return 1;
		time_of_creating = getDateTime();
		emptyMap();

		for (int id = 0; id < 10; id++) { // create ships with sizes
			ship[id] = new BattleShip(getSizeFromID(id));
		}
		if (fillMapWithShips(iface, in) == 0)
			saveMap();
		return 0;
	}

	private void emptyMap() { // create empty map
		map = new int[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = 6;
			}
		}
	}

	private int fillMapWithShips(Interface iface, Input in) {
		for (int id = 0; id < 10; id++) {
			String	position = "";

			iface.putfillMapWithShips(map, getSizeFromID(id));
			while (position.length() < 1) {
				position = in.getNext();
				if (isItPosition(position))
					ship[id].setPosition(position, map);
				else {
					iface.putstr("wrong position try again");
					position = "";
				}
			}
		}

		return 0;
	}

	private void saveMap() {
		char[]	charMap = new char[100];
		String	fileName = "./resources/maps/" + map_name + ".map";

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				charMap[i * 10 + j] = (char) map[i][j];
			}
		}
		String	strMap = new String(charMap);
		writeIntoFile(fileName, strMap + time_of_creating, false);
	}

	private int createMapName(Interface iface, Input in) {
		String name = "";

		iface.putCreatingOfMap();;		
		while (name.length() < 1) {
			iface.putAnswer();
			name = in.getNext();
			if (name.equals("1")) {
				return 1;
			}
			if (fileNameExist(name + ".map", "./resources/maps")) {
				name = "";
				iface.putstr("map with this name already exist\n");
			}
		}
		map_name = name;
		return 0;
	}

}
