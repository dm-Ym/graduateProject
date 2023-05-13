<template>
  <p>- 国内疫情数据展示
    - 国内疫情新闻
    - 近30日的感染人数(柱状图)
    - 中高风险地区（饼图）</p>
  <el-table :data="tableData" border stripe class="w-[99%]">
    <el-table-column prop="province" label="省份" width="180" />
    <el-table-column prop="diagnosed" label="确诊人数" width="180" />
    <el-table-column prop="suspected" label="疑似人数" width="180" />
    <el-table-column prop="isolation" label="隔离人数" width="180" />
    <el-table-column prop="heal" label="治愈人数" width="180" />
    <el-table-column prop="death" label="死亡人数" width="180" />
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        { province: "北京", diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '四川', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '江苏', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '台湾', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '河北', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '山西', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '辽宁', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '吉林', diagnosed: '315', suspected: '315', isolation: '315', heal: '315', death: '315' },
        { province: '黑龙江', diagnosed: '152', suspected: '152', isolation: '152', heal: '152', death: '152' },
        { province: '浙江', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '安徽', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '福建', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '江西', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '山东', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '河南', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '湖北', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '湖南', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '广东', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '海南', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '贵州', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '云南', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '陕西', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '甘肃', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '青海', diagnosed: '510', suspected: '510', isolation: '510', heal: '510', death: '510' },
        { province: '内蒙古自治区', diagnosed: '4', suspected: '568', isolation: '97', heal: '63', death: '3' },
        { province: '广西壮族自治区', diagnosed: '2', suspected: '74', isolation: '75', heal: '4523', death: '6' },
        { province: '西藏自治区', diagnosed: '8', suspected: '23', isolation: '45', heal: '62', death: '3' },
        { province: '新疆维吾尔自治区', diagnosed: '4', suspected: '34', isolation: '36', heal: '2', death: '2' },
        { province: '宁夏回族自治区', diagnosed: '47', suspected: '45', isolation: '2', heal: '22', death: '2' },
        { province: '天津市', diagnosed: '95', suspected: '456', isolation: '42', heal: '25', death: '0' },
        { province: '上海市', diagnosed: '42', suspected: '567', isolation: '85', heal: '93', death: '0' },
        { province: '重庆市', diagnosed: '15', suspected: '79', isolation: '41', heal: '17', death: '0' },
        { province: '香港特别行政区', diagnosed: '6', suspected: '74', isolation: '27', heal: '95', death: '0' },
        { province: '澳门特别行政区', diagnosed: '37', suspected: '25', isolation: '17', heal: '37', death: '0' },
      ]
    }
  }
}
</script>