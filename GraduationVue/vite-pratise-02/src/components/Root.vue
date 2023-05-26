<template>
  <div>
    <!-- 顶部装饰栏 -->
    <div class="decorativeBar">
      <div class="inline-block float-left">
        <div class="inline-block ml-3 text-light-50 pt-[-2]" style="cursor:pointer" @click="toggleCollapse"> ||| </div>
        <span class="text-light-50 ml-2 text-30px leading-45px">社区疫情防控管理系统</span>
      </div>
      <el-dropdown class="float-right" v-if="token">
        <span class="el-dropdown-link leading-45px text-sm mr-8 text-cool-gray-50">
          {{ $store.state.user.fullName }}
          <el-icon class="el-icon--right leading-45px"> <arrow-down /> </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item class="text-sm">
              <button @click="toPer">个人中心</button>
            </el-dropdown-item>
            <el-dropdown-item class="text-sm">
              <button @click="logout">退出登录</button>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <div v-else>
        <button
          class="border-3 border-light-blue-200 bg-gray-200 w-60px h-45px float-right mr-18 leading-45px rounded-15px"
          @click="$router.push('/register')">注册 </button>
        <button
          class="border-3 border-light-blue-200 bg-gray-200 w-60px h-45px float-right mr-2 leading-45px rounded-15px"
          @click="$router.push('/login')">登录</button>
      </div>
    </div>
    <!-- <div class="headerY">
    <div class="w-1887px bg-[#B6B6B6] mx-auto text-center leading-35px">
    </div>
  </div> -->
    <div router="router">
      <!-- 侧边 -->
      <el-container>
        <el-aside class="asideY" :width="isCollapse ? '0%' : '13%'">
          <div class="bg-[#EEEEEE] text-light-50">
            <div class="text-center text-black"> 菜单</div>
          </div>
          <el-menu :router="true" :unique-opened="true" background-color="#EEEEEE" text-color="black"
            :collapse="isCollapse" :collapse-transition="false">
            <!--开启,然后配合index-->
            <template v-for="(first, index) in menuList">
              <!-- 有子路由的 -->
              <el-sub-menu :index="first.path" v-if="first.children && first.meta.visiable" :key="index">
                <template v-if="first.children">
                  <el-menu-item-group v-for="(second, sec_index) in first.children" :key="sec_index">
                    <el-menu-item :index="first.path + '/' + second.path" :key="index" :router="first.name"
                      class="text-18px">
                      {{ second.name }}
                    </el-menu-item>
                  </el-menu-item-group>
                </template>
                <!-- 用来显示有子路有的标签作为展开页面的标识 -->
                <template #title>
                  <span>{{ first.name }}</span>
                </template>
              </el-sub-menu>
              <!-- 没有子路由的作为页面跳转 -->
              <el-menu-item v-if="!first.hidden && first.meta.visiable" :index="first.path" :key="first.path"
                :router="first.path" class="text-18px">
                {{ first.name }}
              </el-menu-item>
            </template>
          </el-menu>
        </el-aside>
        <el-main class="mainY">
          <router-view></router-view>
        </el-main>
      </el-container>
    </div>
  </div>
</template>


<script>
import router from '../router/index.js';
import { useStore } from 'vuex';
// import store from '../store';
const store = useStore()
export default {
  data() {
    return {
      isCollapse: false,
      menuList: [],
      roles: 'guest',
      // user: store.state.user,
      token: false
    }
  },
  created() {
    this.menuList = router.options.routes[1].children
    if (localStorage.getItem('token')) {
      this.token = true
    } else {
      this.token = false
    }
    if (localStorage.getItem('token')) {
      this.roles = this.$store.state.roles
    }
    // console.log(this.menuList);
    this.menuList.forEach(element => {
      // console.log(element);
      element.meta.roles.forEach(item => {
        // console.log(item.indexOf(this.roles));
        // console.log(this.roles);
        if (item.indexOf(this.roles) >= 0) {
          element.meta.visiable = true
        } else {
          element.meta.visiable = false
        }
      })
    })
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // personal
    toPer() {
      router.push('/per')
    },
    // 登出
    logout() {
      // 清除token并返回首页
      localStorage.removeItem('token')
      this.$store.dispatch('clearUser')
      this.$store.commit('SET_UserInfo', { fullName: '', roles: 'guest' })
      localStorage.removeItem('user')
      console.log(this.$store.state.user);
      this.menuList = router.options.routes[1].children
      this.roles = 'guest'
      this.menuList.forEach(element => {
        // console.log(element);
        element.meta.roles.forEach(item => {
          // console.log(item.indexOf(this.roles));
          // console.log(this.roles);
          if (item.indexOf(this.roles) >= 0) {
            element.meta.visiable = true
          } else {
            element.meta.visiable = false
          }
        })
      })
      localStorage.setItem('roles', 'guest')
      location.reload()
      router.push('/home')
    }
  }
}
</script>

<style lang="less">
.decorativeBar {
  @apply bg-[#297AD8] w-[100%] h-45px text-center mx-auto;
}

.asideY {
  height: 710px;
}

.mainY {
  height: 850px;
}

li {
  @apply leading-80px px-20px;
}
</style>
