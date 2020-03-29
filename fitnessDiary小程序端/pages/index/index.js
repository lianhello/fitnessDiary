// pages/index/index.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    article: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: app.globalData.localhost + '/articleName',
      success: res => {
        console.log(res)
        this.setData({
          article: res.data
        })
      },
      fail: res => {
        console.log(res)
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 
   */
  calorie: function(event) {
    wx.navigateTo({
      url: '../calorie/calorie',
    })
  },
  articleMain: function(event) {
    wx.navigateTo({
      url: '../article/article',
      success: res => {
        //向打开页面传送数据
        res.eventChannel.emit('id', { id: event.currentTarget.id})
      }
    })
  }
})