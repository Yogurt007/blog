<template>
  <div class="mod-demo-ueditor">
    <!-- <el-alert
      title="提示："
      type="warning"
      :closable="false">
      <div slot-scope="description">
        <p class="el-alert__description">1. 此Demo只提供UEditor官方使用文档，入门部署和体验功能。具体使用请参考：http://fex.baidu.com/ueditor/</p>
        <p class="el-alert__description">2. 浏览器控制台报错“请求后台配置项http错误，上传功能将不能正常使用！”，此错需要后台提供上传接口方法（赋值给serverUrl属性）</p>
      </div>
    </el-alert> -->
    <div><el-input v-model="title"></el-input></div>
    <script
      :id="ueId"
      class="ueditor-box"
      type="text/plain"
      style="width: 100%; height: 400px"
    ></script>
    <el-button type="success" @click="updateStatus ? update() : save()">{{
      updateStatus ? "更新" : "保存"
    }}</el-button>
    <!-- 获取内容 -->
    <p><el-button @click="getContent()">获得内容</el-button></p>
    <el-dialog
      title="内容"
      :visible.sync="dialogVisible"
      :append-to-body="true"
    >
      {{ ueContent }}
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import ueditor from "ueditor";
export default {
  data() {
    return {
      title: "",
      ue: null,
      ueId: `J_ueditorBox_${new Date().getTime()}`,
      ueContent: "",
      dialogVisible: false,
      id: "",
      updateStatus: false,
      content: "Hello world!",
    };
  },
  mounted() {
    this.ue = ueditor.getEditor(this.ueId, {
      // serverUrl: '', // 服务器统一请求接口路径
      imageUrlPrefix: "http://localhost:8080/",

      zIndex: 3000,
    });
  },
  methods: {
    getContent() {
      this.dialogVisible = true;
      this.ue.ready(() => {
        this.ueContent = this.ue.getContent();
      });
    },
    save() {
      this.ueContent = this.ue.getContent();
      if (this.title.length != 0 && this.ueContent.length != 0) {
        this.$http({
          url: this.$http.adornUrl("/blog/article/save"),
          method: "post",
          data: this.$http.adornData({
            title: this.title,
            content: this.ueContent,
          }),
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "保存成功",
              type: "success",
              duration: 1500,
            });
            this.$router.push({ name: "blog-list" });
          }
        });
      } else {
        this.$message.error("保存失败");
      }
    },
    update(){
      this.ueContent = this.ue.getContent();
      if (this.title.length != 0 && this.ueContent.length != 0) {
        this.$http({
          url: this.$http.adornUrl("/blog/article/update"),
          method: "post",
          data: this.$http.adornData({
            id:this.id,
            title: this.title,
            content: this.ueContent,
          }),
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "更新成功",
              type: "success",
              duration: 1500,
            });
          }else{
            this.$message.error("更新失败");
          }
        });
      } else {
        this.$message.error("更新失败,内容为空");
      }
    },
    getArticleById() {
      this.$http({
        url: this.$http.adornUrl("/blog/article/get/" + this.id),
        method: "get",
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "获取文章成功",
            type: "success",
            duration: 1500,
          });
          this.updateStatus = true;
          console.log(data);
          this.title = data.article.title;
          this.ue.ready(() => {
            this.ue.setContent(data.article.content);
          });
        }
      });
    },
  },

  created() {},
  activated() {
    this.id = this.$route.params.id;
    if (this.id) {
      console.log("带ID传参:" + this.id);
      this.getArticleById();
    }
  },
};
</script>
<style lang="scss">
.mod-demo-ueditor {
  position: relative;
  z-index: 510;
  > .el-alert {
    margin-bottom: 10px;
  }
}
</style>
