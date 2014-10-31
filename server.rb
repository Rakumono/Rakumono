# -*- coding: utf-8 -*-
require 'sinatra'

get '/' do
  erb :index
end

get '/nodes.json' do 
  content_type :json
  rawdata = <<-JSON
{
    "nodes":
    {
    "SUIEI":{"color":"red","shape":"dot","alpha":1},
    "boushi":{"color":"#b2b19d","shape":"dot","alpha":1},
    "halfviz":{"color":"#a7af00","alpha":0,"link":"/halfviz","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/00000/21114-0700930.jpg?_ex=96x96","caption":"日本語テスト"},
    "atlas":{"color":"#a7af00","alpha":0,"link":"/atlas","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/10019/33477-1001949.jpg?_ex=96x96","caption":"Maybe中国語も使えるかも、試したくない、もし長長長長長ければどうすりゃいいんだろなぁ"},
    "echolalia":{"color":"#a7af00","alpha":0,"link":"/echolalia","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/08105/33160-0810534.jpg?_ex=96x96","caption":"This is my test node"},
    "docs":{"color":"#b2b19d","shape":"dot","alpha":1},
    "reference":{"color":"#922E00","alpha":0,"link":"#reference","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/00000/21127-0700477.jpg?_ex=96x96","caption":"This is my test node"},
    "introduction":{"color":"#922E00","alpha":0,"link":"#introduction","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/00000/21114-0701213.jpg?_ex=96x96","caption":"This is my test node"},
    "code":{"color":"#b2b19d","shape":"dot","alpha":1},
    "shuiyong":{"color":"orange","alpha":0,"link":"https://github.com/samizdatco/arbor","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/08127/21127-0812768.jpg?_ex=96x96","caption":"This is my test node"},
    ".zip":{"color":"orange","alpha":0,"link":"/js/dist/arbor-v0.92.zip","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/e-zakkamania/cabinet/08117/32187-0811753.jpg?_ex=96x96","caption":"This is my test node"},
    ".tar.gz":{"color":"orange","alpha":0,"link":"/js/dist/arbor-v0.92.tar.gz","src":"http://thumbnail.image.rakuten.co.jp/@0_mall/bunbunbee/cabinet/sweetsbuoquet/400/ca-usc-002b.jpg?_ex=96x96","caption":"This is my test node"}},
 "edges":
    {
     "SUIEI":{"boushi":{"length":0.05},"docs":{"length":0.05},"code":{"length":0.05}},
     "boushi":{"halfviz":{},"atlas":{},"echolalia":{}},
     "docs":{"reference":{},"introduction":{}},
     "code":{".zip":{},".tar.gz":{},"shuiyong":{}}
    }
}
  JSON
end
