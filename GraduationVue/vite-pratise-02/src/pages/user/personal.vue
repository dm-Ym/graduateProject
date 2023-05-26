<template>
  <button @click="isDisabled">编辑</button>
  <el-form ref="ruleFormRef" :model="ruleForm" label-width="120px" class="demo-ruleForm" size="large" status-icon
    :disabled="disabled">
    <el-form-item label="姓名" prop="fullName">
      <el-input v-model="ruleForm.fullName" class="w-500px" />
    </el-form-item>
    <el-form-item label="性别" prop="gender">
      <el-select v-model="ruleForm.gender">
        <el-option label="男" value="男" />
        <el-option label="女" value="女" />
      </el-select>
    </el-form-item>
    <el-form-item label="年龄" prop="age">
      <el-input v-model="ruleForm.age" class="w-500px" />
    </el-form-item>
    <el-form-item label="电话号码" prop="phone">
      <el-input v-model="ruleForm.phone" class="w-500px" />
    </el-form-item>
    <el-form-item label="密码" prop="pwd">
      <el-input type="password" v-model="ruleForm.pwd" class="w-500px" />
    </el-form-item>
    <el-form-item label="社区" prop="neighborhood">
      <el-input v-model="ruleForm.neighborhood" class="w-500px" />
    </el-form-item>
    <el-form-item label="小区" prop="community">
      <el-input v-model="ruleForm.community" class="w-500px" />
    </el-form-item>
    <el-form-item label="单元楼" prop="residential">
      <el-input v-model="ruleForm.residential" class="w-500px" />
    </el-form-item>
    <el-form-item label="门牌号" prop="doorplate">
      <el-input v-model="ruleForm.doorplate" class="w-500px" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" v-if="isShowBtn" @click="submitForm(ruleFormRef)"> 提交 </el-button>
      <el-button @click="$router.push('/')">取消并返回主页</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import store from '../../store';
import { updateUser } from '../../api/put';

export default {
  data() {
    return {
      user: store.state.perdat,
      ruleForm: '',
      // 是否显示提交按钮
      isShowBtn: 'false',
      // 是否禁用输入框
      disabled: 'false',
    }
  },
  created() {
    // console.log(this.user);
    this.ruleForm = this.user
  },
  methods: {
    submitForm() {
      updateUser('user', {
        userId: this.user.userId,
        fullName: this.ruleForm.fullName,
        gender: this.ruleForm.gender,
        age: this.ruleForm.age,
        phone: this.ruleForm.phone,
        pwd: this.ruleForm.pwd,
        neighborhood: this.ruleForm.neighborhood,
        community: this.ruleForm.community,
        residential: this.ruleForm.residential,
        doorplate: this.ruleForm.doorplate,
      }).then(res => {
        store.commit("SET_UserInfo", res.data.data)
        console.log(res.data.data);
        console.log(store.state.user);
      })
    },
    isDisabled() {
      this.disabled = !this.disabled
      this.isShowBtn = 'true'
    }
  }
}
</script>
