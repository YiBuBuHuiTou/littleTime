import { login, logout } from '@/api/userAPI'

const state = {
  userInfo: {
    userName: '',
    nickName: '',
    tel: '',
    email: '',
    role: ''
  }
}
const mutations = {

}
const actions = {
  login () {
    login()
  },
  logout () {
    logout()
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
