<template>
  <div class="dashboard-container">
    <el-row type="flex" justify="center">
      <el-col :span="14">
        <div class="btn-wrap">
          <el-form :inline="true" class="demo-form-inline">
            <el-form-item>
              <el-input placeholder="请输入你要查询资料的名称" class="input" />
            </el-form-item>
            <el-form-item>
              <el-select placeholder="请选择" value="">
                <el-option :value="1">尚在开发中</el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="getData">查询</el-button>
            </el-form-item>
          </el-form>
          <div>
            <el-button type="primary" class="btn" @click="handleMap"
              >一键生成图表</el-button
            >
          </div>
        </div>
        <el-table :data="tableData" size="mini" border style="width: 100%">
          <el-table-column prop="id" label="id" width="180" />
          <el-table-column prop="name" label="名称" width="180" />
          <el-table-column prop="category" label="分类" />
          <el-table-column label="操作">
            <template slot-scope>
              <el-button type="danger" @click="handleGet">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination">
          <el-pagination
            :current-page="0"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="76"
            @size-change="handleGet"
            @current-change="handleGet"
          />
        </div>
      </el-col>
      <el-col :span="10">
        <div v-if="showFlag" id="bar" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from "axios";
import echarts from "echarts";
export default {
  name: "Dashboard",
  data() {
    return {
      echartsData: [],
      tableData: [],
      showFlag: false
    };
  },
  computed: {},
  created() {
    this.getData();
  },
  mounted() {},
  methods: {
    getData() {
      axios({
        method: "post",
        url: "http://139.159.147.237:8080/yxyz/type/findAll"
      }).then(res => {
        this.tableData = res.data.data;
        this.handleData(res.data.data);
      });
    },

    handleData(data) {
      const catArr = [];
      data.forEach(item => {
        const flag = catArr.some(catItem => catItem.name === item.category);
        if (flag) {
          const index = catArr.findIndex(
            catItem => catItem.name === item.category
          );
          catArr[index].value += 1;
        } else {
          catArr.push({
            name: item.category,
            value: 1
          });
        }
      });
      this.echartsData = catArr;
      console.log(catArr);
    },

    handleMap() {
      this.showFlag = false;
      this.$nextTick(() => {
        this.showFlag = true;
        this.$nextTick(() => {
          this._drawMap();
        });
      });
    },
    handleGet() {
      this.$message("正在开发中");
    },
    _drawMap() {
      var dom = document.getElementById("bar");
      var myChart = echarts.init(dom);
      const option = {
        title: {
          text: "分类占比",
          subtext: "",
          x: "center"
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          left: "left",
          data: this.echartsData
        },
        series: [
          {
            name: "分类占比",
            type: "pie",
            radius: "55%",
            center: ["50%", "70%"],
            data: this.echartsData,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              },
              normal: {
                label: {
                  show: true,
                  formatter: "{b}-{d}%",
                  textStyle: {
                    fontWeight: 300,
                    fontSize: 16
                    //文字的字体大小
                  }
                },
                lableLine: {
                  show: true
                }
              }
            }
          }
        ]
      };
      myChart.setOption(option);
    }
  }
};
</script>

<style lang="scss" scoped>
.dashboard-container {
  .btn-wrap {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    .btn {
      margin-left: 40px;
    }
  }
  .input-wrap {
    display: flex;
    flex-direction: row;
    flex: 1;
    justify-content: space-between;
  }
  .input {
    width: 200px;
  }
  .pagination {
    margin-top: 15px;
    display: flex;
    justify-content: center;
  }
  #bar {
    height: 500px;
  }
}
</style>
