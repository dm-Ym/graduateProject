<template>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border stripe class="w-[99%]">
    <el-table-column type="index" label="编号" width="80" />
    <el-table-column prop="userName" label="姓名" width="120" />
    <el-table-column prop="syringes" label="第几针" sortable width="120" />
    <el-table-column prop="vaccine" label="哪款疫苗" width="220" />
    <el-table-column prop="createTime" label="接种时间" width="220" />
    <el-table-column label="操作" width="180">
      <template v-slot="scope">
        <el-button type="danger" @click="onDeleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />
</template>

<script>
import { getRecord, getListRecord } from '@/api/get';
// import { createUser } from '@/api/post.js';
// import { deleteUser } from '@/api/delete.js';

export default {
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 13,
      total: 100,
      background: true
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getListRecord().then((res) => {
        console.log(res.data);
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
    // 新增记录
    // submitForm(form) {
    //   createUser(form).then(res => {
    //     this.getData()
    //   })
    // },
    // // 删除记录
    // onDeleteUser(row) {
    //   deleteUser(row.userId).then(res => {
    //     this.getData()
    //   })
    // },
    // 搜索
    onSearch(data) {
      //   getByName(data).then(res => {
      //     console.log(res.data.data);
      //     this.total = 1
      //     this.tableData = res.data.data
      //   })
    }
  },
}
</script>