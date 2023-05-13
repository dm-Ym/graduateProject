<template>
  <div class="bg-cyan-400 w-auto h-100">
    <button @click="onGet">按钮</button>
  </div>
</template>

<script setup>
import { get } from '../api/get';
import { ElNotification } from 'element-plus'

const onGet = () => {
  get(1).then(res => {
    ElNotification({
      message: res.data.message,
      type: 'success',
      duration: 3000,
    })
    console.log(res.data.data);
  })
}
</script>