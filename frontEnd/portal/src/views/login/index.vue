<template>
  <div class="login-container">
    <el-form :model="loginForm" :rules="loginRules" label-position="left" label-width="70px" >
      <div class="title-container">
        <h3>用户登录</h3>
      </div>
      <el-form-item label="用户名">
        <el-input
          v-model="loginForm.userName"
          autocomplete="off"
          placeholder="please input your account name"
          name="userName"
          prefix-icon="el-icon-user-solid"
          clearable>
        </el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input
          v-model="loginForm.password"
          autocomplete="off"
          placeholder="please input your account's password"
          name="password"
          prefix-icon="el-icon-lock"
          show-password
          clearable>
        </el-input>
      </el-form-item>
      <el-button type="primary" @click="showDaig=true">登录</el-button>
      <el-button>注册</el-button>
    </el-form>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import { validateString } from '@/utils/validate'

export default {
  name: 'Login',
  data () {
    const validateForm = (rule, value, callback) => {
      if (!validateString(value)) {
        callback(new Error('The text can not be less than 0 digits'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        userName: '',
        password: ''
      },
      loginRules: {
        userName: [{ required: true, trigger: 'blur', validator: validateForm }],
        password: [{ required: true, trigger: 'blur', validator: validateForm }]
      },
      loading: false
    }
  },
  components: {
    // login
  }
}
</script>
<style lang="scss" scoped>
  .login-container {
    min-height: 100%;
    width: 450px;
    overflow: hidden;
    margin-top: 200px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
