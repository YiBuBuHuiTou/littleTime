import axios from 'axios'
import router from '../router'
import store from '@/store/index'
import auth from '@/utils/auth'
// 默认超时设置
axios.defaults.timeout = 50000
// 相对路径设置
axios.defaults.baseURL = process.env.API_BASE_URL
// request interceptor config
axios.interceptors.request.use(
  response => {
    if (store.getters.token) {
      response.headers['X-Token'] = auth.getToken()
    }
    return response
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)
/**
 * response interceptor
 * code 200  => success
 *      201  => expired
 *
 */
axios.interceptors.response.use(
  response => {
    const result = response.data
    if (result.code !== 200 || result.code !== 201) {
      this.$message.error('request error')
    } else if (result.code === 201) {
      // resend request
    } else {
      return result
    }
  },
  error => {
    console.log(error)
  }
)
