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
  background-image: url(~@/assets/img/front_bg1.png);
  background-size: cover;
}
</style>