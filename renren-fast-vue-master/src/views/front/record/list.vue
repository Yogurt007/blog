<template>
  <div class="record-main">
    <div class="div-article">
      <article>
        <ul class="of">
          <li v-for="(artDto, index) in artDtoList.slice(0, num)" :key="index">
            <section class="i-article-wrap">
              <div class="div-time">
                <span class="time">{{
                  artDto.blogRecordsEntity.createTime
                    .substring(0, 10)
                    .replaceAll("-", ".")
                }}</span>
              </div>
              <div class="art-title">
                <span class="title">{{ artDto.blogRecordsEntity.title }}</span>
              </div>
              <div>
                <div
                  v-for="(art, index) in artDto.artList"
                  :key="index"
                  class="art-div"
                  v-viewer
                >
                  <img :src="art.url" class="art-img" />
                </div>
              </div>
            </section>
            <div class="line"></div>
          </li>
        </ul>
      </article>
    </div>
    <div style="width: 100%">
      <button class="loading-btn" @click="loading" v-show="loadingStatus">
        <img src="~@/assets/img/loading.png" style="height: 100px" />
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loadingStatus: true,
      num: 5,
      artDtoList: [],
      image: "https://edu-po.oss-cn-beijing.aliyuncs.com/blog/luyuan.jpg",
    };
  },
  comments: {},
  methods: {
    getArtDtoList() {
      this.$http({
        url: this.$http.adornUrl("/blog/records/list"),
        method: "get",
        params: this.$http.adornParams(),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.artDtoList = data.artDtoList;
          if (this.num >= this.artDtoList.length) {
            this.loadingStatus = false;
          }
        }
      });
    },
    loading() {
      if (this.num >= this.artDtoList.length) {
        this.loadingStatus = false;
        return;
      }
      this.num = this.num + 5;
    },
    scrollBottom() {
      let scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop;
      let clientHeight = document.documentElement.clientHeight;
      let scrollHeight = document.documentElement.scrollHeight;
      if (scrollTop + clientHeight >= scrollHeight) {
        this.loading();
      }
    },
  },
  created() {
    this.getArtDtoList();
    window.addEventListener("scroll", this.scrollBottom);
  },
  mounted() {},
};
</script>

<style>
.record-main {
  width: 100%;
  margin: 0;
  padding: 0;
  background-color: #f9f9f9;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif
}
.of {
  list-style: none;
}
.time {
  font-weight: bold;
  font-size: 30px;
  color: #ccc;
}
.div-time{
  padding-top: 30px;
}
.art-img {
  height: 200px;
  width: 300px;
  object-fit: contain;
  margin-right: 20px;
  cursor: pointer;
  border: 1px solid gainsboro;
}
.art-div {
  display: inline-block;
  margin-bottom: 20px;
}
.art-title {
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: 20px;
  color: #ccc;
}
.line {
  width: 95%;
  height: 1px;
  border-top: 1px solid #ccc;
  margin-top: 30px;
}
.loading-btn:focus {
  outline: none;
}
.loading-btn {
  text-align: center;
  border: none;
  margin: auto;
  display: flex;
  justify-content: center;
  align-items: center;
}
ul{
  margin: 0;
}
</style>