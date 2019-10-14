const app = getApp();
Component({
  options: {
    addGlobalClass: true,
    
  },
  data: {
    userInfo: {},
    openId:"",
    saveingAmount:0.00,
    promotionAmount: 0.00,
    totalAmount: 0.00,
    shareTitle: "自买省钱，推广赚钱，优惠券多多",
    shareImageUrl: "/images/basicprofile.png",

  },
  /**
   * 生命周期
   */
  lifetimes:{
    //实例被创建时执行
    created:function(){
 
    },
    // 实例被载入页面节点时执行
    attached:function(){
      this.setData({
        userInfo: app.globalData.userInfo,
        openId: app.globalData.openId
      })
      console.info(this.data.userInfo)
    }
  },
  methods: {
    getUserInfo:function(){
      app.updateUserInfo();
      this.setData({
        userInfo: app.globalData.userInfo,
        openId: app.globalData.openId
      })
    }
  },

})