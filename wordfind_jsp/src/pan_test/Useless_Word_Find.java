package pan_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Useless_Word_Find {
	public static void main(String[] args) throws IOException {
		File file = new File("D:/testData_h.txt");
		int top_n = 3;
		ArrayList<String> sentence_sets = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;

			while ((tempString = reader.readLine()) != null) {

				//System.out.println("line " + line + ": " + tempString);
				sentence_sets.add(tempString.trim());
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		tokenizer.JapaneseTokenizer.Calculate_Relevent_Word(sentence_sets,top_n);
		System.out.println("the size of sentence_sets is: "+sentence_sets.size());
	}

}
