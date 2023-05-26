<template>
  <el-table :data="tableData" border stripe class="w-[99%]">
    <el-table-column type="index" label="编号" width="70" />
    <el-table-column prop="hospital" label="医院" width="280" />
    <el-table-column prop="vaccine" label="疫苗名称" sortable width="180" />
    <el-table-column prop="boTime" label="日期时间" width="220" />
    <el-table-column prop="perNum" label="当前预约人数" width="150" />
    <el-table-column label="操作" width="180">
      <template v-slot="scope">
        <!-- <el-button type="primary" @click="submitForm(scope.row)">预约</el-button> -->
        <el-button type="primary" @click="subForm(scope.row)">预约</el-button>
        <el-button v-if="isDelete" type="danger" @click="onDeleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="dialogVisible1" title="提示" width="55%" size="large">
    <div class="text-22px mb-4">{{ '是否确认预约 ? ' }}</div>
    <div class="text-22px mb-4">{{ '确认后无法更改。' }}</div>
    <div class="text-22px">{{ '请检查是否误触。' }}</div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitForm">确认</el-button>
        <el-button @click="dialogVisible1 = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { getListPer, getListBooking } from '../../api/get';
import { updateBooking } from '../../api/put.js';
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
      table2: ''
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
    submitForm() {
      updateBooking({ pid: this.iid, uid: this.user.userId }).then(res => {
        this.dialogVisible1 = false
        // console.log(res.data.message);
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
  },
  mounted() {
    // 检查LocalStorage中是否存在删除状态
    if (localStorage.getItem('buttonDeleted')) {
      this.isShow = false;
    }
  }
}
</script>
