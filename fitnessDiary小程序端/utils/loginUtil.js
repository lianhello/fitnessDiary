module.exports = {
  login: login
}
const localhost = 'http://192.168.0.104:8080';
// 登录
function login() {
  wx.login({
    success: res => {
      // 发送 res.code 到后台换取 openId, sessionKey, unionId
      console.log(res.code)
      wx.request({
        url: localhost + '/wxlogin',
        method: "GET",
        data: {
          code: res.code
        },
        success: res => {
          console.log(res),
            wx.setStorageSync('token', res.data)
        }
      })
    }
  })
}