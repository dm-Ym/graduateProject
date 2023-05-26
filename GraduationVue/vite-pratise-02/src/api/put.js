import service from '@/axios/axios.js';
import { ElNotification } from 'element-plus'

export function userUpdata(uid, username) {
  // put/post一致,只是名字不一样
  return service.put("/api/user", data, config)
}

/**
 *  更新留言
 */
export function updateComments(data) {
  return service.put("/api/comuser", data)
}
export function updateCommStatus(data) {
  return service.put("/api/comadmin", data)
}

/**
 *  更新用户信息
 *  user: user / admin
 */
export function updateUser(user, data) {
  return service.put("/api/" + user, data)
}

/**
 *  更新建议状态  
 */
export function updateAdviceStatus(id) {
  return service.put("/api/advice", { id: id })
}
export function updateStatus(id) {
  return service.put("/api/advice/status", { id: id })
}
/**
 *  更新个人接种记录
 */
export function updateRecord(data) {
  return service.put("/api/record", data)
}
/**
 * 预约
 */
export function updateBooking(data) {
  return service.put("/api/booking", data)
}