<template>
  <div class="record-main">
    <div>
      <article class="i-article-list">
        <ul class="of">
          <li v-for="(artDto, index) in artDtoList" :key="index">
            <section class="i-article-wrap">
              <div>
                <span class="time">{{
                  artDto.blogRecordsEntity.createTime.substring(0, 10)
                }}</span>
              </div>
              <div class="art-title">
                <span class="title">"{{ artDto.blogRecordsEntity.title }}"</span>
              </div>
              <div>
                <div v-for="(art, index) in artDto.artList" :key="index" class="art-div">
                  <img :src="art.url" class="art-img" />
                </div>
              </div>
            </section>
            <div class="line"></div>
          </li>
        </ul>
      </article>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      artDtoList: [],
    };
  },
  methods: {
    getArtDtoList() {
      this.$http({
        url: this.$http.adornUrl("/blog/records/list"),
        method: "get",
        params: this.$http.adornParams(),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.artDtoList = data.artDtoList;
        }
      });
    },
  },
  created() {
    this.getArtDtoList();
  },
};
</script>

<style>
.record-main {
  width: 70%;
  margin: auto;
  background-color: #f9f9f9;
}
.of {
  list-style: none;
}
.time {
  font-weight: bold;
  font-size: 30px;
  color: #ccc;
}
.art-img {
  height: 200px;
  object-fit: contain;
  border-radius: 20px;
  margin-right: 20px;
}
.art-div{
  display: inline-block;
  margin-bottom: 20px;
}
.art-title{
  margin-top: 10px;
  margin-bottom: 10px;
}
.line{
  width: 95%;
  height: 1px;
  border-top: 1px solid #ccc;
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>