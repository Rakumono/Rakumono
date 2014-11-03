package decode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchResult {
	private String count;
	private String hits;
	private String page;
	private String first;
	private String last;
	private String carrier;
	private String pageCount;
	private List<HashMap<String, Item>> Items = new ArrayList<HashMap<String, Item>>();
	private List<GenreInformation> GenreInformation;
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public List<HashMap<String, Item>> getItems() {
		return Items;
	}
	public void setItems(List<HashMap<String, Item>> items) {
		Items = items;
	}
	public List<GenreInformation> getGenreInformation() {
		return GenreInformation;
	}
	public void setGenreInformation(List<GenreInformation> genreInformation) {
		GenreInformation = genreInformation;
	}
	
}
