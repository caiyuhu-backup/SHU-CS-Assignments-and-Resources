<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-page-header @back="goBack" content="详情页面">
        </el-page-header>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :xs="2" :sm="3" :md="2" :lg="3" :xl="4">
        <div class="grid-content bg-purple"></div>
      </el-col>
      <el-col :xs="14" :sm="12" :md="14" :lg="13" :xl="12">
        <el-card shadow="always" class="search1">
          <div class="search-div">
            <el-input v-model="keyWord" class="search-input" clearable placeholder="请输入关键字" size="small"></el-input>
            <el-button @click="toSearch" size="small" type="primary" style="margin-left: 10px;">点击搜索</el-button>
          </div>
          <el-empty :image-size="200" v-if="total === 0"></el-empty>
          <el-card shadow="never" class="search2" v-for="item in result" :key="item.id">
            <router-link :to="{path: '/article', query: {id: item.id}}" style="text-decoration: none;"><h1 class="search-title">{{ item.title }}</h1></router-link>
            <div class="search-content">
              <MarkDown :text="item.content" class="search-content"/>
            </div>
            <div class="search-left">
              <div class="icon-like">
                <svg style="margin-bottom: -3px;" t="1645944239121" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="9980" width="16" height="16">
                  <path
                      d="M857.28 344.992h-264.832c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-71.808-153.792-140.544-143.808-60.608 8.8-89.536 59.904-89.536 125.536v59.296c0 76.064-58.208 140.928-132.224 148.064l-117.728-0.192A67.36 67.36 0 0 0 64 483.04V872c0 37.216 30.144 67.36 67.36 67.36h652.192a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824zM128 872V483.04c0-1.856 1.504-3.36 3.36-3.36H208v395.68H131.36A3.36 3.36 0 0 1 128 872z m767.328-417.088l-73.728 388.96a38.72 38.72 0 0 1-38.048 31.488H272V476.864a213.312 213.312 0 0 0 173.312-209.088V208.512c0-37.568 12.064-58.912 34.72-62.176 27.04-3.936 67.36 38.336 67.36 80.48 0 37.312-9.504 84-28.864 139.712a32 32 0 0 0 30.24 42.496h308.512a38.72 38.72 0 0 1 38.048 45.888z"
                      p-id="9981" fill="#999999"></path>
                </svg>
                {{ item.likeCount }}
              </div>
              <div class="icon-comment">
                <i class="el-icon-chat-line-round"></i>
                {{ item.commentCount }}
              </div>
            </div>
            <div class="search-right">
              <div class="search-author-name">
                {{ item.authorName }}
              </div>
              <div class="search-time">
                {{ item.createTime }}
              </div>
            </div>
          </el-card>
          <el-pagination
              style="text-align: center; margin-top: 10px; margin-bottom: 20px;"
              background
              :page-size="size"
              :current-page="current"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="total">
          </el-pagination>
        </el-card>
      </el-col>
      <el-col :xs="6" :sm="6" :md="6" :lg="5" :xl="4">
        <el-card shadow="always" class="search1">
          <h1>相关搜索</h1>
          <el-divider></el-divider>
          <el-tag
              v-for="(item, index) in searchHistoryList"
              :key="index"
              class="search-tag"
              type="warning"
              :hit="true"
              effect="dark">
            {{item}}
          </el-tag>
        </el-card>
<!--        <el-card shadow="always" class="search1">
          <h1>全站热搜榜</h1>
          <el-divider></el-divider>
          <div>
            <ul>
              <li>

              </li>
            </ul>
          </div>
        </el-card>-->
      </el-col>
      <el-col :xs="2" :sm="3" :md="3" :lg="3" :xl="2">
        <div class="grid-content bg-purple-light"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import MarkDown from "@/components/MarkDown/MarkDown"
import Vue from "vue";
export default {
  name: "Search",
  components: {
    MarkDown
  },
  data() {
    return {
      current: 1,
      size: 10,
      total: 0,
      keyWord: '',
      result: [],
      searchHistoryList: []
    }
  },
  mounted() {
    this.keyWord = this.$route.params.keyWord

    this.toSearch()
  },
  methods: {
    async search() {
      const result = await this.$API.reqSearchArticle({
        uid: this.$store.state.user.userInfo.userId || '',
        text: this.keyWord,
        current: this.current,
        size: this.size
      })
      if (result.data.code === 200 && result.data.data !== null) {
        this.current = result.data.data.current
        this.size = result.data.data.size
        this.total = result.data.data.total
        this.result = result.data.data.articleBriefParams
      } else {
        // this.$message.error(result.data.msg)
      }
    },
    currentChange(current) {
      this.current = current
      this.search()
    },
    goBack() {
      this.$router.push(this.$route.params.fromRouter)
    },
    async getSearchHistory() {
      if (this.$store.state.user.userInfo.userId) {
        const result = await this.$API.reqGetSearchHistory(this.$store.state.user.userInfo.userId)
        if (result.data.code === 200) {
          console.log(result.data.data)
          this.searchHistoryList = result.data.data
        }
      }
    },
    async toSearch() {
      const loading = Vue.prototype.$loading({
        lock: true, // 是否锁屏
        text: '拼命加载中', // 加载动画的文字
        spinner: 'el-icon-loading', // 引入的loading图标
        background: 'rgba(0, 0, 0, 0.8)' // 背景颜色
      })
      this.result = []
      await this.search()
      await this.getSearchHistory()
      loading.close()
    }
  },
  watch: {
    keyWord() {

    }
  }
}
</script>

<style scoped>
.search1 {
  margin: 21px 0 0 0;
}

.search-tag {
  margin-top: 10px;
  margin-right: 10px;
}

.search-div {
  text-align: center;
  vertical-align: middle;
}

.search-input {
  width: 257px;
  margin-bottom: 6px;
}

.search2 {
  margin: 6px 0 0 0;
}

.search-title {
  color: #42b983;
  margin: -5px 0 7px 0;
  font-size: 18px;
  vertical-align: top;
}

.search-content {
  font-size: 11px;
  color: #595963;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  display: -moz-box;
  -moz-line-clamp: 2;
  -moz-box-orient: vertical;
  word-wrap: break-word;
  word-break: break-all;
  white-space: normal;
}

.search-left {
  float: left;
  margin-top: 6px;
  margin-bottom: 10px;
}

.search-right {
  float: right;
  margin-top: 6px;
  margin-bottom: 10px;
}

.icon-like {
  color: #999999;
  display: inline;
}

.icon-comment {
  color: #999999;
  margin-left: 79px;
  display: inline;
}

.search-author-name {
  color: #999999;
  margin-right: 73px;
  display: inline;
}

.search-time {
  color: #999999;
  display: inline;
}
</style>