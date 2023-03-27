<template>
  <el-row>
    <el-form :model="formm">
      <el-form-item prop="userid">
        <el-input v-model="formm.userid" placeholder="请输入id" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmitt" class="w-250px rounded-4xl">登录</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>


<script>
import { reactive } from 'vue'
import { getById } from '../api/login'

const formm = reactive({
  userid: ''
})
const onSubmitt = () => {
  getById(form.userid).then(res => {
    console.log(res);
  }).catch(err => {
    console.log(err);
  })
}
</script>