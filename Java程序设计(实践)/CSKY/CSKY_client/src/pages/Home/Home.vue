<template>
<div class="all">
  <el-row :gutter="20">
    <el-col :span="20" :offset="2">
      <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
        <el-breadcrumb-item></el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      </el-breadcrumb>
    </el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :xs="0" :sm="1" :md="1" :lg="3" :xl="4">
      <div class="grid-content bg-purple"></div>
    </el-col>
    <el-col :xs="24" :sm="22" :md="22" :lg="18" :xl="16">
      <FrontPage></FrontPage>
    </el-col>
    <el-col :xs="0" :sm="1" :md="1" :lg="3" :xl="4">
      <div class="grid-content bg-purple-light"></div>
    </el-col>
  </el-row>
  <el-row :gutter="20">
    <el-col :xs="2" :sm="2" :md="2" :lg="4" :xl="5">
      <div class="grid-content bg-purple"></div>
    </el-col>
    <el-col :xs="10" :sm="10" :md="10" :lg="8" :xl="7">
      <el-card shadow="always">
        <h1 style="font-size: 20px;">
          <svg t="1646131969982" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="22383" width="25" height="25"><path d="M763.648 969.472H261.12c-114.048 0-206.592-92.544-206.592-206.592V260.352C54.528 146.304 147.072 53.76 261.12 53.76h502.528C877.696 53.76 970.24 146.304 970.24 260.352V762.88c0 114.048-92.544 206.592-206.592 206.592z" fill="#00D16B" p-id="22384"></path><path d="M538.496 596.096h-96.64c-12.672 0-23.04-10.24-23.04-23.04 0-12.672 10.24-23.04 23.04-23.04h98.816c47.744 0 91.136-32.256 100.736-78.976 13.184-64.256-35.84-120.96-97.792-120.96H376.32c-56.576 0-106.752 46.336-105.216 102.912 0.768 28.032 12.928 53.632 33.408 71.808 8.32 7.296 10.24 19.456 4.608 28.928-7.296 12.16-24.064 15.232-34.56 5.888-47.616-42.24-65.536-113.92-32.64-177.152 25.728-49.408 79.104-78.336 134.784-78.336h166.912c86.784 0 156.16 76.16 144.768 165.248-9.6 73.472-75.776 126.72-149.888 126.72z" fill="#FFFFFF" p-id="22385"></path><path d="M649.216 719.104H486.4c-74.24 0-140.416-53.248-149.76-126.72-11.52-89.088 57.856-165.248 144.64-165.248h97.152c12.672 0 23.04 10.24 23.04 23.04 0 12.672-10.24 23.04-23.04 23.04h-94.336c-47.744 0-91.136 32.256-100.736 78.976-13.184 64.256 35.84 120.96 97.792 120.96h168.832c35.328 0 69.504-16.64 88.192-46.592 25.6-41.216 17.792-89.856-11.136-121.216-7.552-8.192-8.704-20.48-2.048-29.568 8.32-11.392 25.216-13.056 34.944-2.688 41.472 44.16 54.016 112.512 20.736 171.904-26.24 46.848-77.696 74.112-131.456 74.112z" fill="#FFFFFF" p-id="22386"></path></svg>
          研招答疑</h1>
        <ul style="margin-top: 7px;">
          <li style=" margin-bottom: 10px;" v-for="item in list1" :key="item.id" class="text-view">
            <svg t="1646132127856" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="23252" width="15" height="15"><path d="M517.12 517.12m-460.8 0a460.8 460.8 0 1 0 921.6 0 460.8 460.8 0 1 0-921.6 0Z" fill="#FCE8D8" p-id="23253"></path><path d="M505.856 226.304c-16.384-5.12-32.768 6.144-34.816 23.04-5.12 37.376-36.864 91.136-91.136 146.944-16.384 16.896-34.816 32.256-53.248 46.592-43.52 34.304-74.752 88.576-74.752 156.16 0 75.776 48.128 203.264 283.648 203.264s269.824-154.112 269.824-203.264c0-45.056-9.728-137.728-83.968-190.464-10.24-7.168-24.576-4.096-30.72 7.168-6.656 12.288-15.36 23.552-37.376 37.376-5.632 3.584-12.8-1.536-11.776-8.192 12.8-59.904 12.288-172.544-135.68-218.624z" fill="#EF885B" p-id="23254"></path><path d="M556.032 679.936c32.768-3.072 57.856-31.232 55.296-62.976 0-1.536 0-2.56-0.512-3.584-3.584-25.088-15.36-43.52-44.032-68.608-21.504-18.944-19.968-53.76-17.408-73.728 1.024-6.656-5.12-11.776-11.264-9.216-31.232 12.288-103.936 47.104-108.032 113.664-2.048 28.672 6.656 53.76 18.944 74.24 9.728 16.384 26.624 27.136 45.568 29.696 10.752 1.536 23.04 2.048 35.84 2.048 9.728-0.512 17.92-1.024 25.6-1.536z" fill="#FCE8D8" p-id="23255"></path></svg>
            &nbsp;
            <router-link style="font-size: 15px;" :to="{ path: '/article', query: { id: item.id}}">{{ item.title }}</router-link>
          </li>
        </ul>
      </el-card>
    </el-col>
    <el-col :xs="10" :sm="10" :md="10" :lg="8" :xl="7">
      <el-card shadow="always">
        <h1><svg t="1646132054392" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="22535" width="25" height="25"><path d="M960 192 576 192 576 128c0-38.4-25.6-64-64-64L128 64l0 0c0-38.4-25.6-64-64-64S0 25.6 0 64l0 896c0 38.4 25.6 64 64 64s64-25.6 64-64l0-256 320 0 0 64c0 38.4 25.6 64 64 64l448 0c38.4 0 64-25.6 64-64L1024 256C1024 217.6 998.4 192 960 192zM896 704 576 704l0-64c0-38.4-25.6-64-64-64L128 576 128 192l320 0 0 64c0 38.4 25.6 64 64 64l384 0L896 704z" p-id="22536" fill="#1296db"></path></svg>
          考研大纲</h1>
        <ul style="margin-top: 7px;">
          <li style=" margin-bottom: 10px;" v-for="item in list2" :key="item.id" class="text-view">
            <svg t="1646131450452" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="21037" width="15" height="15"><path d="M769.568 377.696c-1.648 114.8-48.272 163.648-48.272 163.648C716.896 285.632 464.48 11.52 464.48 11.52c83.408 169.776-285.344 383.232-285.344 689.36 0 192.032 236.864 333.088 355.584 308.88C1047.776 905.2 769.568 377.696 769.568 377.696L769.568 377.696zM512 932.816c0 0-265.92-130.848-7.472-420.8 0 0 128.4 130.08 103.712 251.28 0 0 36.208 3.84 82.304-62.144C690.544 701.136 701.312 885.248 512 932.816L512 932.816z" p-id="21038" fill="#d81e06"></path></svg>
            &nbsp;
            <router-link style="font-size: 15px;" :to="{ path: '/article', query: { id: item.id}}">{{ item.title }}</router-link>
          </li>
        </ul>
      </el-card>
    </el-col>
    <el-col :xs="2" :sm="2" :md="2" :lg="4" :xl="5">
      <div class="grid-content bg-purple-light"></div>
    </el-col>
  </el-row>
</div>
</template>

<script>
import FrontPage from "@/components/FrontPage/FrontPage"
import Vue from "vue";

export default {
  name: "Home",
  components: {
    FrontPage
  },
  data() {
    return {
      list1: [],
      list2: []
    }
  },
  methods: {
    async getArticleByTid(tid) {
      const result = await this.$API.reqGetArticleByTid(tid)
      if (result.data.code === 200) {
        return result.data.data
      } else {
        return []
      }
    }
  },
  async mounted() {
    const loading = Vue.prototype.$loading({
      lock: true, // 是否锁屏
      text: '拼命加载中', // 加载动画的文字
      spinner: 'el-icon-loading', // 引入的loading图标
      background: 'hsla(0,0%,100%,.9)' // 背景颜色
    });
    this.list1 = await this.getArticleByTid(26)
    this.list2 = await this.getArticleByTid(27)
    loading.close();
  }
}
</script>

<style scoped>

</style>
