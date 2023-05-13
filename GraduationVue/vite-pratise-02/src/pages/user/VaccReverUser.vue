<template>
  <div class="h-50px">
    <el-button type="primary" class="mr-10" @click="dialogVisible = true">新增记录</el-button>
  </div>
  <el-table :data="tableData" border stripe class="w-[99%]">
    <el-table-column type="index" label="编号" width="70" />
    <el-table-column prop="manufacturers" label="疫苗生产厂商" width="180" />
    <el-table-column prop="types" label="疫苗种类" width="180" />
    <el-table-column prop="finish" label="完成剂数" sortable width="180" />
    <el-table-column prop="createTime" label="完成日期" width="220" />
    <el-table-column label="操作" width="180">
      <template v-slot="scope">
        <el-button type="primary" @click="onUpdateDia(scope.row)">修改</el-button>
        <el-button type="danger" @click="onDelete(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!-- 弹窗新增 -->
  <el-dialog v-model="dialogVisible" title="新增" width="40%" :size="formSize">
    <el-form :model="form" label-width="140px">
      <el-form-item label="疫苗生产厂商">
        <el-select v-model="form.manufacturers" placeholder="请选择">
          <el-option label="国药北京生物新冠灭活疫苗" value="国药北京生物新冠灭活疫苗" />
          <el-option label="武汉生物北京科兴中维新冠肺炎疫苗" value="武汉生物北京科兴中维新冠肺炎疫苗" />
          <el-option label="武汉生物新冠疫苗" value="武汉生物新冠疫苗" />
          <el-option label="安徽智飞龙科马新冠疫苗" value="安徽智飞龙科马新冠疫苗" />
          <el-option label="康希诺新冠疫苗" value="康希诺新冠疫苗" />
        </el-select>
      </el-form-item>
      <el-form-item label="疫苗种类">
        <el-select v-model="form.types" placeholder="请选择">
          <el-option label="灭活疫苗" value="灭活疫苗" />
          <el-option label="重组蛋白疫苗" value="重组蛋白疫苗" />
          <el-option label="腺病毒载体疫苗" value="腺病毒载体疫苗" />
          <el-option label="核酸疫苗" value="核酸疫苗" />
        </el-select>
      </el-form-item>
      <el-form-item label="完成剂数" prop="finish">
        <el-select v-model="form.finish" placeholder="请选择">
          <el-option label="一" value="一" />
          <el-option label="二" value="二" />
          <el-option label="三" value="三" />
          <el-option label="四" value="四" />
          <el-option label="五" value="五" />
          <el-option label="六" value="六" />
        </el-select>
      </el-form-item>
      <el-form-item label="当前时间">
        <div class="w-850px bg-gray-100 pl-40px border-2">{{ currentTime }}</div>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitForm(form)"> 添加 </el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 弹窗更新 -->
  <el-dialog v-model="dialogVisible1" title="新增" width="40%" :size="formSize">
    <el-form :model="form1" label-width="140px">
      <el-form-item label="疫苗生产厂商">
        <el-select v-model="form1.manufacturers" placeholder="请选择">
          <el-option label="国药北京生物新冠灭活疫苗" value="国药北京生物新冠灭活疫苗" />
          <el-option label="武汉生物北京科兴中维新冠肺炎疫苗" value="武汉生物北京科兴中维新冠肺炎疫苗" />
          <el-option label="武汉生物新冠疫苗" value="武汉生物新冠疫苗" />
          <el-option label="安徽智飞龙科马新冠疫苗" value="安徽智飞龙科马新冠疫苗" />
          <el-option label="康希诺新冠疫苗" value="康希诺新冠疫苗" />
        </el-select>
      </el-form-item>
      <el-form-item label="疫苗种类">
        <el-select v-model="form1.types" placeholder="请选择">
          <el-option label="灭活疫苗" value="灭活疫苗" />
          <el-option label="重组蛋白疫苗" value="重组蛋白疫苗" />
          <el-option label="腺病毒载体疫苗" value="腺病毒载体疫苗" />
          <el-option label="核酸疫苗" value="核酸疫苗" />
        </el-select>
      </el-form-item>
      <el-form-item label="完成剂数" prop="finish">
        <el-select v-model="form1.finish" placeholder="请选择">
          <el-option label="一" value="一" />
          <el-option label="二" value="二" />
          <el-option label="三" value="三" />
          <el-option label="四" value="四" />
          <el-option label="五" value="五" />
          <el-option label="六" value="六" />
        </el-select>
      </el-form-item>
      <el-form-item label="当前时间">
        <div class="w-850px bg-gray-100 pl-40px border-2">{{ currentTime }}</div>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onUpdate(form1)"> 添加 </el-button>
        <el-button @click="dialogVisible1 = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { getRecord } from '@/api/get';
import store from '../../store';
import { createRecord } from '@/api/post.js';
import { deleteRecord } from '@/api/delete.js';
import { updateRecord } from '@/api/put.js';

export default {
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      dialogVisible1: false,
      isDelete: '',
      user: store.state.user,
      formSize: 'large',
      background: true,
      currentTime: '',
      scopeRow: ''
    }
  },
  created() {
    this.getData()
    setInterval(() => {
      this.currentTime = new Date().toLocaleString();
    }, 1000);
  },
  methods: {
    getData() {
      getRecord(this.user.userId).then((res) => {
        // console.log(res.data.data);
        this.tableData = res.data.data
      })
      // console.log(localStorage.getItem('roles'));
      // if (localStorage.getItem('roles') === 'user') {
      //   this.isDelete = false
      // } else {
      //   this.isDelete = true
      // }
    },
    // 新增
    submitForm(form) {
      createRecord(form).then(res => {
        this.dialogVisible = false
        this.getData()
      })
    },
    // 删除记录
    onDelete(row) {
      deleteRecord(row.id).then(res => {
        this.getData()
      })
    },
    // 更新
    onUpdate(form1) {
      form1.id = this.scopeRow.id
      updateRecord(form1).then(res => {
        console.log(res.data.data);
        this.dialogVisible1 = false
        this.getData()
      })
    },
    onUpdateDia(row) {
      this.scopeRow = row
      this.dialogVisible1 = true
    }
    // 搜索
    // onSearch(data) {
    //   // getByName(data).then(res => {
    //   //   this.total = 1
    //   //   this.tableData = res.data.data
    //   // })
    // }
  }
}
</script>
<script setup>
import { reactive } from 'vue'
const form = reactive({
  uid: store.state.user.userId,
  userName: store.state.user.fullName,
  manufacturers: '',
  types: '',
  finish: '',
  createTime: ''
})
const form1 = reactive({
  id: '',
  uid: store.state.user.userId,
  userName: store.state.user.fullName,
  manufacturers: '',
  types: '',
  finish: '',
  createTime: ''
})
</script>