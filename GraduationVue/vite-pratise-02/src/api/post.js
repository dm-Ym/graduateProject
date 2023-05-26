import service from '@/axios/axios.js';

/**
 *  Anno 只有管理员才能新增公告 
 */
export function createAnno(data) {
  return service.post('/api/anno', data)
}

/**
 *  用户留言/商品代买
 * user: user / admin
 */
export function createComm(user, data) {
  return service.post('/api/com' + user, data)
}
export function createCommm(data) {
  return service.post('/api/comadmin/reply', data)
}

/**
 *  新增投诉
 *  operate: 操作: complaints / replies
 */
export function createCom(operate, data) {
  return service.post('/api/' + operate, data)
}

/**
 *  上报信息
 */
export function createMsg(data) {
  return service.post('/api/msguser', data)
}

/**
 *  新增用户
 */
export function createUser(data) {
  return service.post('/api/admin', data)
}

/**
 *  疫苗预约和接种记录
 */
export function createBook(data) {
  return service.post('/api/booking', data)
}
export function createRecord(data) {
  return service.post('/api/record', data)
}

/**
 *  行程上报
 */
export function createTrip(data) {
  return service.post('/api/trip', data)
}

/**
 *  新增建议
 */
export function createAdvice(data) {
  return service.post('/api/advice', data)
}

