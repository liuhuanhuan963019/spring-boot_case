import Vue from 'vue'
import App from './App.vue'
import {
  Table,
  Input,
  Button,
  Popconfirm,
  message,
  Modal,
} from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
Vue.config.productionTip = false

Vue.use(Table)
Vue.use(Button)
Vue.use(Input)
Vue.use(Popconfirm)
Vue.use(Modal)

Vue.prototype.$message = message

new Vue({
  render: (h) => h(App),
}).$mount('#app')
