import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Note from './views/Note.vue'
import BlogMain from './views/admin/BlogMain.vue'
import ArticleAdd from './views/admin/ArticleAdd.vue'
import ArticleList from './views/admin/ArticleList.vue'
import ArticleDetail from './views/admin/ArticleDetail.vue'
import CommentList from './views/admin/CommentList.vue'
import ArticleEdit from './views/admin/ArticleEdit.vue'
import Detail from './views/Detail.vue'
import Login from './views/Login.vue'

import NoteCom from "@/components/NoteCom.vue";
import AboutCom from "@/components/AboutCom.vue";

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [   
    {
    path: '/login',
    name: 'login',
    components:
    {
      'admin': Login
    }
  },
    {
    path: '/detail',
    name: 'detail',
    components:
    {
      'admin': Detail
    }
  },
    {
      path: '/articleEdit',
      name: 'articleEdit',
      components:
      {
        'admin': ArticleEdit
      }
    },
    {
      path: '/commentList',
      name: 'commentList',
      components:
      {
        'admin': CommentList
      }
    },
    {
      path: '/articleDetail',
      name: 'articleDetail',
      components:
      {
        'admin': ArticleDetail
      }
    },
    {
      path: '/articleList',
      name: 'articleList',
      components:
      {
        'admin': ArticleList
      }

    },
    {
      path: '/articleAdd',
      name: 'articleAdd',
      components: {
        'admin': ArticleAdd
      }
    },
    {
      path: '/blogMain',
      name: 'blogMain',
      components: {
        'admin': BlogMain
      }
    },
    {
      path: '/note',
      name: 'note',
      component: Note
    },
    {
      path: '/',
      name: 'home',
      components: {
        'home': Home,
        'note': NoteCom,
        'about': AboutCom
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
