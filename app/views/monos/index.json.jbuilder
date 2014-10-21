json.array!(@monos) do |mono|
  json.extract! mono, :id, :itemcode
  json.url mono_url(mono, format: :json)
end
