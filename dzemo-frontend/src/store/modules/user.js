import userService from '../../api/user'
const state = {
  user: {},
  loan: {}
}

const mutations = {
  setUser (state, user) {
    state.user = user
  },
  setLoan (state, loan) {
    state.loan = loan
  }
}

const actions = {
  getUser ({ commit }) {
    userService.getUser().then(response => {
      commit('setUser', response.data)
    }).catch(error => {
      console.log(error)
    })
  },
  getLoan ({ commit }) {
    userService.getLoan().then(response => {
      commit('setLoan', response.data)
    }).catch(error => {
      console.log(error)
    })
  }
}
const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}
