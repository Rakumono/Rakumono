class List < ActiveRecord::Base
  has_and_belongs_to_many :monos
  belongs_to :user
end
