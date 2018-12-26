import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Note from './views/Note.vue'
import BlogMain from './views/admin/BlogMain.vue'
import ArticleAdd from './views/admin/ArticleAdd.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
    path: '/articleAdd',
    name: 'articleAdd',
    component: ArticleAdd
    },
    {
      path: '/blogMain',
      name: 'blogMain',
      component: BlogMain
    }, 
    {
      path: '/note',
      name: 'note',
      component: Note
    }, 
    {
      path: '/',
      name: 'home',
      components:{
        default:Home,
        'note':Note
      }
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }
  ]
})
