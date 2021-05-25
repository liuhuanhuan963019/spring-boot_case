<template>
  <div class="">
    <div id="myDom">
      <h2>文章详情</h2>
      <el-card>
        <div class="title">{{ detail.title }}</div>
        <div class="des">{{ detail.description }}</div>
        <div class="con" v-html="detail.content" />
        <div class="time">{{ detail.createdatetime }}</div>
      </el-card>
    </div>

    <el-button type="primary" @click="showShare">分享</el-button>
    <el-dialog

      :visible.sync="isShowShare"
      width="50%"
    >
      <div v-loading="loading" class="showImg shareShare">
        <div v-if="shareImg" style="width:100%">
          <img id="shareImg" :src="shareImg" alt="">
          <p>右键选择图片并保存</p>
        </div>

      </div>

    </el-dialog></div>
</template>

<script>
import html2canvas from 'html2canvas'
export default {
  components: {},
  props: {},
  data() {
    return {
      detail: {},
      loading: false,
      isShowShare: false,
      shareImg: ''
    }
  },
  computed: {},
  watch: {},
  created() {
    console.log(this.$route)
    const item = this.$route.params.item

    this.detail = item
  },
  mounted() {},
  methods: {

    async showShare() {
      this.isShowShare = true
      this.loading = true
      const domId = 'myDom'
      window.pageYOffset = 0
      document.documentElement.scrollTop = 0
      document.body.scrollTop = 0
      this.$nextTick(async() => {
        const dom = document.getElementById(domId)
        console.log('tt', dom, dom.offsetLeft, dom.offsetWidth)
        const canvas = await html2canvas(dom, {
          useCORS: true,
          allowTaint: true
          // x: dom.offsetLeft - dom.offsetWidth / 2,
          // y: window.pageYOffset

        })
        console.log(1234)
        this.loading = false
        this.shareImg = canvas.toDataURL()
      })
    }
  }
}
</script>

<style scoped lang="scss">
.title {
  font-weight: bold;
  font-size: 16px;
  text-align: left;
  margin-bottom: 10px;
}
.des {
  font-size: 14px;
  text-align: left;
  margin-bottom: 10px;
}
.con {
  font-size: 14px;
  text-align: left;
  margin-bottom: 10px;
}
.time {
  font-size: 14px;
  text-align: left;
}
::v-deep .el-card__body{
  background:#fff
}
.showImg{
  height:100%;
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
  p{
    margin-top:10px;
    color:#333;
    text-align:center;
    font-weight:bold;
  }
  img{
    width:100%;

  }
  .loading{
          color:#fff;
    font-weight:bold;
  }
}

</style>
