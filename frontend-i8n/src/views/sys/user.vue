<template>
  <div class="app-container">
    <el-button v-permission="['Admin']" type="primary" @click="handleAdd">
      {{ $t('user.add') }}
    </el-button>

    <el-table v-loading="listLoading" class="main-table" :data="list" border fit stripe style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Username">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Email">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions">
        <template slot-scope="scope">
          <el-button v-permission="['Admin']" type="primary" size="small" @click="handleEdit(scope)">
            {{ $t('global.edit') }}
          </el-button>
          <el-button v-permission="['Admin']" type="danger" size="small" @click="handleDelete(scope)">
            {{ $t('global.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit User':$t('user.add')">
      <el-form ref="userForm" :model="userForm" :rules="rules" label-width="80px" label-position="left">
        <el-form-item :label="$t('user.username')">
          <el-input v-model="userForm.username" :disabled="dialogType==='edit'" :placeholder="$t('user.tips.username')" />
        </el-form-item>
        <el-form-item :label="$t('user.email')">
          <el-input v-model="userForm.email" :placeholder="$t('user.tips.email')" />
        </el-form-item>
        <el-form-item label="Roles">
          <el-tree ref="tree" :data="roles" :props="defaultProps" show-checkbox node-key="id" class="permission-tree" />
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
import { fetchList, addUser, editUser, deleteUser, getDetail } from '@/api/user'
import { getRoles } from '@/api/role'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const defaultUser = {
  id: 0,
  username: '',
  email: '',
  roleIds: []
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
      roles: [],
      dialogVisible: false,
      dialogType: 'new',
      rules: {
        username: [{ validator: validateRequire }]
      },
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      },
      defaultProps: {
        children: 'children',
        label: 'roleName'
      }
    }
  },
  created() {
    this.getList()
    this.getRoles()
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
    getRoles() {
      getRoles().then(response => {
        this.roles = response.data.list
      })
    },
    handleAdd() {
      this.userForm = Object.assign({}, defaultUser)
      if (this.$refs.tree) {
        this.$refs.tree.setCheckedNodes([])
      }
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      getDetail(scope.row.id).then(response => {
        this.userForm = response.data
        this.$refs.tree.setCheckedKeys(this.userForm.roleIds)
      })
    },
    handleDelete({ $index, row }) {
      this.$confirm('Confirm to remove the user?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          await deleteUser(row.id)
          this.list.splice($index, 1)
          this.$message({
            type: 'success',
            message: 'Delete succed!'
          })
        })
        .catch(err => { console.error(err) })
    },
    confirmUser() {
      const isEdit = this.dialogType === 'edit'
      const checkedKeys = this.$refs.tree.getCheckedKeys()
      this.userForm.roleIds = checkedKeys
      console.log(this.userForm)

      this.$refs.userForm.validate(valid => {
        if (valid) {
          this.loading = true
          if (isEdit) {
            editUser(this.userForm.id, this.userForm).then(response => {
              this.$notify({
                title: '成功',
                message: '修改用户成功',
                type: 'success',
                duration: 2000
              })
              this.listLoading = false
              this.dialogVisible = false
              this.getList()
            })
          } else {
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
          }
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
