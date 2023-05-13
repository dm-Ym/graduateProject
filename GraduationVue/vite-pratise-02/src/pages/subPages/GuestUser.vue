<template>
  <div class="h-50px">
    <el-row class="inline-block">
      <input v-model="search" placeholder="根据姓名或者电话搜索" class="w=200px" />
      <el-button circle class="ml-2" @click="onSearch(search)"><el-icon>
          <Search />
        </el-icon></el-button>
    </el-row>
    <el-button type="primary" class="float-right mr-10" @click="dialogVisible = true">新增用户</el-button>
  </div>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" border stripe class="w-[99%]">
    <!-- <el-table-column prop="userId" label="id" width="80" /> -->
    <el-table-column type="index" label="编号" width="80" />
    <el-table-column prop="fullName" label="姓名" width="100" />
    <el-table-column prop="age" label="年龄" sortable width="100" />
    <el-table-column prop="gender" label="性别" width="80" />
    <el-table-column prop="phone" label="电话" width="160" />
    <el-table-column prop="neighborhood" label="社区" width="180" />
    <el-table-column prop="community" label="小区" width="180" />
    <el-table-column prop="residential" label="楼宇" width="180" />
    <el-table-column prop="doorplate" label="门牌号" width="150" />
    <el-table-column prop="health" label="健康与否" width="100" />
    <el-table-column label="操作" width="180">
      <template v-slot="scope">
        <el-button type="primary">详情</el-button>
        <el-button type="danger" @click="onDeleteUser(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :small="small"
    :background="background" layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />
</template>

<script>
import { getAll, getByName } from '@/api/get';
import { createUser } from '@/api/post.js';
import { deleteUser } from '@/api/delete.js';

export default {
  data() {
    return {
      tableData: [],
      search: '',
      currentPage: 1,
      pageSize: 13,
      total: 100,
      dialogVisible: false
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getAll().then((res) => {
        console.log(res.data);
        this.total = res.data.data.length
        this.tableData = res.data.data
      })
    },
    handleCurrentChange(value) {
      this.currentPage = value
    },
    handleSizeChange(value) {
      this.pageSize = value;
      this.currentPage = 1
    },
    submitForm(form) {
      createUser(form).then(res => {
        this.getData()
        this.dialogVisible = false
      })
    },
    onDeleteUser(row) {
      deleteUser(row.userId).then(res => {
        this.getData()
      })
    },
    onSearch(data) {
      getByName(data).then(res => {
        console.log(res.data.data);
        this.total = 1
        this.tableData = res.data.data
      })
    }
  },
  computed: {
    filteredData() {
      return this.tableData.filter(data => {
        return Object.values(data).some(val => {
          return String(val).toLowerCase().includes(this.search.toLowerCase())
        })
      })
    }
  }
}
</script>
<script setup>
import { reactive, ref } from 'vue'

const formSize = ref('large')
const background = ref(true)
const value = ref(true)

const form = reactive({
  fullName: '',
  gender: '',
  age: '',
  phone: '',
  pwd: '123456',
  neighborhood: '',
  community: '',
  residential: '',
  doorplate: '',
  roles: ''
})
</script>