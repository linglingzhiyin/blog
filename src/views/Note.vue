<template>
  <div class="note">
    <h1>心得笔记</h1>
    <div class="row">
      <div class="col-lg-2 col-sm-3">
        <ul id="notexin">
          <li v-for="keyword in keywords">
            <a href="#">{{ keyword }}</a>
          </li>
        </ul>
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
              <a href="#">阅读全文</a>
            </footer>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Note",
  data: function() {
    return {
      articleList: [],
      keywords: ["java","spring","springboot"]
    };
  },
  props: {},
  created() {
    //fetch
    this.$axios.get("/api/articleList").then(result => {
      this.articleList = result.data;
    });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
