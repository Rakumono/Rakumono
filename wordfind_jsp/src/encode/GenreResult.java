package encode;

import java.util.List;

import decode.Item;

public class GenreResult {
	private int genreID;
	private String genreName;
	private int genreCount;
	private List<Item> items;
	public int getGenreID() {
		return genreID;
	}
	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public int getGenreCount() {
		return genreCount;
	}
	public void setGenreCount(int genreCount) {
		this.genreCount = genreCount;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
