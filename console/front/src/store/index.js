
import Vue from 'vue';
import Vuex from 'vuex';
// 데이터를 로컬스토리지에 저장하기위한 용이한 플러그인
// import createPersistedState from 'vuex-persistedstate';
import VueCookies from 'vue-cookies'
import router from '@/Routes'

Vue.use(Vuex);

export default new Vuex.Store({

    namespace: true,
    state: {
        drawer: true,
        userId: ''
    },
    mutations: {
        toggleDrawer(state) {
            state.drawer = !state.drawer;
        },
        /***
         * 모든쿠키제거
         */
        removeToken () {
            VueCookies.keys().forEach(cookie => VueCookies.remove(cookie));
            router.push("/login");
        },

    },
    actions: {
        TOGGLE_DRAWER({ commit }) {
            commit('toggleDrawer');
        }
    },
    getters: {
        DRAWER_STATE(state) {
            return state.drawer;
        }
    },
    plugins: [
        // createPersistedState({
            /***
             * paths 옵션은 어떤페이지에서 진행할 것인지에대해 설정.
             * 미설정시, 모든 페이지마다 로컬스토리지저장된값을 들고다니기 때문에
             * 속도가 저하되는 경험을 할 수 있다.
             */
            // paths: ["keywords"]
        // })
    ]
});
