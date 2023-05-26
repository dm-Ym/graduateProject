<template>
  <div class="border-b-2px border-black">留言</div>
  <div>
    <div>
      <el-input v-model="comp" :autosize="{ minRows: 6, maxRows: 8 }" type="textarea" placeholder="" :size="formSize"
        resize="both" show-word-limit maxlength="200" />
      <el-button :disabled="isDisabled" class="bg-[#FC5531] text-light-50 float-right"
        @click="onComplaints">留言</el-button>
    </div>
    <ul>
      <li v-for="comp in Complaints" :key="comp.id" class=" leading-40px border-b-1px border-black">
        <!-- 姓名 时间 -->
        <!-- <div>{{ comp.comName }}--{{ comp.coTime }}
          <span class="text-red-400">{{ comp.status === 0 ? '未处理' : '已处理' }}</span>
        </div> -->
        <!-- 内容 -->
        <div> {{ comp.content }}</div>

        <button class="ml-3 text-gray-400" @click="onDelete(comp.id)">删除</button>
        <button class="mx-3 text-gray-400" @click="onComments(comp.id)">
          {{ comp.showText ? '收起' : '评论' }}
        </button>
        <button @click="toggleComment(comp.id)" class="text-gray-400" v-if="comp.replies.length != 0">
          {{ comp.showReplies ? '折叠' : '查看回复' }}
        </button>
        <div v-if="comp.showText">
          <el-input v-model="reply" :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" placeholder="" :size="formSize"
            resize="both" class="w-300px" show-word-limit maxlength="200" />
          <div class="leading-1">
            <el-button :disabled="isReplyDisabled" class=" bg-red-300" @click="onReply(comp)">回复</el-button>
          </div>
        </div>

        <!-- 回复 -->
        <ul v-if="comp.showReplies">
          <li v-for="reply in comp.replies" :key="reply.id" class="leading-40px border-b-1px border-gray-300">
            <div>{{ reply.reName }}--{{ reply.reTime }} </div>
            {{ reply.reContent }}
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<script>
import { getCommentsById } from '../../api/get';
import { createComm } from '../../api/post.js';
import { deleteComments } from '../../api/delete.js';
// import { updateCommStatus } from '../../api/put';
// import { getComp } from '@/api/get.js';
// import { createCom } from '@/api/post.js';
import store from '@/store';
export default {
  data() {
    return {
      Complaints: [],
      currentCommentId: false,
      showPLun: false,
      comp: '',
      reply: '',
      user: store.state.user,

    }
  },
  computed: {
    //  作用： 没有内容禁止提交
    isDisabled() {
      return !this.comp
    },
    isReplyDisabled() {
      return !this.reply
    }
  },
  created() {
    this.getComplaints()
  },
  methods: {
    getComplaints() {
      getCommentsById('user', this.user.userId).then(result => {
        this.Complaints = result.data.data
      })
    },
    // 用来展开回复框
    onComments(id) {
      if (this.currentCommentId === id) {
        this.currentCommentId = null;
      } else {
        this.currentCommentId = id;
      }
      this.Complaints.forEach((comment) => {
        if (comment.id === id) {
          comment.showText = !comment.showText;
          comment.showReplies = false
        } else {
          comment.showText = false;
        }
      });
    },
    // 折叠/展开
    toggleComment(id) {
      if (this.currentCommentId === id) {
        this.currentCommentId = null;
      } else {
        this.currentCommentId = id;
      }
      this.Complaints.forEach((comment) => {
        if (comment.id === id) {
          comment.showReplies = !comment.showReplies;
          comment.showText = false
        } else {
          comment.showReplies = false;
        }
      });
    },
    // 删除留言
    onDelete(id) {
      deleteComments('user', id).then(res => {
        this.getComplaints()
      })
    },
    // 添加留言
    onComplaints() {
      createComm('user', {
        uid: this.user.userId,
        comName: this.user.fullName,
        content: this.comp,
      }).then(res => {
        this.comp = ''
        this.getComplaints()
      })
    },
    // 回复
    onReply(comp) {
      createCom('replies', {
        pid: comp.id,
        uid: this.user.userId,
        reName: this.user.fullName,
        reContent: this.reply,
        toUid: 0
      }).then(res => {
        console.log(res.data);
        this.getComplaints()
        this.reply = ''
      })
    }
  }
}
</script>

<script setup>
import { ref } from 'vue'
const formSize = ref('large')
</script>

<style>
.el-textarea__inner {
  width: 100%;
  background-color: #F7F9FB;
}
</style>