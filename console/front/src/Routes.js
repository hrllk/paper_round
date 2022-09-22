import Vue from 'vue';
import VueRouter from 'vue-router';

import VueCookies from 'vue-cookies';
// import Layout from '@/components/Layout/Layout';

// Pages
// import Dashboard from '@/pages/Dashboard/Dashboard';
// import Typography from '@/pages/Typography/Typography'
// import Tables from '@/pages/Tables/Basic'
// import Notifications from '@/pages/Notifications/Notifications'
// import Icons from '@/pages/Icons/Icons'
// import Charts from '@/pages/Charts/Charts'
// import Maps from '@/pages/Maps/Google'
// import Error from "@/pages/Error/Error";
// import Login from "@/pages/Login/Login";

Vue.use(VueRouter);


const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('./pages/Login/Login')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach(async (to, from, next) => {

  // console.log("VueCookies: ", VueCookies);
  console.log("to: ", to);
  console.log("from: ", from);
  console.log("next: ", next);
  // console.log("store : ", store);
  console.log('accessToken : ', VueCookies.get('accessToken'))
  // console.log('refreshToken: ', VueCookies.get('refreshToken'))

  next();
})
export default router;
// export default new Router({
//   mode: 'history',
//   routes: [
//     {
//       path: '/login',
//       name: 'Login',
//       component: Login
//     },
  //   {
  //   path: '/',
  //   redirect: 'login',
  //   name: 'Layout',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'dashboard',
  //       name: 'Dashboard',
  //       component: Dashboard,
  //     },
  //     {
  //       path: 'typography',
  //       name: 'Typography',
  //       component: Typography,
  //     },
  //     {
  //       path: 'tables',
  //       name: 'Tables',
  //       component: Tables
  //     },
  //     {
  //       path: 'notifications',
  //       name: 'Notifications',
  //       component: Notifications
  //     },
  //     {
  //       path: 'icons',
  //       name: 'Icons',
  //       component: Icons
  //     },
  //     {
  //       path: 'charts',
  //       name: 'Charts',
  //       component: Charts
  //     },
  //     {
  //       path: 'maps',
  //       name: 'Maps',
  //       component: Maps
  //     },
  //   ],
  // },
  //   {
  //     path: '*',
  //     name: 'Error',
  //     component: Error
  //   }
  // ],
// });

