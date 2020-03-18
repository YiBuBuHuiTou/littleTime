const state = {
  retry_count: ''
}

const mutations = {
  RETRY_COUNT_ADD: state => {
    state.retry_count++
  },
  RETRY_COUNT_CLEAR: state => {
    state.retry_count = 0
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
