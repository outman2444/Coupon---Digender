// pages/goodsSearch/goodsSearch.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showHistoryNoList:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  /**
   * 点击搜索框 显示搜索历史
   */
  showHistoryView:function () {
    this.setData({
      showHistoryNoList:false,
    })
  },
  /**
   * 搜索商品
   */
  searchGoods:function (){
    // 隐藏 搜索历史  显示商品列表
    this.setData({
      showHistoryNoList: true,
    })

    // 请求 搜索商品列表
    app.ajaxUtil.doPost({
      url: "searchGoods",
      params: {
        // wxCode: res.code
      },
    }).then(res => {
      console.info(res)
    })      

  }
})