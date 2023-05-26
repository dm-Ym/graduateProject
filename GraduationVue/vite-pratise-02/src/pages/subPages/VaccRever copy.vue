<template>
  <div class="h-90px">
    <el-button type="primary" class="mr-10" @click="dialogVisible = true">增加医院</el-button>
  </div>
  <el-table :data="tableData" border stripe class="w-[99%]">
    <el-table-column type="index" label="编号" width="70" />
    <el-table-column prop="hospital" label="医院" width="180" />
    <el-table-column prop="vaccine" label="疫苗名称" sortable width="180" />
    <el-table-column prop="boTime" label="日期时间" width="220" />
    <el-table-column prop="perNum" label="当前预约人数" width="150" />
    <el-table-column label="操作" width="180">
      <template v-slot="scope">
        <el-button type="primary" @click="submitForm(scope.row)">预约</el-button>
        <el-button v-if="isDelete" type="danger" @click="onDeleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 弹窗新增 -->
  <el-dialog v-model="dialogVisible" title="新增用户" width="40%" :size="formSize">
    <el-form :model="form" label-width="140px">
      <el-form-item label="姓名">
        <el-input v-model="form.fullName" class="w-500px" />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.gender" placeholder="请选择">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" class="w-500px" placeholder="请输入0-120的整数" />
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="form.phone" class="w-500px" placeholder="请输入11位电话号码" />
      </el-form-item>
      <el-form-item label="证件类型" prop="neighborhood">
        <el-select v-model="form.neighborhood" class="w-500px">
          <el-option label="居民身份证、临时居民身份证或者户口簿" value="居民身份证、临时居民身份证或者户口簿" />
          <el-option label="中国人民解放军军人身份证件、中国人民武装警察身份证件" value="中国人民解放军军人身份证件、中国人民武装警察身份证件" />
          <el-option label="港澳居民来往内地通行证、台湾居民来往大陆通行证或者其他有效旅行证件" value="港澳居民来往内地通行证、台湾居民来往大陆通行证或者其他有效旅行证件" />
          <el-option label="外国公民护照" value="外国公民护照" />
          <el-option label="法律、行政法规和国家规定的其他有效身份证件" value="法律、行政法规和国家规定的其他有效身份证件" />
        </el-select>
      </el-form-item>
      <el-form-item label="证件号码" prop="pwd" :label-width="140">
        <el-input type="password" v-model="form.pwd" class="w-500px" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitForm(form)"> 添加 </el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { updateBooking } from '@/api/put.js';
// import { deleteUser } from '@/api/delete.js';

export default {
  data() {
    return {
      tableData: [],
      // search: '',
      // currentPage: 1,
      // pageSize: 13,
      // total: 100,
      dialogVisible: false,
      isDelete: ''
    }
  },
  created() {
    this.getData()

  },
  methods: {
    getData() {
      getListBooking().then((res) => {
        // this.total = res.data.data.length
        this.tableData = res.data.data
      })
      // console.log(localStorage.getItem('roles'));
      if (localStorage.getItem('roles') === 'user') {
        this.isDelete = false
      } else {
        this.isDelete = true
      }
    },
    // 预约
    submitForm(row) {
      createBook(row.id).then(res => {
        console.log(res.data);
        this.getData()
        // this.dialogVisible = false
      })
    },
    // 删除医院信息
    onDeleteUser(row) {
      // deleteUser(row.userId).then(res => {
      //   this.getData()
      // })
    },
  }
}
</script>
<script setup>
import { reactive, ref } from 'vue'

const formSize = ref('large')

const form = reactive({

})
</script>