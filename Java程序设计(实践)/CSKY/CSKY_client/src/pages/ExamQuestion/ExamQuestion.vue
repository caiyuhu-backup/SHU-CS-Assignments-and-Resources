<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
          <el-breadcrumb-item></el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>考研真题</el-breadcrumb-item>
          <el-breadcrumb-item>{{ subjectName }}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin-top: 10px;">
      <el-col :span="20" :offset="2">
        <el-card>
          <el-row :gutter="20">
            <el-col :xs="4" :sm="3" :md="3" :lg="3" :xl="5">
              <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :xs="16" :sm="20" :md="20" :lg="19" :xl="16">
              <el-table
                  :data="fileList"
                  lazy
                  stripe
                  border
                  v-loading="loading"
                  style="width: 100%;"
                  @selection-change="selectChange">
                <el-table-column
                    type="selection"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="year"
                    sortable
                    label="年份"
                    width="150">
                  <template slot-scope="scope">
                    <span style="color: #4cb9fc;">{{scope.row.year}}</span>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="subjectCode"
                    label="专业代码"
                    width="180">
                  <template slot-scope="scope">
                    <div class="name-wrapper">
                      <el-tag size="medium" type="danger">{{ scope.row.subjectCode }}</el-tag>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="subject"
                    label="专业名称"
                    width="310">
                </el-table-column>
                <el-table-column label="操作" width="275">
                  <template slot-scope="scope">
                    <el-popconfirm
                        confirm-button-text='确认'
                        cancel-button-text='取消'
                        @confirm="handleDownload(scope.$index, scope.row)"
                        icon-color="#999999"
                        title="确认下载吗？">
                      <el-button
                          slot="reference"
                          type="warning"
                          size="mini"
                      >点击下载
                      </el-button>
                    </el-popconfirm>
                  </template>
                </el-table-column>
              </el-table>
              <el-button type="primary" size="medium" style="margin-top: 10px;" @click="handleDownloadMore">批量下载
              </el-button>
              <el-pagination
                  style="text-align: center; margin-top: 10px;"
                  small
                  :page-size="size"
                  :current-page="current"
                  @current-change="currentChange"
                  layout="prev, pager, next"
                  :total="total">
              </el-pagination>
            </el-col>
            <el-col :xs="4" :sm="2" :md="2" :lg="2" :xl="2">
              <div class="grid-content bg-purple-light"></div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "ExamQuestion",
  data() {
    return {
      code: 0,
      current: 1,
      size: 10,
      total: 0,
      fileList: [],
      selectedFile: [],
      loading: true
    }
  },
  mounted() {
    this.getExamQuestion()
  },
  methods: {
    async getExamQuestion() {
      const result = await this.$API.reqGetExamQuestion({
        code: this.$route.query.id,
        page: {
          current: this.current,
          size: 10
        }
      })
      if (result.data.code === 200) {
        this.fileList = result.data.data.fileList
        this.current = result.data.data.current
        this.size = result.data.data.size
        this.total = result.data.data.total
        this.loading = false
      } else {
        this.$message.error(result.data.msg)
      }
    },
    handleDownload(index, file) {
      window.open(file.url)
    },
    handleDownloadMore() {
      if (this.selectedFile.length === 0) {
        this.$message.warning('请选择需要下载的内容')
        return
      }
      this.selectedFile.forEach(file => {
        window.open(file.url)
      })
    },
    selectChange(selection) {
      this.selectedFile = selection
    },
    currentChange(current) {
      this.current = current
      this.getExamQuestion()
    },
  },
  watch: {
    questionId() {
      this.getExamQuestion()
    }
  },
  computed: {
    questionId() {
      return this.$route.query.id
    },
    subjectName() {
      switch (this.$route.query.id) {
        case '1':
          return '考研政治'
        case '2':
          return '考研英语'
        case '3':
          return '考研数学'
        case '4':
          return '考研计算机'
        default:
          return ''
      }
    }
  }
}
</script>

<style scoped>
.warning-row {
  background: oldlace;
}

.success-row {
  background: #f0f9eb;
}
</style>