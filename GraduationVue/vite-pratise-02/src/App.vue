<template>
  <!-- 顶部装饰栏 -->
  <div class="decorativeBar">topBar</div>
  <div class="headerY">
    <div>Header</div>
  </div>

  <div class="sectionY" router="router">
    <!-- 侧边 -->
    <el-aside class="asideY">
      <el-scrollbar :model="test">

        <el-menu v-for="(item, index) in $router.options.routes">
          <el-sub-menu v-if="!item.hidden">
            <template #title>
              <router-link :to="item.path">{{ item.name }}</router-link>
            </template>
            <el-menu-item-group v-for="(item2, index2) in item.children">
              <el-menu-item>
                <router-link :to="item2.path">{{ item2.name }}</router-link>
              </el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <router-view></router-view>
  </div>
</template>

<script setup>
import router from './router/index'
import { ref, reactive } from 'vue'
// console.log(this.$router.mathed[0].hidden);
</script>

<style lang="less">
.decorativeBar {
  @apply bg-green-300 min-w-1530px h-20 text-center leading-80px;
}

.headerY {
  @apply bg-[#B3C0D1] my-2 min-w-1530px p-0 h-35px;

  div {
    @apply w-1530px h-35px bg-orange-300 mx-auto text-center leading-35px;
  }
}

.sectionY {
  @apply mx-auto w-1530px flex;

  .asideY {
    @apply bg-[#D3DCE6] w-[12%] h-auto mr-2 float-left;
  }
}

li {
  @apply leading-80px px-20px;
}
</style>
