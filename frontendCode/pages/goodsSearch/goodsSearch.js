// pages/goodsSearch/goodsSearch.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    showView: 1, // 1. 搜索历史  2. 列表   3. 空数据页面
    goodsList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.searchGoods()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  /**
   * 点击搜索框 显示搜索历史
   */
  showHistoryView: function() {
    this.setData({
      showView: 1,
    })
  },
  /**
   * 搜索商品
   */
  searchGoods: function() {
    // 隐藏 搜索历史  显示商品列表
    this.setData({
      showView: 2,
    })

    // 请求 搜索商品列表
    app.ajaxUtil.doPost({
      url: "searchGoods",
      params: {
        // wxCode: res.code
      },
    }).then(res => {
      let body = JSON.parse(res.body)
      let list = body.goods_search_response.goods_list;
      if (list.length <= 0) {
        this.setData({
          showView: 3,
        })
      } else {
        let nowList = this.data.goodsList;
        list.map((val, index) => {
          nowList.push(val)
        })
        this.setData({
          goodsList: nowList,
        })
      }
      console.info(this.data.goodsList)

    })

  },
  /**
   * 跳转商品详情页
   */
  toGoodsDetail: function(event) {
    console.info(event)
    let goodsid = event.target.dataset.goodsid
    console.info("商品ID->" + goodsid)
    wx.navigateTo({
      url: '/pages/goodsDetail/goodsDetail?goodsid=' + goodsid,
    })

  }

})