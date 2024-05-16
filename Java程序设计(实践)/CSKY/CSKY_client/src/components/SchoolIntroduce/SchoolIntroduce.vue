<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <h1>学校介绍</h1>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col class="elCol1" :span="14" :push="2">
        <el-card shadow="always">
          <div>
            <MarkDown :text="introduce" class="content"/>
          </div>
        </el-card>
      </el-col>
      <el-col class="elCol2" :span="10" :push="2">
        <el-card shadow="always">
          <el-descriptions title="学校信息" direction="vertical" :column="1" size="medium">
            <el-descriptions-item label="学校地址"><span style="color: #77b72c">{{address}}</span></el-descriptions-item>
            <el-descriptions-item label="联系电话"><span style="color: #77b72c">{{phone}}</span>
            </el-descriptions-item>
            <el-descriptions-item label="学校官网"><a rel="nofollow" :href="officialSite" target="_blank"
                                                  style="color: #4cb9fc; text-decoration: none;">{{sname}}</a>
            </el-descriptions-item>
            <el-descriptions-item label="研究生院"><a rel="nofollow" :href="postgraduateSite" target="_blank"
                                                  style="color: #4cb9fc; text-decoration: none;">{{sname}}研究生院官网</a>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MarkDown from '@/components/MarkDown/MarkDown'

export default {
  name: "index",
  props: ['schoolIntroduce'],
  components: {
    MarkDown
  },
  data() {
    return {
      introduce: '',
      postgraduateSite: '',
      address: '',
      phone: '',
      officialSite: '',
      sname: ''
    }
  },
  watch: {
    schoolIntroduce(sc) {
      this.introduce = sc.introduction
      this.postgraduateSite = sc.postgraduateSite
      this.address = sc.address
      this.phone = sc.phone
      this.officialSite = sc.officialSite
      this.sname = sc.sname
    }
  }
}
</script>

<style scoped>
.elCol1 {
  margin: 14px 0;
  /*width: 700px;*/
  /*float: left;*/
}

.elCol2 {
  width: 348px;
  margin: 14px 6px;
  height: 400px;
}

h1 {
  font-size: 22px;
}
</style>
