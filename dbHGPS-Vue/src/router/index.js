import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/index',
    redirect: '/home'
  },
  {
    path: '/index,html',
    redirect: '/home'
  },
  // 其他页面
  {
    path: '/',
    component: resolve => require(['@/components/Layout.vue'], resolve),
    children: [
      {
        path: 'home',
        component: resolve => require(['@/views/Home.vue'], resolve)
      },
      {
        path: 'search',
        component: resolve => require(['@/views/Search.vue'], resolve)
      },
      {
        path: 'page',
        component: resolve => require(['@/views/Search.vue'], resolve)
      },
      {
        path: 'download',
        component: resolve => require(['@/views/Download.vue'], resolve)
      },
      {
        path: 'visualization',
        component: resolve => require(['@/views/Visualization.vue'], resolve)
      },
      {
        path: 'predict',
        component: resolve => require(['@/views/Predict.vue'], resolve)
      },
      {
          path: 'about',
          component: resolve => require(['@/views/About.vue'], resolve)
      },
      {
        path: 'contact',
        component: resolve => require(['@/views/Contact.vue'], resolve)
      },
  ]
  },
]

const router = new VueRouter({
  mode: 'history',    // history需要服务端支持
  base: '/',  // 配置 nginx访问结构
  routes
})

export default router
