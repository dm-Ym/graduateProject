// import axios from "../axios/index";
import axios from "axios"

export function login(userName, pwd) {
  return axios.post('/login', (
    userName,
    pwd
  ))
}

export function getById(userid) {
  return axios.get('/', (
    userid
  ))
}