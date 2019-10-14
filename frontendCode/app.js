//app.js
const ajaxUtil = require("./utils/ajaxUtil.js")
App({

  globalData: {
    userInfo: null,
    openId:null,
    ColorList: [{
        title: '嫣红',
        name: 'red',
        color: '#e54d42'
      },
      {
        title: '桔橙',
        name: 'orange',
        color: '#f37b1d'
      },
      {
        title: '明黄',
        name: 'yellow',
        color: '#fbbd08'
      },
      {
        title: '橄榄',
        name: 'olive',
        color: '#8dc63f'
      },
      {
        title: '森绿',
        name: 'green',
        color: '#39b54a'
      },
      {
        title: '天青',
        name: 'cyan',
        color: '#1cbbb4'
      },
      {
        title: '海蓝',
        name: 'blue',
        color: '#0081ff'
      },
      {
        title: '姹紫',
        name: 'purple',
        color: '#6739b6'
      },
      {
        title: '木槿',
        name: 'mauve',
        color: '#9c26b0'
      },
      {
        title: '桃粉',
        name: 'pink',
        color: '#e03997'
      },
      {
        title: '棕褐',
        name: 'brown',
        color: '#a5673f'
      },
      {
        title: '玄灰',
        name: 'grey',
        color: '#8799a3'
      },
      {
        title: '草灰',
        name: 'gray',
        color: '#aaaaaa'
      },
      {
        title: '墨黑',
        name: 'black',
        color: '#333333'
      },
      {
        title: '雅白',
        name: 'white',
        color: '#ffffff'
      },
    ],
    normalSceneList: [1001, 1005, 1006, 1007, 1027]
  },
  onLaunch: function(e) {
    console.info("app方法参数")
    console.info(e)

    // 获取系统信息
    wx.getSystemInfo({
      success: e => {
        this.globalData.StatusBar = e.statusBarHeight;
        let custom = wx.getMenuButtonBoundingClientRect();
        this.globalData.Custom = custom;
        this.globalData.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
      }
    })

    // 登录
    wx.login({
      success: res => {
        console.info(res)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        ajaxUtil.doPost({
          url: "loginByCode",
          params: {
            wxCode: res.code
          },
        }).then(res => {
          let body = JSON.parse(res.body)
          console.info("openid -->" + body.openid)    
          this.globalData.openId =body.openid

          // 更新用户信息
            this.updateUserInfo(e.scene)
          
        })
      }
    })
   
    

  
  },
  /**
   * 更新用户信息
   */
  updateUserInfo: function (scene, fromOpenId) {

    // 判断场景值  延时更新用户信息
    console.info("更i性能->>" + this.globalData.normalSceneList.indexOf(scene))
    if (this.globalData.normalSceneList.indexOf(scene) != -1) {
      return false;
    }

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }

              // 更新后台用户信息
              console.info("开始执行更新用户信息")
              ajaxUtil.doPost({
                url: "updateUserInfo",
                params: {
                  avatarUrl: res.userInfo.avatarUrl,
                  city: res.userInfo.city,
                  country: res.userInfo.country,
                  gender: res.userInfo.gender,
                  language: res.userInfo.language,
                  nickName: res.userInfo.nickName,
                  province: res.userInfo.province,
                  openId: this.globalData.openId,
                  fromOpenId: fromOpenId
                },
              }).then(res => {
                console.info(res)
              })      
              console.info("结束执行更新用户信息")
              
            }
          })
        }
      }

    })
   
   

  },
  /**将ajaxUtils 挂在全局 */
  ajaxUtil: ajaxUtil
})