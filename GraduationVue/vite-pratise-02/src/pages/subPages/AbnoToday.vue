<template>
  <!-- <div class="h-60px">
    <el-button type="primary">体温异常</el-button>
    <el-button type="primary">高风险地区返回</el-button>
  </div> -->
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
    <el-table-column type="index" label="序号" sortable width="100" />
    <el-table-column prop="abnoName" label="用户名" sortable width="180" />
    <el-table-column prop="temperature" label="体温" sortable width="120" />
    <el-table-column prop="risk" label="是否高风险地区返回" sortable width="220" />
    <el-table-column prop="location" label="地址详情" sortable width="230" />
    <el-table-column prop="addTime" label="更新日期" sortable width="200" />
    <el-table-column label="操作" width="180">
      <!-- <el-button type="primary">详情</el-button> -->
      <el-button type="danger">删除</el-button>
    </el-table-column>
  </el-table>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />
</template>

<script>
import { getAllToday } from '@/api/get';
export default {
  data() {
    return {
      tableData: [],
      // search: '',
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
      getAllToday().then((res) => {
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
