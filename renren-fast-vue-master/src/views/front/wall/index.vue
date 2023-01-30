<template>
  <div class="wall-main">
    <div :class="{ 'wall-btn': !show, 'wall-btn-img': show }">
      <button @click="showImg">
        <img src="~@/assets/img/cat.jpg" style="height: 100px" />
      </button>
      <div></div>
    </div>
    <div>
      <el-input
        v-model="content"
        :autosize="{ minRows: 5, maxRows: 10 }"
        type="textarea"
        placeholder="Please input"
      />
    </div>
    <div>
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

        <el-table-column prop="content">
        </el-table-column>
        <el-table-column prop="createTime" width="100" />
      </el-table>
      <el-pagination
        :current-page="page"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center"
        layout="total, prev, pager, next, jumper"
        @current-change="getPageInfo"
      />
    </div>
  </div>
</template>

<script>
import { set } from "lodash-es";
export default {
  data() {
    return {
      show: false,
      content: "",
      list: null,
      page: 1, //开始页
      limit: 8, //每页记录数
      total: 10, //总记录数
    };
  },
  methods: {
    showImg() {
      console.log("点击");
      if(this.content.length >= 1){
        this.save();
      }
      this.changeShow();
    },
    changeShow() {
      if (this.show) {
        this.show = false;
      } else {
        this.show = true;
      }
    },
    save(){
      console.log("保存了");
      this.$http({
        url: this.$http.adornUrl("/blog/comment/save"),
        method: "post",
        data: this.$http.adornData({
          content: this.content
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "留言成功，祝你开心每一天~",
            type: "success",
            duration: 2000,
          });
          this.content = "";
        } else {
          this.$message.error("留言失败");
        }
      });
    },
    getPageInfo(page = 1){  
      this.page = page;
      this.$http({
        url: this.$http.adornUrl(
          "/blog/comment/page/" + this.page + "/" + this.limit
        ),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.list = data.pageInfo.records;
          console.log(this.list);
          this.total = data.pageInfo.records.length;
        } else {
          this.$message.error("获取评论列表失败");
        }
      });
    }
  },
  created(){
    this.getPageInfo()
  }
};
</script>

<style>
.wall-main {
  width: 70%;
  margin: auto;
  background-color: #f9f9f9;
}
.wall-btn {
  height: 335px;
  line-height: 335px;
  text-align: center;
  padding-left: 45%;
  border: none;
  background-color: white;
  background-image: url(~@/assets/img/comment.jpg);
  background-repeat: no-repeat;
}
.wall-btn-img {
  height: 335px;
  line-height: 335px;
  text-align: center;
  padding-left: 45%;
  border: none;
  background-color: black;
  background-image: url(~@/assets/img/erha.png);
  background-size: contain;
}
</style>