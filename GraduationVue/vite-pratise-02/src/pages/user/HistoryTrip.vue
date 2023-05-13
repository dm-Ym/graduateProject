<template>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border stripe class="w-[99%]">
    <!-- <el-table-column prop="userId" label="id" width="80" /> -->
    <el-table-column type="index" label="编号" width="80" />
    <el-table-column prop="tripName" label="姓名" width="100" />
    <el-table-column prop="origin" label="起始地" width="280" />
    <el-table-column prop="end" label="结束地" width="280" />
    <el-table-column prop="startTime" label="开始时间" width="200" />
    <el-table-column prop="endTime" label="结束时间" width="220" />
    <el-table-column prop="transportation" label="交通工具" width="180" />
  </el-table>
  <!-- 分页 -->
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />
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
      user: store.state.user
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getListTripById(this.user.userId).then((res) => {
        this.total = res.data.data.length
        // console.log(res);
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
    // 预约
    submitForm(form) {
      // createUser(form).then(res => {
      //   this.getData()
      //   this.dialogVisible = false
      // })
    },
    // 删除医院信息
    onDeleteUser(row) {
      // deleteUser(row.userId).then(res => {
      //   this.getData()
      // })
    },
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