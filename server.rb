require 'sinatra'

get '/' do
  erb :index
end

get '/nodes.json' do 
  content_type :json
  rawdata = <<-JSON
"{"nodes":{"SUIEI":{"color":"red","shape":"dot","alpha":1},"boushi":{"color":"#b2b19d","shape":"dot","alpha":1},"halfviz":{"color":"#a7af00","alpha":0,"link":"/halfviz","src":"image/2.jpg","caption":"This is my test node"},"atlas":{"color":"#a7af00","alpha":0,"link":"/atlas"},"echolalia":{"color":"#a7af00","alpha":0,"link":"/echolalia"},"docs":{"color":"#b2b19d","shape":"dot","alpha":1},"reference":{"color":"#922E00","alpha":0,"link":"#reference"},"introduction":{"color":"#922E00","alpha":0,"link":"#introduction"},"code":{"color":"#b2b19d","shape":"dot","alpha":1},"shuiyong":{"color":"orange","alpha":0,"link":"https://github.com/samizdatco/arbor"},".zip":{"color":"orange","alpha":0,"link":"/js/dist/arbor-v0.92.zip"},".tar.gz":{"color":"orange","alpha":0,"link":"/js/dist/arbor-v0.92.tar.gz"}},"edges":{"SUIEI":{"boushi":{},"docs":{},"code":{}},"boushi":{"halfviz":{},"atlas":{},"echolalia":{}},"docs":{"reference":{},"introduction":{}},"code":{".zip":{},".tar.gz":{},"shuiyong":{}}}}"
  JSON
end
