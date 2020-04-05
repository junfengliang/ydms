import request from '@/utils/request'

var baseURL = process.env.VUE_APP_BASE_API_YDMS // 直接通过覆盖的方式

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    baseURL: baseURL,
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    baseURL: baseURL,
    headers: { 'X-Token': token }
  })
}
export function getDetail(id) {
  return request({
    url: `/user/${id}`,
    method: 'get',
    baseURL: baseURL
  })
}
export function logout() {
  return request({
    url: '/user/logout',
    baseURL: baseURL,
    method: 'post'
  })
}
export function fetchList(query) {
  return request({
    url: '/user/list',
    method: 'get',
    baseURL: baseURL,
    params: query
  })
}

export function addUser(data) {
  return request({
    url: '/user',
    method: 'post',
    baseURL: baseURL,
    data
  })
}
export function editUser(id, data) {
  return request({
    url: `/user/${id}`,
    method: 'put',
    baseURL: baseURL,
    data
  })
}
export function deleteUser(id) {
  return request({
    url: `/user/${id}`,
    method: 'delete',
    baseURL: baseURL
  })
}
