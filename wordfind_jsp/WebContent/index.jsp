<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8" import="service.*" import="java.util.*"%>

<% String keywords = request.getParameter("keywords");
	int api_num = Integer.parseInt(request.getParameter("api_num"));
	if(api_num == 1)
	{
		List<String> words = new ArrayList<String>();
		//out.println("keywords is :" + keywords + "<br/>");
		//keywords = "ab|cd|ef";
		String keyword_arrays[] = keywords.split(">");
		for(String item:keyword_arrays){
			words.add(item);
		}
		ShopDetect sd = new ShopDetect();
		out.println(sd.findShops(words));
	}
	
	if(api_num == 2)
	{
		//out.println("this is 2");
		String word = request.getParameter("word");
		String shopname = request.getParameter("shopname");
		String num = request.getParameter("num");
		ShopItemDetect sd = new ShopItemDetect();
		out.println(sd.getItemsInShop(word, num, shopname));
		
	}
	
	//int genre_num = Integer.parseInt(request.getParameter("genre_num"));
	//int item_num=Integer.parseInt(request.getParameter("item_num"));
	//out.println("keyword is:"+keyword+"<br/>");
	//out.println("genre_num is:"+genre_num+"<br/>");
	//out.println("item_num is:"+item_num+"<br/>");
   //KeywordDetect kd = new KeywordDetect();
   //out.println(kd.findRelatedWords(keyword, genre_num, item_num));
%>

