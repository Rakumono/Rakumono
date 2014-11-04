package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import decode.Item;
import decode.SearchResult;
import encode.GenreResult;
import util.GenreClimber;
import api.RakutenIchiba;
import api.SearchCondition;

public class KeywordDetect {
	
	
	public String findRelatedWords(String keyword, int genre_num, int item_num) throws Exception{
		//initiate the genre name map
		HashMap<String, String> nameMap = JSON.parseObject(GenreClimber.genreNameMap, new TypeReference<HashMap<String, String>>() {});
		//The map contains the genreID and counting
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		
		RakutenIchiba ichiba = new RakutenIchiba();
		SearchCondition condition = new SearchCondition();
		condition.setKeyword(keyword);
		condition.setSort_method("-reviewCount");
		condition.setGenreInformationFlag("1");
		condition.setHits("1");
		int flag = 0;
		for(String second_level_genre : GenreClimber.second_level){
			condition.setGenreId(second_level_genre);
			String jsonString = ichiba.ichibaItemSearch(condition, flag);
			wordMap.putAll(ichiba.getGenreCountFromJSON(jsonString));
			Thread.sleep(00);
			flag++;
		}
		List<String> genres =  getFirstNKeys(wordMap, genre_num);
		//generate search result of items for each genre
		flag = 0;
		List<GenreResult> grs = new ArrayList<GenreResult>();
		for(String genre : genres){
			GenreResult gr = new GenreResult();
			gr.setGenreID(Integer.valueOf(genre));
			gr.setGenreName(nameMap.get(genre));
			gr.setGenreCount(wordMap.get(genre));
			
			List<Item> items = new ArrayList<Item>(); 
			
			condition.setGenreId(genre);
			//for now, only under 30; over 30 is to be implemented
			condition.setHits(String.valueOf(item_num));
			String jsonString_item = ichiba.ichibaItemSearch(condition, flag);
			SearchResult searchResult = JSON.parseObject(jsonString_item, SearchResult.class);
			for(HashMap<String, Item> map : searchResult.getItems()){
				items.add(map.get("Item"));
			}
			gr.setItems(items);
			grs.add(gr);
			flag++;
		}
		
		return JSON.toJSONString(grs);
	}	
		
	public static List<String> getFirstNKeys(HashMap<String, Integer> map, int n){
		List<String> resultList = new ArrayList<String>();
		ByValueComparator bvc = new ByValueComparator(map);
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys, bvc);
		int flag = 0;
		for (String key : keys) {
			if(flag <= n - 1){
				System.out.printf("%s -> %d\n", key, map.get(key));
				resultList.add(key);
				flag++;
			}
		}
		return resultList;
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
}
