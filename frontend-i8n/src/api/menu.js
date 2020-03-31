import request from '@/utils/request'

var baseURL = process.env.VUE_APP_BASE_API_YDMS // 直接通过覆盖的方式

export function fetchAll() {
  return request({
    url: '/menu/listAll',
    method: 'get',
    baseURL: baseURL
  })
}
export function fetchLeft() {
  return request({
    url: '/menu/listLeft',
    method: 'get',
    baseURL: baseURL
  })
}

export function addMenu(data) {
  return request({
    url: '/menu/add',
    method: 'post',
    baseURL: baseURL,
    data
  })
}

export function editMenu(data) {
  return request({
    url: `/menu/edit`,
    method: 'post',
    baseURL: baseURL,
    data
  })
}

export function deleteMenu(id) {
  return request({
    url: `/menu/delete`,
    method: 'post',
    baseURL: baseURL,
    params: { id }
  })
}
