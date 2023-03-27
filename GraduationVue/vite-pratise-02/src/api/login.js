import service from "../axios";
import { ElNotification } from 'element-plus'

export function login(uid, username) {

  return service.request({
    method: "POST",
    data: {
      uid: uid,
      username: username
    },
    url: "/api/login"
  })
  // .then(res => {
  //   ElNotification({
  //     message: res.data,
  //     type: 'success',
  //     duration: 3000,
  //   })
  //   console.log(res);

  // }).catch(err => {
  //   ElNotification({
  //     title: '错误提示',
  //     message: err.response.data,
  //     type: 'error',
  //     duration: 3000,
  //   })
  // })
}
