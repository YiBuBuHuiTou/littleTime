const state = {
  retryCount: ''
}

const mutations = {
  RETRY_COUNT_ADD: state => {
    state.retryCount++
  },
  RETRY_COUNT_CLEAR: state => {
    state.retryCount = 0
  }
}

const actions = {
  retryCountAdd ({ commit }) {
    commit('RETRY_COUNT_ADD')
  },
  retryCountClear ({ commit }) {
    commit('RETRY_COUNT_CLEAR')
  }
}

export default {
  state,
  mutations,
  actions
}
