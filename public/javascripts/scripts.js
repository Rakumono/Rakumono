
function renderRow(k, id, keynum, mono_count) {
    
    get_shop_mono(k, id, keynum, mono_count);
    
}
function renderAnother(k) {
    if (k < $('.renderable').length) {
	//alert(k);
	var dom = $('.renderable').eq(k);
	var id = dom.attr('id');
	var count = 0;
	renderRow(k, id, 0, 3);
	//var key_total = $("input[name='keyword']").length;
	
    }
}


function remove_loading(id) {
    $("#" + id + " > .loading_img").remove();
    //alert(id);
}

function get_shop_mono(k, id, keynum, mono_num) {
    //alert("SB");
    var url = "item";
    var keyword_dom = $("input[name='keyword']");
    if (keynum >= keyword_dom.length) {
	remove_loading(id);
	renderAnother(k+1);
	return;
    }
    var keyword = keyword_dom.eq(keynum).val();
    $.ajax( {
	url: url,
	type: 'get',
	data: {shopname: id, keyword: keyword, item_num: mono_num},
	dataType: 'json',
	success: function(data) {
	    var cate_template = '<h2 class="category-title">{::keyword::}</h2><div class="mono-list">{::monobox::}<div class="clear"></div></div>';
	    var mono_template = '<div class="mono-box" code="{::itemCode::}"><button class="cart-btn togglable" toggled="false" price="{::itemPrice::}"><i class="fa fa-shopping-cart"></i></button><a class="mono-avater" href="{::itemUrl::}"><img src="{::imageUrl::}"><div class="mono-price-corner"><span class="mono-price">&yen;&nbsp;{::itemPrice::} </span></div></a><div class="mono-info"><div class="mono-title"><a href="{::itemUrl::}">{::itemName::}</a></div><div class="mono-rank"><span>Rank: {::reviewAverage::}</span>&nbsp; &nbsp;<span>Reviews: {::reviewCount::}</span></div></div></div>';
	    //alert(data['item']['itemName']);
	    var str = "";
	    var items = data['item'];
	    //for (var i = 0; i < items.length; i ++) {
	    var t = mono_template;
	    for (var i = 0; i < items.length; i ++)
	    {
		var item = items[i];
		str += t.replace(/{::itemUrl::}/g, item['itemUrl']).replace(/{::itemPrice::}/g, item['itemPrice'])
		    .replace(/{::itemPoint::}/g, item['itemPoint']).replace(/{::itemName::}/g, item['itemName'])
		    .replace(/{::reviewAverage::}/g, item['reviewAverage']).replace(/{::reviewCount::}/g, item['reviewCount']).replace(/{::imageUrl::}/g, item['imageUrl']);
		
	    }
	    var result = cate_template.replace(/{::keyword::}/g, keyword).replace(/{::monobox::}/g , str);
	    //alert(result);
	    $(result).insertBefore($("#" + id + "> .loading_img"));
	    //alert(result);
	    renderRow(k, id, keynum+1, mono_num);
	}
    });
    
}

$(document).ready(function(){
    $("#monolist").on('click', '.folder-btn', function(){
  
    });
    $('#monolist').fullpage( {
	scrollOverflow: true
    });
    
    
    var totalprice = 0;
  
    $("#monolist").on('click', '.togglable', function(){
	//alert("SB");
	//alert($(this).attr("toggled"));
	function refreshTotalPrice() {
	    $("#total-price").html("&yen;&nbsp;&nbsp;" + totalprice);
	}
	if ($(this).attr("toggled") == "false") {
	    $(this).attr("toggled" ,"true");
	    $(this).addClass("cart-selected");
	    totalprice += parseInt($(this).attr("price"));
	    refreshTotalPrice();
	} else {
	    $(this).attr("toggled" , "false");
	    $(this).removeClass("cart-selected");
	    totalprice -= parseInt($(this).attr("price"));
	    refreshTotalPrice();
	}
    });
    
    
    var index = 0;
    renderAnother(0);
    
});

