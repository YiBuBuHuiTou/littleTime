import { login, logout } from '@/api/userAPI'

const state = {
  userInfo: {
    userName: '',
    nickName: '',
    credential: window.localStorage.getItem('credential') === null ? '' : window.localStorage.getItem('credential'),
    phoneNumber: '',
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
