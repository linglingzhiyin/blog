<template>
  <!-- <link rel="stylesheet" href="../assets/css/bootstrap4.0.min.css" > -->
  <div id="app">
    <router-view name="home"/>
    <router-view/>
    <router-view name="admin"/>

    <div class="container">
       <router-view name="note"></router-view>
       <router-view name="about"></router-view>
    </div>
  </div>
</template>


<script>
// @ is an alias to /src
export default {
  components: {}
};
</script>

<style>
</style>
