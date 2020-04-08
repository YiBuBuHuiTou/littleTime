import axios from 'axios'
// import router from '../router'
import store from '@/store/index'
import { getToken, setToken, setRefreshToken } from '@/utils/auth'
import { Message } from 'element-ui'
import { refreshToken } from '@/api/authAPI'
// 默认超时设置
axios.defaults.timeout = 100000
// 相对路径设置
axios.defaults.baseURL = ' http://106.54.198.188:7300/mock/5e8814bea47fbe3bbcf4bd9e/portal'
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
    if (result.code === 1001) {
      if (process.env.NODE_ENV === 'development') {
        Message.success('操作成功')
      }
      return result
    } else if (result.code === 6001) {
      Message.warning('token 过期')
      const self = this
      refreshToken().then(response => {
        if (response.accessToken !== '') {
          setToken(response.access_token)
        }
        if (response.refreshToken !== '') {
          setRefreshToken(response.refreshToken)
        }
        self.store.dispatch('common/retryCountClear')
      }).catch(err => {
        console.log('刷新token失败:' + err)
        if (store.getters.retry_count !== 0) {
          store.dispatch('common/retryCountClear')
        } else {
          refreshToken().then(response => {
            if (response.accessToken !== '') {
              setToken(response.access_token)
            }
            if (response.refreshToken !== '') {
              setRefreshToken(response.refreshToken)
            }
          })
        }
      })
    } else if (result.code === 2001) {
      Message.warning('访问被拒绝')
    } else if (result.code === 3001) {
      Message.warning('无权限操作')
    } else if (result.code === 4001) {
      Message.warning('请求出现错误')
    } else {
      Message.warning('出现未知错误')
    }
  },
  error => {
    Message({
      message: '请求失败，请检查网络重新再试。',
      type: 'error'
    })
    console.log(error)
  }
)
export default axios
