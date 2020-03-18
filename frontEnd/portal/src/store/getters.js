const getters = {
  retry_count: state => state.common.retry_count,
  access_token: state => state.authentication.access_token,
  refresh_token: state => state.authentication.refresh_token,
  userInfo: state => state.user.userInfo
}
export default getters
