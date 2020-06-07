<template>
  <div class="app-container" style="width:300px">
    <el-form ref="pwdForm" :model="pwdForm" :rules="rules" label-width="80px" label-position="left">
      <el-form-item prop="old" :label="$t('fpwd.old')">
        <el-input v-model="pwdForm.old" :placeholder="$t('fpwd.oldPassTips')" type="password" />
      </el-form-item>
      <el-form-item prop="password" :label="$t('fpwd.newPass')">
        <el-input v-model="pwdForm.password" :placeholder="$t('fpwd.newPassTips')" type="password" />
      </el-form-item>
      <el-form-item prop="confirmPassword" :label="$t('fpwd.confirmPass')">
        <el-input v-model="pwdForm.confirmPassword" :placeholder="$t('fpwd.confirmPassTips')" type="password" />
      </el-form-item>
    </el-form>
    <div style="text-align:right">
      <el-button :loading="loading" type="primary" @click.native.prevent="handlePassword">
        {{ $t('global.confirm') }}
      </el-button>
    </div>
  </div>
</template>

<script>
import { changePassword } from '@/api/user'
import store from '@/store'

const defaultPwd = {
  old: '',
  password: '',
  confirmPassword: ''
}
export default {
  name: 'ResetPwd',
  data() {
    const passValidator = (rule, value, callback) => {
      if (this.pwdForm.password !== this.pwdForm.confirmPassword) {
        callback(new Error(this.$t('validate.passInconsistent')))
      } else {
        callback()
      }
    }
    return {
      pwdForm: Object.assign({}, defaultPwd),
      rules: {
        old: [
          { required: true, message: this.$t('validate.old') }
        ],
        password: [
          { required: true, message: this.$t('validate.password') }
        ],
        confirmPassword: [
          { required: true, message: this.$t('validate.confirmPassword') },
          { required: true, validator: passValidator }
        ]
      },
      loading: false
    }
  },
  mounted() {
    window.vue = this
  },
  methods: {
    handlePassword: function() {
      this.$refs.pwdForm.validate((valid) => {
        if (valid) {
          this.loading = true
          changePassword(this.pwdForm).then(response => {
            this.$alert('密码修改成功', '修改密码', {
              confirmButtonText: '确定',
              callback: action => {
                store.dispatch('user/resetToken').then(() => {
                  location.reload()
                })
              }
            })
          }).finally(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
