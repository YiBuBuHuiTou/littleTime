import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const moduleFiles = require.context('./modules', true, /\.js$/)
const modules = moduleFiles.keys().reduce((modules, modulePath) => {
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  const value = moduleFiles(modulePath)
  modules[moduleName] = value.default
  return modules
}

)

export default new Vuex.Store({

  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules
})
