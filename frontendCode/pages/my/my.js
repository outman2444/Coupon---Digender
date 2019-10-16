const app = getApp();
Component({
  options: {
    addGlobalClass: true,
    
  },
  data: {
    userInfo: {},
    hasUserInfo: false,
    openId:"",
    totalInfo:{
      estimate_promotion_amount_totla:0,
      order_amount_totaol:0,
      primotion_amount_total:0
    },
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


      // 初始化页面用户数据
      app.updateUserInfo(()=> {
        this.setData({
          userInfo: app.globalData.userInfo,
          hasUserInfo: true,
          openId: app.globalData.openId,
        })
        console.info("页面数据：")
        console.info(this.data)

        // 初始化页面统计数据
        this.queryStatisticsInfo()
      })

    }
  },
  methods: {
    
    /**
     * 获取用户统计数据
     */
    queryStatisticsInfo:function (){
      app.ajaxUtil.doPost({
        url: "queryStatisticsInfo",
        params: {
          "vc2OpenId": this.data.openId
        },
      }).then(res => {
        console.info(res)
        if (res.respCode == 10000) {
          this.setData({
            totalInfo : res.body
          })
        }
      })
    }
  },

})