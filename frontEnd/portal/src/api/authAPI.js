// eslint-disable-next-line camelcase
import http_client from '@/http'
export function refreshToken () {
  return http_client({
    url: '/auth/refreshToken',
    method: 'post'
  })
}
