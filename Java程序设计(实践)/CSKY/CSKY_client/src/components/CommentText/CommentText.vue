<template>
  <div class="b_comment_bg">
    <div class="comment-content">
      <div class="comment-head">
        <h3 class="comment_title">评论</h3>
        <div class="area-bg">
          <el-input id="comment_text" class="comment-text" resize="none"
                    type="textarea"
                    :rows="3"
                    maxlength=200
                    ref="comment_text"
                    :placeholder="placeholder"
                    v-model="insert_comment.comment_text"
                    @focus="comment_focus()"
                    @blur="comment_blur()"
          >
          </el-input>
        </div>
        <div class="submit-bg">
          <el-popover
              placement="bottom-start"
              trigger="click"
              ref="emoji_popover"
              v-model="emoji_visible"
              style="padding:0;"
              popper-class="no_padding"
              visible-arrow="false"
          >
            <!--添加padding:0px-->
            <div class="emoji-box" style="display: block; left: 86px; top: 209px;">
              <div class="emoji-title"><span>颜文字</span></div>
              <div class="emoji-wrap">
                <a @click="add_emoji(item)" v-for="(item, index) in emoji_list" class="emoji-list emoji-text"
                   :data-emoji-text="item" :key="index">{{ item }}
                </a>
              </div>
            </div>
            <el-button @click="emoji_visible != emoji_visible" slot="reference" size="small" plain><i
                class="el-icon-star-on"></i>表情
            </el-button>
          </el-popover>
          <div class="submit_area">
            <el-popover :open-delay=500
                        placement="top"
                        popper-class="comment-button-default"
                        ref="popover_comment"
                        visible-arrow="false"
                        trigger="manual"
                        :content="comment_msg">
              <el-button v-popover:popover_comment ref="comment_Button" :loading="buttom_loading" slot="reference"
                         class="a"
                         @click="comment_submit" type="primary" size="medium"
                         style="position:relative;maring-right:0px;padding-left:45px;padding-right:45px;">
                {{ comment_button_text }}
              </el-button>
            </el-popover>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentText",
  data() {
    return {
      // 评论。
      insert_comment: {
        "comment_name": "",
        "comment_text": "",
      },
      //颜文字选项。
      emoji_visible: false, //是否显示颜文字弹出框
      //提交评论的按钮。
      buttom_loading: false, //是否显示“加载中”
      comment_button_text: "评论",//评论按钮上的文字

      //提示的文字
      comment_msg: '发送成功',
      emoji_list: ['(⌒▽⌒)', '（￣▽￣）', '(=・ω・=)', '(｀・ω・´)', '(〜￣△￣)〜', '(･∀･)', '(￣3￣)', '╮(￣▽￣)╭', '( ´_ゝ｀)', '←_←', '→_→', '(<_<)', '(>_>)', '(;¬_¬)', '("▔□▔)/', '(ﾟДﾟ≡ﾟдﾟ)!?', 'Σ(ﾟдﾟ;)', '(｡･ω･｡)', '(´･_･`)', '（￣へ￣）', '(╯°口°)╯(┴—┴', '_(:3」∠)_']
    }
  },
  props: {
    placeholder: {
      type: String,
      default: "想说点什么？"
    }
  },
  methods: {
    comment_focus() {
      document.getElementById('comment_text').setAttribute('style', 'background-color:white;border-color:#00a1d6');
    },
    comment_blur() {
      document.getElementById('comment_text').setAttribute('style', '');
    },
    add_emoji(emoji) {
      //获得焦点
      let tc = document.getElementById('comment_text');
      tc.focus();
      //插入
      let tclen = tc.value.length;
      //计算表情长度。
      let emoji_length = emoji.length;
      tc.focus();
      if (typeof document.selection != "undefined") {
        document.selection.createRange().text = emoji;
      } else {
        // if (document.selection) {
        //记录光标的起始位置
        var position = tc.selectionStart;
        //计算表情长度。
        emoji_length = emoji.length;
        position = position + emoji_length;

        tc.value = tc.value.substr(0, tc.selectionStart) + emoji + tc.value.substring(tc.selectionStart, tclen);

        // alert(tc.value );
        this.insert_comment.comment_text = tc.value;
        tc.selectionStart = tc.selectionEnd = position;
      }
      //隐藏弹出的popover
      this.emoji_visible = !this.emoji_visible;
    },
    comment_submit() {
      // console.log("子组件开始验证格式。");
      //验证验证码
      //判断条件比较多的话（对输入的字符数量做限制等等，可以把方法封装下函数）  【注意】不要用document,getElementbyclass。绑定v-model或者用refs
      if (this.insert_comment.comment_text === "") {
        // console.log("评论为空");
        // console.log(document.getElementsByClassName("comment-button-default")[0]);
        document.getElementsByClassName("comment-button-default")[0].style.visibility = "visible";
        document.getElementsByClassName("comment-button-default")[0].style.backgroundColor = "red";
        document.getElementsByClassName("comment-button-default")[0].style.color = "#fff";
        document.getElementsByClassName("comment-button-default")[0].classList.add("comment-button-error");
        // document.getElementsByClassName("comment-button-default")[0].innerHTML = "评论不能为空哦~";
        this.comment_msg = "评论不能为空哦~";
        this.$refs['popover_comment'].doShow();
        //提示信息显示1.5秒。
        window.setTimeout(function () {
          document.getElementsByClassName('comment-button-default')[0].style.visibility = 'hidden'
        }, 1500);
        // this.$refs['popover_comment'].doClose();
      }
      //全部验证通过
      else {
        this.start_loading();
        document.getElementsByClassName("a")[0].style.paddingRight = "45px";
        document.getElementsByClassName("a")[0].style.paddingLeft = "45px";
        this.$emit('submit_click');
      }
    },
    comment_send() {
      //判断是否评论成功。
      //成功。
      // this.$refs.comment_Button.loading = false;
    },
    //接受子组件（验证组件）的传值，改变验证状态。
    verify_pass() {
      this.if_verify_pass = true;
    },

    //-----【父组件调用的方法】-----
    //验证失败 或 AJAX提交失败。
    err_submitFn(err_msg, time) {
      //关闭载入中。
      this.buttom_loading = false;
      // document.getElementsByClassName("a")[0].children[1].innerHTML = "评论";
      this.comment_button_text = "评论";

      //函数本体。
      // console.log("执行方法"+err_msg);
      err_msg = err_msg || '评论失败';
      time = time || 1500;
      document.getElementsByClassName("comment-button-default")[0].style.visibility = "visible";
      document.getElementsByClassName("comment-button-default")[0].style.backgroundColor = "red";
      document.getElementsByClassName("comment-button-default")[0].style.color = "#fff";
      document.getElementsByClassName("comment-button-default")[0].classList.add("comment-button-error");
      this.comment_msg = err_msg;
      this.$refs['popover_comment'].doShow();
      //提示信息显示1.5秒。
      window.setTimeout(function () {
        document.getElementsByClassName('comment-button-default')[0].style.visibility = 'hidden'
      }, time);
    },
    start_loading(loading_msg) {
      loading_msg = loading_msg || "加载中";
      this.comment_button_text = loading_msg;
      document.getElementsByClassName("a")[0].style.paddingRight = "29px";
      document.getElementsByClassName("a")[0].style.paddingLeft = "30px";
      this.buttom_loading = true;
    },
    success_submit(suc_msg, time) {
      // console.log("执行评论成功的回调函数。");
      suc_msg = suc_msg || "评论成功";
      time = time || 1500;
      this.buttom_loading = false;
      document.getElementsByClassName("comment-button-default")[0].classList.remove("comment-button-error");
      this.comment_msg = suc_msg;
      document.getElementsByClassName("comment-button-default")[0].style.backgroundColor = "#000";
      document.getElementsByClassName("comment-button-default")[0].style.color = "#fff";
      //显示提示信息
      document.getElementsByClassName("comment-button-default")[0].style.visibility = "visible";
      this.$refs['popover_comment'].doShow();
      window.setTimeout(function () {
        document.getElementsByClassName('comment-button-default')[0].style.visibility = 'hidden'
      }, time);
      //重置按钮
      this.comment_button_text = "评论";
      this.buttom_loading = false;

      //清空输入内容
      this.insert_comment.comment_text = "";
      this.insert_comment.comment_name = "";
    }
  }
}
</script>
<style>
.b_comment_bg {
  margin: 7px 0;
}

/*评论部分*/
.comment-bg {
  margin-top: 20px;
  width: 100%;
  /*float: left;*/
  /*border:1px solid yellow;*/
}

.comment-content {
  margin: 0px 0;
  font-size: 14px;
  padding: 0 10px;
  background-color: #FFF;
  /*border:1px solid blue;*/
}

.comment-head {
}

.comment_title {
  height: 20px;
  padding-left: 5px;
  font-size: 16px;
  color: #333;
  user-select: none;
}

.area-bg {
  position: relative;
  padding-right: 6px;
  margin: 10px 0;
}

.comment-text textarea {
  /*background-color: #F4F5F7;*/
  height: 65px;
}

/*.comment-text textarea:hover{*/
/*background-color: white;*/
/*border-color: #00a1d6;*/
/*}*/
.el-textarea :hover {
  background-color: white;
  border-color: #00a1d6;
}

.focus {
  background-color: white;
}

.area-content {
  overflow: auto;
  padding: 10px;
  width: 717px;
  height: 70px;
  border: 0;
  background: #f8f8f8;
  word-break: break-all;
  resize: none;
  font-size: 14px;
  font-weight: normal;
}

.area-default {
  position: absolute;
  left: 10px;
  top: 10px;
  width: 200px;
  color: #999;
  cursor: text;
  font-size: 14px;
  font-weight: normal;
  text-align: left;
  display: block;
}

.submit-bg {
  /*overflow: hidden;!*error-msg换行*!*/
  position: relative; /*提交按钮，定位*/
  width: 100%;
  height: 34px;
  display: flex; /*Flex布局*/
  align-items: center; /*指定垂直居中*/
  /*overflow:hidden;*/
  margin-bottom: 24px;
}

.submit-bg * {
  float: left;
}

.submit_area {
  position: absolute;
  right: 35px;
}

.clear {
  clear: both;
}

div.nickname {
  margin-top: 5px;
  float: left;
}

div.submit-content {
  text-align: right;
  /*float: right;*/
}

.btn-comment {
  width: 126px;
  height: 36px;
  line-height: 36px;
  font-size: 16px;
  display: inline-block;
  color: #FFF;
  text-align: center;
  background: #4389ed;
  border-radius: 5px;
}

/*表情*/
/*标题：颜文字*/
.emoji-box {
  font-size: 12px;
  font-family: Microsoft YaHei, Arial, Helvetica, sans-serif;
  color: #222;
  overflow: visible;
  background: #fff;
  border: 1px solid #ccd0d7;
  /*box-shadow: 0 1px 5px 0 rgba(0,0,0,.14);*/
  width: 315px;
  /*position: absolute;*/
  /*top: 25px;*/
  /*z-index: 103;*/
}

.emoji-title {
  margin: 8px 18px 0;
  color: #99a2aa;
}

.emoji-wrap {
  margin: 4px 8px 8px;
  height: 152px;
  overflow: auto;
}

.emoji-text {
  color: #111;
  border-radius: 4px;
  transition: background .2s;
  display: inline-block;
  padding: 3px 5px;
  margin: 2px 3px;
  outline: 0;
  text-decoration: none;
  cursor: pointer;
}

.emoji-box a:hover {
  background-color: #DDDDDD;
  /*display:inline;*/
}


.comment-button-default {
  background: black;
  /*color: white;*/
  opacity: 0.92;
  top: 357px; /*上下偏移量*/
  text-align: center;
}

/*//评论框 弹出层样式 error*/

.comment-button-error {
  background-color: #65c0ea !important;
  color: white;
  opacity: 0.92;
  top: 357px; /*上下偏移量*/
  text-align: center;
}

/*//APP*/
/*/评论框，未选中时颜色*/
.comment-text textarea {
  background: #F4F5F7;
  height: 65px;
}

/*//评论框 弹出层样式 success*/
/*//隐藏箭头*/
.popper__arrow {
  visibility: hidden;
}

/*给表情popover添加0padding样式*/
.no_padding {
  padding: 0;
}
</style>