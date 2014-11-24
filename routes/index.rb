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
    item_num = 3 unless params['item_num']
    string = HTTP.get URI(URI::escape("#{API_HOST}api_num=2&shopname=#{params[:shopname]}&word=#{params[:keyword]}&item_num=#{item_num}"))
  end
  
  get '/shop' do
    haml :mono
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

