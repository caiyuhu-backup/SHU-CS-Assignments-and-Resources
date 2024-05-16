<template>
  <el-row :gutter="20">
    <el-col :span="20" :offset="2">
      <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
        <el-breadcrumb-item></el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>院校列表</el-breadcrumb-item>
      </el-breadcrumb>
      <div>
        <el-input v-model="keyWord" placeholder="请输入内容" class="input" clearable size="small"></el-input>
      </div>
      <div>
        <el-row class="elRow">
          <el-empty :image-size="200" v-if="total===0"></el-empty>
          <el-col class="elCol" v-for="(school) in schoolList" :key="school.sid">
            <el-card shadow="hover" class="elCard" v-loading="loading">
              <img :src='school.badgeImg' alt="校徽">
              <router-link :to="{
                path: '/schoolDetail',
                query: {
                  sid: school.sid
                }
              }">
                {{ school.sname }}
              </router-link>
              <div>
                <MarkDown :text="school.shortIntroduction"/>
              </div>
            </el-card>
          </el-col>
          <el-col style="text-align: center">
            <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageParam.size"
                :current-page="pageParam.current"
                :pager-count="5"
                @current-change="currentChange"
                :total="total">
            </el-pagination>
          </el-col>
        </el-row>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import MarkDown from "@/components/MarkDown/MarkDown"

export default {
  name: "Schools",
  components: {
    MarkDown
  },
  data() {
    return {
      schoolList: [],
      pageParam: {
        current: 1,
        size: 5
      },
      // 总页数
      pages: 0,
      // 总条数
      total: 0,
      keyWord: '',
      loading: true
    }
  },
  methods: {
    async getSchoolList(pageParam) {
      const result = await this.$API.reqGetSchoolList(pageParam)
      if (result.data.code === 200) {
        this.schoolList = result.data.data.schoolVoList
        this.pages = result.data.data.pages
        this.total = result.data.data.total
        this.loading = false
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
      }
    },
    currentChange(val) {
      this.pageParam.current = val
      if (this.keyWord) {
        this.searchSchool()
      } else {
        this.getSchoolList(this.pageParam)
      }
    },
    async searchSchool(current) {
      if (current) {
        this.pageParam.current = current
      }
      const result = await this.$API.reqSearchSchool({
        text: this.keyWord,
        current: this.pageParam.current,
        size: this.pageParam.size,
        uid: this.$store.state.user.userInfo.userId
      })
      if (result.data.code === 200) {
        this.schoolList = result.data.data.schoolVoList
        this.pages = result.data.data.pages
        this.total = result.data.data.total
        this.loading = false
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
      }
    }
  },
  mounted() {
    this.getSchoolList(this.pageParam)
  },
  watch: {
    keyWord() {
      this.searchSchool(1)
    }
  }
}
</script>

<style scoped>
.input {
  width: 238px;
  height: 10px;
  margin: 6px 0 0 29px;
}

.elRow {
  margin: 20px 30px;
  border-radius: 4px
}

.elCol {
  margin: 5px auto;
}

.elCard {
}

.elCard img {
  height: 100px;
  width: 96px;
  float: left;
  padding: 5px;
  margin: 0 10px 0 -13px;
}

.elCard a {
  font-size: 25px;
  font-weight: bold;
  color: black;
  text-decoration: none;
  margin: -5px 0 5px 0;
  display: block;
}

.elCard a:hover {
  color: #4cb9fc;
}

.elCard div {
  font-size: 16px;
}
</style>
