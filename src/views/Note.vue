<template>
  <div id="note">
    <app-header/>
    <div class="container">
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
          <note-com/>
        </div>
      </div>
    </div>
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
      articleList: [],
      keywords: ["java", "spring", "springboot"]
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
