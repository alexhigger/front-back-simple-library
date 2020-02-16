<template>
  <div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixedid prop="id" label="编号" width="150"></el-table-column>
      <el-table-column prop="name" label="图书名" width="250"></el-table-column>
      <el-table-column prop="author" label="作者" width="250"></el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button type="text" size="small" @click="deleteBook(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="pageSize"
      :total="total"
      @current-change="page"
    ></el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    edit(row) {
      console.log(row);
      this.$router.push({
        path: "/update",
        query: {
          id: row.id
        }
      });
    },
    deleteBook(row) {
      const _this = this;
      _this
        .$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
        .then(() => {
          axios.delete("http://localhost:8181/book/deleteById/" + row.id);
          window.location.reload();
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    page(currentPage) {
      const _this = this;
      axios
        .get("http://localhost:8181/book/findAll/" + (currentPage - 1) + "/6")
        .then(function(resp) {
          console.log(resp);
          _this.tableData = resp.data.content;
          _this.pageSize = resp.data.size;
          _this.total = resp.data.totalElements;
        });
    }
  },

  data() {
    return {
      pageSize: null,
      total: null,
      tableData: [
        {
          id: "1",
          name: "解忧杂货店",
          author: "东野圭吾"
        },
        {
          id: "1",
          name: "解忧杂货店",
          author: "东野圭吾"
        },
        {
          id: "1",
          name: "解忧杂货店",
          author: "东野圭吾"
        },
        {
          id: "1",
          name: "解忧杂货店",
          author: "东野圭吾"
        }
      ]
    };
  },
  created() {
    const _this = this;
    axios.get("http://localhost:8181/book/findAll/0/6").then(function(resp) {
      console.log(resp);
      _this.tableData = resp.data.content;
      _this.pageSize = resp.data.size;
      _this.total = resp.data.totalElements;
    });
  }
};
</script>