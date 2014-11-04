package test;

import service.KeywordDetect;

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
		
		KeywordDetect kd = new KeywordDetect();
		System.out.println(kd.findRelatedWords("desk", 2, 2));
	}

}
