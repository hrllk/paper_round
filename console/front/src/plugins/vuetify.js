import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import config from '@/config'
import 'font-awesome/css/font-awesome.css'
import '@mdi/font/css/materialdesignicons.css' // Ensure you are using css-loader

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: config.light
        }
    },
    icons: {
        iconfont: 'mdi', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg'
    },
});
