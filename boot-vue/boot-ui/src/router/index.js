import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/login.vue'
import MainIndex from '@/components/main/index.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/index',
      name: 'MainIndex',
      component: MainIndex
    },
    {
      path: '/manage',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
