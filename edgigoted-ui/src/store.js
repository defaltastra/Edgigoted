// src/store.js
import { createStore } from 'vuex'

const store = createStore({
  state() {
    return {
      isAuthenticated: false,
      user: null
    }
  },
  mutations: {
    login(state, user) {
      state.isAuthenticated = true
      state.user = user
    },
    logout(state) {
      state.isAuthenticated = false
      state.user = null
    }
  },
  actions: {
    login({ commit }, user) {
      // Simulate login
      setTimeout(() => {
        commit('login', user)
      }, 1000)
    },
    logout({ commit }) {
      commit('logout')
    }
  }
})

export default store
