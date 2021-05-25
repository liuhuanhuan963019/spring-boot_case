<template>
  <div class="delete-wrap">

    <div class="btn-wrap">

      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input placeholder="尚在开发中" class="input" />
        </el-form-item>
        <el-form-item>
          <el-select placeholder="尚在开发中" value="">
            <el-option :value="1">尚在开发中</el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="_getTableData">查询</el-button>
        </el-form-item>
      </el-form>

    </div>
    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        label="id"
        width="120"
      >
        <template slot-scope="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        width="120"
      />
      <el-table-column
        prop="category"
        label="分类"
        show-overflow-tooltip
      />
    </el-table>
    <div class="pagination">
      <div class="delete-wrap">
        <el-button type="danger" @click="deleteConfirm">删除</el-button>
      </div>
      <el-pagination
        :current-page="0"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="76"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  components: {

  },
  props: {

  },
  data() {
    return {
      tableData: [],
      multipleSelection: []
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
    toggleSelection(rows) {
      if (rows) {
        this.tableData.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row, true)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    deleteConfirm() {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteSelection()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    deleteSelection() {
      console.log(this.multipleSelection)
      const ids = this.multipleSelection.map(item => item.id).join('$')
      const formData = new FormData()
      formData.append('ids', ids)

      axios({
        method: 'post',
        url: 'http://139.159.147.237:8080/yxyz/type/delete',
        data: formData,
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        if (res.data.code === 0) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this._getTableData()
        } else {
          this.$message({
            type: 'error',
            message: '删除失败!'
          })
        }
      })
    },
    _getTableData() {
      axios({
        method: 'post',
        url: 'http://139.159.147.237:8080/yxyz/type/findAll',

        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }

      }).then(res => {
        this.tableData = res.data.data
      })
    }
  }
}
</script>

<style scoped lang="scss">
.delete-wrap{
    max-width:800px;
    margin:0 auto;
    .btn-wrap{
        display:flex;
        flex-direction: row;
        justify-content: space-between;
        margin:20px 0
    }
    .pagination{
              margin-top:20px;
        display:flex;
        justify-content: space-between;

    }
}
</style>
