import http from '../http'

export default {
  getUser () {
    return http.get('/account')
  },
  getLoan () {
    return http.get('/loan')
  }
}
