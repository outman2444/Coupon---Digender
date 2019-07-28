/**
 * 全局请求封装
 */

/**
 * 引入外部模块
 */
const util = require("./util.js")

// 正确的接口响应码
// 成功  失败  异常
const respCde = [10000, 10001, 10002]

/**
 * 全局post请求封装
 */
export function doGet({
  url,
  params,
  extra,
  dataType,
  loading
}) {
  //显示loading
  util.loading("使出吃奶的劲儿拼命加载中... ...")
  // 执行请求
  return baseRequest("GET", {
    url,
    params,
    extra,
    dataType,
    loading
  });
}

/**
 * 全局get请求封装
 */
export function doPost({
  url,
  params,
  extra,
  dataType,
  loading
}) {
  //显示loading
  util.loading("使出吃奶的劲儿拼命加载中... ...")
  // 执行请求
  return baseRequest("POST", {
    url,
    params,
    extra,
    dataType,
    loading
  });
}

/**
 * 全局文件上传请求封装
 */
export function doUpload({
  url,
  filePath,
  name,
  formData,
  loading
}) {
  //显示loading
  util.loading("使出吃奶的劲儿拼命上传中... ...")
  // 执行请求
  return baseRequest("UPLOAD", {
    url,
    filePath,
    name,
    formData,
    loading
  });
}

/**
 * 基本请求工具
 */
function baseRequest(reqType, options) {
  const promise = new Promise((resolve, reject) => {
    const realOption = {}

    // 请求地址
    realOption.url = util.getUrl(options.url)

    //成功回调
    realOption.success = function(res) {
      // 如果有loading 则隐藏
      wx.hideLoading();
      // 解析请求结果
      if (res.statusCode < 400) {
        if (respCde.indexOf(res.data.respCode) >-1) {
          resolve(res.data)
        } else {
          util.sysWraningModal({
            title: "请求错误",
            content: "接口响应码不在小程序解析范围内"
          })
          reject(res.data)
        }
      } else {
        util.sysWraningModal({
          title: "请求错误",
          content: "接口请求失败，请检查参数类型"
        })
        reject(res.data)
      }
    }

    // 请求失败回调
    realOption.fail = function(e) {
      // 如果有loading 则隐藏
      wx.hideLoading();

      util.sysWraningModal({
        title: "请求错误",
        content: "请求失败"
      })
      reject("网络出错")
    }

    if (reqType == "POST" || reqType == "GET") {
      // 参数
      realOption.data = options.params
      // 请求方式
      realOption.method = reqType
      // 数据类型
      realOption.dataType = options.dataType || 'json'
      // 设置其他请求头
      if (!!options.extra) {
        switch (options.extra.contentType) {
          case " form":
            realOption.header = {
              'content-type': 'application/x-www-form-urlencoded'
            }
            break;
          case "json":
            realOption.header = {
              'content-type': 'application/json;charset=utf-8'
            }
            break;
        }
      }
      // 执行请求
      wx.request(realOption);
    } else if (reqType == "UPLOAD") {
      // 文件路径
      realOption.filePath = options.filePath
      // 文件名
      realOption.name = options.name
      // 表单数据
      realOption.formData = options.formData
      // 文件上传
      const uploadTask = wx.uploadFile(realOption);
      uploadTask.onProgressUpdate(res => {
        console.info("文件上传进度")
      })
    }
  })
  return promise;
}