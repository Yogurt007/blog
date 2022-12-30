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
              <div>
                <span>{{ artDto.blogRecordsEntity.title }}</span>
              </div>
              <div>
                <div v-for="(art, index) in artDto.artList" :key="index" class="art-div">
                  <img :src="art.url" class="art-img" />
                </div>
              </div>
            </section>
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
          console.log("获取记录成功");
          console.log(data.artDtoList);
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
}
.art-img {
  height: 250px;
  object-fit: contain;
  border-radius: 20px;
}
.art-div{
  display: inline-block;
  margin-bottom: 10%;
}
</style>