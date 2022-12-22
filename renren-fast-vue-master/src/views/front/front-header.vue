<template>
  <div class="header-main">
    <div class=""></div>
    <div class="navigation">
      <el-menu class="site-navbar__menu" mode="horizontal">
        <el-menu-item index="home" @click="$router.push({ name: 'front-home' })">
            <span class="title">首页</span>
        </el-menu-item>
        <el-menu-item index="Fblog-list" @click="$router.push({ name: 'front-blog-list' })">
            <span class="title">博客</span>
        </el-menu-item>
        <el-menu-item index="record-list" @click="$router.push({ name: 'record-list' })">
            <span class="title">生活</span>
        </el-menu-item>
        <el-menu-item index="3">
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