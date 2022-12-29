<template>
  <div>
    <el-input v-model="title" placeholder="请输入你的标题~"></el-input> <br>
    <el-input v-model="content" placeholder="描述你的内容" type="textarea"></el-input> <br>
    <el-upload
      class="upload"
      :action="url"
      list-type="picture-card"
      name="file"
      :file-list="fileList"
      :before-upload="beforeUploadHandle"
      :on-success="successHandle"
    >
      <el-icon><Plus />+</el-icon>
    </el-upload>

    <!-- <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog> -->
    <el-button type="success" @click="save()">保存</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fileList: [],
      urlList: [],
      url: "",
      title: "",
      content: "",
      describeList: [],
    };
  },
  methods: {
    // 上传之前
    beforeUploadHandle(file) {
      if (
        file.type !== "image/jpg" &&
        file.type !== "image/jpeg" &&
        file.type !== "image/png" &&
        file.type !== "image/gif"
      ) {
        this.$message.error("只支持jpg、png、gif格式的图片！");
        return false;
      }
    },
    // 上传成功
    successHandle(response, file, fileList) {
      this.fileList = fileList;
      if (response && response.code === 0) {
        var url = response.url;
        console.log("返回阿里云的地址为：" + url);
        this.urlList.push(url);
      } else {
        this.$message.error(response.msg);
      }
    },
    save() {
      this.$http({
        url: this.$http.adornUrl("/blog/records/save"),
        method: "post",
        data: this.$http.adornData({
          title: this.title,
          content: this.content,
          describe: this.describeList,
          url: this.urlList
        }),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "保存成功",
            type: "success",
            duration: 1500,
          });
        } else {
          this.$message.error("保存失败");
        }
      });
    },
  },
  created() {
    this.url = this.$http.adornUrl(
      `/sys/oss/upload?token=${this.$cookie.get("token")}`
    );
  },
};
</script>

<style>
</style>