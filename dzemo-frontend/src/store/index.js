import Vue from 'vue'
import Vuex from 'vuex'
import alert from './modules/alert'
import user from './modules/user'
import transaction from './modules/transaction'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    alert,
    user,
    transaction
  }
})
