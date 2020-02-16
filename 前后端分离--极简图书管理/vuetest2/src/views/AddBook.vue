<template>
  <div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      style="width:60%"
      class="demo-ruleForm"
    >
      <el-form-item label="图书名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="ruleForm.author"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">点击新增</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
export default {
  data() {
    return {
      ruleForm: {
        name: "",
        author: ""
      },
      rules: {
        name: [{ required: true, message: "请输入图书名称", trigger: "blur" }],
        author: [{ required: true, message: "请选择作者名称", trigger: "blur" }]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          axios
            .post("http://localhost:8181/book/add/", this.ruleForm)
            .then(function(resp) {
              if (resp.data == "success") {
                //提示信息
                _this.$alert("书籍《"+_this.ruleForm.name+"》添加成功", "消息", {
                  confirmButtonText: "确定",
                  callback: action => {
                   _this.$router.push('/BookManage')
                  }
                });
              } else {
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>