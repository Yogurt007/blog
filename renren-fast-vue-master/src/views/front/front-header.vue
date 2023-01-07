<template>
  <div class="header-main">
    <div class=""></div>
    <div class="navigation">
      <el-menu class="site-navbar__menu" mode="horizontal">
        <el-menu-item index="1" @click="$router.push({ name: 'front-home' })">
            <span class="title">首页</span>
        </el-menu-item>
        <el-menu-item index="2" @click="$router.push({ name: 'front-codeBook-list' })">
            <span class="title">码录</span>
        </el-menu-item>
        <el-menu-item index="3" @click="$router.push({ name: 'front-notes-list' })">
            <span class="title">随笔</span>
        </el-menu-item>
        <el-menu-item index="4" @click="$router.push({ name: 'front-record-list' })">
            <span class="title">文艺</span>
        </el-menu-item>
        <el-menu-item index="5">
            <span class="title">墙</span>
        </el-menu-item>
      </el-menu>  
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
.header-main {
  height: 150px;
  width: 70%;
  /* background-color: #A7D7C5;
  background-image: linear-gradient(#A7D7C5, #f9f9f9); */
  margin: auto;
  display: flex;
  justify-content: center;
  align-items: center;
}

.title{
  font-size: 150%;
}
</style>