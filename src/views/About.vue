<template>
  <div id="about">
    <app-header/>
    <div class="container">
      <about-com/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";

import AboutCom from "@/components/AboutCom.vue";
export default {
  name: "about",
  components: {
    AboutCom,
    appHeader: Header
  }
};
</script>