import service from '@/axios/axios';

/**
 *  user
 *  user: user / admin 
 */
export function getByName(data) {
  return service.get("/api/admin/search/" + data)
}
export function getAll() {
  return service.get("/api/admin/")
}

/**
 * Anno
 */
export function getAnno() {
  return service.get("/api/anno/")
}

/**
 * 投诉
 */
export function getComp() {
  return service.get("/api/complaints")
}
export function getOne(uid) {
  return service.get("/api/complaints/one/" + uid)
}
// const urls = [
//   '/api/complaints',
//   '/api/replies'
// ]
// export function getComp() {
//   return Promise.all(urls.map(url => service.get(url)))
// }

/**
 *  留言  
 */
export function getCommentsById(user, id) {
  return service.get("/api/com" + user + '/' + id)
}

export function getListComments() {
  return service.get("/api/comadmin")
}
export function getListDown() {
  return service.get("/api/comadmin/down")
}
export function getCommByName(comName) {
  return service.get("/api/comadmin/name/" + comName)
}



/**
 * 信息上报
 * user: user / admin
 */
export function getMsgById(user, id) {
  return service.get("/api/msg" + user + '/' + id)
}
export function getMsg() {
  return service.get("/api/msgadmin")
}

/**
 *  异常用户
 */
export function getAllAbno() {
  return service.get('/api/abno')
}
// 今日新增
export function getAllToday() {
  return service.get('/api/abno/today')
}
// 体温异常
export function getErrTem() {
  return service.get('/api/abno/errtem')
}
// 是否高风险返回
export function getIsRisk() {
  return service.get('/api/abno/risk')
}
// 康复用户
export function getAllHeal() {
  return service.get('/api/heal')
}

/**
 *  疫苗接种
 */
export function getBooking(id) {
  return service.get('/api/booking/' + id)
}
export function getListBooking() {
  return service.get('/api/booking')
}
export function getListBookingById(uid) {
  return service.get('/api/booking/user/' + uid)
}
// 记录
export function getRecord(id) {
  return service.get('/api/record/' + id)
}
export function getListRecord() {
  return service.get('/api/record')
}

/**
 *  行程上报
 */
export function getListTrip() {
  return service.get('/api/trip')
}
export function getListTripById(id) {
  return service.get('/api/trip/' + id)
}
/**
 *  意见
 */
export function getListAdvice() {
  return service.get('/api/advice')
}