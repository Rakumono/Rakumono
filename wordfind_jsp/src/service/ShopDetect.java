package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import util.GenreClimber;

import com.alibaba.fastjson.JSON;

import encode.Shop;
import encode.ShopResult;
import api.RakutenIchiba;
import api.SearchCondition;

public class ShopDetect {
	
	//the format of input: List<String> 
	//output: json string
	public String findShops(List<String> keywords) throws Exception{
		HashMap<String, String> shopNameMap = new HashMap<String, String>();
		HashMap<String, String> shopUrlMap = new HashMap<String, String>();
		
		Set<String> shopSet = new HashSet<String>();
		Set<String> shopSet_temp = new HashSet<String>();
		List<String> keywords_origin = new ArrayList<String>();
		if(keywords.size() < 2){
			return "";
		}
		shopSet = findShopListByKeywords(keywords.get(0), shopNameMap, shopUrlMap);
		String firstKeyword = keywords.get(0);
		keywords.remove(firstKeyword);
		keywords_origin.add(firstKeyword);
		for(String keyword : keywords){
			shopSet_temp.clear();
			shopSet_temp.addAll(shopSet);
			shopSet.retainAll(findShopListByKeywords(keyword, shopNameMap, shopUrlMap));
			//consider when there's no result. Will finish it later
			if(shopSet.size() == 0){
				ShopResult shopResult = new ShopResult();
				List<Shop> shops = new ArrayList<Shop>();
				for(String shopCode : shopSet_temp){
					Shop shop = new Shop();
					shop.setShopCode(shopCode);
					shop.setShopName(shopNameMap.get(shopCode));
					shop.setShopUrl(shopUrlMap.get(shopCode));
					shops.add(shop);
				}
				shopResult.setShops(shops);
				shopResult.setFull(false);
				shopResult.setKeywords(keywords_origin);
				return JSON.toJSONString(shopResult);
			}
			keywords_origin.add(keyword);
			Thread.sleep(500);
		}
		//construct result
		ShopResult shopResult = new ShopResult();
		List<Shop> shops = new ArrayList<Shop>();
		for(String shopCode : shopSet){
			Shop shop = new Shop();
			shop.setShopCode(shopCode);
			shop.setShopName(shopNameMap.get(shopCode));
			shop.setShopUrl(shopUrlMap.get(shopCode));
			shops.add(shop);
		}
		shopResult.setShops(shops);
		shopResult.setFull(true);
		shopResult.setKeywords(keywords_origin);
		return JSON.toJSONString(shopResult);
	}
	
	/**
	 * Return the list of shopcode of search keyword
	 * @param keyword
	 * @param shopUrlMap 
	 * @param shopNameMap 
	 * @return
	 * @throws Exception 
	 */
	public Set<String> findShopListByKeywords(String keyword, HashMap<String, String> shopNameMap, HashMap<String, String> shopUrlMap) throws Exception{
		List<String> jsonStringList = new ArrayList<String>();	
		
		Set<String> shopCodeSet = new HashSet<String>();
		RakutenIchiba ichiba = new RakutenIchiba();
		
		int threadNum = 20;
		CountDownLatch threadSignal = new CountDownLatch(threadNum);
		
		//finish http get and put all the String into list
		for(int i = 1; i <= threadNum; i++){
			new MyThread(jsonStringList, keyword, i, threadSignal).start();
		}
		threadSignal.await();
		System.out.println("All FINISHED!!!!!!!!!");
		//traverse the list
		for(String jsonString : jsonStringList){
			shopCodeSet.addAll(ichiba.getShopCodes(jsonString, shopNameMap, shopUrlMap));
		}
		return shopCodeSet;
	}
	
	class MyThread extends Thread {

		private int i;
		private List<String> jsonStringList;
		private String keyword;
		private CountDownLatch threadsSignal;

		public MyThread(List<String> jsonStringList, String keyword, int i, CountDownLatch threadsSignal) {
			this.jsonStringList = jsonStringList;
			this.i = i;
			this.keyword = keyword;
			this.threadsSignal = threadsSignal;
		}

		public void run() {
			// System.out.println(getName() + " 线程运行开始!");
			RakutenIchiba ichiba = new RakutenIchiba();
			SearchCondition condition = new SearchCondition();
			condition.setKeyword(keyword);
			condition.setSort_method("-reviewCount");
			condition.setPage(String.valueOf(i));
			condition.setAppID(GenreClimber.chooseAppID(i));
			try {
				jsonStringList.add(ichiba.ichibaItemSearch(condition));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			threadsSignal.countDown();
			System.out.println(i + "finished!!");
			// System.out.println(getName() + " 线程运行结束!");
		}
	}
	

}
