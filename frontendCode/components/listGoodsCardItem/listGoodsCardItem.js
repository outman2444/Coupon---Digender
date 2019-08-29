// components/listGoodsCardItem/listGoodsCardItem.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

    mallName: {
      type: String,
      value: '商铺名称',
    },
    goodsName: {
      type: String,
      value: '商品名称',
    },
    goodsThumbnailUrl: {
      type: String,
      value: '商品缩略图',
    },
    merchantType: {
      type: String,
      value: '店铺类型，1-个人，2-企业，3-旗舰店，4-专卖店，5-专营店，6-普通店',
    },
    couponDiscount: {
      type: String,
      value: '优惠券面额，单位为分',
    },
    promotionRate: {
      type: String,
      value: '佣金比例，千分比',
    },
    salesTip: {
      type: String,
      value: '已售卖件数',
    },
    activityType: {
      type: String,
      value: '活动类型，0-无活动;1-秒杀;3-限量折扣;12-限时折扣;13-大促活动;14-名品折扣;15-品牌清仓;16-食品超市;17-一元幸运团;18-爱逛街;19-时尚穿搭;20-男人帮;21-9块9;22-竞价活动;23-榜单活动;24-幸运半价购;25-定金预售;26-幸运人气购;27-特色主题活动;28-断码清仓;29-一元话费;30-电器城;31-每日好店;32-品牌卡;101-大促搜索池;102-大促品类分会场;',
    },
    cltCpnDiscount: {
      type: String,
      value: '店铺收藏券面额，单位为分',
    },
    totalCount: {
      type: String,
      value: '返回商品总数',
    },
 
  },

  /**
   * 组件的初始数据
   */
  data: {
    merchantTypeName: ["", "个人", "企业", "旗舰店", "专卖店", "专营店", "普通店"]
  },

  /**
   * 组件的方法列表
   */
  methods: {

  }
})