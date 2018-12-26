<template>
  <div class="articleDetail">
    <app-header></app-header>
    <table class="table table-striped table-sm">
      <tr class="table-active">
        <th width="15%">ID</th>
        <td>${article.id}</td>
      </tr>
      <tr class="table-secondary">
        <th>标题</th>
        <td>${article.title}</td>
      </tr>
      <tr class="table-success">
        <th>关键字</th>
        <td>${article.keywords}</td>
      </tr>
      <tr class="table-danger">
        <th>简介</th>
        <td>${article.desci}</td>
      </tr>
      <tr class="table-warning">
        <th>发表时间</th>
        <td>${article.localTime}</td>
      </tr>
      <tr class="table-info">
        <th>点击量</th>
        <td>${article.click}</td>
      </tr>
      <tr class="table-light">
        <th>内容</th>
        <td>${article.content}</td>
      </tr>
    </table>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/admin/Header.vue";

export default {
  name: "articleDetail",
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
