<template>
  <div class="pwd-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      autocomplete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">
          {{ $t('fpwd.find') }}
        </h3>
      </div>

      <el-form-item prop="username">
        <el-input
          ref="username"
          v-model="loginForm.username"
          :placeholder="$t('fpwd.usernameTips')"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleUsername">
        {{ $t('global.next') }}
      </el-button>
    </el-form>

    <el-dialog :visible.sync="dialogVisible" :title="$t('fpwd.find')">
      <el-form ref="pwdForm" :model="pwdForm" :rules="pwdRules" label-width="80px" label-position="left">
        <el-form-item :label="$t('fpwd.code')">
          <el-input v-model="pwdForm.code" :placeholder="$t('fpwd.codeTips')">
            <el-button v-show="showTime" slot="append" style="color: white;background-color: #3c8dbc" @click="sendEmail(loginForm.username)">发送验证码</el-button>
            <el-button v-show="!showTime" slot="append" style="color: white;background-color: #3c8dbc;margin-left: -20px">{{ sendTime }}秒</el-button>
          </el-input>
        </el-form-item>

        <el-form-item :label="$t('fpwd.newPass')">
          <el-input v-model="pwdForm.password" type="password" :placeholder="$t('fpwd.newPassTips')" />
        </el-form-item>
        <el-form-item :label="$t('fpwd.confirmPass')">
          <el-input v-model="pwdForm.confirmPassword" type="password" :placeholder="$t('fpwd.confirmPassTips')" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="cancelPass">
          {{ $t('global.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirmPass">
          {{ $t('global.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { checkUsername, sendVerifyCode, resetPassword } from '@/api/user'
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('Please enter the correct user name'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', message: this.$t('fpwd.usernameTips') }]
      },
      pwdForm: {
        code: '',
        password: '',
        email: '',
        confirmPassword: ''
      },
      pwdRules: {
        code: [{ required: true, trigger: 'blur', validator: validateUsername }]
      },
      showTime: true,
      sendTime: 0,
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      dialogVisible: false,
      redirect: '/login',
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    // window.addEventListener('storage', this.afterQRScan)
  },
  mounted() {
    window.vue = this
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    sendEmail() {
      const TIME_COUNT = 60 //  更改倒计时时间
      if (!this.timer) {
        this.sendTime = TIME_COUNT
        this.showTime = false
        this.timer = setInterval(() => {
          if (this.sendTime > 0 && this.sendTime <= TIME_COUNT) {
            this.sendTime--
          } else {
            this.showTime = true
            clearInterval(this.timer) // 清除定时器
            this.timer = null
          }
        }, 1000)
      }
      sendVerifyCode(this.loginForm).then(response => {
        this.$notify({
          title: '成功',
          message: '发送验证码成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    handleUsername() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          checkUsername(this.loginForm).then(response => {
            this.dialogVisible = true
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    confirmPass() {
      this.$refs.pwdForm.validate(valid => {
        if (valid) {
          this.dialogVisible = false
          this.pwdForm.email = this.loginForm.username
          resetPassword(this.pwdForm).then(response => {
            this.$notify({
              title: '成功',
              message: '密码修改成功',
              type: 'success',
              duration: 2000
            })
            this.$router.push('/login')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    cancelPass() {
      this.dialogVisible = false
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }
  }
}
</script>

<style lang="scss" scoped>

.pwd-container {
  min-height: 100%;
  width: 100%;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
}
</style>
