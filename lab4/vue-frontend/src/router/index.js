import { createRouter, createWebHistory } from 'vue-router';

import IndexView from '../views/IndexView.vue'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexView
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes, 
})

router.beforeEach(async to => {
  const publicPages = ['/'];
  const authRequired = !publicPages.includes(to.path);
  const token = localStorage.getItem('token')

  if (authRequired && token == undefined) {
      return '/';
  }
});

export default router
