package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;

import decode.Item;
import encode.ShopItemResult;
import util.GenreClimber;
import api.RakutenIchiba;
import api.SearchCondition;

public class ShopItemDetect {
	//input: String keyword, String hits, String shopCode
	//output: json string
	public String getItemsInShop(String keyword, String hit_number, String shopCode) throws Exception{
		SearchCondition condition = new SearchCondition();
		RakutenIchiba ichiba = new RakutenIchiba();
		List<Item> itemList = new ArrayList<Item>();
		condition.setKeyword(keyword);
//		condition.setHits(hits);
		condition.setShopCode(shopCode);
		condition.setAppID(GenreClimber.chooseAppID(new Random().nextInt(20)));
		condition.setSort_method("-reviewCount");
		String jsonString = ichiba.ichibaItemSearch(condition);
		for(int i = 0; i < Integer.valueOf(hit_number); i++){
			Item item = ichiba.getItemFromJson(jsonString, Integer.valueOf(i));
			if(item == null){
				break;
			}
			itemList.add(item);
		}		
		ShopItemResult shopItemResult = new ShopItemResult();
		shopItemResult.setKeyword(keyword);
		shopItemResult.setItem(itemList);
		shopItemResult.setShopCode(shopCode);
		return JSON.toJSONString(shopItemResult);
	}
}
