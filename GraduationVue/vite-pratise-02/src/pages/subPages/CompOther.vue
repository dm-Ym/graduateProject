<template>
  <div class="border-b-2px border-black">投诉</div>
  <!-- class="w-500px" -->
  <div>
    <div>
      <el-input v-model="comp" :autosize="{ minRows: 6, maxRows: 8 }" type="textarea" placeholder="" :size="formSize"
        resize="both" show-word-limit maxlength="200" />
      <el-button :disabled="isDisabled" class="bg-[#FC5531] text-light-50 float-right"
        @click="onComplaints">评论</el-button>
    </div>
    <ul>
      <li v-for="comp in Complaints" :key="comp.id" class=" leading-40px border-b-1px border-black">
        <div>{{ comp.coName }}--{{ comp.coTime }}
          <span class="text-cyan-400">{{ comp.status === 0 ? '已解决' : '' }}</span>
        </div>
        <div> {{ comp.coContent }}</div>

        <button class="mx-3 text-gray-400" @click="onComments(comp.id, $event)">
          {{ comp.showText ? '收起' : '评论' }}
        </button>
        <button @click="toggleComment(comp.id)" class="text-gray-400">
          {{ comp.showReplies ? '折叠' : '查看回复' }} </button>

        <div v-if="comp.showText">
          <el-input v-model="reply" :autosize="{ minRows: 4, maxRows: 8 }" type="textarea" placeholder="" :size="formSize"
            resize="both" class="w-300px" show-word-limit maxlength="200" />
          <div class="leading-1">
            <el-button :disabled="isReplyDisabled" class=" bg-red-300" @click="onReply(comp)">评论</el-button>
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
import { getComp } from '@/api/get.js';
import { createCom } from '@/api/post.js';
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
      rules: {
        comp: [
          { required: true, message: "有asdlaksd加", trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
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
      getComp().then(result => {
        this.Complaints = result.data.data
      })
    },
    onComments(id, event) {
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
    onComplaints() {
      createCom('complaints', {
        uid: this.user.userId,
        coName: this.user.fullName,
        coContent: this.comp
      }).then(res => {
        console.log(res);
        this.getComplaints()
        this.comp = ''
      })
    },
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