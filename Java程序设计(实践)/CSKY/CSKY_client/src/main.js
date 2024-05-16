import Vue from 'vue'
import App from './App.vue'
//引入路由
import router from './router'
//引入store
import store from '@/store'
//引入mock，模拟数据
import '@/mock/mockServer'
//ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI)
/*import {Button, Backtop, MessageBox, Link} from 'element-ui'
Vue.use(Button)
Vue.use(Backtop)
Vue.use(Link)
Vue.prototype.$msgbox = MessageBox;
Vue.prototype.$alert = MessageBox.alert;*/
//将所有api引入
import * as API from '@/api'
//图片懒加载
import VueLazyload from 'vue-lazyload'
import loadingGif from '@/assets/loading.gif'
Vue.use(VueLazyload, {
  preLoad: 1.3,
  loading: loadingGif,
  attempt: 1
})
//关闭生产提示
Vue.config.productionTip = false
new Vue({
  render: h => h(App),
  //注册路由
  router,
  //注册store
  store,
  //安装全局事件总线，$bus就是当前应用的vm
  beforeCreate() {
    Vue.prototype.$bus = this
    Vue.prototype.$API = API
  },
}).$mount('#app')
