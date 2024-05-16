<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-breadcrumb
          separator-class="el-icon-arrow-right"
          style="margin: 0 20px"
        >
          <el-breadcrumb-item></el-breadcrumb-item>
          <el-breadcrumb-item to="/schools">院校列表</el-breadcrumb-item>
          <el-breadcrumb-item>{{ $route.query.sname }}</el-breadcrumb-item>
          <el-breadcrumb-item>教师</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <div class="teacherInfo">
          <h1>师资介绍</h1>
          <el-row class="elCol2">
            <el-col>
              <el-card shadow="always">
                <el-descriptions
                  title="教师信息"
                  direction="vertical"
                  :column="1"
                  size="medium"
                >
                  <el-descriptions-item label="个人介绍">
                    <span style="color: #003f81; font-weight: bold">
                      {{ teacherDetail.tname }}
                    </span>
                    <span>
                      {{ teacherDetail.proresume }}
                    </span>
                  </el-descriptions-item>
                  <el-descriptions-item label="个人主页">
                    <a
                      rel="nofollow"
                      :href="teacherDetail.turl"
                      v-if="teacherDetail.turl"
                      target="_blank"
                      style="color: #4cb9fc"
                    >
                      {{ teacherDetail.turl }}
                    </a>
                    <a
                      rel="nofollow"
                      :href="teacherDetail.turl"
                      v-if="!teacherDetail.turl"
                      target="_blank"
                      style="color: #4cb9fc"
                    >
                      暂无
                    </a>
                  </el-descriptions-item>
                  <el-descriptions-item label="Email">
                    <span style="color: #003f81" v-if="teacherDetail.temail">
                      {{ teacherDetail.temail }}
                    </span>
                    <span style="color: #003f81" v-if="!teacherDetail.temail">
                      暂无
                    </span>
                  </el-descriptions-item>
                  <el-descriptions-item label="生涯履历">
                    <span style="color: #003f81" v-if="teacherDetail.eduresume">
                      {{ teacherDetail.eduresume }}
                    </span>
                    <span
                      style="color: #003f81"
                      v-if="!teacherDetail.eduresume"
                    >
                      暂无
                    </span>
                  </el-descriptions-item>
                  <el-descriptions-item label="研究方向">
                    <span style="color: #003f81" v-if="teacherDetail.studydir">
                      {{ teacherDetail.studydir }}
                    </span>
                    <span style="color: #003f81" v-if="!teacherDetail.studydir">
                      暂无
                    </span>
                  </el-descriptions-item>
                </el-descriptions>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      teacherDetail: {},
    };
  },
  mounted() {
    this.getTeacherDetail();
  },
  methods: {
    async getTeacherDetail() {
      const result = await this.$API.reqGetTeacherDetail(this.$route.query.tid);
      if (result.data.code === 200) {
        this.teacherDetail = result.data.data;
      } else {
        this.$message.error("系统异常~ " + result.data.msg);
      }
    },
  },
};
</script>

<style scoped>
.teacherInfo {
  margin: 20px 30px;
  font-size: 20px;
}
.elCol2 {
  margin: 14px 0;
  height: 400px;
}
</style>
