<template>
  <div>
    <!-- 表格 -->
    <el-table
      v-if="list"
      :data="list.slice((page - 1) * limit, page * limit)"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="标题" />

      <el-table-column prop="content" label="内容" :show-overflow-tooltip="true"></el-table-column>

      <el-table-column prop="type" label="类型" width="60" />

      <el-table-column prop="look" label="浏览数" width="60" />

      <el-table-column prop="love" label="点赞数" width="60" />

      <el-table-column prop="deleted" label="是否删除" width="60" />

      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="update(scope.row.id)"
            >修改</el-button
          >
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="100" />
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getArticleList"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: null,
      page: 1, //开始页
      limit: 7, //每页记录数
      total: 10, //总记录数
    };
  },
  methods: {
    getArticleList(page = 1) {
      this.page = page;
      this.$http({
        url: this.$http.adornUrl(
          "/blog/article/page/" + this.page + "/" + this.limit
        ),
        methods: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.list = data.pageInfo.records;
          this.total = data.pageInfo.records.length;
        } else {
            this.$message.error("获取文章列表失败");
        }
      });
    },
    update(id){
        this.$router.push({name:'blog-update',params:{id:id}})
    }
  },
  created() {
    this.getArticleList();
  },
};
</script>

<style>
</style>