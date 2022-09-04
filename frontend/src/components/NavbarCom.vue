<template>
  <header>
    <div class="white_container">
      <router-link to="/" class="left">
        <div class="logo">
          <img src="@/assets/logo.png" alt="한림대학교 로고" />
          <img src="@/assets/univ.png" alt="한림대학교 슬로건" />
        </div>
        <div class="bar"></div>
        <div class="dorm">Dormitory</div>
      </router-link>
    </div>
    <div class="blue_container"></div>
  </header>
</template>

<script></script>

<style scoped lang="less">
header {
  background-color: white;
  .white_container {
    height: 30px;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    .left {
      display: flex;
      align-items: center;
      cursor: left;
      .logo {
        margin-right: 4px;
      }
      .bar {
        width: 1.5px;
        height: 70%;
        background: #858585;
        margin-right: 6px;
      }
      .dorm {
        font-size: 20px;
        font-weight: 700;
        color: #222222;
      }
    }
  }
  .blue_container {
    background-color: #3675c7;
    height: 50px;
    padding: 10px;
  }
}
</style>
