/* eslint-disable vue/max-attributes-per-line */
<template>
  <div class="app-container">
    <el-button type="primary" @click="handleAdd">
      {{ $t('menu.add') }}
    </el-button>

    <el-table
      :data="list"
      style="width: 100%"
      border
      row-key="id"
      class="main-table"
    >
      <el-table-column label="Name">
        <template slot-scope="scope">
          <span>
            <i :class="scope.row.icon" />
            {{ scope.row.name }}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="Url">
        <template slot-scope="scope">
          <span>{{ scope.row.url }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Permission">
        <template slot-scope="scope">
          <span>{{ scope.row.permission }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Type">
        <template slot-scope="scope">
          <span>{{ getType(scope.row.type) }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="OrderNum">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNum }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Actions">
        <template slot-scope="scope">
          <el-button type="primary" size="small" icon="el-icon-edit" @click="handleEdit(scope)">
            {{ $t('global.edit') }}
          </el-button>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="handleDelete(scope)" />
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?$t('menu.edit'):$t('menu.add')">
      <el-form ref="menuForm" :model="menuForm" :rules="rules" label-width="80px" label-position="left">
        <el-form-item :label="$t('menu.parent')">
          <el-cascader
            v-model="menuForm.parentId"
            :options="list"
            :props="{ checkStrictly: true, value: 'id', label: 'name' }"
            clearable
          />
        </el-form-item>

        <el-form-item :label="$t('menu.name')">
          <el-input v-model="menuForm.name" />
        </el-form-item>
        <el-form-item :label="$t('menu.url')">
          <el-input v-model="menuForm.url" />
        </el-form-item>
        <el-form-item :label="$t('menu.permission')">
          <el-input v-model="menuForm.permission" />
        </el-form-item>
        <el-form-item :label="$t('menu.type')">
          <el-radio-group v-model="menuForm.type">
            <el-radio :label="1">{{ $t('menu.menu') }}</el-radio>
            <el-radio :label="2">{{ $t('menu.button') }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item :label="$t('menu.icon')">
          <el-input v-model="menuForm.icon" />
        </el-form-item>
        <el-form-item :label="$t('menu.orderNum')">
          <el-input v-model="menuForm.orderNum" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">
          {{ $t('global.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirmMenu">
          {{ $t('global.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchAll, addMenu, deleteMenu } from '@/api/menu'

const defaultMenu = {
  pid: 0,
  parentId: [0],
  name: '',
  type: 1
}
export default {
  name: 'MenuList',
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
      menuForm: Object.assign({}, defaultMenu),
      list: null,
      dialogVisible: false,
      dialogType: 'new',
      rules: {
        name: [{ validator: validateRequire }]
      },
      listLoading: true
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getType(type) {
      if (type === 1) {
        return this.$t('menu.menu')
      } else if (type === 2) {
        return this.$t('menu.button')
      } else {
        return this.$t('global.unknow')
      }
    },
    getList() {
      this.listLoading = true
      fetchAll(this.listQuery).then(response => {
        this.list = response.data.menuVOList
        this.listLoading = false
      })
    },
    handleAdd() {
      this.menuForm = Object.assign({}, defaultMenu)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleEdit({ $index, row }) {
      this.menuForm = Object.assign({}, row)
      this.menuForm.parentId = []
      this.menuForm.parentId[0] = this.menuForm.pid
      this.dialogType = 'edit'
      this.dialogVisible = true
    },
    handleDelete({ $index, row }) {
      this.$confirm('Confirm to remove this menu?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          deleteMenu(row.id).then(response => {
            this.getList()
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          })
        })
        .catch(err => { console.error(err) })
    },
    confirmMenu() {
      console.log(this.menuForm)
      this.$refs.menuForm.validate(valid => {
        if (valid) {
          this.menuForm.pid = this.menuForm.parentId[0]
          this.loading = true
          delete this.menuForm.createTime
          delete this.menuForm.updateTime

          addMenu(this.menuForm).then(response => {
            if (this.dialogType === 'edit') {
              this.message = this.$t('global.edit') + this.$t('menu.message')
            } else {
              this.message = this.$t('global.add') + this.$t('menu.message')
            }
            this.$notify({
              title: this.$t('global.success'),
              message: this.message,
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
