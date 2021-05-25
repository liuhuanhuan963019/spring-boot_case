//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    msgData: [],
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getData()
  },
  changeInputValue(ev) {
    this.setData({
      inputVal: ev.detail.value,
    })
  },
  // //删除留言
  // DelMsg(ev) {
  //   var n = ev.target.dataset.index

  //   var list = this.data.msgData
  //   list.splice(n, 1)

  //   this.setData({
  //     msgData: list,
  //   })
  // },

  addMsg() {
    wx.request({
      url: 'http://139.159.147.237:9533/message/insert',
      method: 'post',
      data: { content: this.data.inputVal },
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      success: (res) => {
        var code = res.statusCode.toString()
        var startChar = code.charAt(0)
        if ((startChar = '2')) {
          wx.showToast({
            title: '添加成功',
            icon: 'success',
            duration: 2000,
          })
          this.getData()
        } else {
          console.log(res)
        }
      },
      fail: function (err) {
        console.log(err)
      },
    })
  },
  getData() {
    wx.request({
      url: 'http://139.159.147.237:9533/message/select',

      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      success: (res) => {
        var code = res.statusCode.toString()
        var startChar = code.charAt(0)
        if ((startChar = '2')) {
          console.log(res, 'res')
          this.setData({
            msgData: res.data.list,
            inputVal: '',
          })
        } else {
          console.log(res)
        }
      },
      fail: function (err) {
        console.log(err)
      },
    })
  },

  // replyMsg() {
  //   var list = this.data.msgData
  //   list.push({
  //     msg: '回复:' + this.data.inputVal,
  //   })

  //   this.setData({
  //     msgData: list,
  //     inputVal: '',
  //   })
  // },
})
