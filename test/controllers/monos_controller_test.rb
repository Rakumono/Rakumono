require 'test_helper'

class MonosControllerTest < ActionController::TestCase
  setup do
    @mono = monos(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:monos)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create mono" do
    assert_difference('Mono.count') do
      post :create, mono: { itemcode: @mono.itemcode }
    end

    assert_redirected_to mono_path(assigns(:mono))
  end

  test "should show mono" do
    get :show, id: @mono
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @mono
    assert_response :success
  end

  test "should update mono" do
    patch :update, id: @mono, mono: { itemcode: @mono.itemcode }
    assert_redirected_to mono_path(assigns(:mono))
  end

  test "should destroy mono" do
    assert_difference('Mono.count', -1) do
      delete :destroy, id: @mono
    end

    assert_redirected_to monos_path
  end
end
