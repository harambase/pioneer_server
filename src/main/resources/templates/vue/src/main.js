// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import resource from 'vue-resource'
import 'iview/dist/styles/iview.css'
import './assets/css/common/common.css'

Vue.config.productionTip = false
Vue.use(iView)
Vue.use(resource)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
