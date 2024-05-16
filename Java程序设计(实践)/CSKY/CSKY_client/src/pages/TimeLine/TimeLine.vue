<template>
  <el-row :gutter="20">
    <el-col :span="20" :offset="2">
      <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
        <el-breadcrumb-item></el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>考研时间线</el-breadcrumb-item>
      </el-breadcrumb>
      <el-row class="all">
        <el-col>
          <el-card shadow="always">
            <el-timeline>
              <el-timeline-item timestamp="1-2月" placement="top">
                <el-card>
                  <h4>在线估分</h4>
                  <p>
                    <router-link :to="{
                        path: '/article',
                        query: {
                          id: 358
                        }
                      }"
                                 style="color: #4cb9fc;">初试后关注的八件事
                    </router-link>
                  </p>
                  <p>
                    <router-link :to="{
                        path: '/article',
                        query: {
                          id: 359
                        }
                      }"
                                 style="color: #4cb9fc;">如何预判分数及复试准备
                    </router-link>
                  </p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2-3月" placement="top">
                <el-card>
                  <h4>成绩查询</h4>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="3月上旬" placement="top">
                <el-card>
                  <h4>34所复试线</h4>
                  <p>
                    <router-link :to="{
                        path: '/scoreLineList',
                        query: {
                          id: 359
                        }
                      }"
                      style="color: #4cb9fc;">历年复试分数线汇总
                    </router-link>
                  </p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="3月中旬" placement="top">
                <el-card>
                  <h4>考研国家线</h4>
                  <p>历年考研国家线</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="3-4月" placement="top">
                <el-card>
                  <h4>考研复试</h4>
                  <p>复试信息</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="3月18-4月30" placement="top">
                <el-card>
                  <h4>考研调剂</h4>
                  <p>考研调剂信息</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="3-6月" placement="top">
                <el-card>
                  <h4>择校择业</h4>
                  <p>如何择校择业</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="6-7月" placement="top">
                <el-card>
                  <h4>录取通知书</h4>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="7-8月" placement="top">
                <el-card>
                  <h4>考研大纲</h4>
                  <p>
                    <router-link :to="{
                        path: '/examQuestion',
                        query: {
                          id: 359
                        }
                      }"
                      style="color: #4cb9fc;">专业课复习资料
                    </router-link>
                  </p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="8-10月" placement="top">
                <el-card>
                  <h4>招生简章</h4>
                  <p>简章</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="9-10月" placement="top">
                <el-card>
                  <h4>考研报名</h4>
                  <p>考研预报名时间</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="11月" placement="top">
                <el-card>
                  <h4>现场确认</h4>
                  <p>
                    <router-link :to="{
                        path: '/article',
                        query: {
                          id: 361
                        }
                      }"
                                 style="color: #4cb9fc;">现场确认所需材料
                    </router-link>
                  </p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="11月15-12月14" placement="top">
                <el-card>
                  <h4>冲刺备考</h4>
                  <p>冲刺备考攻略</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="12月14-25日" placement="top">
                <el-card>
                  <h4>准考证打印</h4>
                  <p>打印时间：14-25日</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="12月23-24日" placement="top">
                <el-card>
                  <h4>考研时间</h4>
                  <p>
                    <router-link :to="{
                        path: '/article',
                        query: {
                          id: 360
                        }
                      }"
                                 style="color: #4cb9fc;">初试各科时间安排
                    </router-link>
                  </p>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </el-card>
        </el-col>
      </el-row>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "TimeLine"
}
</script>

<style scoped>

</style>
