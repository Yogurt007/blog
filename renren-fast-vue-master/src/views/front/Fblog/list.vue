<template>
  <div class="blog-main">
    <div class="blog-article">
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

      <el-table-column prop="title" label="标题" width="250%">
        <template slot-scope="scope">
          <div @click="$router.push({ name: 'front-blog-read',params:{id:scope.row.id}})">{{scope.row.title}}</div>
        </template>
      </el-table-column>

      <el-table-column prop="content" label="内容" :show-overflow-tooltip="true"></el-table-column>

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
  created() {
    this.getArticleList();
  },
  methods: {
    getBlogList() {
      this.$http({
        url: this.$http.adornUrl("/blog/article/list"),
        method: "get",
        params: this.$http.adornParams(),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          console.log("获取博客列表成功");
          console.log(data.list);
          this.list = data.list;
        }
      });
    },
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
  },
};
</script>

<style lang="scss" scoped>
.blog-main {
}
.blog-article {
  width: 70%;
  height: 700px;
  margin: auto;
  background-color: #f9f9f9;
}
.of {
  list-style: none;
}
ul{
  margin-top: 0;
  margin-bottom: 0;
}
</style>