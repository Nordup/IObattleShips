package IObattleShips;
import java.io.IOException;
import static IObattleShips.MyFile.*;

class WinDrafts {
	String	pathResources = "./resources/window_drafts/";
	String	strHead;
	String	strMenu;
	String	strParting;
	String	strHelp;
	String	strCreatingOfMap;
	
	public WinDrafts() {
		String head_file = pathResources + "Head.txt";
		String menu_file = pathResources + "Menu.txt";
		String parting_file = pathResources + "Parting.txt";
		String help_file = pathResources + "Help.txt";
		String creatingOfMap_file = pathResources + "CreatingOfMap.txt";

		try {
            strHead = readFileAsString(head_file);
			strMenu = readFileAsString(menu_file);
			strParting = readFileAsString(parting_file);
			strHelp = readFileAsString(help_file);
			strCreatingOfMap = readFileAsString(creatingOfMap_file);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
	}
}