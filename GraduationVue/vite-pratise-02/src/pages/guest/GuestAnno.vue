
<template>
  <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
    :default-sort="{ prop: 'index' }" style="width: 100%" @row-click="handleRowClick">
    <el-table-column type="index" sortable label="序号" width="100" />
    <el-table-column prop="createTime" sortable label="创建时间" width="180" />
    <el-table-column prop="createName" label="创建人" width="150" />
    <el-table-column text prop="title" label="标题" width="850" />
    <el-table-column label="操作" width="250">
      <template v-slot="scope">
        <el-button @click="dialogVisible = true" class="bg-green-300">查看详情</el-button>
      </template>
    </el-table-column>
    <!-- <el-table-column prop="content" label="Address" :formatter="formatter" /> -->
  </el-table>

  <!-- 弹窗查看公告详情-->
  <el-dialog v-model="dialogVisible" :title="AnnoTitle" width="55%" :size="formSize">
    <p>{{ content }}</p>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false" v-if="isShow">确认</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>
  <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[12, 20, 40, 70]" :background="background"
    layout="total, sizes, prev, pager, next, jumper" :total="total" :hide-on-single-page="true"
    @size-change="handleSizeChange" @current-change="handleCurrentChange" />
</template>
<script lang="ts" setup>
/**
 * 公告详情采用弹窗式,点击公告名称或者擦看详情,便可
 */
import { reactive, ref } from 'vue'
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

const formSize = ref('large')
const background = ref(true)
const form = reactive({
  name: '',
  content: '',
})
</script>

<script lang="ts">
import { getAnno } from '../../api/get.js';
export default {
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      currentIndex: null,
      content: '',
      currentTime: '',
      isShow: true,
      AnnoTitle: '',
      currentPage: 1,
      pageSize: 12,
      total: 100
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
        // console.log(res.data.data);
        this.tableData = res.data.data
        this.total = res.data.data.length
      })
    },
    onDeleteAnno(row) {
      // deleteAnno()
      console.log(row.id);
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