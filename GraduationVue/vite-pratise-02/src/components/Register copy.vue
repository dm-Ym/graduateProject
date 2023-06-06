<template>
  <div class="bg-green-200 w-710px ml-[30%] mt-[7%]">
    <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="120px" class="demo-ruleForm" :size="formSize"
      status-icon>
      <el-form-item label="姓名" prop="full_name">
        <el-input v-model="ruleForm.full_name" class="w-500px" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="ruleForm.gender">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="ruleForm.age" class="w-500px" placeholder="请输入0-120的整数" />
      </el-form-item>
      <!-- <el-form-item label="身份证号" prop="age">
      <el-input v-model="ruleForm.age" class="w-500px" placeholder="请输入0-120的整数" />
      </el-form-item> -->
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="ruleForm.phone" class="w-500px" placeholder="请输入11位电话号码" />
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input type="password" v-model="ruleForm.pwd" class="w-500px" placeholder="请输入密码" />
      </el-form-item>
      <el-form-item label="社区" prop="neighborhood">
        <el-select v-model="ruleForm.neighborhood">
          <el-option label="倾月社区" value="倾月社区" />
          <el-option label="汇统社区" value="汇统社区" />
          <el-option label="蚌埠社区" value="蚌埠社区" />
          <el-option label="宏发社区" value="宏发社区" />
        </el-select>
      </el-form-item>
      <el-form-item label="小区" prop="community">
        <el-select v-model="ruleForm.community">
          <el-option label="盛世豪庭" value="盛世豪庭" />
          <el-option label="嘉林景苑" value="嘉林景苑" />
          <el-option label="紫金苑" value="紫金苑" />
          <el-option label="锦绣雷庭" value="锦绣雷庭" />
        </el-select>
      </el-form-item>
      <el-form-item label="单元楼" prop="residential">
        <el-input v-model="ruleForm.residential" class="w-500px" placeholder="请输入居住的单元楼" />
      </el-form-item>
      <el-form-item label="门牌号" prop="doorplate">
        <el-input v-model="ruleForm.doorplate" class="w-500px" placeholder="请输入门牌号" />
      </el-form-item>
      <el-form-item label="角色" prop="roles">
        <el-radio-group v-model="ruleForm.roles">
          <el-radio label="管理员" value="admin" />
          <el-radio label="普通用户" value="user" />
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)"> 注册 </el-button>
        <el-button @click="$router.push('/')">取消并返回主页</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'

const formSize = ref('large')
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  full_name: '',
  gender: '',
  age: '',
  phone: '',
  pwd: '',
  neighborhood: '',
  community: '',
  residential: '',
  doorplate: '',
  roles: ''
})

const rules = reactive<FormRules>({
  full_name: [
    { required: true, message: '请输入姓名,姓名不能为空', trigger: 'blur' },
  ],
  gender: [
    { required: true, message: '请选择性别.性别不能为空', trigger: 'change', },
  ],
  age: [
    { required: true, message: '请输入年龄', trigger: 'change', },
  ],
  phone: [
    { required: true, message: '电话不能为空且必须是11位', trigger: 'change', },
    { min: 11, max: 11, message: '电话长度必须为11位' }
  ],
  pwd: [
    { required: true, message: '密码不能为空', trigger: 'change', },
  ],
  neighborhood: [
    { required: true, message: '不能为空', trigger: 'blur' },
  ],
  community: [
    { required: true, message: '不能为空', trigger: 'blur' },
  ],
  residential: [
    { required: true, message: '不能为空', trigger: 'blur' },
  ],
  doorplate: [
    { required: true, message: '不能为空', trigger: 'blur' },
  ],
  roles: [
    { required: true, message: '必须选择', trigger: 'blur' },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}

const options = Array.from({ length: 10000 }).map((_, idx) => ({
  value: `${idx + 1}`,
  label: `${idx + 1}`,
}))
</script>
