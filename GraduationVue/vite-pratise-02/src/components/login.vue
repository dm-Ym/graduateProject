<template>
  <el-row class="min-h-screen">
    <el-col :lg="16" :sm="12" class="bg-green-400 flex items-center justify-center a-bg">
      <div>
        <div class="text-5xl text-light-300 text-stroke-1 mb-4">欢迎回来</div>
        <div>treo foo bar bae yeank chove clues throo</div>
      </div>
    </el-col>
    <el-col :lg="8" :sm="12" class="bg-light-300 flex items-center justify-center flex-col">
      <h1 class="font-bold text-gray-600 text-4xl mb-4">登录页面</h1>
      <div class="mb-2 flex items-center justify-center space-x-2">
        <span class="h-[1px] bg-green-500 w-16"></span>
        <span class="text-gray-500">使用账号密码登录</span>
        <span class="h-[1px] bg-green-500 w-16"></span>
      </div>

      <el-form ref="formRef" :model="form" class="w-250px" :rules="rules">

        <el-form-item prop="uid">
          <el-input v-model="form.uid" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item prop="username">
          <el-input type="password" v-model="form.username" placeholder="请输入密码" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" class="w-250px rounded-4xl">登录</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="onSubmit" class="w-250px rounded-4xl">注册</el-button>
        </el-form-item>

      </el-form>
    </el-col>

  </el-row>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { login } from "../api/login";
import { useRouter } from "vue-router";
import { ElNotification } from 'element-plus'

const router = useRouter()

const form = reactive({
  uid: '',
  username: ''
})

const rules = {
  uid: [{
    required: true,
    message: '用户名不能为空',
    trigger: 'blur'
  }],

  username: [{
    required: true,
    message: '密码不能为空',
    trigger: 'blur'
  }]
}

const formRef = ref(null)

const onSubmit = () => {
  formRef.value.validate((valid) => {  // ref()须使用.value来拿到值
    if (!valid) {
      return false;
    }

    login(form.uid, form.username)
      .then(res => {
        ElNotification({
          message: res.data,
          type: 'success',
          duration: 3000,
        })
        console.log(res);
      }).catch(err => {
        ElNotification({
          title: '错误提示',
          message: err.response.data,
          type: 'error',
          duration: 3000,
        })
      })

  })
}
</script>


<style>
.a-bg {
  background-image: url('../imgs/fqd.jpg');
  background-size: 100%;
  background-repeat: no-repeat;
  background-position: center;
}
</style>
