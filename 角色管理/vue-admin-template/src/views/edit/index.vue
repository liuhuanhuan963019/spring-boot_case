<template>
  <div class>
    <h3>编辑页面</h3>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="form.title" />
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.description" />
      </el-form-item>
      <el-form-item label="正文">
        <quill-editor
          ref="myQuillEditor"
          v-model="form.content"
          class="editor"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @ready="onEditorReady($event)"
        />
      </el-form-item>
      <el-button class="btn" block type="primary" @click="submit">提交</el-button>
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
  components: { quillEditor },
  props: {},
  data() {
    return {
      form: {
        title: '',
        description: '',
        content: ''
      },
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
      }
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    onEditorBlur(quill) {
      console.log('editor blur!', quill)
    },
    onEditorFocus(quill) {
      console.log('editor focus!', quill)
    },
    onEditorReady(quill) {
      console.log('editor ready!', quill)
    },
    submit() {
      if (!this.form.title) {
        this.$message('请输入标题')
      }
      if (!this.form.description) {
        this.$message('请输入描述')
      }
      if (!this.form.content) {
        this.$message('请输入正文')
      }

      const formData = new FormData()
      formData.append('title', this.form.title)
      formData.append('description', this.form.description)
      formData.append('content', this.form.content)
      // 发送 POST 请求
      axios({
        method: 'post',
        url: 'http://139.159.147.237:8080/yxyz/article/addarticle',
        data: formData,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        .then(function(res) {
          if (res.code === 0) {
            this.$message('提交成功')
          }
          // this.form = {
          //   title: '',
          //   description: '',
          //   content: '',
          // }
          this.$router.goBack()
        })
        .catch(function(error) {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped lang="scss">
.editor {
  height: 500px;
}
.btn {
  margin-top: 100px;
}
</style>
