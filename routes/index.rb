

class RakApp < Sinatra::Base
  get '/' do
    haml :index
  end
  get '/search' do
    haml :search
  end
end
