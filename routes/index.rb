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
    keywords_param = keywords.join("%3E").strip
    string = HTTP.get URI("#{API_HOST}api_num=1&keywords=#{keywords_param}")
  end

  get '/item' do
    content_type :json
    item_num = 3 unless params['item_num']
    string = HTTP.get URI("#{API_HOST}api_num=2&shopname=#{params[:shopname]}&word=#{params[:keyword]}&item_num=#{item_num}")
  end
  
  get '/shop' do
    haml :mono
  end

end

