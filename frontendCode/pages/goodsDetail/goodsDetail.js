// pages/goodsDetail/goodsDetail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods_details: {},
    merchantTypeName: ["", "精选", "企业", "旗舰店", "专卖店", "专营店", "精选"],
    serviceTagName: {
      "4": "送货入户并安装",
      "5": "送货入户",
      "6": "电子发票",
      "9": "坏果包赔",
      "11": "闪电退款",
      "12": "24小时发货",
      "13": "48小时发货",
      "17": "顺丰包邮",
      "18": "只换不修",
      "19": "全国联保",
      "20": "分期付款",
      "24": "极速退款",
      "25": "品质保障",
      "26": "缺重包退",
      "27": "当日发货",
      "28": "可定制化",
      "29": "预约配送",
      "1000001": "正品发票",
      "1000002": "送货入户并安装"
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let goodsid = options.goodsid || "" + "7293210543"
    console.info("商品ID ->" + goodsid)
    app.ajaxUtil.doPost({
      url: "goodsDetail",
      params: {
        "vc2GoodId": goodsid
      },
    }).then(res => {

      let body = JSON.parse(res.body)
      let goodsDetail = body.goods_detail_response.goods_details[0]
      console.info("商品详情 -------------")
      console.info(goodsDetail)
      console.info("商品详情 -------------")
      this.setData({
        "goods_details": goodsDetail
      })
    })

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

  }
})