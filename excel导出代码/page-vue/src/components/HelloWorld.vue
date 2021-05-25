<template>
  <div style="text-align:center;width: 80%;margin: auto">
    <div class="btn-wrap">
      <el-button type="primary" plain @click="handleSelect">导出选中</el-button>
      <el-button type="primary" plain @click="handleSelectAll">导出全部数据</el-button>
    </div>
    <el-table :data="tableData" stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="source" label="来源"></el-table-column>
      <el-table-column prop="ttime" :formatter="dateFormat" label="发布时间"></el-table-column>
      <el-table-column prop="url" label="地址"></el-table-column>
    </el-table>

    <div class="block" style="display: inline">
      <el-pagination
        background
        style="width:100%;margin: auto"
        layout="prev, pager, next"
        @current-change="current_change"
        :total="total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      total: 111,
      size: 5,
      currentPage: 1,
      tableData: [],
      multipleSelection: ""
    };
  },
  created() {
    this.$axios
      .get(`page/newlist/${this.currentPage}/${this.size}`)
      .then(res => {
        this.tableData = res.data.data.content;
        console.log(res.data.data.totalPages);
        this.total = res.data.data.totalElements;
      })
      .catch(res => {
        alert(res.data.message);
      });
  },
  timestampToTime(row, column) {
    var date = new Date(row.cjsj); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + "-";
    var M =
      (date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1)
        : date.getMonth() + 1) + "-";
    var D = date.getDate() + " ";
    var h = date.getHours() + ":";
    var m = date.getMinutes() + ":";
    var s = date.getSeconds();
    return Y + M + D + h + m + s;
    console.log(timestampToTime(1533293827000));
  },
  methods: {
    handleSelectAll() {
      window.location.href = "http://127.0.0.1:9527/excel/download";
    },
    //导出选中
    handleSelect() {
      window.location.href = "http://127.0.0.1:9527/excel/download?ids=${this.multipleSelection}";
    },
    handleSelectionChange(val) {
      console.log("val,val", val);
      let centerValue = val.map(item => {
        return item.id;
      });
      this.multipleSelection = centerValue.join("");
      console.log("mu", this.multipleSelection);
    },
    current_change: function(currentPage) {
      this.currentPage = currentPage;
      console.log(currentPage);
      this.$axios
        .get(`page/newlist/${currentPage}/${this.size}`)
        .then(res => {
          this.tableData = res.data.data.content;
          console.log(res.data.data.content);
        })
        .catch(res => {
          alert(res.data.message);
        });
    },
    dateFormat(row, column, cellValue, index) {
      // let daterc = row[column.property];
      // console.
      // console.log(daterc)
      // retur
      // // daterc = daterc.substr(0, 10);
      // // console.log(daterc);
      // // if (daterc != null) {
      // //   return daterc;
      // // }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.btn-wrap {
  margin-bottom: 20px;
  text-align: right;
}
.el-pagination {
  padding: 10px 0;
  float: left;
}
</style>
