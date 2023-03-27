<template>
  <el-row class="min-h-screen">

    <el-col :lg="16" :sm="12" class="bg-green-400 flex items-center justify-center a-bg">
      <!-- ref="formRef" 用来绑定表单验证到登录       rules对象是表单验证所需属性 -->
      <el-form ref="formRef" :model="form" class="w-250px" :rules="rules">

        <el-form-item prop="userid">
          <el-input v-model="form.userid" placeholder="请输入用户名" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" class="w-250px rounded-4xl">登录</el-button>
        </el-form-item>

      </el-form>
    </el-col>

  </el-row>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { getById } from '../api/login'
import { useRouter } from "vue-router";


const router = useRouter()

// do not use same name with ref
const form = reactive({
  userid: ''
})

/**
 * 表单验证
 */
const rules = {
  userid: [{
    required: true,  // 必须
    message: '用户名不能为空',  // 提示信息
    trigger: 'blur'   // 触发时机，焦点离开时
  }]
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
    getById(form.userid).then(res => {
      console.log(res);
    }).catch(err => {
      console.log(err);
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
