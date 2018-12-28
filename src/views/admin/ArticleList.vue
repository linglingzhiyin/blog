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
          <tr v-for="article in articles">
            <th scope="row">{{article.id}}</th>
            <td>{{article.title}}</td>
            <td>{{article.localTime}}</td>
            <td>{{article.click}}</td>
            <td>
              <button
                type="button"
                class="btn btn-outline-info btn-sm"
                @click="fullScreen(article.id)"
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
        </tbody>
      </table>
    </div>
    <nav aria-label="Page navigation example" style="position: absolute;bottom: 10px;left: 42%">
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
  name: "articleList",
  data: function() {
    return {
      pageIndex: 1,
      articles: [],
      current: 1,
      pages: 1,
      pagesArr: [],
      size: 5,
      total: 0,
      res: this.$axios.post("/api/admin/articleList").then(result => {
        this.res = result.data.result;
        this.articles = result.data.result.records;
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
        .post("/api/admin/articleList?" + "page=" + pageIndex)
        .then(result => {
          this.articles = result.data.result.records;
        });
    },
    fullScreen: function(articleId) {
       this.$router.push({
          name: 'articleDetail',
          params: {
            articleId: articleId
          }
        })

    }
  },
  components: {
    appHeader: Header
  }
};
</script>
