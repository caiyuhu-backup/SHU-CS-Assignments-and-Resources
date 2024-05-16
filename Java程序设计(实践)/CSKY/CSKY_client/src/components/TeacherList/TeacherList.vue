<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <h1>师资介绍</h1>
      </el-col>
      <el-col :span="24" :offset="2">
        <el-input
            type="text"
            placeholder="请输入内容"
            v-model="search"
            style="width: 200px;"
            size="small"
        />
        <el-table
            @cell-click="toTeacherDetail"
            highlight-current-row
            :data="teacherList"
            style="width: 100%;">
          <el-table-column
              prop="id"
              label="序号"
              type="index"
              width="100">
          </el-table-column>
          <el-table-column
              prop="tname"
              label="教师姓名"
              width="200">
            <template slot-scope="scope">
              <div slot="reference">
                <el-tag size="medium">{{ scope.row.tname }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column
              prop="studydir"
              label="专业方向"
              width="500">
            <template slot-scope="scope">
              <div slot="reference" v-if="scope.row.studydir">
                {{ scope.row.studydir }}
              </div>
              <div slot="reference" v-if="!scope.row.studydir">
                暂无
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="20">
        <el-pagination
            style="text-align: center; margin-top: 10px;"
            :page-size="5"
            :current-page="currentPage"
            small
            @current-change="currentChange"
            layout="prev, pager, next"
            :total="total">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "TeacherList",
  props: ['schoolIntroduce'],
  data() {
    return {
      teacherList: [],
      currentPage: 1,
      pages: 0,
      total: 0,
      search: '',
      sname: ''
    }
  },
  mounted() {
    this.getTeacherList()
  },
  methods: {
    toTeacherDetail(item) {
      this.$router.push({
        path: "/teacherDetail",
        query: {
          tid: item.id,
          sname: this.sname
        }
      })
    },
    async getTeacherList() {
      const result = await this.$API.reqGetTeacherList(this.$route.query.sid, this.currentPage)
      if (result.data.code === 200) {
        this.teacherList = result.data.data.teacherVoList
        this.total = result.data.data.total
        this.pages = result.data.data.pages
      } else {
        this.$message.warning('系统异常~ ' + result.data.msg)
      }
    },
    currentChange(currentPage) {
      this.currentPage = currentPage
      if (this.search) {
        this.searchTeacher()
      } else {
        this.getTeacherList()
      }
    },
    async searchTeacher(current) {
      if (current) {
        this.currentPage = current
      }
      const result = await this.$API.reqSearchTeacher({
        text: this.search,
        current: this.currentPage,
        size: 5,
        sid: this.$route.query.sid,
        uid: this.$store.state.user.userInfo.userId
      })
      if (result.data.code === 200) {
        this.teacherList = result.data.data.teacherVoList
        this.total = result.data.data.total
        this.pages = result.data.data.pages
      } else {
        this.$message.warning('系统异常~ ' + result.data.msg)
      }
    }
  },
  watch: {
    search() {
      this.searchTeacher(1)
    },
    schoolIntroduce(sc) {
      this.sname = sc.sname
    }
  }
}
</script>

<style scoped>
h1 {
  font-size: 22px;
}

.all {
  margin: 0 0 0 0;
}
</style>
