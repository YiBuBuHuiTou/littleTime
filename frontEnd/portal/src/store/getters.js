const getters = {
  retryCount: state => state.common.retryCount,
  token: state => state.authentication.token,
  userInfo: state => state.user.userInfo
}
export default getters
