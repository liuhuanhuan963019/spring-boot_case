<template>
  <div class="email-wrap">
    <h2>前端发送文件和内容后，自动给指定邮箱发邮件功能</h2>
    <el-form ref="form" label-width="80px">
      <el-form-item label="邮件地址">
        <el-input
          v-model="form.email"
          type="input"
          :rows="2"
          placeholder="请输入邮箱地址"
        />
      </el-form-item>
      <el-form-item label="标题">
        <el-input
          v-model="form.title"
          type="input"
          :rows="2"
          placeholder="请输入标题"
        />
      </el-form-item>
      <el-form-item label="正文">
        <quill-editor
          ref="myQuillEditor"
          v-model="form.content"
          class="editor"
          :options="editorOption"
        />
      </el-form-item>
      <el-form-item label="选择附件" class="file">
        <el-upload
          ref="upload"
          :data="form"
          action="http://139.159.147.237:8005/email/send"
          class="upload-demo"
          :on-change="changeFile"
          :on-success="successFile"
          :file-list="fileList"
          :auto-upload="false"
        >
          <el-button slot="trigger" size="small" type="success">选取文件</el-button>

          <div slot="tip" class="el-upload__tip">选择文件后，发送，邮件内容和附件会自动发送到指定邮箱，请注意查收</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">发送</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import { quillEditor } from 'vue-quill-editor'
import axios from 'axios'
export default {
  components: {
    quillEditor
  },
  props: {

  },
  data() {
    return {
      editorOption: {
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'], // 加粗，斜体，下划线，删除线
            ['blockquote', 'code-block'], // 引用，代码块

            [{ header: 1 }, { header: 2 }], // 标题，键值对的形式；1、2表示字体大小
            [{ list: 'ordered' }, { list: 'bullet' }], // 列表
            [{ script: 'sub' }, { script: 'super' }], // 上下标
            [{ indent: '-1' }, { indent: '+1' }], // 缩进
            [{ direction: 'rtl' }], // 文本方向

            [{ size: ['small', false, 'large', 'huge'] }], // 字体大小
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // 几级标题

            [{ color: [] }, { background: [] }], // 字体颜色，字体背景颜色
            [{ font: [] }], // 字体
            [{ align: [] }], // 对齐方式

            ['clean'] // 清除字体样式
            // ['image', 'video'], //上传图片、上传视频
          ]
        },
        theme: 'snow'
      },
      form: {
        email: '',
        title: '',
        content: ''
      },

      fileList: []
    }
  },
  computed: {

  },
  watch: {

  },
  created() {

  },
  mounted() {

  },
  methods: {
    changeFile(file, filelist) { // 文件状态改变时的钩子 把filelist 赋值给data中的filelist
      this.fileList = [...filelist]
    },
    onSubmit() {
      if (!this.form.email) {
        this.$message.error('请输入邮箱')
        return
      }
      if (!this.form.title) {
        this.$message.error('请输入标题')
        return
      }
      if (!this.form.content) {
        this.$message.error('请输入内容')
        return
      }
      if (!this.fileList.length) {
        this.$message.error('请选择文件')
        return
      }

      setTimeout(() => {
        this.$refs.upload.submit() // 手动上传文件
      }, 1)
    },
    successFile() {
      this.$message.success('发送成功')
    }
  }
}
</script>

<style scoped lang="scss">
.email-wrap{
    margin-top:30px;
    margin-right:30px;

    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items:center;

}

.editor {
  height: 200px;
}
.file{
    margin-top:110px;
}
</style>
