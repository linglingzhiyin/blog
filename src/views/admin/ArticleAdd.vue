<template>
  <div class="articleAdd">
    <app-header></app-header>
    <div class="container">
      <div class="form-group">
        <label for="title">文章标题</label>
        <input class="form-control" v-model="article.title" placeholder="标题">
      </div>
      <div class="form-group">
        <label for="catalogId">栏目</label>
        <select class="form-control" v-model="article.catalogId">
          <option
            class="form-control"
            v-for="option in options"
            v-bind:value="option.value"
          >{{ option.text }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="keywords">关键字</label>
        <input class="form-control" v-model="article.keywords" placeholder="关键字">
      </div>

      <div class="form-group">
        <label for="desci">简介</label>
        <textarea class="form-control" rows="3" v-model="article.desci" placeholder="简介"></textarea>
      </div>

      <div class="form-group">
        <label for="div1">内容</label>
        <markdown-blog :content="article.content" @transferContent="getContent"></markdown-blog>
      </div>
      <button class="btn btn-success" id="fabiao" @click="onClick">发表</button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/admin/Header.vue";
import MarkdownBlog from "@/components/MarkdownBlog.vue";
export default {
  name: "articleAdd",
  data() {
    return {
      article: {
        title: "",
        content: "",
        catalogId: 0,
        keywords: "",
        desci: ""
      },
      options: [{ text: "学习", value: 0 }, { text: "生活", value: 1 }]
    };
  },
  methods: {
    onClick: function() {
      this.$axios.post("/api/admin/article", this.article).then(result => {
        //alert("2222" + this.article.content);
        if (result.data.succ != null) alert(result.data.succ);
        else alert(result.data.error);
        // this.article.title = null;
        // this.article.content = null;
        // this.article.catalogId = 0;
        // this.article.keywords = null;
        // this.article.desci = null;
        this.$router.go(0);
      });
    },
    getContent(content) {
      this.article.content = content;
    }
  },
  components: {
    appHeader: Header,
    MarkdownBlog
  }
};
</script>
