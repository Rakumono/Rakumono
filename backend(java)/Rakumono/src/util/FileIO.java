package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class FileIO {

	public static int writeIntoFile(String path, List<String> list){
		try {
			FileWriter fw = new FileWriter(path, true);
			for(String line : list){
				fw.write(line + "\n");
			}
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int writeIntoFile(String path, HashMap<String, String> list){
		try {
			FileWriter fw = new FileWriter(path, true);
			for(String key : list.keySet()){
				fw.write(key + ":" + list.get(key) + "\n");
			}
			
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int writeGenres(String path, HashMap<String, Integer> list){
		try {
			FileWriter fw = new FileWriter(path, true);
			for(String key : list.keySet()){
				fw.write("\"" + key + "\",");
			}
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int clearFile(String path){
		try {
			FileWriter fw = new FileWriter(path);
		    fw.write("");
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int writeHashMap(String path, HashMap<String, String> map){
		try {
			FileWriter fw = new FileWriter(path);
		    fw.write(JSON.toJSONString(map));
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
