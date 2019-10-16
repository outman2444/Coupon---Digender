const app = getApp()
Page({
  data: {
    PageCur: 'my'
  },
  NavChange(e) {
    console.info("当前页面路径：" + e.currentTarget.dataset.cur)
    this.setData({
      PageCur: e.currentTarget.dataset.cur
    })
  },
  onLoad: function(e) {
    console.info("index 信息")
    console.info(e)
    // 推广人openId
    let fromOpenId = e.openId;
    // app.updateUserInfo(1001,fromOpenId);

  },
  /**
   * 分享 转发
   */
  onShareAppMessage: function(res) {

    return {
      title: res.target.dataset.paramsTitle,
      path: 'pages/index/index?openId=' + res.target.dataset.paramsOpenid,
      imageUrl: res.target.dataset.paramsImageurl,
      
    }
  },

})