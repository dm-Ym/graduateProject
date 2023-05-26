<template>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border stripe class="w-[99%]">
    <el-table-column type="index" label="编号" width="80" />
    <el-table-column prop="msgName" label="姓名" width="100" />
    <el-table-column prop="temperature" label="体温(摄氏度)" width="120" />
    <el-table-column prop="health" label="是否健康" width="100" />
    <el-table-column prop="suspected" label="抗原检测" width="160" />
    <el-table-column prop="symptom" label="症状" width="200" />
    <el-table-column prop="location" label="当前位置" width="200" />
    <el-table-column prop="risk" label="是否中高风险" width="100" />
    <el-table-column prop="remark" label="备注" width="280" />
    <el-table-column prop="destination" label="目的地" width="280" />
    <el-table-column prop="content" label="事由" width="200" />
    <el-table-column prop="start" label="开始时间" width="220" />
    <el-table-column prop="end" label="返回时间" width="180" />
  </el-table>
  <!-- 分页 -->
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />
</template>
<script>
import { getListMsg } from '@/api/get';

export default {
  data() {
    return {
      tableData: [],
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
      getListMsg().then((res) => {
        this.total = res.data.data.length
        // console.log(res.data.data);
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
  }

}
</script>