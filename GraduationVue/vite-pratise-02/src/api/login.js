import service from "@/axios/axios.js";
import { ElNotification } from 'element-plus'

export function register(data) {
  return service.post('/api/register', data)
}

export function login(phone, pwd, roles) {

  return service.request({
    method: "POST",
    data: {
      phone: phone,
      pwd: pwd,
      roles: roles
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
