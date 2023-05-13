import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'

// 创建一个新的 store 实例
const store = createStore({
  state() {
    return {
      user: {},
      roles: ''
    }
  },
  mutations: {
    SET_UserInfo(state, user) {
      state.user = user
    },
    SET_Roles(state, roles) {
      state.roles = roles
    },
    clearUser(state) {
      state.user = null
    }
  },
  actions: {
    clearUser({ commit }) {
      commit('clearUser')
    }
  },
  plugins: [createPersistedState()]
})

export default store