/**
 * 全局配置
 */

/**
 * 运行环境
 */
export const condition = "dev"
// const condition = "test"
// const condition = "product"

/**
 * 运行环境配置
 */
export const conditionConf = {
  "dev": {
    "protocol": "http",
    "hostAndport": {
      "manage": "192.168.0.111:10000",
      "goods": "192.168.0.111:10001",
      "order": "192.168.0.111:10002",
      "promotion": "192.168.0.111:10003"
    }
  },
  "test": {
    "protocol": "https",
    "host&port": {
      "manage": "192.168.99.189:10000",
      "goods": "",
      "order": "",
      "promotion": ""
    }
  },
  "product": {
    "protocol": "https",
    "host&port": {
      "manage": "192.168.99.189:10000",
      "goods": "",
      "order": "",
      "promotion": ""
    }
  }
}

/**
 * 请求转发
 */
export const urlList = {
  "manage": {
    "hello":"user/hello",
    "loginByCode":"user/loginByCode",
    "updateUserInfo":"user/updateUserInfo"
  },
  "goods": {
    "searchGoods":"goods/search",
    "goodsDetail": "goods/detail",
    "goodsCollection":"goods/collection",
    "querySearchHistory":"goods/querySearchHistory"
  },
  "order": {

  },
  "promotion": {
    "takeCoupons":"promotion/takeCoupons"

  }
}