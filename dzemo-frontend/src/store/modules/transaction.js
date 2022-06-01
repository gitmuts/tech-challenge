import transactionService from '../../api/transaction'
const state = {
}

const mutations = {
}

const actions = {
  getRecentTransactions ({ commit }) {
    return new Promise((resolve, reject) => {
      transactionService.getRecentTransactions().then((res) => {
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getTransactions ({ commit }, payload) {
    return new Promise((resolve, reject) => {
      transactionService.getTransactions(payload).then((res) => {
        resolve(res)
      }).catch(error => {
        reject(error)
      })
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
