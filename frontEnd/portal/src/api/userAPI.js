// eslint-disable-next-line camelcase
import http_client from '@/http'

export function login (data) {
  return http_client({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function logout () {
  return http_client({
    url: '/user/logout',
    method: 'post'
  })
}
