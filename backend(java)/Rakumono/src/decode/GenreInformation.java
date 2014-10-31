package decode;

import java.util.HashMap;
import java.util.List;

public class GenreInformation {
	List<Genre> parent;
	List<Genre> current;
	List<HashMap<String,Genre>> children;
	public List<Genre> getParent() {
		return parent;
	}
	public void setParent(List<Genre> parent) {
		this.parent = parent;
	}
	public List<Genre> getCurrent() {
		return current;
	}
	public void setCurrent(List<Genre> current) {
		this.current = current;
	}
	public List<HashMap<String, Genre>> getChildren() {
		return children;
	}
	public void setChildren(List<HashMap<String, Genre>> children) {
		this.children = children;
	}
	
}
