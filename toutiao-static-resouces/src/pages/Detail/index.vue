<template>
  <div class="seeDetails">
    <div>
      <button @click="this.$router.back()">back</button>
      <h4 style="font-size: 30px; width: 800px;font-family: 幼圆,sans-serif; font-weight: bold;line-height: 1.55em;">
        {{ detailList.title }}</h4>
    </div>
    <div style="margin-right: 250px">
      <span>{{ detailList.typeName }}</span>
      <span>{{ detailList.pageViews }}浏览</span>
      <span>{{ detailList.pastHours }}小时前</span>
    </div>

    <div style="width: 1000px; margin: 20px 0px 0px 70px; ">
      <p style="font-family: 幼圆,sans-serif; font-size: 18px; color: #20c1dd; font-weight:600; line-height: 1.55em;">
        {{ detailList.article }}
      </p>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'Detail'
})
</script>
<script setup>
import { getshowHeadlineDetail } from "../../api/index"
import { ref, onMounted, reactive, onUpdated } from "vue"
import { useRoute } from 'vue-router'
const route = useRoute() // 路由信息对象

let detailList = ref({}) //详情数据
//获取详情初始化数据
const getDetailList = async () => {
  let result = await getshowHeadlineDetail(route.query.hid)
  console.log("result=" + result)
  console.log(result)
  console.log("result=" + result)
  detailList.value = result
}
// 页面初始化钩子
onMounted(() => {
  getDetailList()
})

</script>

<style lang="less" scoped>
.seeDetails {
  width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;

  div {
    span {
      padding-right: 15px;
      font-size: 14px;
      color: #8d91aa;
    }

    p {
      font-size: 14px;
      color: #2b2e30;
    }
  }
}
</style>
