<template>
  <div style="position: relative">
    <div class="header">
      <Navbar v-show="
        $route.name !== 'login' &&
        $route.name !== 'join' &&
        $route.name !== 'findpw' &&
        $route.name !== 'adminuser' &&
        $route.name !== 'adminuseradd' &&
        $route.name !== 'adminpoint' &&
        $route.name !== 'adminpointadd' &&
        $route.name !== 'adminstudy' &&
        $route.name !== 'adminsleep' &&
        $route.name !== 'admininout' &&
        $route.name !== 'adminconsulting' &&
        $route.name !== 'adminlife' &&
        $route.name !== 'adminlogout'
      "></Navbar>
    </div>
    <div class="banner" v-show="$route.name === 'home'">
      <img src="@/assets/banner.jpg" alt="배너이미지" />
      <div class="banner_container">
        <div class="wrapper">
          <span class="sub">편리한, 안전한, 다양한, 다문화 기숙사</span>
          <h1>한림대학교 학생생활관</h1>
          <a href="/intro" class="btn">생활관소개 바로가기</a>
        </div>
      </div>
    </div>
    <div class="small_banner" v-show="
      $route.name !== 'home' &&
      $route.name !== 'mypage' &&
      $route.name !== 'login' &&
      $route.name !== 'join' &&
      $route.name !== 'findpw' &&
      $route.name !== 'adminuser' &&
      $route.name !== 'adminuseradd' &&
      $route.name !== 'adminpoint' &&
      $route.name !== 'adminpointadd' &&
      $route.name !== 'adminstudy' &&
      $route.name !== 'adminsleep' &&
      $route.name !== 'admininout' &&
      $route.name !== 'adminconsulting' &&
      $route.name !== 'adminlife' &&
      $route.name !== 'adminlogout'
    "></div>
    <div class="container">
      <router-view />
    </div>
    <Footer v-show="
      $route.name !== 'login' &&
      $route.name !== 'join' &&
      $route.name !== 'findpw'
    "></Footer>
  </div>
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

// header
.header {
  width: 100vw;
  z-index: 100;
  position: relative;
}

// banner
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

  .banner_container {
    position: absolute;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    width: 1080px;
    margin: 0 auto;
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

.small_banner {
  background: url("@/assets/banner_small.png") no-repeat;
  width: 100%;
  height: 25%;
  position: absolute;
  top: 0;
  z-index: -1;
}

// content
.container {
  width: 1080px;
  margin: 0 auto;
  position: relative;
  z-index: 1;
}

// footer
</style>
