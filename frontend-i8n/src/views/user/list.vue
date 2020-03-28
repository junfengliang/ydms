<template>
  <div class="app-container">
    <el-button type="primary" @click="handleAdd">
      {{ $t('user.add') }}
    </el-button>

    <el-table v-loading="listLoading" class="main-table" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="Username">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions" width="120">
        <template slot-scope="scope">
          <router-link :to="'/example/edit/'+scope.row.id">
            <el-button type="primary" size="small" icon="el-icon-edit">
              Edit
            </el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit User':$t('user.add')">
      <el-form ref="userForm" :model="userForm" :rules="rules" label-width="80px" label-position="left">
        <el-form-item :label="$t('user.username')">
          <el-input v-model="userForm.username" :placeholder="$t('user.tips.username')" />
        </el-form-item>

      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">
          {{ $t('global.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirmUser">
          {{ $t('global.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, addUser } from '@/api/user'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const defaultUser = {
  username: ''
}
export default {
  name: 'UserList',
  components: { Pagination },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(new Error(rule.field + '为必传项'))
      } else {
        callback()
      }
    }
    return {
      userForm: Object.assign({}, defaultUser),
      list: null,
      total: 0,
      dialogVisible: false,
      dialogType: 'new',
      rules: {
        username: [{ validator: validateRequire }]
      },
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.page.totalCount
        this.listLoading = false
      })
    },
    handleAdd() {
      this.userForm = Object.assign({}, defaultUser)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    confirmUser() {
      console.log(this.userForm)
      this.$refs.userForm.validate(valid => {
        if (valid) {
          this.loading = true
          addUser(this.userForm).then(response => {
            this.$notify({
              title: '成功',
              message: '添加用户成功',
              type: 'success',
              duration: 2000
            })
            this.listLoading = false
            this.dialogVisible = false
            this.getList()
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
.main-table {
  margin-top: 20px;
}
</style>
