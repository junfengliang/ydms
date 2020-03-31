import Layout from '@/layout'

const sysRouter = {
  path: '/sys',
  component: Layout,
  redirect: '/sys/menu',
  name: 'Sys',
  meta: {
    title: 'System',
    icon: 'peoples'
  },
  children: [
    {
      path: 'menu',
      component: () => import('@/views/sys/menu'),
      name: 'MenuList',
      meta: { title: 'MenuList' }
    }
  ]
}
export default sysRouter
