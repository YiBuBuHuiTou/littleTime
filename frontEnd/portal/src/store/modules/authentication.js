import { refreshToken } from '@/api/authAPI'
const state = {
  access_token: window.localStorage.getItem('access_token') === null ? '' : window.localStorage.getItem('access_token'),
  refresh_token: window.localStorage.getItem('refresh_token') === null ? '' : window.localStorage.getItem('refresh_token')
}

const mutations = {
  SET_ACCESS_TOKEN: (state, token) => {
    state.access_token = token
  },
  SET_REFRESH_TOKEN: (state, token) => {
    state.refresh_token = token
  }
}
const actions = {
  refreshToken ({ commit }) {
    refreshToken().then(response => {
      commit('SET_ACCESS_TOKEN', response.access_token)
      commit('SET_REFRESH_TOKEN', response.refresh_token)
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
