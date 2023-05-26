<template>
  <div class="h-50px">
    <el-button type="primary" @click="dialogVisible = true">新增意见</el-button>
  </div>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    :default-sort="{ prop: 'coTime', order: 'descending' }" style="width: 100%">
    <el-table-column type="index" label="序号" width="100" />
    <el-table-column prop="adTime" sortable label="时间" width="200" />
    <el-table-column text prop="adContent" label="意见详情" width="850" />
    <el-table-column text prop="status" label="状态" width="100" :formatter="formatter" />
    <el-table-column label="操作" width="300">
      <template v-slot="scope">
        <el-button v-if="isDelete" class="bg-green-300" @click="onUpdateAdvice(scope.row)">采纳</el-button>
        <el-button v-if="isDelete" class="bg-green-300" @click="onQuashAdvice(scope.row)">撤销</el-button>
        <el-button v-if="isDelete" class="bg-green-300" @click="onDeleteAnno(scope.row)">删除</el-button>
      </template>
    </el-table-column>
    <!-- <el-table-column prop="content" label="Address" :formatter="formatter" /> -->
  </el-table>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[11, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />

  <el-dialog v-model="dialogVisible" title="意见" width="55%" :before-close="handleClose">
    <el-form :model="form" label-width="80px">
      <el-form-item label="当前时间">
        <div class="w-850px bg-gray-100 pl-40px border-2">{{ currentTime }}</div>
      </el-form-item>
      <el-form-item label="建议内容">
        <el-input v-model="form.adContent" type="textarea" :autosize="{ minRows: 10, maxRows: 12 }"
          class="w-850px h-400px text-22px" />
      </el-form-item>
    </el-form>
    <!-- <span>{{ content }}</span> -->
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onCreateAdvice" :disabled="isDisabled">添加</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { getListAdvice } from '../../api/get.js';
import { deleteAdviceAdmin } from '../../api/delete.js';
import { createAdvice } from '../../api/post.js';
import { updateAdviceStatus, updateStatus } from '../../api/put.js';
import store from '../../store';
export default {
  data() {
    return {
      tableData: [],
      uid: store.state.user.userId,
      dialogVisible: false,
      currentTime: '',
      form: {
        adContent: ''
      },
      isDelete: '',
      currentPage: 1,
      pageSize: 11,
      total: 100,
      background: true,
    }
  },
  computed: {
    isDisabled() {
      return !this.form.adContent
    },
  },
  created() {
    setInterval(() => {
      this.currentTime = new Date().toLocaleString();
    }, 1000);
    this.getData()
  },
  methods: {
    getData() {
      getListAdvice().then(res => {
        // console.log(res.data);
        this.tableData = res.data.data
        this.total = res.data.data.length
      })
      console.log(localStorage.getItem('roles'));
      if (localStorage.getItem('roles') === 'user') {
        this.isDelete = false
      } else {
        this.isDelete = true
      }
    },
    onDeleteAnno(row) {
      deleteAdviceAdmin(row.id).then(res => {
        // console.log(res.data);
        this.getData()
      })
    },
    formatter(row, column, index) {
      if (row.status === 1) {
        return "已采纳";
      }
      return '';
    },
    onCreateAdvice() {
      this.dialogVisible = true
      createAdvice({ uid: this.uid, adContent: this.form.adContent }).then(res => {
        console.log(res.data);
        this.form.adContent = ''
      })
      this.dialogVisible = false
      this.getData()
    },
    onUpdateAdvice(row) {
      updateAdviceStatus(row.id).then(res => {
        this.getData()
      })
    },
    onQuashAdvice(row) {
      updateStatus(row.id).then(res => {
        this.getData()
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

<script lang="ts" setup>
import { ElMessageBox } from 'element-plus'
const handleClose = (done: () => void) => {
  ElMessageBox.confirm('你确定要关闭窗口吗?关闭后内容不会保存.请确实是否误触')
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}
</script>