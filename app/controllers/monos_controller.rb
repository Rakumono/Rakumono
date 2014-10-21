class MonosController < ApplicationController
  respond_to :html, :xml, :json
  before_action :set_mono, only: [:show, :edit, :update, :destroy]

  def index
    @monos = Mono.all
    respond_with(@monos)
  end

  def show
    respond_with(@mono)
  end

  def new
    @mono = Mono.new
    respond_with(@mono)
  end

  def edit
  end

  def create
    @mono = Mono.new(mono_params)
    @mono.save
    respond_with(@mono)
  end

  def update
    @mono.update(mono_params)
    respond_with(@mono)
  end

  def destroy
    @mono.destroy
    respond_with(@mono)
  end

  private
    def set_mono
      @mono = Mono.find(params[:id])
    end

    def mono_params
      params.require(:mono).permit(:itemcode)
    end
end
