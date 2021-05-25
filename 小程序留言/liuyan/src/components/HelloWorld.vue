<template>
  <div class="wrap">
    <a-table :columns="columns" :data-source="data" rowKey="id">
      <a slot="createTime" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o" /> Name</span>
      <span slot="tags" slot-scope="tags">
        <a-tag
          v-for="tag in tags"
          :key="tag"
          :color="
            tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'
          "
        >
          {{ tag.toUpperCase() }}
        </a-tag>
      </span>
      <span slot="action" slot-scope="text">
        <a-popconfirm
          title="确定要删除该条留言吗"
          ok-text="确定"
          cancel-text="取消"
          @confirm="confirm(text)"
        >
          <a-button class="btn" type="danger" size="small">删除留言</a-button>
        </a-popconfirm>

        <a-button
          class="btn"
          type="primary"
          size="small"
          v-if="!text.replyContent"
          @click="reply(text)"
          >回复</a-button
        >
        <a-popconfirm
          v-if="text.replyContent"
          title="确定要删除该条回复吗"
          ok-text="确定"
          cancel-text="取消"
          @confirm="replyConfirm(text)"
        >
          <a-button class="btn" type="dashed" size="small">删除回复 </a-button>
        </a-popconfirm>
      </span>
    </a-table>
    <a-modal
      title="回复留言"
      :visible="visible"
      :confirm-loading="confirmLoading"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <a-input v-model="input"></a-input>
    </a-modal>
  </div>
</template>

<script>
import axios from 'axios'
const columns = [
  {
    dataIndex: 'createTime',
    key: 'createTime',
    title: '创建时间',
    slots: { title: 'customTitle' },
    scopedSlots: { customRender: 'name' },
  },
  {
    title: '留言',
    dataIndex: 'content',
    key: 'content',
  },
  {
    title: '回复时间',
    key: 'replyCreateTime',
    dataIndex: 'replyCreateTime',
  },
  {
    title: '回复',
    dataIndex: 'replyContent',
    key: 'replyContent',
  },

  {
    title: 'Action',
    key: 'action',
    scopedSlots: { customRender: 'action' },
  },
]

export default {
  name: 'HelloWorld',
  props: {
    msg: String,
  },
  data() {
    return {
      data: [],
      columns,
      input: '',
      visible: false,
      confirmLoading: false,
    }
  },
  mounted() {
    this.loading = true
    this.getData()
  },
  methods: {
    getData() {
      axios({
        url: 'http://localhost:8089/message/select',
        method: 'get',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
        },
      }).then((res) => {
        console.log(res, 'res')
        this.loading = false
        this.data = res.data.list
      })
    },
    confirm(text) {
      console.log('confirm', text)
      axios({
        url: 'http://localhost:8089/replyMessage/deleteMessages',
        method: 'DELETE',
        params: {
          id: text.id,
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded',
        },
      }).then(() => {
        this.$message.info('删除留言成功')
        this.getData()
      })
    },
    reply(text) {
      this.id = text.id
      this.visible = true
    },
    handleOk() {
      this.confirmLoading = true
      if (!this.input) {
        this.$message.warn('留言内容不能为空')
        return
      }
      axios({
        url: 'http://localhost:8089/replyMessage/insert',
        method: 'POST',
        params: {
          msgId: this.id,
          content: this.input,
        },
        headers: {
          'content-type': 'application/json',
        },
      }).then(() => {
        this.$message.info('回复成功')
        this.getData()
      })
      setTimeout(() => {
        this.visible = false
        this.confirmLoading = false
      }, 2000)
    },
    handleCancel() {
      console.log('Clicked cancel button')
      this.visible = false
    },
    replyConfirm(text) {
      console.log('replyConfirm', text)
      axios({
        url: 'http://localhost:8089/replyMessage/deleteReply',
        method: 'DELETE',
        params: {
          id: text.replyId,
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded',
        },
      }).then(() => {
        this.$message.info('删除留言成功')
        this.getData()
      })
    },
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.wrap {
  max-width: 1000px;
  margin: 0 auto;
}
.description {
  font-size: 14px;
  color: #333;
}
.reply {
  font-size: 12px;
  color: #666;
  background: #ededed;
  padding: 10px;
  margin-top: 10px;
}
.delete {
  margin-left: 20px;
}
.btn {
  margin-right: 10px;
}
</style>
