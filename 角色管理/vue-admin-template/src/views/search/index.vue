<template>
  <div class="search">
    <el-col :span="12">
      <span>搜索：</span>
      <el-autocomplete
        v-model="title"
        class="inline-input"
        :fetch-suggestions="querySearch"
        placeholder="请输入内容"
        :trigger-on-focus="false"
        @select="handleSelect"
      />
    </el-col>
    <el-col :span="18" style="margin-top:20px;">
      <div style="margin-bottom:10px;">结果</div>
      <el-table
        :data="tableData"
        size="mini"
        border
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="id"
          width="100"
        />
        <el-table-column
          prop="name"
          label="名称"
          width="120"
        />
        <el-table-column
          prop="category"
          label="分类"
          width="120"
        />
        <el-table-column
          prop="content"
          label="内容"
        />

      </el-table>
    </el-col>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  components: {},
  props: {},
  data() {
    return {
      title: '',
      selectItem: {},
      tableData: []
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {

  },
  methods: {
    async querySearch(queryString, cb) {
      const data = await this.getData(queryString)
      const result = data.map(item => {
        return {
          ...item,
          value: item && item.name
        }
      })
      // 调用 callback 返回建议列表的数据
      cb(result)
    },
    async getData(queryString) {
      const formData = new FormData()
      formData.append('name', queryString)
      const res = await axios({
        method: 'post',
        url: 'http://139.159.147.237:8080/yxyz/type/findlikename',
        data: formData,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
      console.log(res, 'res')
      return res.data.data
    },
    handleSelect(item) {
      console.log(item)
      this.selectItem = item
      this.tableData = [item]
    }

  }
}
</script>

<style scoped lang="scss">
.search{
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items:center;
}

</style>
