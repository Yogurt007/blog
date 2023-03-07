<template>
  <div class="main">
    <div class="header">
      <front-header></front-header>
    </div>
    <div class="content"><router-view /></div>
    <div class="footer">
      <front-footer></front-footer>
    </div>
  </div>
</template>

<script>
import FrontHeader from "./front-header.vue";
import FrontFooter from "./front-footer.vue";
export default {
  name: "front",
  components: {
    FrontHeader,
    FrontFooter,
  },
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  background-image: url(~@/assets/img/front_bg2.png);
  background-size: cover;
}
.content{
  margin: 0;
  padding: 0;
  width: 70%;
  margin: auto;
  animation: animated-border 1.5s infinite;
  line-height: 30px;
  font-weight: bold;
  color: white;
  border: 2px solid;
  border-radius: 10px;
}
@keyframes animated-border {
  0% {
    box-shadow: 0 0 0 0 rgba(255,255,255,0.4);
  }
  100% {
     box-shadow: 0 0 0 30px rgba(255,255,255,0);
  }
}
</style>