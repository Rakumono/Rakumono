class CreateMonos < ActiveRecord::Migration
  def change
    create_table :monos do |t|
      t.string :itemcode

      t.timestamps
    end
  end
end
