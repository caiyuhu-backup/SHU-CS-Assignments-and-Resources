<template>
  <!-- 头部 -->
  <header class="header" v-if="$route.meta.isShowHeader">
    <!-- 头部的第一行 -->
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-menu :default-active="$route.path" class="top" mode="horizontal" background-color="#ffffff"
                 router text-color="#8dc4fd" active-text-color="#1989fa">
          <el-menu-item index="/home">首页</el-menu-item>
          <el-menu-item index="/schoolList">34所高校</el-menu-item>
          <el-menu-item index="/scoreLineList">考研分数线</el-menu-item>
          <el-submenu index="/">
            <template slot="title">考研真题</template>
            <el-menu-item index="/examQuestion?id=4">计算机408</el-menu-item>
            <el-menu-item index="/examQuestion?id=2">考研英语</el-menu-item>
            <el-menu-item index="/examQuestion?id=1">考研政治</el-menu-item>
            <el-menu-item index="/examQuestion?id=3">考研数学</el-menu-item>
          </el-submenu>
          <el-menu-item index="/timeLine">考研时间线</el-menu-item>
          <el-menu-item index="/md">发布文章</el-menu-item>
          <el-menu-item index="/register" v-if="!username" style="float: right;">注册</el-menu-item>
          <el-menu-item index="/login" v-if="!username" style="float: right;">登录</el-menu-item>
          <el-menu-item v-if="username" style="float: right;">
            <el-dropdown trigger="click">
              <span class="el-dropdown-link">
                    <el-tooltip class="item" effect="light" placement="left">
                      <div slot="content">用户名称<br/>{{ username }}</div>
                      <el-avatar :src="userImg"></el-avatar>
                    </el-tooltip>
                <i class="el-icon-caret-bottom el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item class="clearfix" @click.native="register">
                  注册
                </el-dropdown-item>
                <el-dropdown-item class="clearfix" @click.native="logout">
                  <span>退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <!--头部第二行 搜索区域-->
    <div class="bottom" v-if="$route.meta.isShowSearch">
      <el-row :gutter="20">
        <el-col :xs="1" :sm="2" :md="2" :lg="0" :xl="1">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :xs="4" :sm="3" :md="9" :lg="9" :xl="2">
          <h1 class="logoArea">
            <router-link class="logo" to="/home">
              <img
                  src="../../assets/logo.png"
                  alt="logo">
            </router-link>
          </h1>
        </el-col>
        <el-col :xs="6" :sm="12" :md="10" :lg="14" :xl="20">
          <div class="searchArea">
            <el-form :inline="true" size="medium">
              <el-form-item>
                <el-input v-model="keyWord" prefix-icon="el-icon-search" placeholder="请输入搜索内容"/>
              </el-form-item>
              <el-button type="primary" icon="el-icon-search" @click="goSearch" size="medium">文章搜索</el-button>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-divider></el-divider>
  </header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      keyWord: '',
      centerDialogVisible: false
    }
  },
  methods: {
    goSearch() {
      if (this.keyWord.trim() === '') {
        this.$message.warning('输入框不能为空~')
        return
      }
      let location = {
        name: 'search',
        params: {
          keyWord: this.keyWord,
          fromRouter: this.$route.path
        }
      }
      //如果带有query参数也传过去
      if (this.$route.query) {
        location.query = this.$route.query
      }
      this.$router.push(location)
      this.keyWord = ''
    },
    //退出登录
    async logout() {
      this.$confirm('即将退出登录, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const result = await this.$store.dispatch('user/logout')
        if (result) {
          this.$message({
            type: 'success',
            message: '已退出登录!'
          })
          await this.$router.push('/login')
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });

    },
    register() {
      this.$router.push('/register')
    }
  },
  computed: {
    username() {
      if (this.$store.state.user.userInfo)
        return this.$store.state.user.userInfo.username
      return ''
    },
    userImg() {
      if (this.$store.state.user.userInfo)
        return this.$store.state.user.userInfo.userImg
      return ''
    }
  }
}
</script>

<style scoped lang="less">
.header {
  & > .top {
    background-color: #c6e2ff;
    height: 80px;
    line-height: 80px;
    width: 1140px;
  }


  & > .bottom {
    width: 1140px;
    height: 75px;
    margin: 0 auto;
    overflow: hidden;

    .logoArea {
      float: left;

      .logo {
        img {
          width: 180px;
          margin: -45px 0px;
        }
      }
    }

    .searchArea {
      float: right;
      margin-top: 35px;
      margin-right: 5px;
    }
  }
}
</style>
