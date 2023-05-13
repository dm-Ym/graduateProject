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
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[13, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" hide-on-single-page="false" />

  <!-- 弹窗新增用户 -->
  <el-dialog v-model="dialogVisible" title="新增用户" width="40%" :size="formSize">
    <el-form :model="form" label-width="140px">
      <el-form-item label="姓名">
        <el-input v-model="form.fullName" class="w-500px" />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="form.gender" placeholder="请选择">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="form.age" class="w-500px" placeholder="请输入0-120的整数" />
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="form.phone" class="w-500px" placeholder="请输入11位电话号码" />
      </el-form-item>
      <el-form-item label="密码(默认123456)" prop="pwd" :label-width="140">
        <el-input type="password" v-model="form.pwd" class="w-500px" />
      </el-form-item>
      <el-form-item label="社区" prop="neighborhood">
        <el-select v-model="form.neighborhood">
          <el-option label="倾月社区" value="倾月社区" />
          <el-option label="汇统社区" value="汇统社区" />
          <el-option label="蚌埠社区" value="蚌埠社区" />
          <el-option label="宏发社区" value="宏发社区" />
        </el-select>
      </el-form-item>
      <el-form-item label="小区" prop="community">
        <el-select v-model="form.community">
          <el-option label="盛世豪庭" value="盛世豪庭" />
          <el-option label="嘉林景苑" value="嘉林景苑" />
          <el-option label="紫金苑" value="紫金苑" />
          <el-option label="锦绣雷庭" value="锦绣雷庭" />
        </el-select>
      </el-form-item>
      <el-form-item label="单元楼" prop="residential">
        <el-input v-model="form.residential" class="w-500px" placeholder="请输入居住的单元楼" />
      </el-form-item>
      <el-form-item label="门牌号" prop="doorplate">
        <el-input v-model="form.doorplate" class="w-500px" placeholder="请输入门牌号" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="submitForm(form)"> 添加 </el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
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
    // 新增用户
    submitForm(form) {
      createUser(form).then(res => {
        this.getData()
        this.dialogVisible = false
      })
    },
    // 删除用户
    onDeleteUser(row) {
      deleteUser(row.userId).then(res => {
        this.getData()
      })
    },
    // 搜索
    onSearch(data) {
      getByName(data).then(res => {
        this.total = 1
        this.tableData = res.data.data
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