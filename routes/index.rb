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
    string = HTTP.get URI("#{API_HOST}api_num=1&keywords=#{keywords.join("%3E")}")
  end

  get '/item' do
    content_type :json
    item_num = 3 unless params['item_num']
    string = HTTP.get URI("#{API_HOST}api_num=2&shopname=#{params[:shopname]}&word=#{params[:keyword]}&item_num=#{item_num}")
  end

end
