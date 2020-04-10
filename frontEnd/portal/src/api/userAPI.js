import axios from '@/http'

export function login (data) {
  return axios({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function logout () {
  return axios({
    url: '/user/logout',
    method: 'post'
  })
}

export function getUserInfo (credential) {
  return axios({
    url: '/user/getUserInfo',
    method: 'post',
    data: {
      credential: credential
    }
  })
}
