/**
 * 全局工具类
 */
const config = require("../config.js")

/**
 * 日期格式化
 */
export const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

/**
 * 数字格式化
 */
export const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 判断是否有值
 */
export function isEmpty(val) {
  switch (typeof val) {
    case 'undefined':
      return true;
    case 'string':
      if (val.replace(/(^[ \t\n\r]*)|([ \t\n\r]*$)/g, '').length == 0) return true;
      break;
    case 'boolean':
      if (!val) return true;
      break;
    case 'number':
      if (0 === val || isNaN(val)) return true;
      break;
    case 'object':
      if (null === val || v.length === 0) return true;
      for (var i in val) {
        return false;
      }
      return true;
  }
  return false;
}

/**
 * 全局统一加载loading
 */
export function loading(title, isShow) {
  if (isEmpty(isShow) || isShow) {
    wx.showLoading({
      title: title
    })
  }
}

/**
 * 全局统一模态窗
 */
export function sysWraningModal({
  title,
  content,
}) {
  baseShowModal({
    title: title,
    content: content,
    showCancel: false,
    confirmText: "检查代码",
    confirmColor: "#333"
  })
}

/**
 * 模态窗定制基础
 */
function baseShowModal({
  title,
  content,
  showCancel,
  cancelText,
  cancelColor,
  confirmText,
  confirmColor,
  success,
  fail,
  complete
}) {
  wx.showModal({
    title: title,
    content: content,
  })
}

/**
 * 获取实际请求地址
 */
export function getUrl(url) {
  const currentConditionConf = config.conditionConf[config.condition];
  let hostAndport = ""
  let domain = ""
  let realUrl = ""
  // 1. 获取真实地址 、 真实domain
  for (let domainKey in config.urlList) {
    let domainUrl = config.urlList[domainKey]
    for (let urlKey in domainUrl){
      
      if (url === urlKey) {
        domain = domainKey;
        realUrl = domainUrl[urlKey];
        break;
      }
  }
}

if (isEmpty(domain) || isEmpty(realUrl)) {
  loading("domain或urlList配置有误，请检查")
}
// 2. 获取真实 host&port
hostAndport = currentConditionConf.hostAndport[domain]
if (isEmpty(hostAndport)) {
  loading("hostAndport配置有误，请检查")
}

const finalUrl = currentConditionConf.protocol + "://" + hostAndport + "/" + realUrl
console.info("发送请求：" + finalUrl)
return finalUrl
}