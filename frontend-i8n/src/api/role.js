import request from '@/utils/request'

var baseURL = process.env.VUE_APP_BASE_API_YDMS // 直接通过覆盖的方式

export function getRoutes() {
  return request({
    url: '/routes',
    method: 'get'
  })
}

export function getRoles() {
  return request({
    url: '/role',
    method: 'get',
    baseURL: baseURL
  })
}

export function addRole(data) {
  return request({
    url: '/role',
    method: 'post',
    baseURL: baseURL,
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/role/${id}`,
    method: 'put',
    baseURL: baseURL,
    data
  })
}

export function getRoleDetail(id) {
  return request({
    url: `/role/${id}`,
    method: 'get',
    baseURL: baseURL
  })
}

export function deleteRole(id) {
  return request({
    url: `/role/${id}`,
    method: 'delete',
    baseURL: baseURL
  })
}
