<template>
  <header>
    <div class="white_container">
      <div class="content_container">
        <a href="/" class="left">
          <div class="logo">
            <img src="@/assets/logo.png" alt="한림대학교 로고" />
            <img src="@/assets/univ.png" alt="한림대학교 슬로건" />
          </div>
          <div class="bar"></div>
          <div class="dorm">Dormitory</div>
        </a>
        <div class="right">
          <a href="https://www.hallym.ac.kr/">한림대학교</a>
          <a href="/login">로그인</a>
          <a href="/mypage">마이페이지</a>
        </div>
      </div>
    </div>
    <div class="blue_container">
      <div class="content_container">
        <a href="">생활관소개</a>
        <a href="">입퇴사소개</a>
        <a href="">생활안내</a>
        <a href="">게시판</a>
        <a href="">신청 및 예약</a>
      </div>
    </div>
  </header>
</template>

<script></script>

<style scoped lang="less">
header {
  background-color: white;
  .content_container {
    width: 1440px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .white_container {
    height: 30px;
    padding: 5px;
    .left {
      display: flex;
      align-items: center;
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
    .right {
      a {
        color: #222222;
        font-weight: 700;
      }
      a::after {
        margin: 0 8px;
        content: "|";
        color: #858585;
        font-weight: 400;
      }
      a:last-child::after {
        content: none;
      }
    }
  }
  .blue_container {
    background-color: #3675c7;
    height: 50px;
    padding: 6px;
    display: flex;
    align-items: center;
    a {
      font-size: 20px;
      font-weight: 600;
      color: white;
    }
  }
}
</style>
