package IObattleShips;
import java.io.IOException;
import static IObattleShips.MyFile.*;
import static IObattleShips.Time.*;
import static IObattleShips.BattleShip.*;

class Map {
	String					map_name;
	String					time_of_creating;
	int[][]			map;// 1 to 4 - ship id, 5 - shotted ship, 6 - empty fill
	private BattleShip[]	ship = new BattleShip[10];

	public void importMap(Interface iface, Input in) {
	}

	public int readMap(String filename) {
		String strMap;
		String mapPath = "./resources/maps/" + filename + ".map";

		try {
			strMap = readFileAsString(mapPath);
		} catch (IOException e) {
			System.out.println("Cannot read file: " + e.getMessage()); //it will not appear
			return 1;
		}
		if (createMap(strMap, filename) != 0){
			System.out.println("wrong map, choose another one");
			return 1;
		}
		return 0;
	}

	public int createMap(String strMap, String map_name) {
		this.map_name = map_name;
		time_of_creating = strMap.substring(100);
		emptyMap();
		for (int id = 0; id < 10; id++) { // create ships with sizes
			ship[id] = new BattleShip(getSizeFromID(id));
		}
		strMap = strMap.substring(0, 100);
		char[] cMap = strMap.toCharArray();
		if (fillMapWithShips(cMap) != 0)
			return 1;
		return 0;
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

	private int fillMapWithShips(char[] cMap) {
		for (int i = 0; i < 100; i++) { // chars from cMap
			char	code;
			int		id;

			code = cMap[i];
			if (code == 6)
				continue;
			id = this.gerIDFromSize(code); // find ship code
			if (id < 0) // its mean that we haven't any ships or code and size is wrong
				return 1;
			if (ship[id].setPosition(cMap, i, map)) // set ships if we can
				;
			else // if map is wrong
				return 1;
		}
		return 0;
	}
	private int gerIDFromSize(int size) {
		if (size < 1 || size > 4) // if its not ship size
			return -1;
		
		for (int id = 0; id < 10; id++) { // check for every ship
			if (ship[id].size == size && !ship[id].have_pos) // if size right and ships positions is empty
				return id;
		}
		return -1;
	}

	private int fillMapWithShips(Interface iface, Input in) {
		for (int id = 0; id < 10; id++) {
			String	position = "";

			//put interface with map end help
			iface.putfillMapWithShips(map, getSizeFromID(id), map_name);
			while (position.length() < 1) {
				iface.putAnswer();
				position = in.getNext(); // get position where to put ship
				if (position.equals("1")) { // back to menu
					return 1;
				}
				if (position.length() != 5)
					position = "....."; // put wrong pos
				if (ship[id].isItRightPosition(position, iface) && ship[id].setPosition(position, map, iface))
					; // set ships if we can in previous statement
				else { // wrong answer
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
