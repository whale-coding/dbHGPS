import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入element-ui
// import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
// 引入axios
import axios from 'axios';
// 引入element-ui(英文版本)
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en'; // 引入英文语言包
import 'element-ui/lib/theme-chalk/index.css';
// 使用ElementUI
// Vue.use(ElementUI);
Vue.use(ElementUI, { locale });

// 将axios添加到vue的原型上,全局注册，使用方法为：this.$axios
Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
