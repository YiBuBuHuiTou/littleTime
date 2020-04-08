import { refreshToken } from '@/api/authAPI'
const state = {
  token: {
    accessToken: window.localStorage.getItem('accessToken') === null ? '' : window.localStorage.getItem('accessToken'),
    refreshToken: window.localStorage.getItem('refreshToken') === null ? '' : window.localStorage.getItem('refreshToken')
  }
}

const mutations = {
  SET_ACCESS_TOKEN: (state, token) => {
    state.token.accessToken = token
  },
  SET_REFRESH_TOKEN: (state, token) => {
    state.token.refreshToken = token
  }
}
const actions = {
  refreshToken ({ commit }) {
    refreshToken().then(response => {
      commit('SET_ACCESS_TOKEN', response.accessToken)
      commit('SET_REFRESH_TOKEN', response.refreshToken)
    }).catch(err => {
      console.log(err)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
