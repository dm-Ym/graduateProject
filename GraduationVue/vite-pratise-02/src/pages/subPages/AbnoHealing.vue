<template>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
    <el-table-column type="index" label="序号" sortable width="100" />
    <el-table-column prop="abnoName" label="姓名" sortable width="230" />
    <el-table-column prop="healTime" label="康复日期" sortable width="200" />
    <!-- <el-table-column label="操作" width="180">
      <el-button type="primary">详情</el-button>
      <el-button type="danger">删除</el-button>
    </el-table-column> -->
  </el-table>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[18, 30, 50, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />
</template>

<script>
import { getAllHeal } from '@/api/get';

export default {
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 18,
      total: 100,
      background: true,
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getAllHeal().then((res) => {
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
  }
}

</script>
