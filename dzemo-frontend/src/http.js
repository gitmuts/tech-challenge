import axios from 'axios'
import store from './store'
// create a new axios instance
const instance = axios.create({
  baseURL: process.env.VUE_APP_BASE_URI
})

instance.interceptors.request.use(function (config) {
  return config
}, function (err) {
  return Promise.reject(err)
})

// before a response is returned stop nprogress
instance.interceptors.response.use(response => {
  console.log(response)
  return response
}, function (error) {
  // Do something with response error
  if (error.message.includes('Network Error')) {
    store.dispatch('alert/displayAlert', { snackbar: true, color: 'error', message: 'Failed to connect to server, check network connectivity' }, { root: true })
  }
  if (error.response && error.response.status === 401) {
    if (error.response.config.url.indexOf('token') === -1 && error.response.config.url.indexOf('refresh-token') === -1) {
      store.dispatch('session/logout', { root: true })
    }
  }
  return Promise.reject(error)
})

export default instance
