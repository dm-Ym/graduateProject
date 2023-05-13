<template>
  <!-- 顶部装饰栏 -->
  <div class="decorativeBar">topBar
    <el-dropdown class="float-right">
      <span class="el-dropdown-link leading-60px text-sm">
        Dropdown List
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item class="text-sm">Actionsdasdasdasfasfas 1</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
  <div class="headerY">
    <!-- <div class="w-1887px bg-[#B6B6B6] mx-auto text-center leading-35px"> -->
    <!-- </div> -->
  </div>
  <div class="sectionY" router="router">
    <!-- 侧边 -->
    <el-container>
      <el-aside class="asideY" :width="isCollapse ? '5%' : '13%'">
        <div class="bg-[#324358] text-light-50">
          <div class="text-center" style=" cursor:pointer" @click="toggleCollapse"> ||| </div>
          <div class="text-center"> 菜单</div>
        </div>
        <el-menu :router="true" :unique-opened="true" background-color="#324358" text-color="white" :collapse="isCollapse"
          :collapse-transition="false">
          <!--开启,然后配合index-->
          <template v-for="(first, index) in $router.options.routes[1].children">
            <!-- 有子路由的 -->
            <el-sub-menu :index="first.path" v-if="first.children" :key="index">
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
            <el-menu-item v-if="!first.hidden" :index="first.path" :key="first.path" :router="first.path"
              class="text-18px">
              {{ first.name }}
            </el-menu-item>
          </template>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { ArrowDown } from '@element-plus/icons-vue'
// export default {
//   created() {
//     console.log(this.$router.options.routes);
//   }
// }
</script>
<script>
export default {
  data() {
    return {
      isCollapse: false
    }
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style lang="less">
.decorativeBar {
  @apply bg-blue-200 mb-2 w-1902px h-10 text-center leading-80px mx-auto;
}

.headerY {
  @apply bg-[#B6B6B6] min-w-1530px p-0 h-35px;

}

.sectionY {
  @apply mx-auto my-2 w-1902px flex-col bg-light-50;

  .asideY {
    @apply h-auto mr-2 float-left;
  }
}

li {
  @apply leading-80px px-20px;
}
</style>
