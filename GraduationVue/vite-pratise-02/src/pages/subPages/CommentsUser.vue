<template>
  <div class="h-50px">
    <el-row class="inline-block">
      <input v-model="search" placeholder="根据姓名或者电话搜索" class="w=200px" />
      <el-button circle class="ml-2" @click="onSearch(search)"><el-icon>
          <Search />
        </el-icon></el-button>
    </el-row>
    <el-button type="primary" class="float-right mr-10" @click="dialogVisible = true">新增留言</el-button>
  </div>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border stripe class="w-[99%]">
    <el-table-column type="index" label="序号" width="80" />
    <el-table-column prop="comName" label="姓名" width="120" />
    <el-table-column prop="content" label="内容" width="500" />
    <el-table-column prop="createTime" label="创建时间" width="200" />
    <el-table-column label="操作" width="260">
      <template v-slot="scope">
        <el-button type="primary" @click="onUpdateStatus(scope.row)">转为已处理</el-button>
        <el-button type="danger" @click="onDeleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />

  <!-- 弹窗新增 -->
  <el-dialog v-model="dialogVisible" title="留言" width="55%" :before-close="handleClose">
    <el-form :model="form" label-width="80px">
      <el-form-item label="当前时间">
        <div class="w-850px bg-gray-100 pl-40px border-2">{{ currentTime }}</div>
      </el-form-item>
      <el-form-item label="留言内容">
        <el-input v-model="form.content" type="textarea" :autosize="{ minRows: 10, maxRows: 12 }"
          class="w-850px h-400px text-22px" />
      </el-form-item>
    </el-form>
    <!-- <span>{{ content }}</span> -->
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onCreateComm" :disabled="isDisabled">添加</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { getListComments, getCommByName } from '../../api/get';
import { createComm } from '../../api/post.js';
import { deleteComments } from '../../api/delete.js';
import { updateCommStatus } from '../../api/put';
import store from '../../store';

export default {
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 13,
      total: 100,
      dialogVisible: false,
      currentTime: '',
      user: store.state.user,
      background: true,
      form: {
        content: ''
      }
    }
  },
  computed: {
    isDisabled() {
      return !this.form.content
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
      getListComments().then((res) => {
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
    // 新增
    onCreateComm(form) {
      createComm('admin', {
        uid: this.user.userId, comName: this.user.fullName, content: this.form.content
      }).then(res => {
        this.getData()
        this.form.content = ''
        this.dialogVisible = false
      })
    },
    // 删除
    onDeleteUser(row) {
      deleteComments('admin', row.id).then(res => {
        this.getData()
      })
    },
    onUpdateStatus(row) {
      updateCommStatus({ id: row.id, status: 1 }).then(res => {
        console.log(res.data);
        this.getData()
      })
    },
    // 搜索
    onSearch(data) {
      getCommByName(data).then(res => {
        this.total = res.data.data.length
        this.tableData = res.data.data
        this.search = ''
      })
    }
  }
}
</script>
<script lang="ts" setup>
import { ElMessageBox, useId } from 'element-plus'
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