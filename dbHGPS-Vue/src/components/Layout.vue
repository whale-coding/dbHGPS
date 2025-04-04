<template>
  <div>
    <!-- 头部 -->
    <bHeader></bHeader>
    <!-- 中间 -->
    <div>
      <router-view></router-view>
    </div>
    <!-- 底部 -->
    <bFooter></bFooter>
  </div>
</template>
<script>
// 导入所需要的组件
import bHeader from "@/components/Header";
import bFooter from "@/components/Footer";
export default {
  name: "Layout",
  components: {
    bHeader,
    bFooter,
  },
  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>
<style lang="scss" scoped></style>
