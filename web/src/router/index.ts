import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
//引入Ebook管理页面
import adminEbook from '../views/admin/adminEbook.vue'
//引入分类管理页面
import adminCategory from '../views/admin/adminCategory.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/ebook',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // 由于项目占用资源不大，所以不使用懒加载
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/admin/ebook',
    name: 'adminEbook',
    component: adminEbook
  },
  {
    path: '/admin/category',
    name: 'adminCategory',
    component: adminCategory
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
