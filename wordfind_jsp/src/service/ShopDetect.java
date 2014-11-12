package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import util.GenreClimber;

import com.alibaba.fastjson.JSON;

import encode.ShopResult;
import api.RakutenIchiba;
import api.SearchCondition;

public class ShopDetect {
	
	public String findShops(List<String> keywords) throws Exception{
		HashMap<String, String> shopNameMap = new HashMap<String, String>();
		HashMap<String, String> shopUrlMap = new HashMap<String, String>();
		
		Set<String> shopSet = new HashSet<String>();
		if(keywords.size() < 2){
			return "";
		}
		shopSet = findShopListByKeywords(keywords.get(0), shopNameMap, shopUrlMap);
		keywords.remove(keywords.get(0));
		for(String keyword : keywords){
			shopSet.retainAll(findShopListByKeywords(keyword, shopNameMap, shopUrlMap));
			Thread.sleep(500);
		}
		//consider when there's no result. Will finish it later
		if(shopSet.size() == 0){
			
		}
		//construct result
		List<ShopResult> shopResults = new ArrayList<ShopResult>();
		for(String shopCode : shopSet){
			ShopResult shopResult = new ShopResult();
			shopResult.setShopCode(shopCode);
			shopResult.setShopName(shopNameMap.get(shopCode));
			shopResult.setShopUrl(shopUrlMap.get(shopCode));
			shopResults.add(shopResult);
		}
		return JSON.toJSONString(shopResults);
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
