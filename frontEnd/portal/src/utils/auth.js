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
