<template>
  <!-- <link rel="stylesheet" href="../assets/css/bootstrap4.0.min.css" > -->
  <div id="app">
    <app-header></app-header>
    <div class="container">
      <router-view/>
    </div>
  </div>
</template>


<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";

export default {
  components: {
    appHeader: Header
  }
};
</script>

<style>
</style>
