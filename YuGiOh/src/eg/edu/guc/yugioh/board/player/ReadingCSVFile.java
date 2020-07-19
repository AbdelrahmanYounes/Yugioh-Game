package eg.edu.guc.yugioh.board.player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadingCSVFile {

	public static ArrayList<String> readFile(String path) {
		String currentLine = "";
		FileReader fileReader = null;
		ArrayList<String> cards = new ArrayList<String>();
		try {
			fileReader = new FileReader(path);
			BufferedReader br = new BufferedReader(fileReader);
			while ((currentLine = br.readLine()) != null) {
				cards.add(currentLine);
			}
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		catch (IOException e) {

			e.printStackTrace();
		}
		return cards;
	}

}
