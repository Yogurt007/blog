<template>
  <div>
    <el-table v-if="list" :data="list" border fit highlight-current-row>
      <el-table-column
        prop="blogRecordsEntity.title"
        label="标题"
      />

      <el-table-column
        prop="blogRecordsEntity.content  "
        label="内容"
      />

      <el-table-column prop="artList" label="照片列表" width="800px">
        <template slot-scope="scope">
          <div>
            <ul v-for="(picture, index) in scope.row.artList" :key="index">
              <li style="list-style:none">
                <img :src="picture.url" class="list-img" />
                <el-button type="danger" round @click="deleteArt(picture.id)" style="margin-left:100px"
                  >删除</el-button
                >
              </li>
            </ul>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        prop="blogRecordsEntity.createTime"
        label="创建时间"
        width="100"
      />

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="deleteRecord(scope.row.blogRecordsEntity.id)"
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
  methods: {
    getRecordList() {
      this.$http({
        url: this.$http.adornUrl("/blog/records/list/"),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.list = data.artDtoList;
          //console.log(this.list);
        } else {
          this.$message.error("获取列表失败");
        }
      });
    },
    deleteArt(id) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // promise
          this.$http({
            url: this.$http.adornUrl("/blog/records/deleteArt/" + id),
            method: "post",
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "删除成功",
                type: "success",
                duration: 1500,
              });
              window.setTimeout(function () {
                window.location.reload();
              }, 1500);
            } else {
              this.$message.error("失败");
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    deleteRecord(id) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/blog/records/deleteRecords/" + id),
            method: "post",
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "删除成功",
                type: "success",
                duration: 1500,
              });
              this.$router.go(0);
            } else {
              this.$message.error("失败");
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
  created() {
    this.getRecordList();
  },
};
</script>

<style>
.list-img {
  height: 200px;
  object-fit: contain;
}
</style>