<template>
  <div class="dashboard-container">
    <el-upload
      ref="upload"
      multiple
      class="upload-demo"
      action="http://139.159.147.237:8080/yxyz/filetopaf/uploadtopdf"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      :before-upload="beforeUpload"
      list-type="picture"
    >
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <!-- <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button> -->
      <div slot="tip" class="el-upload__tip">可以上传doc/xlsx/pdf</div>
    </el-upload>

  </div>
</template>

<script>

export default {
  name: 'Dashboard',
  components: { },
  data() {
    return {
      fileList: [],
      dialogVisible: false,

      pdfUrl: ''

    }
  },
  computed: {},
  created() {
    this.fileArr = []
  },
  methods: {

    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file, 'hha')
      // if (file.raw.type === 'application/pdf') {
      //   this.pdfUrl = file.response.data[0].url
      //   this.dialogVisible = true
      //   return
      // }
      window.open(file.response.data[0].url)
    },
    beforeUpload(file) {
      console.log(file)
    }

  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
