<template>
  <div class="wrap">
    <div class="top">
      <h1>文章列表</h1>
      <el-button type="primary" @click="goToEdit">添加文章</el-button>
    </div>

    <div class="card-wrap">
      <div v-for="(item, index) in art" :key="index" class="box-card" @click="goToDetail(item)">
        <el-card>
          <div class="title">{{ item.title }}</div>
          <div class="des">{{ item.description }}</div>
          <div class="time">{{ item.createdatetime }}</div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  components: {},
  props: {},
  data() {
    return {
      art: []
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    axios
      .post('http://139.159.147.237:8080/yxyz/article/loadPage')
      .then((res) => {
        console.log(res.data, 'res')
        if (res.data && res.data.data && res.data.data.list) {
          console.log(res.data.data.list, this, 'res')
          const list = res.data.data.list
          this.art = list
        }
      })
      .catch(function(error) {
        console.log(error)
      })
  },
  methods: {
    goToEdit() {
      this.$router.push('/about')
    },
    goToDetail(item) {
      console.log(123)
      this.$router.push({
        name: 'detail',
        params: {
          item: item
        }
      })
    }
  }
}
</script>

<style scoped lang="scss">
.wrap {
  margin-top: 50px;
}
.top {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  button {
    position: absolute;
    right: 0;
  }
}
.card-wrap {
  display: flex;
  flex-wrap: wrap;
}
.box-card {
  margin-bottom: 20px;
  margin-right: 20px;
  width: 30%;
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
  .time {
    font-size: 14px;
    text-align: left;
  }
}
</style>
