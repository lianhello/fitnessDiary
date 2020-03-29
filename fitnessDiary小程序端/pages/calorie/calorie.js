// pages/calorie/calorie.js
import Toast from '../../dist/toast/toast';
import Dialog from '../../dist/dialog/dialog';
var loginUtil = require('../../utils/loginUtil.js')
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    loading: false, //加载
    price: 0, //卡路里总数
    disabled: true, //提交按钮是否可用
    show: false, //浮窗是否显示
    search: '', //搜索栏value
    typeOption: [{ //下拉列表item
      text: '全部类型',
      value: 0
    }],
    typeValue: 0, //下拉列表当前选项
    isLastPage: false, //是否最后一页
    calorieData: [], //卡路里item
    nextPage: 1, //下一页页数
    orderShow: false, //订单弹出层
    orderItem: [], //订单项
    fieldGram: null, //gram输入框
    calorieName: '',
    calorie: null,
    gram: null,
    id: null,
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    this.calorieType()
    this.calorieAll()

  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    if (this.data.isLastPage != true) {
      this.calorieAll()
    } else {
      Toast('到底了哦~');
    }
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  // 显示加载
  loadingShow: function() {
    this.setData({
      loading: true
    })
  },
  // 隐藏加载
  loadingHide: function() {
    this.setData({
      loading: false
    })
  },
  // 定位到类型
  // scroll: function(event) {
  //   wx.pageScrollTo({
  //     selector: "." + event.currentTarget.id
  //   })
  // },
  // 显示浮窗
  shopShow: function(event) {
    let current = event.currentTarget.dataset;
    console.log(event)
    this.setData({
      show: true,
      id: current.id,
      calorieName: current.name,
      calorie: current.calorie,
      gram: current.gram
    })
  },
  // 关闭浮窗
  shopClose() {
    this.setData({
      show: false,
      fieldGram: null
    });
  },
  // 搜索栏变更时
  onChange(e) {
    console.log(e)
    this.setData({
      search: e.detail
    });
  },
  // 搜索 完成
  onSearch: function() {
    this.pageSetting()
    var that = this;
    this.loadingShow()
    wx.request({
      url: app.globalData.localhost + '/calorieSearch',
      data: {
        search: that.data.search
      },
      method: 'GET',
      success: function(res) {
        console.log(res)
        that.setData({
          calorieData: res.data,
          typeValue: 0
        })
      },
      fail: function(res) {
        console.log(res)
      },
      complete: function(res) {
        console.log(res)
        that.loadingHide()
      },
    })
  },
  // 点击搜索
  onClick: function() {
    this.onSearch();
  },
  // 取消搜索
  onClear: function() {
    this.data.isLastPage = false
    this.setData({
      calorieData: []
    })
    this.calorieAll()
  },
  // 选择下拉列表
  onSwitchType: function(value) {
    console.log(value)
    if (value.detail == 0) {
      this.setData({
        calorieData: []
      })
      this.calorieAll()
      return
    }
    this.loadingShow()
    this.pageSetting()
    var that = this
    wx.request({
      url: app.globalData.localhost + '/calorieTypeItem',
      data: {
        type: value.detail
      },
      success: function(res) {
        console.log(res)
        that.setData({
          calorieData: res.data,
          search: ''
        })
      },
      fail: function(res) {
        console.log(res)
      },
      complete: function(res) {
        that.loadingHide()
      }
    })
  },
  //获取所有类型并加载到下拉列表
  calorieType: function() {
    var that = this;
    wx.request({
      url: app.globalData.localhost + '/calorieType',
      success: res => {
        console.log(res)
        for (var i in res.data) {
          var data = {
            text: res.data[i].name,
            value: res.data[i].id
          }
          that.setData({
            typeOption: that.data.typeOption.concat(data)
          })
        }
      }
    })
  },
  // 获取全部数据
  // calorieAll: function() {
  //   this.loadingShow()
  //   var that = this
  //   wx.request({
  //     url: app.globalData.localhost + '/calorieAll',
  //     method: 'GET',
  //     header: {
  //       'Content-Type': 'application/json',
  //     },
  //     success: function (res) {
  //       that.setData({
  //         data: res.data
  //       })
  //     },
  //     fail: function () {
  //       console.log("fail")
  //     },
  //     complete: function (res) {
  //       that.loadingHide()
  //     }
  //   })
  // }
  pageSetting: function() {
    this.setData({
      isLastPage: true,
      nextPage: 1
    })
  },
  // 获取全部数据
  calorieAll: function() {
    this.loadingShow()
    var that = this
    wx.request({
      url: app.globalData.localhost + '/calorieAll',
      data: {
        pageNum: that.data.nextPage
      },
      success: res => {
        console.log(res)
        that.setData({
          isLastPage: res.data.isLastPage,
          nextPage: res.data.nextPage,
          calorieData: that.data.calorieData.concat(res.data.list)
        })
      },
      complete: function(res) {
        that.loadingHide()
      }
    })
  },
  input: function(e) {
    console.log(e)
    this.data.fieldGram = e.detail
  },
  add: function(e) {
    var that = this
    if (this.data.fieldGram == null || this.data.fieldGram == 0 || this.data.fieldGram == '') {
      Dialog.alert({
        title: '错误',
        message: '输入数字哦',
      })
      return
    } else if (wx.getStorageSync("token") == '' || wx.getStorageSync("token") == null) {
      Dialog.confirm({
          title: '错误',
          message: '需要登录',
          asyncClose: true
        })
        .then(() => {
          loginUtil.login()
          setTimeout(() => {
            Dialog.close();
          }, 2000);
        })
        .catch(() => {
          Toast('出错了')
          Dialog.close();
        });
      return
    } else {
      // 添加到订单栏
      var item = [{
        id: that.data.id,
        calorie: that.data.calorie,
        name: that.data.calorieName,
        gram: that.data.gram,
        fieldGram: Number(that.data.fieldGram),
        value: Math.round(that.data.fieldGram / that.data.gram * that.data.calorie)
      }]
      if (this.data.orderItem.length == 0) {
        that.setData({
          orderItem: that.data.orderItem.concat(item),
          price: that.data.price + item[0].value * 100,
          disabled: false
        })
      } else {
        try {
          for (var i = 0; i <= this.data.orderItem.length; i++) {
            if (i == this.data.orderItem.length) {
              that.setData({
                orderItem: that.data.orderItem.concat(item),
                price: that.data.price + item[0].value * 100
              })
              Toast("添加成功")
              return
            }
            if (this.data.orderItem[i].id == item[0].id) {
              var v = that.data.orderItem[i].value + item[0].value
              var f = that.data.orderItem[i].fieldGram + Number(item[0].fieldGram)
              that.setData({
                ['orderItem[' + i + '].value']: v,
                ['orderItem[' + i + '].fieldGram']: f,
                price: that.data.price + item[0].value * 100
              })
              Toast("添加成功")
              return
            }
          }
        } catch (err) {
          Toast("出错了")
        }
      }
    }
  },
  orderShow: function() {
    if (this.data.orderShow == false) {
      this.setData({
        orderShow: true
      })
    } else {
      this.setData({
        orderShow: false
      })
    }
  },
  orderClose: function() {
    this.setData({
      orderShow: false
    })
  },
  // 清空购物车
  orderClear: function() {
    var that = this
    Dialog.confirm({
      title: '清空',
      message: '确认清空'
    }).then(() => {
      // on confirm
      Dialog.close();
      that.setData({
        price: 0,
        orderItem: [],
        disabled: true
      })
      Toast("清空完成")
    }).catch(() => {
      // on cancel
      Dialog.close();
    });
  },
  // 删除order项
  orderItemDelete: function(e) {
    var id = e.currentTarget.dataset.id
    var value = this.data.orderItem[id].value
    this.data.orderItem.splice(id, 1)
    this.setData({
      orderItem: this.data.orderItem,
      price: this.data.price - value * 100,
    })
    if (this.data.price == 0) {
      this.setData({
        disabled: true
      })
    }
  },
  // 点击提交按钮
  orderSubmit: function() {
    var that = this
    if (!this.data.orderItem.length > 0) {
      Toast("不能为空")
    } else {
      Dialog.confirm({
        title: '提交',
        message: '确认提交？'
      }).then(() => {
        var uc = []
        for (var i = 0, oi = this.data.orderItem; i < oi.length; i++) {
          var c = new Object()
          c.id = oi[i].id
          c.gram = oi[i].fieldGram
          uc.push(c)
        }
        wx.request({
          url: app.globalData.localhost + '/addUserCalorie',
          method: 'POST',
          header: {
            authorization: wx.getStorageSync("token")
          },
          data: {
            userCalorie: uc
          },
          success: res => {
            if (res.data.code == 200) {
              that.setData({
                price: 0,
                orderItem: [],
                disabled: true
              })
              Toast("提交成功")
              Dialog.close();
              Dialog.confirm({
                // title: '标题',
                message: '立刻查看？'
              }).then(() => {
                // on close
                wx.switchTab({
                  url: '/pages/data/data',
                })
              }).catch(() => {
                // on cancel
                Dialog.close();
              });
            } else {
              Toast("提交失败")
              console.log(res)
              Dialog.close();
            }
          }
        })
      }).catch(() => {
        // on cancel
        Dialog.close();
        Toast("提交失败")
      });



    }
  },
})