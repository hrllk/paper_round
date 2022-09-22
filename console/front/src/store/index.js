import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

// export default new Vuex.Store({
//
//   namespace: true,
//   state: {
//     drawer: true
//   },
//   mutations: {
//     toggleDrawer(state) {
//       state.drawer = !state.drawer;
//     }
//   },
//   actions: {
//     TOGGLE_DRAWER({ commit }) {
//       commit('toggleDrawer');
//     }
//   },
//   getters: {
//     DRAWER_STATE(state) {
//       return state.drawer;
//     }
//   }
// });

// const login = {
//   mutations: {

    // signup(payload){
    //   console.log("signup");
    //   this.$axios.post('auth/signup', payload, {
    //
    //   }).then(res => console.log("res: ", res));
    // },
    // login(state, payload) {
    // },


//   }
// }
const store = new Vuex.Store({
    // module
})
export default store;
