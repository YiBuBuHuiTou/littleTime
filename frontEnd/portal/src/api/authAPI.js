import axios from '@/http'
export function refreshToken () {
  return axios({
    url: '/auth/refreshToken',
    method: 'post'
  })
}
