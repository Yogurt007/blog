<template>
  <div>
    <el-table v-if="list" :data="list" border fit highlight-current-row>
      <!-- <el-table-column width="60" label="序号">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column> -->
      <el-table-column prop="content" label="内容" />

      <el-table-column prop="examined" label="审核状态">
        <template slot-scope="scope">
          {{ scope.row.examined }}
          <div style="float: right" v-if="scope.row.examined == 0">
            <el-button type="success" plain @click="pass(scope.row.id)">
              通过
            </el-button>
            <el-button type="danger" plain @click="fail(scope.row.id)">
              不通过
            </el-button>
          </div>
        </template>
      </el-table-column>

      <el-table-column prop="createTime" label="创建时间" width="200" />

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="deleteComment(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: null,
    };
  },
  created() {
    this.getCommentList();
  },
  methods: {
    getCommentList() {
      this.$http({
        url: this.$http.adornUrl("/blog/comment/list/"),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.list = data.list;
          console.log(this.list);
        } else {
          this.$message.error("获取列表失败");
        }
      });
    },
    pass(id) {
      //console.log("审核通过 " + id);
      this.$http({
        url: this.$http.adornUrl("/blog/comment/pass/" + id),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "审核成功",
            type: "success",
            duration: 1500,
          });
        } else {
          this.$message.error("审核失败");
        }
      });
    },
    fail(id) {
      //console.log("审核不通过 " + id);
      this.$http({
        url: this.$http.adornUrl("/blog/comment/fail/" + id),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "审核成功",
            type: "success",
            duration: 1500,
          });
        } else {
          this.$message.error("审核失败");
        }
      });
    },
    deleteComment(id) {
      //console.log("删除  " + id);
      this.$http({
        url: this.$http.adornUrl("/blog/comment/delete/" + id),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "删除成功",
            type: "success",
            duration: 1500,
          });
        } else {
          this.$message.error("删除失败");
        }
      });
    },
  },
};
</script>

<style>
</style>