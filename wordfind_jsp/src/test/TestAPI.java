package test;

import java.util.ArrayList;
import java.util.List;

import service.KeywordDetect;
import service.ShopDetect;
import service.ShopItemDetect;

public class TestAPI {
	
	public static void main(String[] args) throws Exception {
//		RakutenIchiba ichiba = new RakutenIchiba();
//		
//		FileIO.clearFile(path);
//		SearchCondition condition = new SearchCondition();
//		condition.setKeyword("ポケモン");
//		condition.setSort_method("-reviewCount");
//		condition.setGenreInformationFlag("1");
//		for(int i = 1; i <= 1; i++){
//			condition.setPage(String.valueOf(i));
//			String jsonString = ichiba.ichibaItemSearch(condition);
////			FileIO.writeIntoFile(path, ichiba.getTitleFromJSON(jsonString));
//			FileIO.writeIntoFile(path, ichiba.getGenreCountFromJSON(jsonString));
//			if(i%3 == 1){
//				Thread.sleep(800);
//			}
//		}
		
//		GenreClimber.getThirdLevelGenreNamemap();
		
//		KeywordDetect kd = new KeywordDetect();
//		System.out.println(kd.findRelatedWords("青森", 10, 9));

//		List<String> words = new ArrayList<String>();
//		words.add("お茶");
//		words.add("いちご");
//		words.add("コーラ");
//		words.add("焼き鳥");
//		words.add("パン粉");
//		words.add("サラダ油");
//		ShopDetect sd = new ShopDetect();
//		System.out.println(sd.findShops(words));
		
		ShopItemDetect sd = new ShopItemDetect();
		System.out.println(sd.getItemsInShop("お茶", "1", "kenkocom"));
		
	}

}
