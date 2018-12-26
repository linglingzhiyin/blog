<template>
  <!-- <link rel="stylesheet" href="../assets/css/bootstrap4.0.min.css" > -->
  <div id="app">
    <router-view/>
    <div class="container">
      <div class="row">
        <div class="col-md-3">
          <router-view name="note"></router-view>
        </div>
      </div>
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
