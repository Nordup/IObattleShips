package IObattleShips;
import java.lang.Math;

class BattleShip {
	int			size;
	boolean		have_pos;//set positions or not
	int[][]		position;//[size][coor]
	int			health;

	public BattleShip(int size) {
		this.size = size;
		have_pos = false;
		position = new int[size][2];
		health = size;
	}

	protected boolean setPosition(char[] cMap, int c, int[][] map) {
		//something that can set positions
		for (int i = 0; i < size; i++) {
			int y = c / 10;
			int x = c % 10;
			if (cMap[c] == size) {
				position[i][0] = y;
				position[i][1] = x;
				cMap[c] = 6; // empty fill
			} else {
				return false;
			}
			if (isInmap(x + 1) && cMap[c + 1] == size)
				c += 1;
			else if (isInmap(y + 1) && cMap[c + 10] == size)
				c += 10;
		}

		if (shipsAround(map)) {// if we have ships around
			return false;
		}
		fillMap(map); // push to map
		have_pos = true; // for Map.getIDFromSize()
		return true;
	}

	protected boolean setPosition(String pos, int[][] map, Interface iface) {
		boolean horisontal = false; // horizontal or vertical
		int		dir = 1; // -1 or +1; up or down; right or left
		char	a = pos.charAt(0);
		char	b = pos.charAt(1);
		char	c = pos.charAt(3);
		char	d = pos.charAt(4);

		if (a == c)
			horisontal = true;
		if (horisontal) {
			if (b - d > 0)
				dir = -1;
		} else {
			if (a - c > 0)
				dir = -1;
		}

		for (int i = 0; i < size; i++) {
			if (horisontal) {
				position[i][0] = a - 'A';
				position[i][1] = i * dir + b - '0';
			} else {
				position[i][0] = i * dir + a - 'A';
				position[i][1] = b - '0';
			}
		}
		if (shipsAround(map)) {// if we have ships around
			iface.putstr("there is ships around\n");
			return false;
		}
		fillMap(map); // push to map
		return true;
	}

	private boolean shipsAround(int[][] map) {
		for (int i = 0; i < size; i++) {
			int y = position[i][0];
			int x = position[i][1];

			if (isInmap(y - 1) && isInmap(x - 1) && isItShip(map[y - 1][x - 1]))
				return true;
			if (isInmap(y - 1) && isInmap(x) && isItShip(map[y - 1][x]))
				return true;
			if (isInmap(y - 1) && isInmap(x + 1) && isItShip(map[y - 1][x + 1]))
				return true;
				
			if (isInmap(y) && isInmap(x - 1) && isItShip(map[y][x - 1]))
				return true;
			if (isInmap(y) && isInmap(x) && isItShip(map[y][x]))
				return true;
			if (isInmap(y) && isInmap(x + 1) && isItShip(map[y][x + 1]))
				return true;

			if (isInmap(y + 1) && isInmap(x - 1) && isItShip(map[y + 1][x - 1]))
				return true;
			if (isInmap(y + 1) && isInmap(x) && isItShip(map[y + 1][x]))
				return true;
			if (isInmap(y + 1) && isInmap(x + 1) && isItShip(map[y + 1][x + 1]))
				return true;
		}
		return false; // no ships around
	}
	private boolean isItShip(int mapVolume) {
		if (mapVolume <= 4)
			return true;
		return false; // empty / no ship
	}
	private boolean isInmap(int size) {
		if (size >= 0 && size <= 9)
			return true;
		return false; // over then map
	}

	private void fillMap(int[][] map) {
		for (int i = 0; i < size; i++) {
			map[position[i][0]][position[i][1]] = size;
		}
	}

	protected boolean isItRightPosition(String pos, Interface iface) {
		char a = pos.charAt(0);
		char b = pos.charAt(1);
		char c = pos.charAt(3);
		char d = pos.charAt(4);

		if (isA_to_J(a) && isA_to_J(c) && isDigit(b) && isDigit(d)){ // check is it chars and digits
			if ((a == c) || (b == d)) { // check is alined
				if (Math.abs(a - c) == size - 1 || Math.abs(b - d) == size - 1) // check if size is right
					return true;
				else
					iface.putstr("wrong size of ship try again\n");
			} else
				iface.putstr("your ship not alined try again\n");
		} else
			iface.putstr("wrong chars or digits try again\n");
		return false;
	}

	private static boolean isA_to_J(char c) {
		if (c >= 'A' && c <= 'J')
			return true;
		else
			return false;
	}
	private static boolean isDigit(char c) {
		if (c >= '0' && c <= '9')
			return true;
		else
			return false;
	}

	public static int getSizeFromID(int id) {
		int size;

		if (id == 0)
			size = 4;
		else if (id <= 2)
			size = 3;
		else if (id <= 5)
			size = 2;
		else if (id <= 9)
			size = 1;
		else
			size = -1;
		return size;
	}
}
