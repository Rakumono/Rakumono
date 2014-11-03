package pan_test;

import java.io.IOException;
import java.util.ArrayList;

public class Test_Interface_For_LRX {
	public static void main(String[] args) throws IOException {
		ArrayList<String> sentence_sets = new ArrayList<String>();
		int top_n = 20;
		String s1 = "";
		String s2 = "";
				
		String s3 = "";
				
		sentence_sets.add(s1);
		sentence_sets.add(s2);
		sentence_sets.add(s3);

		tokenizer.JapaneseTokenizer.Calculate_Relevent_Word(sentence_sets,
				top_n);
	}
}
