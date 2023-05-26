<template>
  <div class="h-60px">
    <el-button type="primary" @click="dialogVisible = true">上报行程</el-button>
  </div>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border stripe class="w-[99%]">
    <!-- <el-table-column prop="userId" label="id" width="80" /> -->
    <el-table-column type="index" label="编号" width="80" />
    <el-table-column prop="tripName" label="姓名" width="100" />
    <el-table-column prop="origin" label="起始地" width="280" />
    <el-table-column prop="end" label="结束地" width="280" />
    <el-table-column prop="startTime" label="开始时间" width="220" />
    <el-table-column prop="endTime" label="结束时间" width="220" />
    <el-table-column prop="transportation" label="交通工具" width="180" />
  </el-table>
  <!-- 分页 -->
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />

  <!-- 弹窗新增 -->
  <el-dialog v-model="dialogVisible" title="行程上报" width="35%">
    <el-form :model="form" label-width="120px" class="pl-6">
      <el-form-item label="起始地">
        <el-input v-model="form.origin" class="w-270px" />
      </el-form-item>
      <el-form-item label="结束地">
        <el-input v-model="form.end" class="w-270px" />
      </el-form-item>
      <el-form-item label="计划" class="mb-0">
        <div class="block">
          <el-date-picker v-model="form.startTime" @change="handleDateChange" type="date" placeholder="请选择日期" />
        </div>至
        <div class="block">
          <el-date-picker v-model="form.endTime" @change="handleDateChange2" type="date" placeholder="请选择日期" />
        </div>
      </el-form-item>
      <el-form-item label="交通工具">
        <el-input v-model="form.transportation" class="w-270px" />
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
import { getListTripById } from '@/api/get';
import { createTrip } from '@/api/post';
import { deleteTrip } from '@/api/delete';
import store from '../../store';

export default {
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 13,
      total: 100,
      background: true,
      user: store.state.user,
      dialogVisible: false,
      form: {
        uid: store.state.user.userId,
        tripName: store.state.user.fullName,
        origin: '',
        end: '',
        startTime: '',
        endTime: '',
        transportation: ''
      }
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getListTripById(this.user.userId).then((res) => {
        this.total = res.data.data.length
        this.tableData = res.data.data
      })
    },
    // 分页
    handleCurrentChange(value) {
      this.currentPage = value
    },
    handleSizeChange(value) {
      this.pageSize = value;
      this.currentPage = 1
    },
    // 上报
    submitForm(form) {
      createTrip(form).then(res => {
        this.dialogVisible = false
        this.getData()
      })
    },
    handleDateChange2(date) {
      // 将选择的日期转换为所需的格式
      const formattedDate = date.toISOString().split('T')[0];
      this.form.endTime = formattedDate;
    },
    handleDateChange(date) {
      // 将选择的日期转换为所需的格式
      const formattedDate = date.toISOString().split('T')[0];
      this.form.startTime = formattedDate;
    },
  }

}
</script>