<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="form" :rules="rules" class="login-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">注册</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="form.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="form.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleRegister"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <el-radio-group v-model="form.role" class="radio-group">
        <el-radio label="admin">Admin</el-radio>
        <el-radio label="boss">Boss</el-radio>
        <el-radio label="leader">Leader</el-radio>
        <el-radio label="user">User</el-radio>
      </el-radio-group>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleRegister">注册</el-button>

      <div class="tips">
        <span style="margin-right:20px;">请输入用户名密码以及角色，完成注册</span>
        <span style="" @click="goToLogin">返回登录</span>

      </div>

    </el-form>
  </div>
</template>

<script>
import { register } from '@/api/user'
export default {
  name: 'Login',
  data() {
    return {
      form: {
        username: '',
        password: '',
        role: 'admin'
      },
      rules: {
        username: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur' }]
      },
      loading: false,
      passwordType: 'password'

    }
  },
  watch: {

  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    async handleRegister() {
      this.$refs.loginForm.validate(async valid => {
        if (valid) {
          this.loading = true
          const formData = new FormData()
          formData.append('username', this.form.username)
          formData.append('password', this.form.password)
          formData.append('role', this.form.role)
          const res = await register(formData)
          console.log(res, 'res')
          if (res.status === 200) {
            this.$message({
              message: res.data.info || res.message,
              type: 'success',
              duration: 2 * 1000,
              onClose: () => {
                this.$router.push('login')
              }
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    goToLogin() {
      this.$router.push('login')
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    span:nth-child(2){
        font-weight:bold;
        cursor: pointer;
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .radio-group{
      margin-bottom:20px;
    ::v-deep .el-radio__label{
        color:#fff;
    }
  }
}
</style>
