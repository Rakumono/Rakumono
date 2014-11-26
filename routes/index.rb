# -*- coding:utf-8 -*-
require 'net/http'
require 'json'

class RakApp < Sinatra::Base
  include Net
  API_HOST = "http://rakumono.cloudapp.net:8080/wordfind/index.jsp?"
  get '/' do
    haml :index
  end
  
  get '/search' do
    haml :search
  end

  post '/' do
    content_type :json
    keywords = params[:keyword].split ","
    keywords_param = keywords.join(">").strip
    string = HTTP.get URI( URI::escape("#{API_HOST}api_num=1&keywords=#{keywords_param}"))
  end

  get '/item' do
    content_type :json
    string = HTTP.get URI(URI::escape("#{API_HOST}api_num=2&shopname=#{params[:shopname]}&word=#{params[:keyword]}&num=#{params[:item_num]}"))
    items = JSON.parse string
    items["item"].each do |item|
      p item
      item[:imageUrl] = item["mediumImageUrls"][0]["imageUrl"].gsub '128x128', '300x300'
      item.delete_if do |k, v|
        ["mediumImageUrls", "smallImageUrls"].include? k
      end
    end
    items.to_json
  end
  
  get '/shop' do
    haml :mono
  end
  

  get '/cart' do
    haml :cart
  end
  
  post '/search' do
    #content_type :json
    keywords = params[:keyword].split ","
    keywords_param = keywords.join(">")
    @monos = Array.new();
    keywords.each do |k|
      @monos.push k.strip
    end
    string = HTTP.get URI( URI::escape("#{API_HOST}api_num=1&keywords=#{keywords_param}"))
    @searchinfo = JSON.parse(string)
    haml :mono
  end
  
end

