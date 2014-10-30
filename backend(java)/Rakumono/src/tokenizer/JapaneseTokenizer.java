package tokenizer;

import java.io.IOException;
import java.util.*;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

public class JapaneseTokenizer {
	public static ArrayList<String> Calculate_Relevent_Word(
			ArrayList<String> sentence_sets, int top_n) {
		ArrayList<String> ret = new ArrayList<String>();
		ArrayList<String> bag_of_words = new ArrayList<String>();
		System.out.println("this is Calculate_releven_word fuction");
		Tokenizer tokenizer;
		tokenizer = Tokenizer.builder().build();
		for (String sentence : sentence_sets) {
			List<Token> result = tokenizer.tokenize(sentence);
			for (Token token : result) {
				String word = token.getSurfaceForm();
				String tag = token.getAllFeatures().trim().split(",")[0];
				if (tag.equals("名詞")) // only keep nonu word to count
				{
					bag_of_words.add(word);
				}
			}
			// System.out.println("------------------");
		}
		System.out.println("the num of bag_of_words is:" + bag_of_words.size());
		ret = Top_Word_Find(bag_of_words, top_n);
		return ret;
	}

	// word count, return top N frequency word
	private static ArrayList<String> Top_Word_Find(
			ArrayList<String> bag_of_words, int top_n) {
		ArrayList<String> ret = new ArrayList<String>();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		for (String word : bag_of_words) {
			if (hashMap.get(word) != null) {
				int value = ((Integer) hashMap.get(word)).intValue();
				value++;
				hashMap.put(word, new Integer(value));
			} else {
				hashMap.put(word, new Integer(1));
			}
		}

		ByValueComparator bvc = new ByValueComparator(hashMap);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
		sorted_map.putAll(hashMap);

		for (String name : sorted_map.keySet()) {
			int num = hashMap.get(name);
			if (num >= 1) {
				System.out.printf("%s -> %d\n", name, hashMap.get(name));
			}
		}
		return ret;
	}

	private static void useless_word_filter() {

		System.out.println("this is useless_word_filter");
	}

	static class ByValueComparator implements Comparator<String> {
		HashMap<String, Integer> base_map;

		public ByValueComparator(HashMap<String, Integer> base_map) {
			this.base_map = base_map;
		}

		public int compare(String arg0, String arg1) {
			if (!base_map.containsKey(arg0) || !base_map.containsKey(arg1)) {
				return 0;
			}

			if (base_map.get(arg0) < base_map.get(arg1)) {
				return 1;
			} else if (base_map.get(arg0) == base_map.get(arg1)) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("this is main fuction");
		useless_word_filter();
	}

}
