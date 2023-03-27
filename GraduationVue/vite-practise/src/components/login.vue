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

      <!-- ref="formRef" 用来绑定表单验证到登录       rules对象是表单验证所需属性 -->
      <el-form ref="formRef" :model="form" class="w-250px" :rules="rules">

        <el-form-item prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input type="password" v-model="form.pwd" placeholder="请输入密码" />
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
import { login } from '../api/login'
import { useRouter } from "vue-router";
import { ElNotification } from 'element-plus'


const router = useRouter()

// do not use same name with ref
const form = reactive({
  userName: '',
  pwd: ''
})

/**
 * 表单验证
 */
const rules = {
  userName: [{
    required: true,  // 必须
    message: '用户名不能为空',  // 提示信息
    trigger: 'blur'   // 触发时机，焦点离开时
  },
    // {
    //   min: 3, max: 5, // 限制表单长度
    //   message: '长度不足或超过',  // 提示信息
    //   trigger: 'blur'
    // }
  ],

  pwd: [{
    required: true,  // 必须
    message: '密码不能为空',  // 提示信息
    trigger: 'blur'   // 触发时机，焦点离开时
  },
    // {
    // min: 3, max: 5, // 限制表单长度
    // message: '长度不足或超过',  // 提示信息
    // trigger: 'blur'
    // }
  ]
}

/**
 * 表单验证绑定到登录按钮
 */
const formRef = ref(null)

const onSubmit = () => {
  formRef.value.validate((valid) => {  // ref()须使用.value来拿到值
    if (!valid) {
      return false;
    }
    login(form.userName, form.pwd).then(res => {
      ElNotification({
        message: res.response.data,
        type: 'success',
        duration: 3000,
      })
    }).catch(err => {
      ElNotification({
        title: '错误提示',
        message: err.response.data,
        type: 'error',
        duration: 3000,
      })
      // router.push("/hello")
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
