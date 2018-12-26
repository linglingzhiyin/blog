<template>
  <div class="articleList">
    <app-header></app-header>
    <div class="container">
      <table class="table table-sm">
        <thead>
          <tr class="table-info">
            <th>id</th>
            <th width="25%">标题</th>
            <th>发表时间</th>
            <th>点击量</th>
            <th>详情</th>
            <th>评论</th>
            <th>编辑</th>
            <th>删除</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${articles}" var="article">
            <tr>
              <th scope="row">${article.id}</th>
              <td>${article.title}</td>
              <td>${article.localTime}</td>
              <td>${article.click}</td>
              <td>
                <button
                  type="button"
                  class="btn btn-outline-info btn-sm"
                  onclick="fullScreen('《${article.title}》','/admin/article/detail?id=${article.id}')"
                >详情</button>
              </td>
              <td>
                <button
                  type="button"
                  class="btn btn-outline-success btn-sm"
                  onclick="fullScreen('《${article.title}》|评论管理','/admin/article/comment?id=${article.id}')"
                >评论</button>
              </td>
              <td>
                <button
                  type="button"
                  class="btn btn-outline-primary btn-sm"
                  onclick="fullScreen('《${article.title}》|编辑','/admin/article/edit?id=${article.id}')"
                >编辑</button>&nbsp;&nbsp;
              </td>
              <td>
                <button
                  type="button"
                  class="btn btn-outline-danger btn-sm"
                  onclick="ifdelete('${article.id}','${article.title}') "
                >删除</button>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/admin/Header.vue";

export default {
  name: "articleList",
  data: function() {
    return {};
  },
  components: {
    appHeader: Header
  }
};
</script>
