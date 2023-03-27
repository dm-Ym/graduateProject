import { createRouter, createWebHashHistory } from 'vue-router'

// import HelloWorld from "../components/HelloWorld.vue";
import Login from "../components/login.vue";
import hello from "../components/hello.vue";

const routes = [
  {
    path: '/',
    component: Login
  },
  {
    path: '/hello',
    component: hello
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router