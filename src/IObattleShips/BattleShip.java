package IObattleShips;

class BattleShip {
	int			size;
	int[][]		position;
	boolean[]	healthy;

	public BattleShip(int size) {
		this.size = size;
		position = new int[size][2];
		healthy = new boolean[size];

		for (int i = 0; i < size; i++) {
			healthy[i] = true;
		}
	}

	protected void setPosition(String pos, int[][] map) {

	}

	public static boolean isItPosition(String pos) {
		return true;
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
