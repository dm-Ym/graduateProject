<template>
  <p>首页</p>
  <button @click="unSubmin">anniu</button>
  <div class="w-1600px">
    <div class="h-350px flex">
      <div class="w-[33%] h-[100%] mr-2 bg-light-200">
        <div class="my-12 ml-10">
          <h1 class="text-black text-32px"> {{ user.fullName }} </h1>
          <h1 class="text-gray-400 text-18px">
            {{ user.roles == "guest" ? '游客登录' : user.roles == 'user' ? '普通用户' : '管理员' }}
          </h1>
        </div>
        <hr class="border-t-black my-4" />
        <p>{{ user }}</p>
      </div>
      <div class="w-[33%] h-[100%] mr-2 bg-[#FC8675]">统计:接种剂数</div>
      <div class="w-[33%] h-[100%] bg-[#4ACACB]">1</div>
    </div>
    <div class="h-350px mt-3 flex">
      <div class="w-[33%] h-[100%]  mr-2 bg-[#4ACACB]">1</div>
      <div class="w-[33%] h-[100%]  mr-2 bg-[#5AB6DF]">1</div>
      <div class="w-[33%] h-[100%] bg-[#FC8675]">1</div>
    </div>
    <div class="h-152px mt-3">
      <div class="">国内疫情</div>
      <div class="mb-2 text-gray-400 border-b border-b-gray-400">数据更新至:</div>
      <div class="flex">
        <div class="inline-block h-[100%]  ml-35 items-center justify-center flex flex-col">
          <div>
            <p>累计确诊</p>
            <p>{{ num }}</p>
            <p>较昨日:</p>
          </div>
        </div>
        <div class="inline-block h-[100%] ml-35 items-center justify-center flex flex-col ">
          <p>现有确诊</p>
          <p></p>
          <p>较昨日:</p>
        </div>
        <div class="inline-block h-[100%] ml-35 items-center justify-center flex flex-col ">
          <p>现有无症状</p>
          <p></p>
          <p>较昨日:</p>
        </div>
        <div class="inline-block h-[100%] ml-35 items-center justify-center flex flex-col ">
          <p>累计治愈</p>
          <p></p>
          <p>较昨日:</p>
        </div>
        <div class="inline-block h-[100%] ml-35 items-center justify-center flex flex-col ">
          <p>累计死亡</p>
          <p></p>
          <p>较昨日:</p>
        </div>
        <div class="inline-block h-[100%] ml-35 items-center justify-center flex flex-col ">
          <p>累计境外</p>
          <p></p>
          <p>较昨日:</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from '../store';
export default {
  data() {
    return {
      user: '',
    }
  },
  created() {
    this.user = store.state.user
    console.log(1);
    // console.log(this.user, '=================>');
  },
  mounted() {
    // this.user = this.$store.state.user
    console.log(2, this.user);
  },
  methods: {
    unSubmin() {
      console.log(store.state.user);
      // this.$store.state.user = 'guest'
      console.log(this.$store.state.user);
      // this.user = store.state.user
      // console.log(this.user);
    }
  }
}
</script>

<style>
body {
  background-color: #F0F0F0;
}
</style>