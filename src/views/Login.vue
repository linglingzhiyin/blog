<template>
  <div id="login">
    <app-header :getCode="code"></app-header>
    <div v-if="code==0">登陆成功
      <router-link class="nav-link" to="/">跳转</router-link>
    </div>
    <div class="text-center" v-if="code==1">
      <br>
      <br>
      <label class="control-label">用户名：</label>
      <input type="text" v-model="username">
      <br>
      <br>
      <label class="control-label" for="inputPassword">密&nbsp&nbsp&nbsp&nbsp码：</label>
      <input type="password" v-model="password">
      <br>
      <button type="button" class="btn btn-success" @click="onclick1(username,password)">登陆</button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from "@/components/Header.vue";

export default {
  data: function() {
    return {
      username: "",
      password: "",
      code: 1
    };
  },
  name: "login",
  components: {
    appHeader: Header
  },
  methods: {
    onclick1: function(username, password) {
      this.$axios
        .post(
          "/api/loginSucc?username=" +
            this.username +
            "&password=" +
            this.password
        )
        .then(result => {
          if (result.data.code != 0) {
            alert("登陆失败");
            this.$router.go(0);
          }
          this.code = result.data.code;
        });
    }
  }
};
</script>
