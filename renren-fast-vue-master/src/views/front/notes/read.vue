<template>
  <div class="read-main">
    <div class="read-title">"{{ title }}"</div>
    <script
      :id="ueId"
      class="ueditor-box"
      type="text/plain"
      style="width: 100%; height: 700px"
    ></script>
  </div>
</template>

<script>
import ueditor from "ueditor";
export default {
  data() {
    return {
      ue: null,
      ueId: `J_ueditorBox_${new Date().getTime()}`,
      ueContent: "",
      id: "",
      updateStatus: false,
      title: "",
    };
  },
  created() {
    this.id = this.$route.params.id;
    if (this.id) {
      // console.log("带ID传参:" + this.id);
      this.getArticleById();
    } else {
      this.$message({
        message: "获取文章失败",
        type: "error",
        duration: 1500,
      });
      this.$router.push({ name: "front-blog-list" });
    }
  },
  mounted() {
    this.ue = ueditor.getEditor(this.ueId, {
      // serverUrl: '', // 服务器统一请求接口路径
      zIndex: 3000,
      readonly: true,
      toolbars: [""],
      initialContent: "hello world",
      enableAutoSave: false,
      wordCount: false,
    });
  },
  methods: {
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
          console.log(data.article);
          this.title = data.article.title;
          this.ue.ready(() => {
            this.ue.setContent(data.article.content);
          });
        }
        this.$http({
          url: this.$http.adornUrl("/blog/article/look/" + this.id),
          method: "get",
        });
      });
    },
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
.read-main {
  width: 70%;
  margin: auto;
}
.read-title {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 15px;
  font-size: 30px;
  color: cadetblue;
}
</style>