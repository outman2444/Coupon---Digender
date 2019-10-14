// pages/goodsDetail/goodsDetail.js
const app = getApp()
import Dialog from '../../vantui/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goodsid:'',
    goods_details: {},
    goods_collect:false,
    van_dialog_show:false,
    van_dialog_text:"",
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
    let goodsid = options.goodsid || "" + "16910971023"
    console.info("商品ID ->" + goodsid)
    // 保存goodsid
    this.setData({
      goodsid: goodsid
    })
    //请求商品详情
    app.ajaxUtil.doPost({
      url: "goodsDetail",
      params: {
        "vc2GoodId": goodsid,
        "vc2OpenId": app.globalData.openId
      },
    }).then(res => {

      // let body = JSON.parse(res.body)
      let body = res.body
      let goodsDetail = body.goods_detail_response.goods_details[0]
      console.info("商品详情 -------------")
      console.info(goodsDetail)
      console.info("商品详情 -------------")
      this.setData({
        "goods_details": goodsDetail,
        "goods_collect": body.goods_detail_response.isCollect
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

  },

  /**
   * ；领取优惠券
   */
  takeCoupons:function (){
    console.info("领取优惠券")
    app.ajaxUtil.doPost({
      url: "takeCoupons",
      params: {
        "vc2GoodId": this.data.goodsid,
        "vc2OpenId": app.globalData.openId
      },
    }).then(res => {
      console.info(res)
      if (res.respCode == 10000){
        //打开拼多多小程序
        this.openPddMiniProgram(res.body)
      }
    })
  },

  /**
   * 打开拼多多小程序
   */
  openPddMiniProgram:function(info){
    console.info("打开拼多多小程序")
    wx.navigateToMiniProgram({
      appId: info.app_id,
      path: info.page_path,
      success(res) {
        // 打开成功
      }
    })
  },
  /**
   * 分享商品
   */
  //转发
  onShareAppMessage: function (res) {
    return {
      title: this.data.goods_details.goods_name,
      path: 'pages/goodsDetail/goodsDetail?goodsid=' + this.data.goodsid,
      imageUrl: this.data.goods_details.goods_gallery_urls[0]
    }
  },

  /**
   * 收藏
   */
  goodsCollection:function(){
    app.ajaxUtil.doPost({
      url: "goodsCollection",
      params: {
        "vc2GoodId": this.data.goodsid,
        "vc2OpenId": app.globalData.openId
      },
    }).then(res => {
      console.info(res)
      if (res.respCode == 10000) {

        this.setData({
          goods_collect: !res.body.numDelFlag,
          van_dialog_show:true,
          van_dialog_text: !res.body.numDelFlag ? "收藏成功":"取消收藏成功"
        })

      }
    })
  },

  

})