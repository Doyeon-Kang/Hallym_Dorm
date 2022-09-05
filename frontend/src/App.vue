<template>
  <div class="header">
    <Navbar
      v-show="$route.name !== 'login' && $route.name !== 'mypage'"
    ></Navbar>
  </div>
  <div class="banner" v-show="$route.name === 'home'">
    <img src="@/assets/banner.jpg" alt="배너이미지" />
    <div class="container">
      <div class="wrapper">
        <span class="sub">편리한, 안전한, 다양한, 다문화 기숙사</span>
        <h1>한림대학교 학생생활관</h1>
        <a href="/intro" class="btn">생활관소개 바로가기</a>
      </div>
    </div>
  </div>
  <div class="container">
    <router-view />
  </div>
  <Footer></Footer>
</template>

<script>
import Navbar from "@/components/NavbarCom.vue";
import Footer from "@/components/FooterCom.vue";

export default {
  name: "app",
  components: {
    Navbar,
    Footer,
  },
};
</script>

<style scoped lang="less">
@import "@/style/style.css";

* {
  margin: 0;
  padding: 0;
}

.header {
  width: 100%;
  z-index: 100;
  position: relative;
}

.banner {
  //display: none;
  z-index: 0;
  width: 100vw;
  height: 50vh;
  position: relative;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  }
  .container {
    position: absolute;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    color: white;
    .wrapper {
      .sub {
        font-size: 18px;
      }
      h1 {
        font-size: 48px;
      }
      .btn {
        display: inline-block;
        border: 1px solid white;
        margin: 40px 0 0;
        color: white;
        padding: 15px 28px;
        &:hover {
          color: #222222;
          background-color: white;
        }
      }
    }
  }
}

.container {
  width: 1080px;
  margin: 0 auto;
}
</style>
