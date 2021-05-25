import request from '@/utils/request'

export function getAdmin(data) {
  return request({
    url: '/getAdminMessage',
    method: 'post',
    data
  })
}
export function getBoss(data) {
  return request({
    url: '/getBossMessage',
    method: 'post',
    data
  })
}
export function getLeader(data) {
  return request({
    url: '/getLeaderMessage',
    method: 'post',
    data
  })
}
export function getUser(data) {
  return request({
    url: '/getUserMessage',
    method: 'post',
    data
  })
}
