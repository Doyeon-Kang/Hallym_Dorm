<template>
  <Navbar></Navbar>
  <router-view />
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
