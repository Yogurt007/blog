<template>
  <div class="home-body">
    <div class="swiper-container">
      <div class="swiper-wrapper">
        <div
          class="swiper-slide"
          v-for="(banner, index) in bannerList"
          :key="index"
        >
          <img :src="banner.url" />
        </div>
        <!-- <div class="swiper-slide">
          <img src="~@/assets/img/front_index.png" />
        </div>
        <div class="swiper-slide">
          <img :src="img2" />
        </div>
        <div class="swiper-slide">
          <img :src="img3" />
        </div> -->
      </div>
      <!-- 如果需要分页器 -->
      <div class="swiper-pagination"></div>
      <!-- 如果需要导航按钮 -->
      <!-- <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div> -->
    </div>
  </div>
</template>

<script>
import Swiper from "swiper"; // 注意引入的是Swiper
import "swiper/css/swiper.min.css"; // 注意这里的引入
export default {
  name: "home",
  data() {
    return {
      // swiper实例  实例上有各种方法
      swiperInstance: null,
      img2: "https://edu-po.oss-cn-beijing.aliyuncs.com/blog/luyuan.jpg",
      img3: "https://edu-po.oss-cn-beijing.aliyuncs.com/20230130/f297b99932da40eeb725723ee5b0be11.png",
      bannerList: null,
    };
  },
  mounted() {
    var swiperInstance = new Swiper(".swiper-container", {
      // config...
      pagination: {
        el: ".swiper-pagination",
      },
      observer: true, //修改swiper自己或子元素时，自动初始化swiper
      observeParents: true, //修改swiper的父元素时，自动初始化swiper
    });
  },
  methods: {
    getBannerList() {
      this.$http({
        url: this.$http.adornUrl("/blog/records/banner"),
        method: "get",
        params: this.$http.adornParams(),
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.bannerList = data.bannerList;
        }
      });
    },
  },
  created() {
    this.getBannerList();
  },
};
</script>

<style>
.home-body {
  height: 750px;
  width: 100%;
  margin: auto;
  background-color: #fbfbfb;
  background-image: url(~@/assets/img/front_index.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.swiper-container {
  width: 100%;
  height: 100%;
}
.swiper-slide img {
  width: 100%;
  height: 100%;
}
</style>