import { getUserInfo } from '@/api/userAPI'
import store from '@/store'
const accessTokenKey = 'accessToken'
const refreshTokenKey = 'refreshToken'
export function getToken () {
  return window.localStorage.getItem(accessTokenKey)
}
export function getRefreshToken () {
  return window.localStorage.getItem(refreshTokenKey)
}
export function setToken () {
  window.localStorage.setItem(accessTokenKey)
}
export function setRefreshToken () {
  window.localStorage.setItem(refreshTokenKey)
}
export function removeToken () {
  window.localStorage.removeItem(accessTokenKey)
}
export function removeRefresToken () {
  window.localStorage.removeItem(refreshTokenKey)
}
export function userInfo2Session (userInfo) {

}
export function loadDefaultMessage () {
  console.log(store.getters.userInfo.credential)
  if (store.getters.userInfo.credential !== '' && store.getters.access_token !== '') {
    const session = window.sessionStorage
    if (!session) {

    } else if (session.getItem('userName') == null || session.getItem('nickName') == null || session.getItem('role') == null) {
      getUserInfo(store.getters.userInfo.credential).then(response => {
        session.setItem('userName', response.data.userInfo.userName)
        session.setItem('nickName', response.data.userInfo.nickName)
        session.setItem('email', response.data.userInfo.email)
        session.setItem('phoneNumber', response.data.userInfo.phoneNumber)
        session.setItem('role', response.data.userInfo.role)
        store.getters.userInfo.userName = response.data.userInfo.userName
        store.getters.userInfo.nickName = response.data.userInfo.nickName
        store.getters.userInfo.email = response.data.userInfo.email
        store.getters.userInfo.phoneNumber = response.data.userInfo.phoneNumber
        store.getters.userInfo.role = response.data.userInfo.role
        console.log(store.getters.userInfo.phoneNumber)
      }).catch(err => {
        console.log(err)
      })
    } else {
      store.getters.userInfo.userName = session.getItem('userName')
      store.getters.userInfo.nickName = session.getItem('nickName')
      store.getters.userInfo.email = session.getItem('email')
      store.getters.userInfo.phoneNumber = session.getItem('phoneNumber')
      store.getters.userInfo.role = session.getItem('role')
    }
    return true
  } else {
    return false
  }
}
