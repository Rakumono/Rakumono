require 'sinatra'
require 'erb'

get '/index' do
  erb :index
end


get '/subject' do
  erb :subject
end


get '/subject2' do
  erb :subject2
end