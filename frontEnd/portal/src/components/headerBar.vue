<template>
  <div class="header">
    <el-row :gutter="20" class="header-row">
      <el-col :span="1" >主站</el-col>
      <div v-if="isLogin">
      <el-col :span="1" :offset="16">用户名</el-col>
      <el-col :span="1" >消息</el-col>
      <el-col :span="1" >动态</el-col>
      <el-col :span="1" >收藏</el-col>
      <el-col :span="1" >历史</el-col>
      <el-col :span="2" >创作中心</el-col>
      </div>
      <div v-else>
      <el-col :span="1" :offset="21" ><el-button type="" @click="redirect_to_login">登录</el-button></el-col>
      <el-col :span="1" ><el-button type="">注册</el-button></el-col>
      </div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'headerBar',
  data () {
    return {
      isLogin: false
    }
  },
  created () {
    if (this.$store.getters.userInfo.credential !== '' && this.$store.getters.access_token !== '') {
      this.isLogin = true
    } else {
      this.isLogin = false
    }
  },
  methods: {
    redirect_to_login () {
      this.$router.push('/login')
    },
    redirect_to_register () {
      this.$router.push('/register')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.header {
  height: 50px;
  width: 100%;
}
.header-row{
  width: 100%;
}
</style>
