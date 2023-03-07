<template>
  <div class="blog-main">
    <div class="blog-article">
      <!-- 搜索框 -->
      <el-input placeholder="请输入内容" v-model="queryInput">
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="queryArticle()"
        ></el-button>
      </el-input>
      <!-- 表格 -->
      <el-table
        v-if="list"
        :data="list.slice((page - 1) * limit, page * limit)"
        border
        fit
        highlight-current-row
        :show-header="false"
      >
        <el-table-column width="60" align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>

        <el-table-column prop="title">
          <template slot-scope="scope">
            <div>
              <div>
                <img :src="titleImgCom(scope.row.content)" class="titleImg" />
              </div>
              <div
                @click="
                  $router.push({
                    name: 'front-codeBook-read',
                    params: { id: scope.row.id },
                  })
                "
                style="margin-left:20%"
              >
                <h1>
                  <a href="#" style="text-decoration:none">{{ scope.row.title }}</a>
                </h1>
              </div>
              <div style="margin-left:20%">
                {{
                  scope.row.content
                    .substring(0, 100)
                    .replace("edu-po.oss-cn-beijing", "******")
                }}
              </div>
            </div>
          </template>
        </el-table-column>

        <el-table-column prop="createTime" width="100" />
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
      limit: 8, //每页记录数
      total: 10, //总记录数
      queryInput: "",
      titleImgUrl:
        "https://edu-po.oss-cn-beijing.aliyuncs.com/blog/luyuan.jpg",
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
          // console.log("获取博客列表成功");
          // console.log(data.list);
          this.list = data.list;
        }
      });
    },
    getArticleList(page = 1) {
      this.page = page;
      this.$http({
        url: this.$http.adornUrl(
          "/blog/article/codeBookPage/" + this.page + "/" + this.limit
        ),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.list = data.pageInfo.records;
          this.total = data.pageInfo.records.length;
        } else {
          this.$message.error("获取文章列表失败");
        }
      });
    },
    queryArticle() {
      if (this.queryInput) {
        var queryJson = { queryInput: this.queryInput, type: "码录" };
        console.log(queryJson);
        this.$http({
          url: this.$http.adornUrl("/blog/article/query"),
          data: queryJson,
          method: "post",
        }).then(({ data }) => {
          if (data && data.code === 0) {
            console.log(data.queryList);
            this.list = data.queryList;
            this.total = data.queryList.length;
          } else {
            this.$message.error("获取文章列表失败");
          }
        });
      }
    },
    titleImgCom(content) {
      //console.log(content);
      var start = content.indexOf("<img src=");
      var end = content.indexOf('title="helloworld"');
      // console.log(content.indexOf("<img src=") + "-" +  content.indexOf("title=\"helloworld\""));
      // console.log(content.substring(start+10,end-2))
      var url = content.substring(start + 10, end - 2);
      if (url.indexOf("png") != -1 || url.indexOf("jpg") != -1) {
        return url;
      }
      return this.titleImgUrl;
    },
  },
  computed: {},
};
</script>

<style lang="scss" scoped>
.blog-main {
}
.blog-article {
  width: 100%;
  margin: auto;
  background-color: #f9f9f9;
}
ul {
  margin-top: 0;
  margin-bottom: 0;
}
.titleImg {
  height: 100px;
  width: 150px;
  float: left;
  object-fit: contain;
}
</style>
