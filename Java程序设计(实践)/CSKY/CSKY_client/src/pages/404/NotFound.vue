<template>
  <el-empty
      description="404 Not Found 请检查访问路径是否有误~"
      :image-size="350"
  >
    <el-button type="danger" size="small" @click="toHome" v-loading.fullscreen.lock="fullscreenLoading">点击返回首页</el-button>
  </el-empty>
</template>

<script>
export default {
  name: "NotFound",
  data() {
    return {
      fullscreenLoading: false
    }
  },
  methods: {
    toHome() {
      this.fullscreenLoading = true
      setTimeout(() => {
        this.fullscreenLoading = false;
        this.$router.push({path: '/home'})
      }, 1000);
    }
  }
}
</script>

<style scoped>

</style>