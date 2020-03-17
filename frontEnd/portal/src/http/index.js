import axios from 'axios'
// import router from '../router'
import store from '@/store/index'
import { getToken } from '@/utils/auth'
// 默认超时设置
axios.defaults.timeout = 50000
// 相对路径设置
axios.defaults.baseURL = 'https://www.easy-mock.com/mock/5e70e5ebe51d0e38885d6763/example'
// request interceptor config
axios.interceptors.request.use(
  response => {
    if (store.getters.token) {
      response.headers['X-Token'] = getToken()
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
export default axios
