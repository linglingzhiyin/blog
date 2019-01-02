<template>
  <div id="detail">
    <app-header></app-header>
    <div class="container">
      <div>
        <br>
        <div>
          <div class="btn btn-success">文章标题</div>
          {{article.title}}
        </div>
        <br>
        <div v-if="article.keywords.length>0">
          <div class="btn btn-success">关键字</div>
          {{article.keywords}}
        </div>
        <br>
        <div>
          <div class="btn btn-success">简介</div>
          {{article.desci}}
        </div>
        <br>
        <div>
          <div class="btn btn-success">发表时间</div>
          {{article.time}}
        </div>
        <br>
        <div>
          <div class="btn btn-success">点击量</div>
          {{article.click}}
        </div>
        <br>
        <div>
          <div class="btn btn-success">内容</div>
          <div id="vhtml" class="container" v-html="article.content"></div>
        </div>
      </div>--------------------------------------------------------------------------------------------------------------
      <br>
      <div v-if="comments.length>0">
        <div class="btn btn-success">评论</div>
        <div class="container" v-for="comment in comments">
          {{ lou++ }}楼&nbsp;&nbsp;{{comment.name}}&nbsp;&nbsp;{{comment.date}}
          <p>{{comment.content}}</p>
        </div> 
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";

export default {
  name: "detail",
  data: function() {
    return {
      lou: 1,
      comments: null,
      article: this.$axios
        .get("/api/article/" + this.$route.params.articleId)
        .then(result => {
          this.article = result.data.article;
          this.comments = result.data.comments.records;
        })
    };
  },
  components: {
    appHeader: Header
  },
  methods: {}
};
</script>
