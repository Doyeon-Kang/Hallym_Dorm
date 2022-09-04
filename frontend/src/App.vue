<template>
  <div class="header">
    <Navbar></Navbar>
  </div>
  <div class="container">
    <router-view />
  </div>
</template>

<script>
import Navbar from "@/components/NavbarCom.vue";

export default {
  name: "app",
  components: {
    Navbar,
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

.header {
  width: 100%;
  height: 60px;
}

.container {
  width: 1440px;
  margin: 0 auto;
}
</style>
