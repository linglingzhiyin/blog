<template>
  <Header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
     <h1>朾的个人博客</h1>
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link class="nav-link" to="/">主页</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="#">心得笔记</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="#">陌上花开</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="#">下载</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/about">关于</router-link>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto" >
        <li class="nav-item">
          <router-link class="nav-link" to="#">登陆</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="#">注册</router-link>
        </li>
      </ul>
    </nav>
  </Header>
</template>
