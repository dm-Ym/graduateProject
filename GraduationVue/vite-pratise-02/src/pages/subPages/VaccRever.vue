<template>
  <div class="h-90px">
    <el-button type="primary" class="mr-10" @click="dialogVisible1 = true">增加医院</el-button>
  </div>
  <el-table :data="tableData" border stripe class="w-[99%]">
    <el-table-column type="index" label="编号" width="70" />
    <el-table-column prop="hospital" label="医院" width="180" />
    <el-table-column prop="vaccine" label="疫苗名称" sortable width="180" />
    <el-table-column prop="boTime" label="日期时间" width="220" />
    <el-table-column prop="perNum" label="当前预约人数" width="150" />
    <el-table-column label="操作" width="180">
      <template v-slot="scope">
        <!-- <el-button type="primary" @click="submitForm(scope.row)">预约</el-button> -->
        <!-- <el-button type="primary" @click="subForm(scope.row)">预约</el-button> -->
        <el-button type="danger" @click="onDeleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="dialogVisible1" title="提示" width="55%" size="large">
    <el-form :model="form" label-width="140px">
      <el-form-item label="医院">
        <el-input v-model="form.hospital" class="w-500px" />
      </el-form-item>
      <el-form-item label="疫苗">
        <el-input v-model="form.vaccine" class="w-500px" />
      </el-form-item>
      <!-- <el-form-item label="时间" prop="age">
        <el-input v-model="form.boTime" class="w-500px" placeholder="请输入0-120的整数" />
      </el-form-item> -->
      <el-form-item label="计划" class="mb-0">
        <div class="demo-date-picker">
          <div class="block">
            <el-date-picker v-model="form.boTime" @change="handleDateChange" type="date" placeholder="请选择日期" />
          </div>
        </div>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitForm(form)">确认</el-button>
        <el-button @click="dialogVisible1 = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { getListPer, getListBooking } from '../../api/get';
// import { updateBooking } from '../../api/put.js';
import { createBook } from '../../api/post.js';
import store from '../../store';
import { ElNotification } from 'element-plus'

export default {
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      dialogVisible1: false,
      isDelete: '',
      formSize: 'large',
      iid: '',  // 记录所在行对应id
      isShow: true,  // 预约成功后删除'预约'按钮,防止重复预约
      user: store.state.user,
      // 记录两次请求的结果
      table1: '',
      table2: '',
      form: {
        hospital: '',
        vaccine: '',
        boTime: ''
      }
    }
  },
  created() {
    this.getData()

  },
  methods: {
    getData() {
      getListBooking().then((res) => {
        this.table1 = res.data.data
        getListPer().then(res => {
          this.table2 = res.data.data
          // console.log(this.table1);
          // console.log(this.table2);

          this.table1.forEach(ele1 => {
            this.table2.forEach(ele2 => {
              if (ele1.id === ele2.pid) {
                ele1.perNum = ele2.num
              }
            })
            if (ele1.perNum === null) {
              ele1.perNum = 0
            }
          })
          this.tableData = this.table1
        })
      })
    },
    // 打开弹窗
    subForm(row) {
      this.iid = row.id
      this.dialogVisible1 = true
    },
    // 预约
    submitForm(form) {
      createBook(form).then(res => {
        this.dialogVisible1 = false
        console.log(res.data.data);
        ElNotification({
          message: res.data.message,
          type: 'error',
          duration: 3000,
        })
        this.getData()
      })
    },
    // 删除医院信息
    onDeleteUser(row) {
      // deleteUser(row.userId).then(res => {
      //   this.getData()
      // })
    },
    handleDateChange(date) {
      // 将选择的日期转换为所需的格式
      const formattedDate = date.toISOString().split('T')[0];
      this.form.boTime = formattedDate;
    },
  },
  mounted() {
    // 检查LocalStorage中是否存在删除状态
    if (localStorage.getItem('buttonDeleted')) {
      this.isShow = false;
    }
  }
}
</script>
