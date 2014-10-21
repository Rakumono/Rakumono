class Comment < ActiveRecord::Base
  has_many :users_comments
  has_many :users, :through => :users_comments
end
