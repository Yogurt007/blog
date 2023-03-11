<template>
  <div class="footer-main">
    <div class="welcome">
      <span class="welcome">welcome in</span>
    </div>
    
    <div>
      <a href="https://beian.miit.gov.cn/" target="_blank">备案号：粤ICP备2023014587号</a>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.footer-main {
  height: 100px;
  width: 70%;
  margin: auto;
  /* background-color:#f9f9f9; */
  
}
.welcome {
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
}
.footer-main span {
  text-align: center;
  /*使文字参照div居中显示*/
  background: linear-gradient(grey, rgb(236, 225, 225));
  -webkit-background-clip: text;
  color: transparent;
  font-weight: bolder; /*文本加粗*/
  font-size: 70px;
}
.footer-main a{
  text-align: center;
  /*使文字参照div居中显示*/
  background: linear-gradient(grey, rgb(236, 225, 225));
  -webkit-background-clip: text;
  color: transparent;
  font-weight: bolder; /*文本加粗*/
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>