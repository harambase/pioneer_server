import Vue from 'vue'
import Router from 'vue-router'
import homePage from '@/components/homePage'
import centerStorage from '@/components/contentPage/centerStorage'
import localStorage from '@/components/contentPage/localStorage'
import messageCenter from '@/components/contentPage/messageCenter'
import userCenter from '@/components/contentPage/userCenter'
import personalCenter from '@/components/contentPage/personalCenter'
import accountInfo from '@/components/personalCenterPage/accountInfo'
import personalInfo from '@/components/personalCenterPage/personalInfo'
import changePwd from '@/components/personalCenterPage/changePwd'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'homePage',
      component: homePage
    }, {
      path: '/centerStorage',
      name: 'centerStorage',
      component: centerStorage,
      meta: {title: '中央仓库'}
    }, {
      path: '/localStorage',
      name: 'localStorage',
      component: localStorage,
      meta: {title: '本地仓库'}
    }, {
      path: '/messageCenter',
      name: 'messageCenter',
      component: messageCenter,
      meta: {title: '消息中心'}
    }, {
      path: '/userCenter',
      name: 'userCenter',
      component: userCenter,
      meta: {title: '用户中心'},
      children: [
        {
          path: 'accountInfo',
          name: 'accountInfo',
          meta: {title: '个人中心', tabName: '账号信息'},
          component: accountInfo
        }, {
          path: 'personalInfo',
          name: 'personalInfo',
          meta: {title: '个人中心', tabName: '个人信息'},
          component: personalInfo
        }, {
          path: 'changePwd',
          name: 'changePwd',
          meta: {title: '个人中心', tabName: '修改密码'},
          component: changePwd
        }
      ]
    }, {
      path: '/personalCenter',
      name: 'personalCenter',
      component: personalCenter,
      meta: {title: '个人中心'},
      children: [
        {
          path: 'accountInfo',
          name: 'accountInfo',
          meta: {title: '个人中心', tabName: '账号信息'},
          component: accountInfo
        }, {
          path: 'personalInfo',
          name: 'personalInfo',
          meta: {title: '个人中心', tabName: '个人信息'},
          component: personalInfo
        }, {
          path: 'changePwd',
          name: 'changePwd',
          meta: {title: '个人中心', tabName: '修改密码'},
          component: changePwd
        }
      ]
    }
  ]
})
