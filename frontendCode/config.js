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
      "manage": "192.168.0.107:10000",
      "goods": "192.168.0.107:10001",
      "order": "",
      "promotion": ""
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
    "loginByCode":"user/loginByCode"
  },
  "goods": {
    "searchGoods":"goods/search"
  },
  "order": {

  },
  "promotion": {

  }
}