<template>
  <div class="wall-main">
    <div :class="{ 'wall-btn': !show, 'wall-btn-img': show }">
      <button @click="showImg">
        <img src="~@/assets/img/cat.jpg" style="height: 100px" />
      </button>
      <div>
      </div>
    </div>
  </div>
</template>

<script>
import { set } from 'lodash-es';
export default {
  data() {
    return {
      show: false,
      content:"1",
    };
  },
  methods: {
    showImg() {
      console.log("点击");
      this.changeShow()
    },
    changeShow(){
      if(this.show){
        this.show = false
      }else{
        this.show = true
      }
    }
  },
};
</script>

<style>
.wall-main {
  width: 70%;
  height: 700px;
  margin: auto;
  background-color: #f9f9f9;
}
.wall-btn {
  height: 500px;
  line-height: 500px;
  text-align: center;
  width: 70%;
  margin: auto;
  border: none;
  background-color: white;
}
.wall-btn-img {
  height: 500px;
  line-height: 500px;
  width: 70%;
  margin: auto;
  border: none;
  background-color: black;
  background-image: url(~@/assets/img/erha.png);
  background-size:contain
}
</style>