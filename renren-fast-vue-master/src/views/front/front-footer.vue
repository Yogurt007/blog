<template>
  <div class="footer-main">
    <span class="welcome">welcome in</span>
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
  display: flex;
  justify-content: center;
  align-items: center;
}
.welcome {
  font-weight: bold;
}
.footer-main span {
  text-align: center;
  /*使文字参照div居中显示*/
  background: linear-gradient(grey, rgb(236, 225, 225));
  -webkit-background-clip: text;
  /*使用了这个属性的意思是，以区块内的文字作为裁剪区域向外裁剪，
  * 文字的背景即为区块的背景，文字之外的区域都将被裁剪掉。*/
  color: transparent;
  /*定义文本颜色为透明*/
  font-weight: bolder; /*文本加粗*/
  font-size: 70px;
  /*文本大小*/
}
</style>