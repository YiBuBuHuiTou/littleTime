import axios from '@/http'
/**
 * 获取受支持的地区
 */
export function getSupportedLocation () {
  return axios({
    url: 'getSupportedLocation',
    method: 'get'
  })
}
