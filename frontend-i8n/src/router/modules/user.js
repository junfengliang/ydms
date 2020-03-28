import Layout from '@/layout'

const userRouter = {
  path: '/user',
  component: Layout,
  redirect: '/user/list',
  name: 'User',
  meta: {
    title: 'User',
    icon: 'peoples'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/user/list'),
      name: 'UserList',
      meta: { title: 'userList' }
    }
  ]
}
export default userRouter
