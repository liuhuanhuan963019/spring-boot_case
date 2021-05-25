<template>
  <div class="">
    <el-form class="form">
      <h1>试一下你的权限吧</h1>
      <el-button type="primary" @click="handleBoss">Boss</el-button>
      <el-button type="success" @click="handleLeader">Leader</el-button>
      <el-button type="warning" @click="handleAdmin">Admin</el-button>
      <el-button type="danger" @click="handleUser">User</el-button>
    </el-form>
  </div>
</template>

<script>
import { getAdmin, getBoss, getLeader, getUser } from '@/api/loginAfter'
export default {
  components: {

  },
  props: {

  },
  data() {
    return {

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
    handleBoss() {
      getBoss().then(res => {
        console.log(res, 'res')
        this.afterHandle(res)
      })
    },
    handleLeader() {
      getLeader().then(res => {
        this.afterHandle(res)
      })
    },
    handleAdmin() {
      getAdmin().then(res => {
        this.afterHandle(res)
      })
    },
    handleUser() {
      getUser().then(res => {
        this.afterHandle(res)
      })
    },
    afterHandle(res) {
      if (res.status === 401) {
        this.$message.error(res.data.info)
      }
      if (res.status === 200) {
        this.$message.success(res.data.info)
      }
    }
  }
}
</script>

<style scoped lang="scss">
.form{
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items:center;
    button{
        margin-top:20px;
        width:200px;
    }
}
</style>
