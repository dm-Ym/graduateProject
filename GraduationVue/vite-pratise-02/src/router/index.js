import { createRouter, createWebHashHistory } from 'vue-router'

import Login from "../components/login.vue";
import Root from "../components/Root.vue";
import Anno from "../pages/Anno.vue"
import Folded from '../pages/Folded.vue';

const routes = [
  { path: '/', redirect: '/home' },
  {
    path: '/', component: Root,
    children: [
      {
        path: '/home', name: "首页", component: () => import('@/pages/Home.vue'),
        meta: { icon: 'el-icon-edit', roles: ['super admin user guest'], visiable: true }
      },
      {
        path: '/users', name: '用户中心', component: Folded, hidden: true,
        meta: { icon: 'el-icon-edit', roles: ['super admin'], visiable: false },
        children: [
          {
            path: 'user', name: '所有用户', hidden: false,
            component: () => import('@/pages/subPages/User.vue'),
          },
          // {
          //   path: 'manage', name: '用户管理', hidden: false,
          //   component: () => import('@/pages/subPages/UserManage.vue'),
          // },
        ]
      },
      {
        path: '/abnormal', name: '异常用户', hidden: true,
        component: () => import('@/pages/Folded.vue'),
        meta: { icon: 'el-icon-edit', roles: ['super admin'], visiable: false },
        children: [
          {
            path: 'users', name: '异常用户', hidden: false,
            component: () => import('@/pages/subPages/AbnoUser.vue'),
          },
          {
            path: 'today', name: '今日新增', hidden: false,
            component: () => import('@/pages/subPages/AbnoToday.vue'),
          },
          {
            path: 'healing', name: '康复用户', hidden: false,
            component: () => import('@/pages/subPages/AbnoHealing.vue'),
          },
        ]
      },
      {
        path: '/vaccination', name: '疫苗接种', hidden: true,
        component: () => import('@/pages/Folded.vue'),
        meta: { icon: 'el-icon-edit', roles: ['super admin'], visiable: false },
        children: [
          // {
          //   path: 'userecord', name: '个人接种记录', hidden: false,
          //   component: () => import('@/pages/user/VaccRecordUser.vue'),
          // },
          {
            path: 'record', name: '接种记录', hidden: false,
            component: () => import('@/pages/subPages/VaccRecord.vue'),
          },
          {
            path: 'reservation', name: '接种预约', hidden: false,
            component: () => import('@/pages/subPages/VaccRever.vue')
          }
        ]
      },
      {
        path: '/vaccination', name: '疫苗接种', hidden: true,
        component: () => import('@/pages/Folded.vue'),
        meta: { icon: 'el-icon-edit', roles: ['user'], visiable: false },
        children: [
          {
            path: 'userecord', name: '个人接种记录', hidden: false,
            component: () => import('@/pages/user/VaccReverUser.vue'),
          },
          {
            path: 'reservation', name: '接种预约', hidden: false,
            component: () => import('@/pages/subPages/VaccRever.vue')
          }
        ]
      },
      {
        path: '/guest', name: '访客模块', hidden: true,
        component: () => import('@/pages/Folded.vue'),
        meta: { icon: 'el-icon-edit', roles: ['super admin'], visiable: false },
        children: [
          {
            path: 'record', name: '访客名单', hidden: false,
            component: () => import('@/pages/subPages/GuestUser.vue')
          },
          // {
          //   path: 'reservation', name: '接种预约', hidden: false,
          //   component: () => import('@/pages/subPages/VaccRever.vue')
          // }
        ]
      },
      {
        path: '/health', name: "健康管理", component: Folded, hidden: true,
        meta: { icon: 'el-icon-edit', roles: ['super admin'], visiable: false },
        children: [
          // {
          //   path: 'usertrip', name: '以往行程', hidden: false,
          //   component: () => import('@/pages/user/HistoryTrip.vue'),
          //   meta: { roles: ['user'], visiable: false }
          // },
          {
            path: 'trip', name: '历史行程', hidden: false,
            component: () => import('@/pages/subPages/HealthTrip.vue')
          },
          {
            path: 'up', name: '信息上报', hidden: false,
            component: () => import('@/pages/subPages/HealthUp.vue')
          },
        ]
      },
      {
        path: '/health', name: "健康管理", component: Folded, hidden: true,
        meta: { icon: 'el-icon-edit', roles: ['user'], visiable: false },
        children: [
          {
            path: 'usertrip', name: '历史行程', hidden: false,
            component: () => import('@/pages/user/HistoryTrip.vue'),
            meta: { roles: ['user'], visiable: false }
          },
          {
            path: 'up', name: '信息上报', hidden: false,
            component: () => import('@/pages/subPages/HealthUp.vue')
          },
        ]
      },
      {
        path: '/anno', name: '公告', component: Anno, hidden: false,
        meta: { icon: 'el-icon-home', roles: ['super admin user guest'], visiable: false },
      },
      // {
      //   path: '/ganno', name: '公告/通知', component: () => import('@/pages/guest/GuestAnno.vue'), hidden: false,
      //   meta: { icon: 'el-icon-home', roles: ['guest'], visiable: true },
      // },
      {
        path: '/complaints', name: '投诉/意见', component: Folded, hidden: true,
        meta: { icon: 'el-icon-edit', roles: ['super admin'], visiable: false },
        children: [
          {
            path: 'others', name: '投诉', hidden: false,
            component: () => import('@/pages/subPages/CompOther.vue')
          },
          // {
          //   path: 'mine', name: '我的', hidden: false,
          //   component: () => import('@/pages/subPages/CompMine.vue')
          // },
          {
            path: 'advice', name: '意见', hidden: false,
            component: () => import('@/pages/subPages/CompAdvice.vue')
          },
        ]
      },
      {
        path: '/complaints', name: '投诉/意见', component: Folded, hidden: true,
        meta: { icon: 'el-icon-edit', roles: ['user'], visiable: false },
        children: [
          {
            path: 'others', name: '投诉', hidden: false,
            component: () => import('@/pages/subPages/CompOther.vue')
          },
          {
            path: 'mine', name: '我的', hidden: false,
            component: () => import('@/pages/subPages/CompMine.vue')
          },
          {
            path: 'advice', name: '意见', hidden: false,
            component: () => import('@/pages/subPages/CompAdvice.vue')
          },
        ]
      },
      {
        path: '/comments', name: '留言', component: Folded, hidden: true,
        meta: { icon: 'el-icon-home', roles: ['super admin'], visiable: false },
        children: [
          {
            path: 'users', name: '全部留言', hidden: false,
            component: () => import('@/pages/subPages/CommentsUser.vue'),
          },
          {
            path: 'down', name: '已处理留言', hidden: false,
            component: () => import('@/pages/subPages/CommentsDown.vue'),
          },
        ]
      },
      {
        path: '/commuser', name: '用户留言', hidden: false,
        component: () => import('@/pages/user/Comm.vue'),
        meta: { icon: 'el-icon-home', roles: ['user'], visiable: false },
      },
      {
        path: '/map', name: "国内各省疫情", component: () => import('@/pages/Map.vue'),
        meta: { icon: 'el-icon-home', roles: ['super admin user guest'], visiable: true },
      },
      {
        path: '/system', name: "系统管理", component: () => import('@/pages/Folded.vue'),
        meta: { icon: 'el-icon-home', roles: ['super admin user guest'], visiable: true }, hidden: true,
        children: [
          {
            path: 'about', name: '关于我们', hidden: false,
            component: () => import('@/pages/subPages/AboutUs.vue')
          },
        ]
      },
      {
        path: '/asd', name: "测试", component: () => import('@/pages/asd.vue'),
        meta: { icon: 'el-icon-home', roles: ['super admin user guest'], visiable: true },
      },
    ]
  },
  { path: '/login', name: 'login', component: Login, hidden: true },
  {
    path: '/register', name: '注册', component: () => import('@/components/Register.vue'),
    hidden: true
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router