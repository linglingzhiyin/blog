<template>
  <div id="articleDetail">
    <app-header></app-header>
    <div class="container">
      <label for="title">文章标题</label>
      <label class="form-control">{{article.title}}</label>
      <label for="title">文章标题</label>
      <label class="form-control">{{article.title}}</label>
      <div v-if="article.keywords.length>0">
        <label for="title">关键字</label>
        <label class="form-control">{{article.keywords}}</label>
      </div>
      <label for="title">简介</label>
      <label class="form-control">{{article.desci}}</label>
      <label for="title">发表时间</label>
      <label class="form-control">{{article.time}}</label>
      <label for="title">点击量</label>
      <label class="form-control">{{article.click}}</label>
      <label for="title">内容</label>
      <div id="vhtml" v-html="article.content"></div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/admin/Header.vue";

export default {
  name: "articleDetail",
  data: function() {
    return {
      article: this.$axios
        .get(
          "/api/admin/article?" +
            "articleId=" +
            this.$route.params.articleId
        )
        .then(result => {
          this.article = result.data.article;
        })
    };
  },
  components: {
    appHeader: Header
  },
  methods: {}
};
</script>
