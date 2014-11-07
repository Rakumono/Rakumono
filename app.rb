# -*- coding: utf-8 -*-
require 'sinatra'
require 'json'
require 'haml'

require_relative 'helpers/init'
require_relative 'models/init'
require_relative 'routes/init'


class RakApp < Sinatra::Base

  helpers do 
    def read_json subject
      jstr = File.read "#{APPROOT}/data/#{subject}.json"
      json = JSON.parse jstr

      data = {}
      json.each do |genre|
        items = []
        genre['items'].each do |item|
          imageUrl = item['mediumImageUrls'][0]['imageUrl']
          itemName = item['itemName'][0..35] + "..."
          items << item.delete_if {|k,v| ['affiliateRate', 'affiliateUrl', 'asurakuArea', 'asurakuClosingTime', 'asurakuFlag', 'availability', 'catchcopy', 'creditCardFlag', 'imageFlag', 'itemCode', 'shopUrl', 'endTime', 'genreId', 'giftFlag', 'itemCaption', 'pointRateEndTime', 'pointRateStartTime', 'postageFlag', 'shipOverseasArea', 'shipOverseasFlag', 'shopAffiliateUrl', 'shopCode', 'shopName', 'shopOfTheYearFlag','smallImageUrls', 'startTime','taxFlag','mediumImageUrls'].include? k}.merge({"imageUrl" => imageUrl.gsub("128x128","300x300")}).update({"itemName" => itemName})
        end
        data.merge!({genre['genreName'] => items})
      end
      data
    end
  end

  get '/' do
    erb :index
  end

  post '/' do
    redirect "/#{params[:keyword]}"
  end


  get '/tagsinput' do
    haml :com_tagsinput
  end

  get '/testangular' do
    haml :testangular
  end

  get '/:name' do
    if (File.exists? "#{APPROOT}/data/#{params[:name]}.json")
      data = read_json params[:name] 
    else
      halt(429,(erb :running, :layout => false, :locals => {:keyword => params[:name]}))
    end
    erb :subject, :locals => {:data => data}
  end

end
__END__