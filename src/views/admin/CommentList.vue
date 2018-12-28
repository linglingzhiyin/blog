<template>
  <div id="commentList">
    <app-header></app-header>
    <div style="position: relative;top: 10%">
      <!-- <c:if test="${!empty succ}">
        <div class="alert alert-success" role="alert">
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
      </c:if>-->
    </div>
    <div class="container">
      <!-- <c:if test="${!empty comments}"> -->
      <table class="table">
        <thead class="thead-default">
          <tr>
            <th>流水号</th>
            <th>评论内容</th>
            <th>日期</th>
            <th>昵称</th>
            <th>邮箱</th>
            <th>删除</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comment in comments">
            <th scope="row">{{comment.id}}</th>
            <td>{{comment.content}}</td>
            <td>{{comment.date}}</td>
            <td>{{comment.name}}</td>
            <td>{{comment.email}}</td>
            <td>
              <button
                type="button"
                class="btn btn-outline-danger btn-sm"
              >删除</button>
            </td>
          </tr>
        </tbody>
      </table>
        <div class="card" v-show="comments.length==0">
            <div class="card-body">
                该文章暂无评论!
            </div>
        </div>
    </div>
    <nav aria-label="Page navigation example" v-show="comments.length!=0" style="position: absolute;bottom: 10px;left: 42%">
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" @click="onClick(1)">&laquo;</a>
        </li>
        <li class="page-item" v-for="pageIndex in pages">
          <a class="page-link" @click="onClick(pageIndex)">{{pageIndex}}</a>
        </li>
        <li class="page-item">
          <a class="page-link" @click="onClick(pages)">&raquo;</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/admin/Header.vue";

export default {
  name: "commentList",
  data: function() {
    return {
      articleId:this.$route.params.articleId,
      comments: [],
      current: 1,
      pages: 1,
      pagesArr: [],
      size: 5,
      comment: this.$axios
        .get(
          "/api/admin/commentList?" +
            "articleId=" +
            this.$route.params.articleId
        )
        .then(result => {
          this.comments = result.data.result.records;
          this.current = result.data.result.current;
          var i = 1;
          for (; i <= result.data.result.pages; i++) {
            this.pagesArr[i - 1] = i;
          }
          this.pages = result.data.result.pages;
          this.size = result.data.result.size;
        })
    };
  },
  methods: {
    onClick: function(pageIndex) {
      this.$axios
        .get("/api/admin/commentList?" +
            "articleId=" +this.articleId+ "&page=" + pageIndex)
        .then(result => {
          this.comments = result.data.result.records;
        });
    }
  },
  components: {
    appHeader: Header
  }
};
</script>
