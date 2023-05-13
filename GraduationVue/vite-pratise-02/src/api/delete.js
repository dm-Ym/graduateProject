import service from '@/axios/axios.js';

export function deleteUser(id) {
  return service.delete("/api/admin/" + id)
  // 二: 将数据放在请求体内
  // return service.delete(url, { data: { id: 1 } })
}


/**
 *  删除公告
 * data: 需要deleteName:phone
 */
export function deleteAnno(id) {
  return service.delete("/api/anno", {
    deleteName: '123',
    id: id
  })
}

/**
 *  删除留言: 
 * user: user / admin
 */
export function deleteComments(user, id) {
  return service.delete("/api/com" + user + '/' + id)
}

/**
 *  删除投诉: 
 *  operate: 参数: complaints / replies
 */
export function deleteComplaints(operate, id) {
  return service.delete("/api/" + operate + '/' + id)
}

export function deleteCompOne(data) {
  return service.delete("/api/complaints/one", {
    data: {
      uid: data.uid,
      id: data.id
    }
  })
}

/**
 *  删除信息
 */
export function deleteMsg(id) {
  return service.delete("/api/msguser/" + id)
}

/**
 *  删除异常用户
 */
export function deleteAbno(id) {
  return service.delete("/api/abno/" + id)
}

/**
 *  删除预约
 */
export function deleteBook(id) {
  return service.delete("/api/booking/" + id)
}

/**
 *  删除行程
 */
export function deleteTrip(id) {
  return service.delete("/api/trip/" + id)
}
/**
 *  删除意见
 */
export function deleteAdviceAdmin(id) {
  return service.delete("/api/advice/" + id)
}

/**
 *  删除接种记录
 */
export function deleteRecord(id) {
  return service.delete("/api/record/" + id)
}