<template>
  <el-table :data="tableData" :default-sort="{ prop: 'coTime', order: 'descending' }" style="width: 100%">
    <el-table-column prop="coTime" sortable label="投诉时间" width="200" />
    <el-table-column text prop="coContent" label="投诉内容" width="850" />
    <el-table-column label="操作" width="250">
      <template v-slot="scope">
        <el-button class="bg-green-300" @click="onDeleteAnno(scope.row)">删除</el-button>
      </template>
    </el-table-column>
    <!-- <el-table-column prop="content" label="Address" :formatter="formatter" /> -->
  </el-table>
</template>

<script>
import { getOne } from '@/api/get.js';
import { deleteCompOne } from '@/api/delete.js';
import store from '../../store';
export default {
  data() {
    return {
      tableData: [],
      uid: store.state.user.userId
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getOne(this.uid).then(res => {
        // console.log(res.data);
        this.tableData = res.data.data
      })
    },
    onDeleteAnno(row) {
      deleteCompOne({ uid: this.uid, id: row.id }).then(res => {
        console.log(res.data);
        this.getData()
      })
    }
  }
}
</script>