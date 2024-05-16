<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
          <el-breadcrumb-item></el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>考研分数线汇总</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :xs="4" :sm="3" :md="2" :lg="3" :xl="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :xs="16" :sm="18" :md="19" :lg="18" :xl="20">
        <el-table
            v-loading="loading"
            :data="scorelineList"
            border
            stripe
            lazy
            style="margin-top: 10px;">
          <el-table-column
              label="序号"
              type="index"
              fixed
              width="50">
          </el-table-column>
          <el-table-column
              fixed
              label="学校名称"
              width="140">
            <template slot-scope="scope">
              <svg t="1646188413276" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2311" width="18" height="18"><path d="M945.4 383.3l-399-204.6c-23.7-12.1-51.9-12.1-75.5 0.2L77.2 383.4c-9.8 5.1-15.8 15.1-15.8 26.1 0 11 6.1 21 15.8 26.1l37.5 19.5v193.5c-14.8 8.3-25 24-25 42.2 0 26.8 21.7 48.5 48.5 48.5s48.5-21.7 48.5-48.5c0-18.2-10.1-33.9-25-42.2v-169l104.5 54.3V746c0 25.7 16.5 48.2 41 56L432 841.7c25.6 8.1 52 12.2 78.3 12.2 25.3 0 50.6-3.7 75.2-11.2l134.1-40.8c24.8-7.6 41.6-30.1 41.7-56l0.8-216.2 183.3-94c9.9-5.1 16-15.1 16-26.1-0.1-11.2-6.2-21.2-16-26.3zM714.2 745.7c0 5.2-3.4 9.7-8.3 11.2l-134.1 40.8c-41.2 12.5-84.6 12.2-125.7-0.8l-124.7-39.6c-4.9-1.6-8.2-6.1-8.2-11.2v-188L470.9 640c11.9 6.2 24.9 9.3 37.9 9.3 12.9 0 25.8-3 37.6-9.1l168.5-86.4-0.7 191.9zM525 598.4c-10.1 5.2-22.2 5.2-32.3-0.1L129 409.4l363.6-188.8c10.1-5.3 22.2-5.3 32.3-0.1l368.4 188.9-368.3 189z" p-id="2312" fill="#1296db"></path></svg>
              <el-link type="success">
                <router-link :to="{path: '/scoreLine',query: {sid: scope.row.schoolId}}" style="color: #5cb87a; text-decoration: none; font-weight: bold;">{{scope.row.sname}}</router-link>
              </el-link>
            </template>
          </el-table-column>
          <el-table-column
              fixed
              prop="collegeName"
              label="学院名称"
              width="110">
          </el-table-column>
          <el-table-column
              label="专业编码"
              width="80">
            <template slot-scope="scope">
              <el-tag type="warning">{{scope.row.majorCode}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              prop="majorName"
              label="专业名称"
              width="100">
          </el-table-column>
          <el-table-column
              prop="majorDir"
              label="专业方向"
              width="90">
            <template slot-scope="scope">
              <div v-if="scope.row.majorDir">{{scope.row.majorDir}}</div>
              <div v-if="!scope.row.majorDir">无</div>
            </template>
          </el-table-column>
          <el-table-column
              sortable
              prop="score21"
              label="2021年分数线"
              width="90">
            <template slot-scope="scope">
              <el-tag type="success">{{scope.row.score21}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              sortable
              prop="score20"
              label="2020年分数线"
              width="90">
            <template slot-scope="scope">
              <el-tag>{{scope.row.score20}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              sortable
              prop="score19"
              label="2019年分数线"
              width="90">
            <template slot-scope="scope">
              <el-tag type="danger">{{scope.row.score19}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
              prop="politicsSubject"
              label="政治科目"
              width="90">
          </el-table-column>
          <el-table-column
              prop="mathSubject"
              label="数学科目"
              width="100">
          </el-table-column>
          <el-table-column
              prop="englishSubject"
              label="英语科目"
              width="100">
          </el-table-column>
          <el-table-column
              prop="computerSubject"
              label="专业课"
              width="180">
            <template slot-scope="scope">
              <el-card shadow="hover">
                {{scope.row.computerSubject}}
              </el-card>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            style="text-align: center; margin-top: 10px; margin-bottom: 20px;"
            background
            :page-size="size"
            :current-page="current"
            @current-change="currentChange"
            layout="prev, pager, next"
            :total="total">
        </el-pagination>
      </el-col>
      <el-col :xs="4" :sm="3" :md="3" :lg="2" :xl="2">
        <div class="grid-content bg-purple-light"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "ScoreLineList",
  data() {
    return {
      current: 1,
      size: 10,
      total: 0,
      scorelineList: [],
      loading: true
    }
  },
  mounted() {
    this.getScoreLineList()
  },
  methods: {
    async getScoreLineList() {
      const result = await this.$API.reqGetScoreLineList({
        current: this.current,
        size: this.size
      })
      if (result.data.code === 200) {
        this.current = result.data.data.current
        this.size = result.data.data.size
        this.total = result.data.data.total
        this.scorelineList = result.data.data.scorelineVoList
        this.loading = false
      } else {
        this.$message.error(result.data.msg)
      }
    },
    currentChange(current) {
      this.current = current
      this.getScoreLineList()
    }
  }
}
</script>

<style scoped>

</style>