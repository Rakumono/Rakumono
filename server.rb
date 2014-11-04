# -*- coding: utf-8 -*-
require 'sinatra'
require 'json'


get '/' do
  erb :index
end

get '/:name' do
  def read_json subject
    jstr = File.read "data/#{subject}.json"
    json = JSON.parse jstr

    data = {}
    json.each do |genre|
      items = []
      genre['items'].each do |item|
        imageUrl = item['mediumImageUrls'][0]['imageUrl']
        items << item.delete_if {|k,v| ['affiliateRate', 'affiliateUrl', 'asurakuArea', 'asurakuClosingTime', 'asurakuFlag', 'availability', 'catchcopy', 'creditCardFlag', 'imageFlag', 'itemCode', 'shopUrl', 'endTime', 'genreId', 'giftFlag', 'itemCaption', 'pointRateEndTime', 'pointRateStartTime', 'postageFlag', 'shipOverseasArea', 'shipOverseasFlag', 'shopAffiliateUrl', 'shopCode', 'shopName', 'shopOfTheYearFlag','smallImageUrls', 'startTime','taxFlag','mediumImageUrls'].include? k}.merge({"imageUrl" => imageUrl.gsub("128x128","200x200")})
      end
      data.merge!({genre['genreName'] => items})
    end
    data
  end
  data = read_json params[:name]
  erb :subject, :locals => {:data => data}
end

__END__
