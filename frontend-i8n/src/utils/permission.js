import store from '@/store'
import Layout from '@/layout'

export function routerMap(str) {
  const components = {
    '/sys/menu': () => import('@/views/sys/menu'),
    '/user/list': () => import('@/views/user/list')
  }
  let component = components[str]
  if (!component) {
    component = Layout
  }
  return component
}

/**
 * @param {Array} value
 * @returns {Boolean}
 * @example see @/views/permission/directive.vue
 */
export default function checkPermission(value) {
  if (value && value instanceof Array && value.length > 0) {
    const roles = store.getters && store.getters.roles
    const permissionRoles = value

    const hasPermission = roles.some(role => {
      return permissionRoles.includes(role)
    })

    if (!hasPermission) {
      return false
    }
    return true
  } else {
    console.error(`need roles! Like v-permission="['admin','editor']"`)
    return false
  }
}
/**
 * 递归处理后台异步返回路由表
 * @param routes routes
 * @param roles
 */
export function filterRouters(routes, roles) {
  const res = []
  routes.forEach((route, i) => {
    const tmp = {
      path: route.url,
      name: route.name,
      component: routerMap(route.url),
      meta: {
        title: route.name,
        icon: route.icon
        // affix: true
        // roles: ['admin', 'editor']
      }
    }
    route.affix = true
    route.children ? tmp.children = route.children : ''
    if (tmp.children) {
      tmp.children = filterRouters(tmp.children, roles) || {}
    }
    res.push(tmp)
  })
  return res
}
