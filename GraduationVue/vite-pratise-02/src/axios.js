import axios from "axios";

const service = axios.create({
  headers: {
    'Content-Type': "application/json",
    Accept: "application/json"
  }
})

export default service