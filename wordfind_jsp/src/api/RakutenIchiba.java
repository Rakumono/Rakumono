package api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;














import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import decode.Genre;
import decode.Item;
import decode.SearchResult;

public class RakutenIchiba {	
	
	/**
	 * Using HttpClient to get API response body
	 * 
	 * @return
	 */
	public String ichibaItemSearch(SearchCondition condition) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(urlConstructor(condition));
           

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        
                        return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            return responseBody;
        } finally {
            httpclient.close();
        }
	}
	
	/**
	 * Construct the url of API 
	 * @return
	 * @throws URISyntaxException 
	 */
	private URI urlConstructor(SearchCondition condition) throws URISyntaxException{
		//using HTTP URI to rewrite
		URI uri = new URIBuilder().setScheme("https").setHost("app.rakuten.co.jp").setPath("/services/api/IchibaItem/Search/20140222")
				.setParameter("format", condition.getFormat())
				.setParameter("keyword", condition.getKeyword())
				.setParameter("genreId", condition.getGenreId())
				.setParameter("shopCode", condition.getShopCode())
				.setParameter("applicationId", condition.getAppID())
				.setParameter("hits", condition.getHits())
				.setParameter("page", condition.getPage())
				.setParameter("sort", condition.getSort_method())
//				.setParameter("genreInformationFlag", condition.getGenreInformationFlag())
				.setParameter("orFlag", condition.getOrFlag()).build();
		return uri;
	}
	
	/**
	 * Parse JSON
	 */
	public List<String> getTitleFromJSON(String jString){
		List<String> itemNameList = new ArrayList<String>();
		SearchResult searchResult = JSON.parseObject(jString, SearchResult.class);
		
		List<HashMap<String, Item>> items = searchResult.getItems();
		for(HashMap<String, Item> item : items){
			itemNameList.add(item.get("Item").getItemName());
		}
		return itemNameList;
	}
	
	public HashMap<String, Integer> getGenreCountFromJSON(String jString){
		HashMap<String, Integer> countList = new HashMap<String, Integer>();
		SearchResult searchResult = JSON.parseObject(jString, SearchResult.class);
		
		List<HashMap<String, Genre>> childGenreList = searchResult.getGenreInformation().get(0).getChildren();
		for(HashMap<String, Genre> map : childGenreList){
			Genre genre = map.get("child");
			countList.put(genre.getGenreId(), genre.getItemCount());
		}
		return countList;
	}
	
	public HashMap<String, String> getGenreMap(String jString){
		HashMap<String, String> namemap = new HashMap<String, String>();
		SearchResult searchResult = JSON.parseObject(jString, SearchResult.class);
		
		List<HashMap<String, Genre>> childGenreList = searchResult.getGenreInformation().get(0).getChildren();
		for(HashMap<String, Genre> map : childGenreList){
			Genre genre = map.get("child");
			namemap.put(genre.getGenreId(), genre.getGenreName());
		}
		return namemap;
		
	}
	
	public String ichibaItemSearch(SearchCondition condition, int flag) throws Exception{
		String[] appIDs = {"1027344104687047127", "1065391229178026160", "1020597589906679143", "1051579793540243350", "1049495469872040215", "1030657779787553704"};
//		new MyThread(condition, appIDs[flag%5]).start();
		condition.setAppID(appIDs[flag%5]);
		return ichibaItemSearch(condition);
	}

	public HashSet<String> getShopCodes(String jsonString, HashMap<String, String> shopNameMap, HashMap<String, String> shopUrlMap) {
		HashSet<String> shopSet = new HashSet<String>();
		SearchResult searchResult = JSON.parseObject(jsonString, SearchResult.class);
		List<HashMap<String, Item>> items = searchResult.getItems();
		for(HashMap<String, Item> item_a : items){
			Item item = item_a.get("Item");
			shopSet.add(item.getShopCode());
			shopNameMap.put(item.getShopCode(), item.getShopName());
			shopUrlMap.put(item.getShopCode(), item.getShopUrl());
		}
		return shopSet;
	}

	public Item getItemFromJson(String jsonString, int num) {
		SearchResult searchResult = JSON.parseObject(jsonString, SearchResult.class);
		return searchResult.getItems().get(num).get("Item");
	}
	


}
