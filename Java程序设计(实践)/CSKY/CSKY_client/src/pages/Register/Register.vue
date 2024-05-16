<template>
  <el-row :gutter="20">
    <el-col :xs="0" :sm="4" :md="4" :lg="4" :xl="6">
      <div class="grid-content bg-purple"></div>
    </el-col>
    <el-col :xs="24" :sm="16" :md="16" :lg="16" :xl="12">
      <el-card shadow="always" class="register-container">
        <!-- 注册内容 -->
        <div class="register">
          <h3>注册新用户
            <span class="go">我有账号，去 <router-link to="/login">登录</router-link>
            </span>
          </h3>
          <el-row :gutter="20">
            <el-col :span="24" :offset="0">
              <el-form :rules="rules" ref="registerForm" class="registerForm" label-position="right" status-icon
                       :model="register">
                <el-row :gutter="20">
                  <el-col :xs="7" :sm="5" :md="3" :lg="3" :xl="3">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="15" :lg="15" :xl="15">
                    <el-form-item label="用户名称" prop="username">
                      <el-tooltip effect="dark" content="由5~15位数字、26个英文字母或者下划线组成" :open-delay="200" placement="left">
                        <el-input v-model="register.username" placeholder="请输入你的用户名"></el-input>
                      </el-tooltip>
                    </el-form-item>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="4" :lg="4" :xl="4">
                  </el-col>
                  <el-col :xs="7" :sm="5" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple-light"></div>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :xs="7" :sm="5" :md="3" :lg="3" :xl="3">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="15" :lg="15" :xl="15">
                    <el-form-item label="邮箱账号" prop="email">
                      <el-tooltip effect="dark" content="xxxxx@xxx.xxx格式，请务必输入有效的邮箱账号" :open-delay="200"
                                  placement="right">
                        <el-input v-model="register.email" placeholder="请输入你的邮箱账号"></el-input>
                      </el-tooltip>
                    </el-form-item>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="4" :lg="4" :xl="4">
                    <el-button type="primary" size="small" @click="sendEmailCode"
                               style="margin-top: 43px; border-radius: 30px;" :disabled="isClickGetCode">
                      <span v-if="!isClickGetCode">发送验证码</span>
                      <span v-if="isClickGetCode">{{ codeNum }} s后重新发送</span>
                    </el-button>
                  </el-col>
                  <el-col :xs="7" :sm="5" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple-light"></div>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :xs="7" :sm="5" :md="3" :lg="3" :xl="3">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="15" :lg="15" :xl="15">
                    <el-form-item label="验证码" prop="emailCode">
                      <el-tooltip effect="dark" content="6位验证码，由26个英文字母和0~9数字组成" :open-delay="200" placement="right">
                        <el-input v-model="register.emailCode" placeholder="请输入邮箱中接收到的验证码"></el-input>
                      </el-tooltip>
                    </el-form-item>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="4" :lg="4" :xl="4">
                    <el-button type="primary" size="small" @click="checkEmailCode"
                               style="margin-top: 43px; border-radius: 30px;" v-show="isShowIcon">点击验证
                    </el-button>
                    <i class="el-icon-circle-check"
                       style="color: greenyellow;margin-top: 40px;font-size: 3.3em;margin-left: 22px;"
                       v-show="!isShowIcon"></i>
                  </el-col>
                  <el-col :xs="7" :sm="5" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple-light"></div>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :xs="7" :sm="5" :md="3" :lg="3" :xl="3">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="15" :lg="15" :xl="15">
                    <el-form-item label="登录密码" prop="password1">
                      <el-tooltip effect="dark" content="以字母开头，长度在6~18之间，只能包含字母、数字和下划线"
                                  :open-delay="200" placement="right">
                        <el-input type="password" v-model="register.password1" placeholder="请输入密码"></el-input>
                      </el-tooltip>
                    </el-form-item>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="4" :lg="4" :xl="4">

                  </el-col>
                  <el-col :xs="7" :sm="5" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple-light"></div>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :xs="7" :sm="5" :md="3" :lg="3" :xl="3">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="15" :lg="15" :xl="15">
                    <el-form-item label="确认密码" prop="password2">
                      <el-tooltip effect="dark" content="再次输入密码"
                                  :open-delay="200" placement="right">
                        <el-input type="password" v-model="register.password2" placeholder="请确认密码"></el-input>
                      </el-tooltip>
                    </el-form-item>
                  </el-col>
                  <el-col :xs="5" :sm="7" :md="4" :lg="4" :xl="4">

                  </el-col>
                  <el-col :xs="7" :sm="5" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple-light"></div>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple"></div>
                  </el-col>
                  <el-col :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                    <div>
                      <el-button type="primary" @click="userRegister" style="display:block;margin:0 auto">完成注册
                      </el-button>
                    </div>
                  </el-col>
                  <el-col :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
                    <div class="grid-content bg-purple-light"></div>
                  </el-col>
                </el-row>
              </el-form>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </el-col>
    <el-col :xs="0" :sm="4" :md="4" :lg="4" :xl="6">
      <div class="grid-content bg-purple-light"></div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'Register',
  data() {
    // 自定义校验规则
    const validateUsername = (rule, value, callback) => {
      if (!/^\w{5,15}$/.test(value)) {
        callback(new Error('请检查用户名格式是否正确'))
      } else {
        callback()
      }
    }
    const validatePassword1 = (rule, value, callback) => {
      if (!/^[a-zA-Z]\w{5,17}$/.test(value)) {
        callback(new Error('密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线'))
      } else {
        callback()
      }
    }
    const validatePassword2 = (rule, value, callback) => {
      if (this.register.password2 === '' || this.register.password1 !== value) {
        callback(new Error('前后密码不一致'))
      } else {
        callback()
      }
    }
    const validateEmail = (rule, value, callback) => {
      if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)) {
        callback(new Error('请输入正确的邮箱地址'))
      } else {
        callback()
      }
    }
    const validateEmailCode = (rule, value, callback) => {
      if (!/^[A-Za-z0-9]{6}$/.test(value)) {
        callback(new Error('请输入6位邮箱验证码'))
      } else {
        callback()
      }
    }
    return {
      register: {
        //用户名
        username: '',
        //密码
        password1: '',
        //确认密码
        password2: '',
        // 用户邮箱
        email: '',
        // 邮箱验证码
        emailCode: ''
      },
      // 是否已经发送验证码
      isClickGetCode: false,
      // 是否显示验证成功图标
      isShowIcon: true,
      // 点击获取验证码后的倒计时
      codeNum: 60,

      rules: {
        username: [
          {required: true, trigger: 'blur', validator: validateUsername},
        ],
        email: [
          {required: true, trigger: 'blur', validator: validateEmail},
        ],
        emailCode: [
          {required: true, trigger: 'blur', validator: validateEmailCode},
        ],
        password1: [
          {required: true, trigger: 'blur', validator: validatePassword1}
        ],
        password2: [
          {required: true, trigger: 'change', validator: validatePassword2}
        ],
      },
      // 校验验证码时的使用
      eid: ''
    }
  },
  computed: {},
  methods: {
    async sendEmailCode() {
      if (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(this.register.email)) {
        this.isClickGetCode = true
        let interval = setInterval(()=>{
          if (this.codeNum > 0) {
            this.codeNum --
          } else {
            clearInterval(interval)
            this.isClickGetCode = false
          }
        }, 1000)
        this.$notify.warning({
          title: '提示',
          message: '验证码发送中...',
          duration: 8000
        })
        try {
          const result = await this.$API.reqGetCode(this.register.email)
          if (result.data.code === 200) {
            await this.$notify.success({
              message: '验证码成功发送，请注意查收！',
              title: '提示',
              duration: 5000
            })
            this.eid = result.data.data.eid
            // 不能再被点击
            this.isClickGetCode = true
          } else {
            this.$message.error('验证码发送失败！')
          }
        } catch (e) {
          this.$message.error(e.message)
        }
      } else {
        this.$message.warning('请先输入正确的邮箱账号')
      }
    },
    async checkEmailCode() {
      if (!this.isClickGetCode) {
        this.$message.warning('请先发送验证码~')
        return
      }
      if (!/^[A-Za-z0-9]{6}$/.test(this.register.emailCode)) {
        this.$message.warning('验证码格式有误，请重新输入')
        return
      }
      try {
        // 先校验验证码是否通过
        const result = await this.$API.reqCheckCode(this.register.emailCode.toUpperCase(), this.eid)
        if (result.data.code !== 200) {
          this.$message.error(result.data.msg)
        } else {
          this.$message.success('验证通过~')
          this.isShowIcon = false
        }
      } catch (e) {
        this.$message.error(e.message)
      }
    },
    async userRegister() {
      await this.$refs.registerForm.validate(async (valid) => {
        if (this.isShowIcon) {
          this.$message.warning('请先校验邮箱验证码是否正确')
          return
        }
        if (valid) {
          try {
            const result = await this.$store.dispatch("user/register", {
              username: this.register.username,
              password: this.register.password1,
              userEmail: this.register.email
            })
            if (result) {
              this.$confirm('注册成功，是否立即登录', '', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'success'
              }).then(() => {
                this.$router.push('/login')
              }).catch(() => {
                this.$router.push('/home')
              });
            }
          } catch (e) {
            this.$message.error(e.message)
          }
        } else {
          this.$message.warning('请检查输入是否有误')
        }
      })
    },
  }
}
</script>

<style lang="less" scoped>
.register-container {
  margin: 22px 0 0 0;
  border-radius: 10px;
  background-color: transparent;

  .register {
    border: 1px solid rgb(223, 223, 223);
    margin: 0 auto;
    border-radius: 24px;

    h3 {
      background: #b3d8ff;
      margin: 0;
      padding: 6px 15px;
      color: #333;
      border-bottom: 1px solid #dfdfdf;
      border-radius: 9px;
      font-size: 20.04px;
      line-height: 30.06px;

      span {
        font-size: 14px;
        float: right;

        a {
          color: #409eff;
          text-decoration: none;
        }
      }
    }

    .registerForm {
      margin: 20px 2px;
    }
  }
}
</style>
