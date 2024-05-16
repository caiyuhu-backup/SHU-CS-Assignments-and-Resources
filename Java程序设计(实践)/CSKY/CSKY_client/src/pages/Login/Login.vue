<template>
  <el-row :gutter="20">
    <video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop" muted>
      <source src="../../assets/login_bg_media.mp4" type="video/mp4">
    </video>
    <el-col :span="20" :offset="2">
      <div class="login-container">
        <!-- 登录 -->
        <div class="login-wrap">
          <el-row :gutter="20">
            <el-col :xs="1" :sm="8" :md="8" :lg="8" :xl="8">
              <div class="grid-content"></div>
            </el-col>
            <el-col :xs="1" :sm="4" :md="10" :lg="10" :xl="10">
              <div class="login">
                <div class="loginForm">
                  <ul class="tab clearFix">
                    <li>
                      <a href="javascript:void(0);" class="current">账户登录</a>
                    </li>
                  </ul>
                  <div class="content">
                    <el-form label-width="100px" :model="loginForm" :rules="rules"
                             label-position="left" status-icon ref="loginForm">
                      <el-form-item label="用户名" prop="username">
                        <el-input v-model="loginForm.username" type="text" placeholder="请输入用户名"></el-input>
                      </el-form-item>
                      <el-form-item label="请输入密码" prop="password">
                        <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"
                                  @keyup.native.enter="userLogin"
                        ></el-input>
                      </el-form-item>
                    </el-form>
                    <form>
                      <el-button type="primary" class="btn" @click.prevent="userLogin">登&nbsp;&nbsp;录</el-button>
                    </form>

                    <div class="call clearFix">
                      <router-link class="register" to="/register" style="text-decoration: none">立即注册</router-link>
                    </div>
                  </div>
                </div>
              </div>
            </el-col>
            <el-col :xs="5" :sm="5" :md="2" :lg="1" :xl="1">
              <div class="grid-content"></div>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  name: 'Login',
  data() {
    // 自定义校验规则
    const validateUsername = (rule, value, callback) => {
      if (!/^\w{5,15}$/.test(value)) {
        callback(new Error('请检查用户名格式是否正确'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (!/^\w{5,17}$/.test(value)) {
        callback(new Error('密码只能6~15位包含数字、字母和下划线'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },

      rules: {
        username: [
          {required: true, trigger: 'blur', validator: validateUsername}
        ],
        password: [
          {required: true, trigger: 'blur', validator: validatePassword}
        ]
      }
    }
  },
  methods: {
    userLogin() {
      this.$refs.loginForm.validate(async (valid)=>{
        if(valid) {
          try {
            const result = await this.$store.dispatch("user/login", {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
            if (result) {
              await this.$message({
                duration: 1500,
                message: '登录成功！',
                type: 'success'
              })
              await this.$router.push('/home')
              const toPath = this.$route.query.redirect || '/home'
              await this.$router.push(toPath)
            } else {
              this.$message.error(result.data.msg)
              this.loginForm.username = ''
              this.loginForm.password = ''
            }
          } catch (e) {
            this.$message.error(e.message)
            this.loginForm.username = ''
            this.loginForm.password = ''
          }
        } else {
          this.$message.warning('请检查输入是否正确')
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.me-video-player {
  background-color: transparent;
  object-fit: fill;
  display: block;
  position: absolute;
  width: 100%;
  left: 0;
}

.login-container {
  margin-top: 65px;
  .login-wrap {
    height: 888px;

    .login {
      height: 487px;
      margin: 0 auto;
    }

    .loginForm {
      margin-top: 89px;
      width: 420px;
      height: 406px;
      box-sizing: border-box;
      background: #fff;
      top: 45px;
      right: -68%;
      position: relative;
      padding: 20px;
      text-align: center;
      border-radius: 4px;

      .tab {

        li {
          width: 100%;

          a {
            width: 100%;
            display: block;
            height: 50px;
            line-height: 50px;
            font-size: 20px;
            font-weight: 700;
            color: #333;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            text-decoration: none;
          }

          .current {
            border-bottom: none;
            color: #579ed4;
          }
        }
      }

      .content {
        width: 380px;
        height: 316px;
        box-sizing: border-box;
        border: 1px solid #ddd;
        border-radius: 4px;
        border-top: none;
        padding: 18px;

        form {
          margin: 15px 0 18px 0;
          font-size: 12px;
          line-height: 18px;

          .btn {
            padding: 6px;
            border-radius: 10px;
            font-size: 16px;
            word-spacing: 4px;
            color: #fff;
            width: 100%;
            height: 36px;
            margin-top: 25px;
            outline: none;
          }
        }

        .call {
          margin-top: 30px;

          ul {
            float: left;

            li {
              float: left;
              margin-right: 5px;
            }
          }

          .register {
            float: right;
            font-size: 15px;
            line-height: 38px;
          }

          .register:hover {
            color: #4cb9fc;
            text-decoration: underline;
          }
        }

      }
    }
  }


}
</style>
