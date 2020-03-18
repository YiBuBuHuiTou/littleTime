<template>
  <div class="login-container">
      <div class="title-container">
        <h3>用户登录</h3>
      </div>
      <div v-loading="loading" element-loading-text="登录中......">
        <el-tabs v-model="activeName" >
          <el-tab-pane name="account" label="密码登录">
            <el-row>
              <el-col :span="24">
                <el-form ref="accountForm" :model="accountForm" :rules="loginRules" label-position="left" >
                  <el-form-item prop="userName">
                    <el-input
                      v-model="accountForm.userName"
                      ref="userName"
                      autocomplete="off"
                      placeholder="账号/手机号/邮箱"
                      name="userName"
                      prefix-icon="el-icon-user-solid"
                      clearable>
                    </el-input>
                  </el-form-item>

                  <el-form-item prop="password">
                    <el-input
                      v-model="accountForm.password"
                      ref="password"
                      autocomplete="off"
                      placeholder="密码"
                      name="password"
                      prefix-icon="el-icon-lock"
                      show-password
                      clearable>
                    </el-input>
                  </el-form-item>
                </el-form>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane name="sms" label="短信验证码登录">
            <el-row>
              <el-col :span="24">
                <el-form :model="telForm" :rules="telRules" label-position="left" >
                  <el-form-item prop="tel">
                    <el-input
                      v-model="telForm.tel"
                      ref="tel"
                      autocomplete="off"
                      placeholder="手机号"
                      name="tel"
                      clearable>
                      |<el-select v-model="telKind"  slot="prepend">
                        <el-option v-for="item in supportedLocation" :key="item.id" :label="item.name" :value="item.id"></el-option>
                      </el-select>
                    </el-input>
                  </el-form-item>

                  <el-form-item prop="sms">
                    <el-input
                      v-model="telForm.sms"
                      ref="sms"
                      autocomplete="off"
                      placeholder="请输入短信验证码"
                      name="sms"
                      prefix-icon="el-icon-lock"
                      clearable>
                      <el-button type="primary" slot="append">获取验证码</el-button>
                    </el-input>
                  </el-form-item>
                </el-form>
              </el-col>
            </el-row>
          </el-tab-pane>

        </el-tabs>
        <el-row>
          <el-col :span="20" style="text-align:left">
            <el-checkbox v-model="rememberMe"><span style="color:black">记住我 </span><span style="font-size:11px; color: grey">不是自己电脑不要勾选此项</span></el-checkbox>
          </el-col>
          <el-col :span="4">
            <el-link type="primary">忘记密码？</el-link>
          </el-col>
        </el-row>
        <el-row style="margin-top:5px">
        <el-col :span="11">
          <el-button type="primary" class="loginBtn" @click="login">登录</el-button>
        </el-col>
        <el-col :span="11" :offset="2">
          <el-button  class="loginBtn">注册</el-button>
        </el-col>
      </el-row>
    </div>
    <el-dialog title="验证码" :visible.sync="showDiag">
      <span>此处验证码</span>
    </el-dialog>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Login',
  components: {
    // login
  },
  data () {
    // const validateForm = (rule, value, callback) => {
    //   if (value.trim() === '' && value.length > 0) {
    //     callback(new Error('用户名不能为空'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      accountForm: {
        userName: '',
        password: ''
      },
      telForm: {
        tel: '',
        password: ''
      },
      loginRules: {
        // userName: [{ required: true, trigger: 'blur', validator: validateForm }]
        // password: [{ required: true, trigger: 'blur', validator: validateForm }]
      },
      telRules: {

      },
      supportedLocation: [
        {
          id: 0,
          name: '中国大陆'
        },
        {
          id: 1,
          name: '中国香港'
        },
        {
          id: 2,
          name: '中国澳门'
        },
        {
          id: 3,
          name: '中国台湾'
        }
      ],
      telKind: 0,
      loading: false,
      activeName: 'account',
      rememberMe: false,
      showDiag: false
    }
  },
  mounted () {

  },

  created () {

  },
  destroyed () {

  },
  methods: {
    login () {
      if (this.activeName === 'account') {
        this.$refs.accountForm.validate(valid => {
          if (valid) {
            this.$store.dispatch('user/login', this.accountForm).then(response => {

            }).catch(err => {
              console.log(err)
            })
          }
        })
      } else {

      }
    }
  },
  watch: {

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
  .loginBtn {
    width: 100%;
  }
  .el-input {
    .el-select {
      width: 120px;
    }
    .el-button {
      background-color: #409eff;
      color: #FFF;
    }
  }
</style>
