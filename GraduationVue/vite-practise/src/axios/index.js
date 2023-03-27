import axios from "axios";

const service = axios.create({
  baseURL: "/"
})

service.interceptors.request.use((config) => {
  if (!config.url.startsWith('/api')) {
    config.url = `/api${config.url}`
  }
  return config
})

export default service