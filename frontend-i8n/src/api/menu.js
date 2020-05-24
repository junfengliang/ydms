import request from '@/utils/request'

var baseURL = process.env.VUE_APP_BASE_API_YDMS // 直接通过覆盖的方式

export function fetchAll() {
  return request({
    url: '/menu',
    method: 'get',
    baseURL: baseURL
  })
}
export function fetchLeft() {
  return request({
    url: '/menu',
    method: 'get',
    baseURL: baseURL,
    params: { 'all': 'false' }
  })
}

export function addMenu(data) {
  return request({
    url: '/menu',
    method: 'post',
    baseURL: baseURL,
    data
  })
}

export function editMenu(data) {
  return request({
    url: `/menu`,
    method: 'put',
    baseURL: baseURL,
    data
  })
}

export function deleteMenu(id) {
  return request({
    url: `/menu/${id}`,
    method: 'delete',
    baseURL: baseURL
  })
}
