<template>
  <div id="NoteCom">
 
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
</template>

<script>
export default {
  name: "NoteCom",
  components: {},
  data: function() {
    return {
  
      articleList: [],
      keywords: []
    };
  },
  props: {},
  created() {
    //fetch
    this.$axios.get("/api/articleList").then(result => {
      this.articleList = result.data.result.records;
    });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
