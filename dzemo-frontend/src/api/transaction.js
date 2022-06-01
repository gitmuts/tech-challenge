import http from '../http'

export default {
  getRecentTransactions () {
    return http.get('/transaction/recent')
  },
  getTransactions (payload) {
    return http.get(`/transaction?start_date=${payload.startDate}&end_date=${payload.endDate}`)
  }
}
