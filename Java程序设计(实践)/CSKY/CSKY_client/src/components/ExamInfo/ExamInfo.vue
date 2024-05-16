<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <h1>招考信息</h1>
      </el-col>
    </el-row>
    <el-row style="height: auto" :gutter="20">
      <el-col class="elCol1" :span="14" :push="2">
        <el-card shadow="always">
          <ul>
            <li class="liLink" v-for="item in recruitInfoVoList" :key="item.articleId">
              <router-link :to="{
                path: '/article',
                query: {
                  id: item.articleId
                }
              }" style="text-decoration: none; color: #049cdb">{{item.title}}</router-link>
            </li>
          </ul>
          <el-pagination
              style="text-align: center"
              :page-size="size"
              small
              layout="prev, pager, next"
              @current-change="currentChange"
              :total="total">
          </el-pagination>
        </el-card>
      </el-col>
      <el-col class="elCol2" :span="10" :push="2">
        <el-card shadow="always">
          <el-descriptions direction="vertical" :column="1" size="medium" :colon="false">
            <el-descriptions-item>
              <a style="color: #4cb9fc;" :href="recruitment.indexOf('URL: ') === -1 ? recruitment : recruitment.slice(4)" target="_blank">
                招生简章
              </a>
            </el-descriptions-item>
            <el-descriptions-item>
              <router-link style="color: #4cb9fc;"
                   :to="{
                    path: '/scoreLine',
                    query: {
                      sid: sid
                    }
                  }">
                分数线
              </router-link>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "index",
  props: ['schoolIntroduce'],
  data() {
    return {
      sid: 0,
      sname: '',
      recruitment: '',
      recruitInfoVoList: [],
      current: 0,
      size: 0,
      total: 0,
    }
  },
  watch: {
    async schoolIntroduce(sc) {
      this.sid = sc.sid
      this.sname = sc.sname
      this.recruitment = sc.recruitment

      await this.getRecruitInfo()
    }
  },
  methods: {
    currentChange(current) {
      this.current = current
      this.getRecruitInfo()
    },
    async getRecruitInfo() {
      const result = await this.$API.reqGetRecruitInfo({
        schoolId: this.sid,
        page: {
          current: this.current,
          size: 10
        }
      })
      if (result.data.code === 200) {
        this.recruitInfoVoList = result.data.data.recruitInfoVoList
        this.current = result.data.data.current
        this.size = result.data.data.size
        this.total = result.data.data.total
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
      }
    }
  }
}
</script>

<style scoped>
h1 {
  font-size: 22px;
}
.elCol1 {
  margin: 14px 0;
  /*width: 700px;*/
  /*float: left;*/
}
.liLink {
  font-size: 15px;
  margin: 12px auto;
}
.elCol2 {
  width: 348px;
  margin: 14px 6px;
  height: 400px;
}
</style>
