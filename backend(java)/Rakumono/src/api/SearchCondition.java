package api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchCondition {
	private String appID = "1027344104687047127";
	private final static String format = "json";
	private String keyword;
	private String genreId;
	private String shopCode;
	private String hits;
	private String page;
	private String sort_method;
	private String minPrice;
	private String maxPrice;
	private String availability;
	private String imageFlag;
	private String NGKeyword;
	private final static String orFlag = "1";
	private String purchaseType;
	private String pointRateFlag;
	private String pointRate;
	private String postageFlag;
	private String creditCardFlag;
	private String giftFlag;
	private String hasReviewFlag;
	private String genreInformationFlag;
	
	public SearchCondition(){
		genreId="";
		shopCode="";
		hits="";
		page="";
	}
	
	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getFormat() {
		return format;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
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

	public String getSort_method() {
		return sort_method;
	}

	public void setSort_method(String sort_method) {
		this.sort_method = sort_method;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getImageFlag() {
		return imageFlag;
	}

	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}

	public String getNGKeyword() {
		return NGKeyword;
	}

	public void setNGKeyword(String nGKeyword) {
		NGKeyword = nGKeyword;
	}

	public String getOrFlag() {
		return orFlag;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getPointRateFlag() {
		return pointRateFlag;
	}

	public void setPointRateFlag(String pointRateFlag) {
		this.pointRateFlag = pointRateFlag;
	}

	public String getPointRate() {
		return pointRate;
	}

	public void setPointRate(String pointRate) {
		this.pointRate = pointRate;
	}

	public String getPostageFlag() {
		return postageFlag;
	}

	public void setPostageFlag(String postageFlag) {
		this.postageFlag = postageFlag;
	}

	public String getCreditCardFlag() {
		return creditCardFlag;
	}

	public void setCreditCardFlag(String creditCardFlag) {
		this.creditCardFlag = creditCardFlag;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}

	public String getHasReviewFlag() {
		return hasReviewFlag;
	}

	public void setHasReviewFlag(String hasReviewFlag) {
		this.hasReviewFlag = hasReviewFlag;
	}

	public String getGenreInformationFlag() {
		return genreInformationFlag;
	}

	public void setGenreInformationFlag(String genreInformationFlag) {
		this.genreInformationFlag = genreInformationFlag;
	}

	/**
	 * Get XML String of utf-8
	 * 
	 * @return XML-Formed string
	 */
	public String getUTF8String(String xml) {
		// A StringBuffer Object
		StringBuffer sb = new StringBuffer();
		sb.append(xml);
		String xmString = "";
		String xmlUTF8 = "";
		try {
			xmString = new String(sb.toString().getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
			System.out.println("utf-8 编码：" + xmlUTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// return to String Formed
		return xmlUTF8;
	}
}
