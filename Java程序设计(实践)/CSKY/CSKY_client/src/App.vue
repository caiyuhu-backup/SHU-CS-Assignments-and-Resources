<template>
  <div id="outer">
    <el-backtop>
      <i class="el-icon-caret-top"></i>
    </el-backtop>
    <Header></Header>
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "@/components/Header/Header";

export default {
  name: 'App',
  components: {
    Header
  },
}
</script>

<style>
.bread {
  margin: 0 20px;
}
.all {
  margin: 20px 30px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: transparent;
}
.bg-purple-light {
  background: transparent;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.text-view {
  display: inline-block;
  white-space: nowrap;
  width: 100%;
  overflow: hidden;
  text-overflow:ellipsis;
}
</style>
