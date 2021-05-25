<template>
  <div class="">
    <h2>文章详情</h2>
    <el-card>
      <div class="title">{{ detail.title }}</div>
      <div class="des">{{ detail.description }}</div>
      <div class="con" v-html="detail.content" />
      <div class="time">{{ detail.createdatetime }}</div>
    </el-card>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      detail: {}
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
  methods: {}
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
</style>
