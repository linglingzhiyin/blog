<template>
  <div id="note">
    <app-header/>
    <div class="container">
      <div class="row">
        <div class="col-lg-2 col-sm-3">
          <div class="span3 bs-docs-sidebar">
            <ul id="notexin" class="nav-list">
              <li v-for="(value, key) in keywords">
                <label @click="onClick1(key)">{{key}}({{value}})</label>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-lg-10 col-sm-9">
          <ul id="notexin">
            <li v-for="article in articleList">
              <!-- <time>{{article.localTime}}</time> -->
              <h2 class="title">
                <a href="#">{{article.title}}</a>
              </h2>
              <span>
                <i>{{article.keywords}}</i>
              </span>
              <section class="article-content markdown-body">
                <blockquote>
                  <p>{{article.desci}}</p>
                </blockquote>......
              </section>
              <footer>
                <button
                  type="button"
                  class="btn btn-outline-primary btn-sm"
                  @click="fullScreen('detail',article.id)"
                >阅读全文</button>
              </footer>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <nav aria-label="Page navigation example" style="position: absolute;bottom: 10px;left: 42%">
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" @click="onClick(staticKey,1)">&laquo;</a>
        </li>
        <li class="page-item" v-for="pageIndex in pages">
          <a class="page-link" @click="onClick(staticKey,pageIndex)">{{pageIndex}}</a>
        </li>
        <li class="page-item">
          <a class="page-link" @click="onClick(staticKey,pages)">&raquo;</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";

import NoteCom from "@/components/NoteCom.vue";

export default {
  name: "Note",
  components: {
    NoteCom,
    appHeader: Header
  },
  data: function() {
    return {
      current: 1,
      pages: 1,
      pagesArr: [],
      size: 3,
      total: 0,
      staticKey: "",
      articleList: this.$axios.get("/api/articleList").then(result => {
        this.articleList = result.data.result.records;
        this.current = result.data.result.current;
        var i = 1;
        for (; i <= result.data.result.pages; i++) {
          this.pagesArr[i - 1] = i;
        }
        this.pages = result.data.result.pages;
        this.size = result.data.result.size;
      }),
      keywords: this.$axios.get("/api/keywordList").then(result => {
        this.keywords = result.data;
      })
    };
  },
  props: {},
  methods: {
    onClick1: function(keyword) {
      if (keyword == "Count") {
        this.$axios.get("/api/articleList").then(result => {
          this.articleList = result.data.result.records;
          this.current = result.data.result.current;
          var i = 1;
          for (; i <= result.data.result.pages; i++) {
            this.pagesArr[i - 1] = i;
          }
          this.pages = result.data.result.pages;
          this.size = result.data.result.size;
        });
      } else {
        this.staticKey = keyword;
        this.$axios.get("/api/articleList/" + keyword).then(result => {
          this.articleList = result.data.result.records;
          var i = 1;
          for (; i <= result.data.result.pages; i++) {
            this.pagesArr[i - 1] = i;
          }
          this.pages = result.data.result.pages;
        });
      }
    },
    fullScreen: function(url, articleId) {
      this.$router.push({
        name: url,
        params: {
          articleId: articleId
        }
      });
    },
    onClick: function(keyword, pageIndex) {
      if (keyword != "")
        this.$axios
          .get("/api/articleList/" + keyword + "?page=" + pageIndex)
          .then(result => {
            this.articleList = result.data.result.records;
          });
      else
        this.$axios
          .get("/api/articleList?" + "page=" + pageIndex)
          .then(result => {
            this.articleList = result.data.result.records;
          });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
