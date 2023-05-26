<template>
  <div class="w-560px bg-blue-200 ml-40 border-3 border-black">
    <div>
      <el-form :model="form" label-width="120px" :label-position="labelPosition" class="pl-6 border-b-3 border-black ">
        <el-form-item label="体温(摄氏度)">
          <el-input v-model="form.temperature" class="w-270px" />
        </el-form-item>
        <el-form-item label="有无新冠症状">
          <el-select v-model="form.health" placeholder="请选择">
            <el-option label="是" value="是" />
            <el-option label="否" value="否" />
          </el-select>
        </el-form-item>
        <el-form-item label="症状(没有则不填)" prop="symptom">
          <el-checkbox-group v-model="form.symptom">
            <el-checkbox label="发热" name="symptom" />
            <el-checkbox label="干咳" name="symptom" />
            <el-checkbox label="乏力" name="symptom" />
            <el-checkbox label="咽痛 等症状" name="symptom" />
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="核酸/抗原检测">
          <el-radio-group v-model="form.suspected">
            <el-radio label="已检测，呈阳性" />
            <el-radio label="已检测，呈阴性" />
            <el-radio label="未检测" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="当前所在位置">
          <el-input v-model="form.location" class="w-270px" />
        </el-form-item>
        <el-form-item label="是否中高风险地区">
          <el-select v-model="form.risk" placeholder="请选择">
            <el-option label="是" value="是" />
            <el-option label="否" value="否" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" class="mb-0">
          <el-input v-model="form.remark" type="textarea" class="w-470px" />
        </el-form-item>
      </el-form>

      <el-form :model="form2" label-width="120px" class="">
        <el-form-item label="目的地" class="mb-0">
          <el-input v-model="form2.destination" class="w-270px" />
        </el-form-item>
        <el-form-item label="事由" class="mb-0">
          <el-input v-model="form2.content" class="w-270px" />
        </el-form-item>
        <el-form-item label="计划" class="mb-0">
          <div class="demo-date-picker">
            <div class="block">
              <el-date-picker v-model="form2.start" @change="handleDateChange" type="date" placeholder="请选择日期" />
            </div>
          </div>至
          <div class="demo-date-picker">
            <div class="block">
              <el-date-picker v-model="form2.end" @change="handleDateChange2" type="date" placeholder="请选择日期" />
            </div>
          </div>期间进出小区
        </el-form-item>
      </el-form>
    </div>
    <div class="border-t-black border-3 pl-7">
      <el-button type="primary" @click="onsubmit()">提交</el-button>
      <el-button>取消</el-button>
    </div>
  </div>
</template>

<script>
import { createMsg } from '@/api/post.js'
import store from '../../store'
export default {
  data() {
    return {
      user: store.state.user,
      labelPosition: 'top',
      form: {
        msgName: '',
        temperature: '',
        health: '',
        suspected: '',
        symptom: [],
        location: '',
        risk: '',
        remark: ''
      },
      form2: {
        destination: '',
        content: '',
        start: '',
        end: ''
      }
    }
  },
  methods: {
    handleDateChange2(date) {
      // 将选择的日期转换为所需的格式
      const formattedDate = date.toISOString().split('T')[0];
      this.form2.end = formattedDate;
    },
    handleDateChange(date) {
      // 将选择的日期转换为所需的格式
      const formattedDate = date.toISOString().split('T')[0];
      this.form2.start = formattedDate;
    },
    onsubmit() {
      createMsg({
        uid: this.user.userId,
        msgName: this.user.fullName,
        temperature: this.form.temperature,
        health: this.form.health,
        suspected: this.form.suspected,
        symptom: this.form.symptom.join(','),
        location: this.form.location,
        risk: this.form.risk,
        remark: this.form.remark,
        destination: this.form2.destination,
        content: this.form2.content,
        start: this.form2.start,
        end: this.form2.end
      }).then(res => {
        console.log(res);
        this.form = ''
        this.form2 = ''
      })
    }
  }
}
</script>

<!-- <script setup>
import { reactive, ref } from 'vue'

const labelPosition = ref('top')
// do not use same name with ref
const form = reactive({
  msg_name: '',
  temperature: '',
  health: '',
  suspected: '',
  symptom: [],
  location: '',
  risk: '',
  create_time: ''
})

const form2 = reactive({

})

const onSubmit = () => {
  createMsg().then(res => {

  })
}
</script> -->
<!-- 
          <el-select v-model="form.health" placeholder="请选择" class="w-150px">
            <el-option label="当天" value="当天" />
            <el-option label="第二天" value="第二天" />
            <el-option label="第三天" value="第三天" />
          </el-select>
          <el-select v-model="form.health" class="w-150px">
            <el-option label="8:00" value="8:00" />
            <el-option label="9:00" value="9:00" />
            <el-option label="10:00" value="10:00" />
            <el-option label="11:00" value="11:00" />
            <el-option label="12:00" value="12:00" />
            <el-option label="13:00" value="13:00" />
            <el-option label="14:00" value="14:00" />
            <el-option label="15:00" value="15:00" />
            <el-option label="16:00" value="16:00" />
            <el-option label="17:00" value="17:00" />
            <el-option label="18:00" value="18:00" />
            <el-option label="19:00" value="19:00" />
            <el-option label="20:00" value="20:00" />
            <el-option label="21:00" value="21:00" />
            <el-option label="22:00" value="22:00" />
            <el-option label="23:00" value="23:00" />
          </el-select>期间进出小区 -->

