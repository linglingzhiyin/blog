<template>
  <div class="home">
    <app-header></app-header>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";

export default {
  name: "home",
  components: {
    appHeader: Header
  }
};
</script>
