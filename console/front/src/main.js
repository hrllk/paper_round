import Vue from 'vue'
import axios from './plugins/axios'
import App from './App.vue'
import router from './Routes'
import store from './store/index'
import vuetify from './plugins/vuetify'
import * as VueGoogleMaps from 'vue2-google-maps';
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import VueCookies from "vue-cookies"
import VueWordCloud from 'vue-wordcloud';

Vue.use(Toast);
Vue.use(VueCookies)

Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyB7OXmzfQYua_1LEhRdqsoYzyJOPh9hGLg',
  },
});

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  store,
  axios,
  VueWordCloud,
  render: h => h(App),
}).$mount('#app')
