<!--
针对于 /sellerpage 这条路径而显示出来的
在这个组件中, 通过子组件注册的方式, 要显示出Seller.vue这个组件
 -->
<template>
  <div class="com-page">
    <Help></Help>
  </div>
</template>

<script>
import Help from '@/components/Help'
export default {
  data () {
    return {}
  },
  methods: {},
  components: {
    Help: Help
  }
}
</script>

<style lang="less" scoped>
</style>
