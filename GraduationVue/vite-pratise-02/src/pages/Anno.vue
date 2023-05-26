<template>
  <div class="h-90px" v-if="isNew">
    <el-button type="primary" @click="addAnno">新增公告</el-button>
  </div>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    :default-sort="{ prop: 'index' }" style="width: 100%" @row-click="handleRowClick">
    <el-table-column type="index" sortable label="序号" width="100" />
    <el-table-column prop="createTime" sortable label="创建时间" width="180" />
    <el-table-column prop="createName" label="创建人" width="150" />
    <el-table-column text prop="title" label="标题" width="850" />
    <el-table-column label="操作" width="250">
      <template v-slot="scope">
        <el-button @click="dialogVisible = true" class="bg-green-300">查看详情</el-button>
        <el-button v-if="isDelete" class="bg-red-300" @click="onDeleteAnno(scope.row)">
          删除公告</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!-- 弹窗查看公告详情-->
  <el-dialog v-model="dialogVisible" :title="AnnoTitle" width="55%" :size="formSize" :before-close="handleClose">
    <p v-if="isShow">{{ content }}</p>
    <el-form v-else :model="form" label-width="80px">
      <el-form-item label="当前时间">
        <div class="w-850px bg-gray-100 pl-40px border-2">{{ currentTime }}</div>
      </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="form.title" class="w-850px text-22px" />
      </el-form-item>
      <el-form-item label="公告内容">
        <el-input v-model="form.content" type="textarea" :autosize="{ minRows: 10, maxRows: 12 }"
          class="w-850px h-400px text-22px" />
      </el-form-item>
    </el-form>
    <!-- <span>{{ content }}</span> -->
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false" v-if="isShow">确认</el-button>
        <el-button type="primary" @click="onCreateAnno(form)" v-else>添加</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[12, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ElMessageBox } from 'element-plus'

const role = store.state.roles

const handleClose = (done: () => void) => {
  if (role === 'admin') {
    ElMessageBox.confirm('你确定要关闭窗口吗?关闭后内容不会保存.请确实是否误触')
      .then(() => {
        done()
      })
  } else {
    done()
  }
}

const formSize = ref('large')
const background = ref(true)
const form = reactive({
  createName: store.state.user.fullName,
  title: '',
  content: '',
})
</script>

<script lang="ts">
import { getAnno } from '../api/get.js';
import { deleteAnno } from '../api/delete.js';
import { createAnno } from '../api/post';
import store from '../store';
export default {
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      currentIndex: null,
      content: '',
      currentTime: '',
      isShow: true,  // 显示确认/提交按钮
      AnnoTitle: '',
      currentPage: 1,
      pageSize: 12,
      total: 100,
      roles: 'guest',
      isNew: 'false',
      isDelete: 'false',
      role: store.state.roles
    }
  },
  created() {
    setInterval(() => {
      this.currentTime = new Date().toLocaleString();
    }, 1000);
    this.getAnnoData()
  },
  methods: {
    getAnnoData() {
      getAnno().then(res => {
        this.tableData = res.data.data
        this.total = res.data.data.length
      })
      if (localStorage.getItem("roles")) {
        this.roles = localStorage.getItem('roles')
      } else {
        this.roles = 'guest'
      }
      if (this.roles === 'guest') {
        this.isNew = false
        this.isDelete = false
      } else if (this.roles == 'user') {
        this.isNew = false
        this.isDelete = false
      } else {
        this.isNew = true
        this.isDelete = true
      }
    },
    onDeleteAnno(row) {
      // deleteAnno()
      console.log(row.id);
    },
    onCreateAnno(form) {
      createAnno(form).then(res => {
        this.dialogVisible = false
        this.getData()
      })
    },
    handleRowClick(row) {
      this.currentIndex = this.tableData.indexOf(row)
      this.content = this.tableData[this.currentIndex].content
      this.isShow = true
      this.AnnoTitle = '公告详情'
    },
    addAnno() {
      this.dialogVisible = true
      this.isShow = false
      this.AnnoTitle = '新增公告'
    },
    handleCurrentChange(value) {
      this.currentPage = value
    },
    handleSizeChange(value) {
      this.pageSize = value;
      this.currentPage = 1
    }
  }
}
</script>