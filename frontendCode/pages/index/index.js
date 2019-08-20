const app = getApp()

Page({
  data: {
    PageCur: 'supersearch'
  },
  NavChange(e) {
    console.info("当前页面路径：" + e.currentTarget.dataset.cur)
    this.setData({
      PageCur: e.currentTarget.dataset.cur
    })
  },
  onLoad: function () {

  }
})