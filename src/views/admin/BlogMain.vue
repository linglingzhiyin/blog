<template>
  <div class="blogMain">
    <app-header></app-header>
    <div class="card mb-3">
      <div style="height: 180px;overflow: hidden">
        <!-- <img
          class="card-img-top"
          src="../../../assets/images/82839-106.jpg"
          alt="Card image cap"
          style="height: 100%;width:100%;"
        >-->
      </div>

      <div class="card-body">
        <h4 class="card-title">${admin.username}}</h4>
        <p class="card-text">
          <small class="text-muted">上次登录时间:${loginLog.localTime}}</small>
        </p>
        <p class="card-text">
          <small class="text-muted">上次登录IP:${loginLog.ip}}</small>
        </p>
        <p class="card-text">
          <small class="text-muted">本次登录IP:${clientIp}}</small>
        </p>
      </div>
    </div>
    <div>
      <table class="table table-hover">
        <p class="text-success" style="text-align: center">系统统计</p>
        <thead>
          <tr>
            <th>#</th>
            <th>文章数</th>
            <th>评论数</th>
            <th>登陆次数</th>
          </tr>
        </thead>
        <tbody>
          <tr class="table-success">
            <th scope="row">全部</th>
            <td>{{res.articleCount}}</td>
            <td>{{res.commentCount}}</td>
            <td>{{res.loginNum}}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div style="width: 50%;position: relative;left: 25%">
      <table class="table table-sm">
        <p class="text-success" style="text-align: center">系统信息</p>

        <tr>
          <th scope="row">服务器IP</th>
          <td>{{res.hostIp}}</td>
        </tr>
        <tr>
          <th scope="row">服务器端口</th>
          <td>{{res.hostPort}}</td>
        </tr>
        <tr>
          <th scope="row">服务器当前时间</th>
          <td>{{res.date}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/admin/Header.vue";

export default {
  name: "blogMain",
  data: function() {
    return {
      res: this.$axios.get("/api/main").then(result => {
        this.res = result.data;
      })
    };
  },
  components: {
    appHeader: Header
  }
};
</script>
