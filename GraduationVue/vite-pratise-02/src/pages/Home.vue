<template>
  <div class="w-1600px">
    <div class="h-300px flex">
      <div class="w-[33%] h-[100%] mr-2 bg-[#FC8675] relative">
        <div class="text-30px absolute top-8 left-14 text-light-50">系统首页</div>
        <img src="../images/主页.png" class=" absolute top-16 left-6" style="width: 170px;" />
        <div class="custom-color absolute text-black text-32px top-22 right-40"> {{ user.fullName }} </div>
        <div class="text-gray-300 absolute text-18px top-30 right-49">
          {{ user.roles == "guest" ? '游客登录' : user.roles == 'user' ? '普通用户' : '管理员' }}
        </div>
      </div>
      <div class="w-[33%] h-[100%]  mr-2 bg-[#5AB6DF]">
        <img src="../images/疫情1.png" class="h-[100%] w-[100%]" />
      </div>
      <div class="w-[33%] h-[100%] bg-[#4ACACB] relative">
        <img src="../images/治愈统计.png" class=" absolute top-10 left-8" style="width: 170px;" />
        <div class="text-22px absolute top-30 left-62 text-light-50">治愈率：{{ four }}%</div>
      </div>
    </div>

    <div class="h-300px mt-3 flex">
      <div class="w-[33%] h-[100%]  mr-2 bg-[#4ACACB] relative">
        <el-table :data="tableData.slice(0, 7)" :default-sort="{ prop: 'index' }"
          style="width: 100% background-color: #4ACACB;">
          <el-table-column text prop="title" label="最新公告" width="500" show-overflow-tooltip />
        </el-table>
        <router-link to="/anno" class="aY">查看详情>></router-link>
      </div>
      <div class="w-[33%] h-[100%] mr-2 bg-[#5AB6DF] relative">
        <div class="text-30px absolute top-8 left-14 text-light-50">接种统计</div>
        <img src="../images/医疗箱.png" class=" absolute top-18 left-10" style="width: 170px;" />
        <div class="text-22px absolute top-16 right-10 text-light-50">第一针接种率：{{ first }}%</div>
        <div class="text-22px absolute top-26 right-10 text-light-50">第二针接种率：{{ second }}%</div>
        <div class="text-22px absolute top-36 right-10 text-light-50">第三针接种率：{{ third }}%</div>
        <div class="text-22px absolute top-46 right-10 text-light-50">总接种率：{{ totalNum }}%</div>
      </div>
      <div class="w-[33%] h-[100%] bg-[#6A8ABE]">
        <img src="../images/疫情2.png" class="h-[100%] w-[100%]" />
      </div>
    </div>
    <div class="h-112px mt-7">
      <div class="text-22px">国内疫情</div>
      <div class="mb-2 text-gray-400 border-b border-b-gray-400">数据更新至: 2022-12-28 00:00</div>
      <div class="flex bg-light-100 w-[80%] ml-25">
        <div class="inline-block h-[100%]  ml-25 items-center justify-center flex flex-col">
          <div>
            <p class="pl-3 text-22px">累计确诊</p>
            <p class="text-28px my-3 text-[#A31D13]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#BB391C]">+46184</span></p>
          </div>
        </div>
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">现有确诊</p>
            <p class="text-28px my-3 text-[#E44A3D]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#E44A3D]">+24325</span></p>
          </div>
        </div>
        <!-- <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">现有无症状</p>
            <p class="text-28px my-3 text-[#A31D13]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#BB391C]">+46184</span></p>
          </div>
        </div> -->
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">累计治愈</p>
            <p class="text-28px my-3 text-[#34AA70]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#34AA70]">+5552</span></p>
          </div>
        </div>
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">累计死亡</p>
            <p class="text-28px my-3 text-[#000]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#000]">+78</span></p>
          </div>
        </div>
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">境外输入</p>
            <p class="text-28px my-3 text-[#FFA352]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#FFA352]">+116</span></p>
          </div>
        </div>
      </div>
    </div>
    <div class="h-152px mt-3">
      <div class="mt-12 text-22px">全球疫情</div>
      <div class="mb-2 text-gray-400 border-b border-b-gray-400">数据更新至: 2022-12-28 00:00</div>
      <div class="flex bg-light-100 w-[80%] ml-25">
        <div class="inline-block h-[100%]  ml-25 items-center justify-center flex flex-col">
          <div>
            <p class="pl-3 text-22px">累计确诊</p>
            <p class="text-28px my-3 text-[#A31D13]">624398550</p>
            <p class="text-gray-400">较昨日:<span class="text-[#BB391C]">+236809</span></p>
          </div>
        </div>
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">现有确诊</p>
            <p class="text-28px my-3 text-[#E44A3D]">40252572</p>
            <p class="text-gray-400">较昨日:<span class="text-[#E44A3D]">+34849</span></p>
          </div>
        </div>
        <!-- <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">现有无症状</p>
            <p class="text-28px my-3 text-[#A31D13]">9506895</p>
            <p class="text-gray-400">较昨日:<span class="text-[#BB391C]">+46184</span></p>
          </div>
        </div> -->
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">累计治愈</p>
            <p class="text-28px my-3 text-[#34AA70]">577527551</p>
            <p class="text-gray-400">较昨日:<span class="text-[#34AA70]">+201530</span></p>
          </div>
        </div>
        <div class="inline-block h-[100%] ml-25 items-center justify-center flex flex-col ">
          <div>
            <p class="pl-3 text-22px">累计死亡</p>
            <p class="text-28px my-3 text-[#000]">6618427</p>
            <p class="text-gray-400">较昨日:<span class="text-[#000]">+430</span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getAnno } from '../api/get.js';
import { getListJz } from '@/api/get';
import { getAllHeal } from '@/api/get';
import store from '../store';
export default {
  data() {
    return {
      user: '',
      tableData: [],
      num: '',
      first: '',
      second: '',
      third: '',
      totalNum: 0,
      four: ''
    }
  },
  created() {
    this.user = store.state.user
    getAnno().then(res => {
      this.tableData = res.data.data
    })
    getListJz().then(res => {
      res.data.data.forEach(ele1 => {
        if (ele1.finish == '一') {
          this.first = (ele1.numfinish / store.state.total * 100).toFixed(2)
        } else if (ele1.finish == '二') {
          this.second = (ele1.numfinish / store.state.total * 100).toFixed(2)
        } else if (ele1.finish == '三') {
          this.third = (ele1.numfinish / store.state.total * 100).toFixed(2)
        }
        this.totalNum += ele1.numfinish
      })
      this.totalNum = (this.totalNum / store.state.total * 100).toFixed(2)
    })
    getAllHeal().then((res) => {
      this.four = (res.data.data.length / store.state.total * 100).toFixed(2)
    })
  },
  mounted() {
  },
  methods: {

  }
}
</script>

<style>
body {
  background-color: #F0F0F0;
}

.aY {
  float: right;
  position: absolute;
  top: 2px;
  right: 12px;
  z-index: 1;
  text-decoration: underline;
  font-size: 14px;
  color: blue;
}

.custom-color {
  color: white;
  /* 设置自定义的字体颜色 */
}
</style>