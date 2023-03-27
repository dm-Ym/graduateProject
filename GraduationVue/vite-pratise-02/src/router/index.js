import { createRouter, createWebHashHistory } from 'vue-router'

import Login from "../components/login.vue";
import Home from "../pages/Home.vue"
import Users from "../pages/Users.vue";

import Lao3 from "../pages/Lao3.vue";
import Lao4 from "../pages/Lao4.vue";

const routes = [

  { path: '/home', name: 'home', hidden: false, component: Home },
  {
    path: '/users', name: 'users', hidden: false, component: Users, redirect: '/users/tab3',
    children: [
      { path: 'tab3', name: 'lao3', component: Lao3 },
      { path: 'tab4', name: 'lao4', component: Lao4 },
    ]
  },
  { path: '/login', name: 'login', hidden: true, component: Login },
  { path: '/', name: '重定向路劲', redirect: '/home', hidden: true }

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router