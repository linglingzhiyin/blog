<template>
  <Header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarTogglerDemo03"
        aria-controls="navbarTogglerDemo03"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <router-link class="navbar-brand text-success" to="/blogMain">博客管理</router-link>

      <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <li class="nav-item">
            <router-link class="nav-link" to="/articleAdd">添加文章</router-link>
          </li>
          <!-- <li class="nav-item active">
            <a class="nav-link" href="/blogMain">主页</a>
          </li> -->
          <li class="nav-item">
              <router-link class="nav-link" to="/articleList">文章管理</router-link>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/admin/article/search" method="GET">
          <input
            class="form-control mr-sm-2"
            type="search"
            placeholder="文章标题或内容..."
            aria-label="Search"
            name="word"
          >
          <button class="btn btn-outline-success my-2 my-sm-0 btn-sm" type="submit">搜索</button>
        </form>
        <a class="btn btn-outline-danger btn-sm" href="/admin/logout" role="button">退出</a>
      </div>
    </nav>
  </Header>
</template>
