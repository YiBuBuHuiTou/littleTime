import axios from 'axios'
import router from '../router'
import store from '@/store/index'
// 默认超时设置
axios.defaults.timeout = 50000
// 相对路径设置
axios.defaults.baseURL = process.env.API_BASE_URL
axios.interceptors.request.use(

)
