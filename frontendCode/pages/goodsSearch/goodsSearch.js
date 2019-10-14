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
    goodsList: [],
    goodsNameInput:'', // 商品名称搜索
    sortType:{ 
      "0":false,
      "2": false,
      "6": false,
      "8": false
    }, // 排序类型
    page:1,  // 页号
    historyList:[]
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
    this.setData({
      page:this.data.page+1
    })
    this.searchGoods(false)
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
    // 显示搜索历史试图
    this.setData({
      showView: 1,
      goodsNameInput:""
    })

    console.info("查询搜索历史")
    // 查询搜索历史
    app.ajaxUtil.doPost({
      url: "querySearchHistory",
      params: {
        vc2OpenId: app.globalData.openId
      },
    }).then(res => {
      this.setData({
        historyList: res.body
      })

    })

  },
  /**
   * 按钮触发 搜索商品
   */
  btnsearchGoods:function(){
    this.setData({
      page: 1
    })
    this.searchGoods(true)
  },
  /**
   * 搜索商品
   */
  searchGoods: function(isclear) {

    // 隐藏 搜索历史  显示商品列表
    this.setData({
      showView: 2,
    })

    let finalSortType = 0;
    Object.keys(this.data.sortType).forEach((key) =>{
      if (this.data.sortType[key]){
        finalSortType = key;
      }
    })

    let params =  {
      vc2GoodName: this.data.goodsNameInput,
      sortType: finalSortType,
      page:this.data.page,
      vc2OpenId: app.globalData.openId
    }
    console.info("搜索商品参数 -->")
    console.info(params)
    // 请求 搜索商品列表
    app.ajaxUtil.doPost({
      url: "searchGoods",
      params: params,
    }).then(res => {
      let body = JSON.parse(res.body)
      let list = body.goods_search_response.goods_list;
      if (list.length <= 0) {
        this.setData({
          showView: 3,
        })
      } else {
        let nowList = this.data.goodsList;
        if (isclear){
          nowList = []
        }
        list.map((val, index) => {
          nowList.push(val)
        })
        this.setData({
          goodsList: nowList,
        })
      }

    })

  },
  /**
   * 跳转商品详情页
   */
  toGoodsDetail: function(event) {
    
    let goodsid = event.target.dataset.goodsid
    wx.navigateTo({
      url: '/pages/goodsDetail/goodsDetail?goodsid=' + goodsid,
    })

  },

  /**
   * 商品名称搜索输入框
   */
  goodsNameInput: function(e){
    this.setData({
      goodsNameInput:e.detail.value
    })
  },
  /**
   * 选中搜索栏选项
   */
  selectSearchBarBtn:function(e){
    let type = e.target.dataset.type;

    // 将所有搜索栏重置
    let sortType = {
      "0":false,
      "2": false,
      "6": false,
      "8": false
    }
    // 选中当前按钮
    sortType[type] = true;

    this.setData({
      sortType: sortType
    })
    
    // 执行搜索
    this.searchGoods(true)
    
    // 使页面回到顶部
    wx.pageScrollTo({
      scrollTop: 0,
      duration: 500
    })
  },
  /**
   * 点击搜索历史设置 搜索框
   */
  setSearchText:function (e){
    let text = e.target.dataset.text;
    this.setData({
      goodsNameInput: text
    })
  }

})