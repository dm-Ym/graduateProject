<template>
  <div class="common-layout" router="router">
    <el-container>
      <el-header>
        <!-- <div class="inline-block float-left">
      <div class="h-45px">社区疫情防控管理系统</div>
    </div> -->
        <el-dropdown class="float-right" v-if="token">
          <span class="el-dropdown-link leading-45px text-sm mr-8 text-cool-gray-50">
            {{ $store.state.user.fullName }}
            <el-icon class="el-icon--right leading-45px"> <arrow-down /> </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item class="text-sm">个人中心</el-dropdown-item>
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
      </el-header>
      <el-container>
        <el-aside :width="isCollapse ? '5%' : '13%'">
          <div class="bg-[#EEEEEE] text-light-50">
            <div class="text-center text-black" style=" cursor:pointer" @click="toggleCollapse"> ||| </div>
            <div class="text-center text-black"> 菜单</div>
          </div>
          <el-menu :router="true" :unique-opened="true" background-color="#EEEEEE" text-color="black"
            :collapse="isCollapse" :collapse-transition="false" style="height: 100vh;">
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
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import router from '../router/index.js';
import { useStore } from 'vuex';
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
    // const that = this
    this.menuList = router.options.routes[1].children
    if (localStorage.getItem('token')) {
      this.token = true
    } else {
      this.token = false
    }
    if (localStorage.getItem('token')) {
      this.roles = this.$store.state.roles
    }
    console.log(this.menuList);
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
      // if (element.children.meta.roles) {
      //   element.cildren.meta.roles.forEach(item => {
      //     // console.log(item.indexOf(this.roles));
      //     // console.log(this.roles);
      //     if (item.indexOf(this.roles) >= 0) {
      //       element.meta.visiable = true
      //     } else {
      //       element.meta.visiable = false
      //     }
      // })
      // }
    })
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    logout() {
      // 清除token并返回首页
      localStorage.removeItem('token')
      localStorage.removeItem('user')
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
      router.push('/')
      location.reload()
    }
  }
}
</script>

<style lang="less">
// html,
// body,
// #app,
// .el-container {
//   height: 100%;
// }


.el-header {
  width: 100%;
  height: 45px;
  background: #297AD8;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
}

// .el-aside {
//   display: block;
//   position: absolute;
//   left: 0;
//   top: 45px;
//   bottom: 0;
//   overflow: hidden;
// }
.el-aside {
  height: calc(100vh - 45px); // 设置左侧 aside 高度
  overflow: auto;
}

.el-main {
  padding: 0;
  height: 500px;
  // height: calc(100vh - 60px); // 设置主体 main 高度
  overflow-y: scroll;
}

// .el-main {
//   position: absolute;
//   left: 250px;
//   right: 0;
//   top: 60px;
//   bottom: 0;
//   overflow-y: scroll;
// }

li {
  @apply leading-80px px-20px;
}

.el-main {
  --el-main-padding: 0px;
  margin-left: 20px;
  overflow: hidden;
}
</style>
