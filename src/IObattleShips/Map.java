package IObattleShips;
import static IObattleShips.MyFile.*;
import static IObattleShips.Time.*;

class Map {
	String			map_name;
	String			time_of_creating;
	private int[][]	map;// 1 is shoted ship, 2 is empty/sea, 3 is alive ship

	public int createMap(Interface iface, Input in) {
		if (createMapName(iface, in) == 1)
			return 1;
		time_of_creating = getDateTime();
		emptyMap();

		//something for map creating
		saveMap();
		return 0;
	}

	public void readMap() {

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

	private void emptyMap() {
		map = new int[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = 2;
			}
		}
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
