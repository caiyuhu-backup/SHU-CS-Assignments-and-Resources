<template>
  <div>
    <!--  面包屑  -->
    <el-row :gutter="20">
      <el-col :span="20" :offset="2">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
          <el-breadcrumb-item></el-breadcrumb-item>
          <el-breadcrumb-item>帖子</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>

    <div class="all">
      <el-row :gutter="20">
        <el-col :xs="1" :sm="2" :md="2" :lg="3" :xl="3">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :xs="14" :sm="15" :md="14" :lg="14" :xl="12">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span id="title">{{ title }}</span>
              <span class="icon">
                <span style="margin-right: 10px; color: #77b72c; font-weight: bold;">
                  <i class="el-icon-chat-line-round"></i>
                  <span>{{ commentCount }}</span>
                </span>
                <span style="color: #77b72c; font-weight: bold;">
                  <i class="el-icon-view"></i>
                  <span>{{ historyCount }}</span>
                </span>
              </span>
            </div>
            <div class="userInfo">
              <router-link to="/home" class="userLink" style="text-decoration: none;">
                {{ author.username }}
              </router-link>
              {{ create_time }}&nbsp;发表&nbsp;&nbsp;&nbsp;
              最后修改于：{{ model_time }}
            </div>
            <MarkDown :text="content" class="content"/>
            <div class="lcs">
              <a href="javascript:void(0);" class="endEmg" style="text-decoration: none;" @click="addLikes">
                <svg v-if="!isLiked" t="1645938610946" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="2076" width="25" height="25">
                  <path
                      d="M64 483.04V872c0 37.216 30.144 67.36 67.36 67.36H192V416.32l-60.64-0.64A67.36 67.36 0 0 0 64 483.04zM857.28 344.992l-267.808 1.696c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-68.832-155.488-137.568-145.504-60.608 8.8-67.264 61.184-67.264 126.816v59.264c0 76.064-63.84 140.864-137.856 148L256 416.96v522.4h527.552a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824z"
                      p-id="2077" fill="#707070"></path>
                </svg>
                <svg v-if="isLiked" t="1645938610946" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="2076" width="25" height="25">
                  <path
                      d="M64 483.04V872c0 37.216 30.144 67.36 67.36 67.36H192V416.32l-60.64-0.64A67.36 67.36 0 0 0 64 483.04zM857.28 344.992l-267.808 1.696c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-68.832-155.488-137.568-145.504-60.608 8.8-67.264 61.184-67.264 126.816v59.264c0 76.064-63.84 140.864-137.856 148L256 416.96v522.4h527.552a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824z"
                      p-id="2077" fill="#1296db"></path>
                </svg>
                点赞
                <span>({{ likesCount }})</span>
              </a>
              <a href="javascript:void(0);" class="endEmg" style="text-decoration: none;" @click="addFavorite">
                <svg v-if="!isFavorite" t="1645938837889" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="4011" width="25" height="25">
                  <path
                      d="M832.8 63.9H191.2c-17.8 0-32.3 14.5-32.3 32.3V878c0 23.3 23.9 38.9 45.3 29.6L489.8 782l331.4 128.4c21.2 8.2 44-7.4 44-30.1V96.2c-0.1-17.9-14.5-32.3-32.4-32.3z"
                      fill="#8a8a8a" p-id="4012"></path>
                </svg>
                <svg v-if="isFavorite" t="1645938837889" viewBox="0 0 1024 1024" version="1.1"
                     xmlns="http://www.w3.org/2000/svg" p-id="4011" width="25" height="25">
                  <path
                      d="M832.8 63.9H191.2c-17.8 0-32.3 14.5-32.3 32.3V878c0 23.3 23.9 38.9 45.3 29.6L489.8 782l331.4 128.4c21.2 8.2 44-7.4 44-30.1V96.2c-0.1-17.9-14.5-32.3-32.4-32.3z"
                      fill="#1296db" p-id="4012"></path>
                </svg>
                收藏
                <span>({{ favoriteCount }})</span>
              </a>
<!--              <el-popover
                  placement="top-start"
                  title="转发"
                  width="200"
                  trigger="click"
                  content="">
                <p class="url">{{ url }}</p>
                <div style="text-align: right; margin: 0">
                  <el-button @click="addRepost" class="cpButton">点击复制</el-button>
                </div>
                <a href="javascript:void(0);" slot="reference" class="endEmg" style="text-decoration: none;">
                  <img src="@/assets/icon/share.png" slot="reference" alt="转发">
                  转发
                  <span>({{ repostCount }})</span>
                </a>
              </el-popover>-->

            </div>
            <div class="tags">
              相关标签：
              <el-tag size="small" v-for="tag in tagList" :key="tag.id" style="margin-right: 14px;">{{
                  tag.tagName
                }}
              </el-tag>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="0" :sm="0" :md="0" :lg="2" :xl="4">
          <div class="grid-content bg-purple-light"></div>
        </el-col>
        <el-col :xs="8" :sm="6" :md="7" :lg="5" :xl="5">
          <el-card shadow="hover" style="margin-bottom: 25px;">
            <div class="hotTags">
              <svg t="1646135379027"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="27125" width="18" height="18"><path d="M769.568 377.696c-1.648 114.8-48.272 163.648-48.272 163.648C716.896 285.632 464.48 11.52 464.48 11.52c83.408 169.776-285.344 383.232-285.344 689.36 0 192.032 236.864 333.088 355.584 308.88 513.056-104.56 234.848-632.064 234.848-632.064zM512 932.816s-265.92-130.848-7.472-420.8c0 0 128.4 130.08 103.712 251.28 0 0 36.208 3.84 82.304-62.144 0-0.016 10.768 184.096-178.544 231.664z" p-id="27126" fill="#d81e06"></path></svg>
              热门标签</div>
            <el-tag class="hotTag" v-for="(item, index) in tagNameList" :key="item.id" v-if="index < 8">{{item.tagName}}</el-tag>
          </el-card>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="elCol4">
        <el-col :xs="1" :sm="2" :md="2" :lg="3" :xl="3">
          <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :xs="14" :sm="15" :md="14" :lg="14" :xl="12">
          <el-card class="box-card">
            <comment-text
                ref="my_comment"
                :placeholder="placeholder"
                @submit_click="submit_click"
            />
            <h1 class="title">全部评论 {{ commentCount }}</h1>
            <!--     回复区       -->
            <el-dialog
                title="说点什么呢？"
                :visible.sync="dialogVisible"
                width="50%">
              <el-input
                  type="textarea"
                  :placeholder="'回复' + commentParam.username"
                  maxlength="200"
                  v-model="replyText"
                  show-word-limit/>
              <span slot="footer" class="dialog-footer">
                  <el-button @click="giveup_son_click">取 消</el-button>
                  <el-button type="primary" @click="submit_son_click">确 定</el-button>
                </span>
            </el-dialog>
            <!--     评论列表      -->
            <div v-for="comment in commentList" :key="comment.id">
              <div class="comment">
                <!--      头像          -->
                <div>
                  <a href="" class="user-link">
                    <el-avatar style="margin: 8px 8px;" size="medium" :src="comment.author.userImg"></el-avatar>
                  </a>
                </div>
                <div class="content-box">
                  <!--       主评论           -->
                  <div class="comment-main">
                    <div class="user-box">
                      <a href="" target="_blank" style="text-decoration: none;">
                        <span class="username">
                          {{ comment.author.username }}
                        </span>
                      </a>
                      <!---->
                      <time :title="comment.createTime" class="time">
                        {{ comment.createTime }}
                      </time>
                    </div>
                    <div class="comment-content">
                      <MarkDown :text="comment.content" class="content"/>
                    </div>
                    <!---->
                    <div class="action-box">
                      <div class="like">
                        <a href="javascript:void(0);" @click="addCommentLike(comment)">
                          <svg v-if="comment.isLiked === false" t="1646056397659" class="icon" viewBox="0 0 1024 1024"
                               version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2770" width="20" height="20">
                            <path
                                d="M990.80712 399.4c-22.2-23.2-52-36.5-85-37.2H689.50712l12-47.5c16.5-66.6 13.7-136.2-8.4-202.2L686.00712 92.7c-16.4-47.4-61-79.6-111.6-80.2H571.00712c-64.5 0.8-117.9 51.9-121.5 118.4v29.3c0.1 3.7 0.1 7.4 0.1 10.9-2 121.2-102.7 218.3-224.1 216H118.80712l-2.7 0.1C50.30712 392-0.69288 447.5 0.00712 513.1v379.6c0 65.5 53.3 118.8 118.8 118.8h669.7c53.5-0.8 100.5-37.4 114.1-88.4l116.7-407.6c2.8-9.8 4.4-20 4.6-30.3 0.8-32.1-11-62.6-33.1-85.8zM74.90712 892.7V512.6c-0.3-26.1 19.6-48.1 45.4-50.6h79.4v474.5h-81c-24.1 0.1-43.8-19.6-43.8-43.8z m872.4-397.8L830.50712 903.1c-5.2 19.4-22.8 33.2-42.4 33.5H274.70712V458.5c139.5-21.7 247.4-140.6 249.9-286.3 0.1-4.9 0-9.8-0.1-13.4l-0.1-25.9c1.4-25.2 22.3-45.2 47.1-45.5h2.5c18.6 0.2 35.1 12.1 41.3 30.2l7 19.5c17.3 51.6 19.5 106.8 6.4 159.9l-4.2 17.5L612.00712 362l-0.1 0.1V437h292.9c12.1 0.3 23.4 5.3 31.7 14 8.3 8.8 12.8 20.2 12.5 32.3 0 4-0.6 7.9-1.7 11.6z"
                                fill="#8a8a8a" p-id="2771"></path>
                          </svg>
                          <svg v-if="comment.isLiked === true" t="1646056397659" class="icon" viewBox="0 0 1024 1024"
                               version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2770" width="20" height="20">
                            <path
                                d="M990.80712 399.4c-22.2-23.2-52-36.5-85-37.2H689.50712l12-47.5c16.5-66.6 13.7-136.2-8.4-202.2L686.00712 92.7c-16.4-47.4-61-79.6-111.6-80.2H571.00712c-64.5 0.8-117.9 51.9-121.5 118.4v29.3c0.1 3.7 0.1 7.4 0.1 10.9-2 121.2-102.7 218.3-224.1 216H118.80712l-2.7 0.1C50.30712 392-0.69288 447.5 0.00712 513.1v379.6c0 65.5 53.3 118.8 118.8 118.8h669.7c53.5-0.8 100.5-37.4 114.1-88.4l116.7-407.6c2.8-9.8 4.4-20 4.6-30.3 0.8-32.1-11-62.6-33.1-85.8zM74.90712 892.7V512.6c-0.3-26.1 19.6-48.1 45.4-50.6h79.4v474.5h-81c-24.1 0.1-43.8-19.6-43.8-43.8z m872.4-397.8L830.50712 903.1c-5.2 19.4-22.8 33.2-42.4 33.5H274.70712V458.5c139.5-21.7 247.4-140.6 249.9-286.3 0.1-4.9 0-9.8-0.1-13.4l-0.1-25.9c1.4-25.2 22.3-45.2 47.1-45.5h2.5c18.6 0.2 35.1 12.1 41.3 30.2l7 19.5c17.3 51.6 19.5 106.8 6.4 159.9l-4.2 17.5L612.00712 362l-0.1 0.1V437h292.9c12.1 0.3 23.4 5.3 31.7 14 8.3 8.8 12.8 20.2 12.5 32.3 0 4-0.6 7.9-1.7 11.6z"
                                fill="#1296db" p-id="2771"></path>
                          </svg>
                        </a>
                      </div>
                      <div class="amount">
                        {{ comment.praiseNum }}
                      </div>
                      <div class="reply">
                        <a @click="reply(comment, 0)" style="text-decoration: none;">回复</a>
                      </div>
                    </div>
                  </div>
                  <!--     子评论            -->
                  <div class="comment" v-for="(childComment, index) in comment.children" :key="index">
                    <div>
                      <a href="" class="user-link">
                        <el-avatar style="margin: 8px 8px;" size="small" :src="childComment.author.userImg"></el-avatar>
                      </a>
                    </div>
                    <div class="content-box">
                      <div class="comment-main">
                        <div class="user-box">
                          <a target="_blank" rel="" style="text-decoration: none;">
                            <span class="username">
                              {{ childComment.author.username }}
                            </span>
                          </a>
                          <div v-if="childComment.author.username" style="display: inline;">
                            <span class="reply">回复</span>
                            <a target="_blank" rel="" style="text-decoration: none;">
                            <span class="username">
                              {{ childComment.toUser.username }}
                            </span>
                            </a>
                          </div>
                          <!---->
                          <time :title="childComment.createTime" class="time">
                            {{ childComment.createTime }}
                          </time>
                        </div>
                        <div class="comment-content">
                          <MarkDown :text="childComment.content" class="content"/>
                        </div>
                        <!---->
                        <div class="action-box">
                          <div class="like">
                            <a href="javascript:void(0);" @click="addCommentLike(childComment)">
                              <svg v-if="childComment.isLiked === false" t="1646056397659" class="icon"
                                   viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2770"
                                   width="20" height="20">
                                <path
                                    d="M990.80712 399.4c-22.2-23.2-52-36.5-85-37.2H689.50712l12-47.5c16.5-66.6 13.7-136.2-8.4-202.2L686.00712 92.7c-16.4-47.4-61-79.6-111.6-80.2H571.00712c-64.5 0.8-117.9 51.9-121.5 118.4v29.3c0.1 3.7 0.1 7.4 0.1 10.9-2 121.2-102.7 218.3-224.1 216H118.80712l-2.7 0.1C50.30712 392-0.69288 447.5 0.00712 513.1v379.6c0 65.5 53.3 118.8 118.8 118.8h669.7c53.5-0.8 100.5-37.4 114.1-88.4l116.7-407.6c2.8-9.8 4.4-20 4.6-30.3 0.8-32.1-11-62.6-33.1-85.8zM74.90712 892.7V512.6c-0.3-26.1 19.6-48.1 45.4-50.6h79.4v474.5h-81c-24.1 0.1-43.8-19.6-43.8-43.8z m872.4-397.8L830.50712 903.1c-5.2 19.4-22.8 33.2-42.4 33.5H274.70712V458.5c139.5-21.7 247.4-140.6 249.9-286.3 0.1-4.9 0-9.8-0.1-13.4l-0.1-25.9c1.4-25.2 22.3-45.2 47.1-45.5h2.5c18.6 0.2 35.1 12.1 41.3 30.2l7 19.5c17.3 51.6 19.5 106.8 6.4 159.9l-4.2 17.5L612.00712 362l-0.1 0.1V437h292.9c12.1 0.3 23.4 5.3 31.7 14 8.3 8.8 12.8 20.2 12.5 32.3 0 4-0.6 7.9-1.7 11.6z"
                                    fill="#8a8a8a" p-id="2771"></path>
                              </svg>
                              <svg v-if="childComment.isLiked === true" t="1646056397659" class="icon"
                                   viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2770"
                                   width="20" height="20">
                                <path
                                    d="M990.80712 399.4c-22.2-23.2-52-36.5-85-37.2H689.50712l12-47.5c16.5-66.6 13.7-136.2-8.4-202.2L686.00712 92.7c-16.4-47.4-61-79.6-111.6-80.2H571.00712c-64.5 0.8-117.9 51.9-121.5 118.4v29.3c0.1 3.7 0.1 7.4 0.1 10.9-2 121.2-102.7 218.3-224.1 216H118.80712l-2.7 0.1C50.30712 392-0.69288 447.5 0.00712 513.1v379.6c0 65.5 53.3 118.8 118.8 118.8h669.7c53.5-0.8 100.5-37.4 114.1-88.4l116.7-407.6c2.8-9.8 4.4-20 4.6-30.3 0.8-32.1-11-62.6-33.1-85.8zM74.90712 892.7V512.6c-0.3-26.1 19.6-48.1 45.4-50.6h79.4v474.5h-81c-24.1 0.1-43.8-19.6-43.8-43.8z m872.4-397.8L830.50712 903.1c-5.2 19.4-22.8 33.2-42.4 33.5H274.70712V458.5c139.5-21.7 247.4-140.6 249.9-286.3 0.1-4.9 0-9.8-0.1-13.4l-0.1-25.9c1.4-25.2 22.3-45.2 47.1-45.5h2.5c18.6 0.2 35.1 12.1 41.3 30.2l7 19.5c17.3 51.6 19.5 106.8 6.4 159.9l-4.2 17.5L612.00712 362l-0.1 0.1V437h292.9c12.1 0.3 23.4 5.3 31.7 14 8.3 8.8 12.8 20.2 12.5 32.3 0 4-0.6 7.9-1.7 11.6z"
                                    fill="#1296db" p-id="2771"></path>
                              </svg>
                            </a>
                          </div>
                          <div class="amount">
                            {{ childComment.praiseNum }}
                          </div>
                          <div class="reply">
                            <a style="text-decoration: none;" @click="reply(childComment, 1, comment)">回复</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
            </div>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.current"
                :page-sizes="[20, 15, 10, 5, 3, 1]"
                :page-size="page.size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </el-card>
        </el-col>
        <el-col :xs="0" :sm="0" :md="0" :lg="2" :xl="4">
          <div class="grid-content bg-purple-light"></div>
        </el-col>
        <el-col :xs="8" :sm="6" :md="7" :lg="5" :xl="5">
          <el-card shadow="hover">
            <div class="hotTags">更多文章</div>
            <ul>
              <li style="font-size: 12px;" v-for="item in moreArticle" :key="item.id" class="liLink">
                <router-link style="color: #4cb9fc" :to="{ path: '/article', query: { id: item.id}}">{{ item.title }}</router-link>
              </li>
            </ul>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import MarkDown from '@/components/MarkDown/MarkDown'
import CommentText from "@/components/CommentText/CommentText"
import {getToken} from "@/utils/token"
import Vue from "vue"
import Clipboard from 'clipboard'

export default {
  name: "index",
  components: {
    MarkDown, CommentText
  },
  data() {
    return {
      content: '',
      comments: '',
      title: '',
      likesCount: 0,
      isLiked: false,
      repostCount: 0,
      favoriteCount: 0,
      isFavorite: false,
      historyCount: 1,
      commentCount: 0,
      commentList: [],
      tagList: [],
      author: {},
      create_time: '',
      model_time: '',
      placeholder: "想说点什么？评论支持markdown语法。",//默认文字提示。
      // 展示回复框
      dialogVisible: false,
      replyText: '',
      // 回复用的comment
      commentParam: {
        userId: this.$store.state.user.userInfo.userId,
        articleId: this.$route.query.id,
        parentId: 0,
        toUserId: 0
      },
      // 分页信息
      page: {
        current: 1,
        size: 10
      },
      pages: 0,
      total: 0,
      // 更多
      moreArticle: [],
      tagNameList: [],
      url: '1'
    }
  },
  methods: {
    //点击评论按钮后，触发的事件
    async submit_click() {
      // 先判断是否登录
      if (!getToken()) {
        this.$message.warning('未登录，请先登录~')
        await this.$router.push('/login')
        return
      }
      const result = await this.$API.reqAddParentComment({
        userId: this.$store.state.user.userInfo.userId,
        articleId: this.$route.query.id,
        parentId: 0,
        toUserId: 0,
        content: this.comment_text
      })
      if (result.data.code === 200) {
        this.$refs.my_comment.success_submit("评论成功", 1500)
        await this.getComment()
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
        this.$refs.my_comment.err_submitFn("评论失败", 1500)
      }
      //你可以在这里验证用户输入的格式。
      //若格式错误可调用此函数：
      //this.$refs.my_comment.err_submitFn("格式错误",1500)

      //你可以在这儿请求AJAX
      //失败回调：
      // this.$refs.my_comment.err_submitFn("评论失败",1500)
      //成功回调
      // this.$refs.my_comment.success_submit("评论成功", 1500)
    },
    async submit_son_click() {
      // 先判断是否登录
      if (!getToken()) {
        this.$message.warning('未登录，请先登录~')
        await this.$router.push('/login')
        return
      }
      if (this.replyText === '') {
        this.$message.warning('评论区不能为空~')
        return
      }
      this.commentParam.content = this.replyText
      const result = await this.$API.reqAddParentComment(this.commentParam)
      if (result.data.code === 200) {
        this.$message.success('评论成功~')
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
      }
      this.replyText = ''
      this.dialogVisible = false
      await this.getComment()
    },
    giveup_son_click() {
      this.dialogVisible = false
    },
    reply(comment, index, comment_) {
      this.dialogVisible = true
      if (index === 0) {
        this.commentParam.parentId = comment.id
        this.commentParam.toUserId = comment.author.userId
        this.commentParam.username = comment.author.username
      } else {
        this.commentParam.parentId = comment_.id
        this.commentParam.username = comment.author.username
      }
    },
    async getArticle() {
      const result = await this.$API.reqGetArticleById(this.$route.query.id)
      if (result.data.code === 200) {
        this.title = result.data.data.article.title
        this.content = result.data.data.article.content
        this.author = result.data.data.author
        this.tagList = result.data.data.tagsList
        await this.getMoreArticle()
        this.create_time = result.data.data.article.createTime
        this.model_time = result.data.data.article.modifyTime
        this.likesCount = result.data.data.likesCount
        this.repostCount = result.data.data.repostCount
        this.favoriteCount = result.data.data.favoriteCount
        this.historyCount = result.data.data.historyCount
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
        await this.$router.push({path: '/404'})
      }
    },
    async getAUCondition() {
      const result = await this.$API.reqGetAUCondition({
        aid: this.$route.query.id,
        uid: this.$store.state.user.userInfo.userId
      })
      if (result.data.code === 200) {
        this.isLiked = result.data.data.isLiked
        this.isFavorite = result.data.data.isFavorit
      } else {
        this.$message.error(result.data.msg)
      }
    },
    async getComment() {
      const result = await this.$API.reqGetCommentById({
        articleId: this.$route.query.id,
        page: this.page,
        userId: this.$store.state.user.userInfo.userId
      })
      if (result.data.code === 200) {
        this.commentCount = result.data.data.commentCount
        this.commentList = result.data.data.commentVoList
        this.pages = result.data.data.pages
        this.total = result.data.data.total
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
      }
    },
    async addCommentLike(comment) {
      if (!getToken()) {
        await this.$message.warning('当前尚未登录，请先登录...')
        await this.$router.push('/login')
      }
      if (comment.isLiked) {
        const result = await this.$API.reqRevokeCommentLike({
          cid: comment.id,
          uid: this.$store.state.user.userInfo.userId
        })
        if (result.data.code === 200) {
          this.$notify.warning({
            title: '警告',
            message: '取消成功~'
          })
          await this.getComment()
        } else {
          this.$message.error('系统异常~ ' + result.data.msg)
        }
        return
      }
      const result = await this.$API.reqAddCommentLike({
        aid: this.$route.query.id,
        cid: comment.id,
        uid: this.$store.state.user.userInfo.userId
      })
      if (result.data.code === 200) {
        this.$notify.success({
          title: '成功',
          message: '点赞成功~'
        })
        await this.getComment()
      } else {
        this.$message.error('系统异常~ ' + result.data.msg)
      }
    },
    handleSizeChange(size) {
      this.page.size = size
      this.getComment()
    },
    handleCurrentChange(current) {
      this.page.current = current
      this.getComment()
    },
    async addLikes() {
      if (!getToken()) {
        this.$message.warning('当前尚未登录，请先登录')
        await this.$router.push('/login')
        return
      }
      if (!this.isLiked) {
        const result = await this.$API.addLikes({
          aid: this.$route.query.id,
          uid: this.$store.state.user.userInfo.userId
        })
        if (result.data.code === 200) {
          this.isLiked = true
          this.likesCount = result.data.data.likeCount
          this.$notify.success({
            title: '成功',
            message: '点赞成功！',
          })
        } else {
          this.$notify.error({
            title: '错误',
            message: '点赞失败~ ' + result.data.msg
          })
        }
      } else {
        const result = await this.$API.revokeLikes({
          aid: this.$route.query.id,
          uid: this.$store.state.user.userInfo.userId
        })
        if (result.data.code === 200) {
          this.isLiked = false
          this.likesCount = result.data.data.likeCount
          this.$notify.warning({
            title: '警告',
            message: '已取消点赞~',
          })
        } else {
          this.$notify.error({
            title: '错误',
            message: '取消失败~ ' + result.data.msg
          })
        }
      }
    },
    async addFavorite() {
      if (!getToken()) {
        this.$message.warning('当前尚未登录，请先登录')
        await this.$router.push('/login')
        return
      }
      if (!this.isFavorite) {
        const result = await this.$API.addFavorite({
          aid: this.$route.query.id,
          uid: this.$store.state.user.userInfo.userId
        })
        if (result.data.code === 200) {
          this.isFavorite = true
          this.favoriteCount = result.data.data.favoriteCount
          this.$notify.success({title: '成功', message: '收藏成功！'})
        } else {
          this.$notify.error({title: '失败', message: '收藏失败~' + result.data.msg})
        }
      } else {
        const result = await this.$API.revokeFavorite({
          aid: this.$route.query.id,
          uid: this.$store.state.user.userInfo.userId
        })
        if (result.data.code === 200) {
          this.isFavorite = false
          this.favoriteCount = result.data.data.favoriteCount
          this.$notify.warning({title: '警告', message: '已取消收藏~'})
        } else {
          this.$notify.error({title: '失败', message: '取消收藏失败~' + result.data.msg})
        }
      }
    },
    async addRepost() {
       /* const url = this.$refs.url;
        url.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        alert("已复制好，可贴粘。");*/
      /*const result = await this.$API.addRepost({
        aid: this.$route.query.id,
        uid: this.$store.state.user.userInfo.userId
      })
      if (result.data.code === 200) {
        this.repostCount = result.data.data.repostCount
      }*/
    },
    async getMoreArticle() {
      const result = await this.$API.reqSearchArticle({
        text: this.tagList[0].tagName,
        current: 1,
        size: 5
      })
      if (result.data.code === 200) {
        this.moreArticle = result.data.data.articleBriefParams
      } else {
        this.$message.error(result.data.msg)
      }
    },
    async getTagNameList() {
      const result = await this.$API.reqGetTagNameList()
      if (result.data.code === 200) {
        this.tagNameList = result.data.data
      }
    }
  },
  mounted() {
    const loading = Vue.prototype.$loading({
      lock: true, // 是否锁屏
      text: '拼命加载中', // 加载动画的文字
      spinner: 'el-icon-loading', // 引入的loading图标
      background: 'hsla(0,0%,100%,.9)' // 背景颜色
    });
    this.getArticle()
    this.getAUCondition()
    this.getComment()
    this.$API.reqAddHistory({
      aid: this.$route.query.id,
      uid: this.$store.state.user.userInfo.userId
    })
    this.getTagNameList()
    loading.close();
  },
  computed: {
    comment_text() {//获取子组件的评论内容。
      return this.$refs.my_comment.insert_comment.comment_text;
    }
  }
}
</script>

<style scoped>
#title {
  font-size: 32px;
  line-height: 36px;
}

.icon {
  font-size: 17px;
  float: right;
  margin: 10px 12px;
}

.userInfo {
  background-color: #f8f8f8;
  width: 645px;
  height: 40px;
  line-height: 40px;
  font-size: 13px;
}

.userLink {
  margin: auto 12px;
  color: #4cb9fc;
}

.lcs {
  text-align: center;
  margin: 22px auto;
}

.endEmg {
  margin: auto 20px;
  font-size: 15px;
  font-weight: bold;
  color: #77b72c;
}

.tags {
  font-size: 14px;
}

.hotTags {
  font-size: 16px;
  font-weight: 700;
  margin: 0 auto 15px 0;
}

.hotTag {
  margin: 6px 5px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.liLink {
  font-size: 13px;
  margin: 8px auto;
}

.elCol4 {
  margin: 10px 0;
}

.title {
  position: relative;
  line-height: 30px;
  font-weight: 600;
  font-size: 18px;
  color: #252933;
  width: 100%;
  justify-content: space-between;
  padding-bottom: 8px;
}

.comment {
  display: flex;
  padding: 1px 0;
}

.user-link {
  flex: 0 0 auto;
}

a {
  text-decoration: none;
  cursor: pointer;
  color: #909090;
}

.content-box {
  flex: 1 1 auto;
  margin-left: 3px;
}

.user-box {
  margin: 4px auto;
}

.username {
  max-width: 128px;
  font-size: 14px;
  color: #00a1d6
}

.comment-main {
  position: relative;
}

.comment-content {
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
}

.action-box {
  display: flex;
  align-items: center;
}

.like {

}

.amount {
  font-size: 5px;
  margin-left: -8px;
  margin-right: 17px;
}

.reply {
  font-size: 5px;
  color: #42b983;
  font-weight: bold;
}

.moreReply {
  margin: 2px 5px;
}

.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 100%;
  margin: 9px 0;
}
</style>
