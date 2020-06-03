package IObattleShips;
import java.io.IOException;
import static IObattleShips.MyFile.*;

class WinDrafts {
	String	pathResources = "./resources/window_drafts/";
	String	pathChars = "./resources/characters/";
	String	strHead;
	String	strMenu;
	String	strParting;
	String	strHelp;
	String	strCreatingOfMap;
	String[]	chars = new String[4];
	
	public WinDrafts() {
		String head_file = pathResources + "Head.txt";
		String menu_file = pathResources + "Menu.txt";
		String parting_file = pathResources + "Parting.txt";
		String help_file = pathResources + "Help.txt";
		String creatingOfMap_file = pathResources + "CreatingOfMap.txt";
		String[] chars_file = new String[] { pathChars + "ship.txt", pathChars + "shotted.txt", pathChars + "empty.txt", pathChars + "cloud.txt"};

		try {
            strHead = readFileAsString(head_file);
			strMenu = readFileAsString(menu_file);
			strParting = readFileAsString(parting_file);
			strHelp = readFileAsString(help_file);
			strCreatingOfMap = readFileAsString(creatingOfMap_file);
			chars[0] = readFileAsString(chars_file[0]);
			chars[1] = readFileAsString(chars_file[1]);
			chars[2] = readFileAsString(chars_file[2]);
			chars[3] = readFileAsString(chars_file[3]);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
	}
}