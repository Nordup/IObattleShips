package IObattleShips;
import java.util.Scanner;

class Input {
	Scanner scan;

	public Input() {
		scan = new Scanner(System.in);
	}
	
	public int getInt() {
		return scan.nextInt();
	}

	public String getNext() {
		return scan.next();
	}

	public String getLine() {
		return scan.nextLine();
	}

}

