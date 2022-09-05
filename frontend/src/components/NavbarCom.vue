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
    <div class="drop_menu" @mouseover="mouseOver" @mouseleave="mouseLeave">
      <div class="blue_container">
        <div class="title" >
          <a href="">생활관소개</a>
          <a href="">입퇴사안내</a>
          <a href="">생활안내</a>
          <a href="">게시판</a>
          <a href="">신청 및 예약</a>
        </div>
      </div>
      <div class="drop content_container" v-show="active">
        <ul class="intro">
          <li><a href="">학생생활관 소개</a></li>
          <li><a href="">인사말/연혁</a></li>
          <li><a href="">행정실</a></li>
          <li><a href="">관리실 & 시설운영실</a></li>
          <li><a href="">사감단</a></li>
          <li><a href="">건물소개</a></li>
          <li><a href="">시설현황</a></li>
          <li><a href="">시설소개</a></li>
          <li><a href="">건물안내도</a></li>
          <li><a href="">오시는 길</a></li>
        </ul>
        <ul class="inout">
          <li><a href="">입사안내</a></li>
          <li><a href="">선발기준</a></li>
          <li><a href="">퇴사안내</a></li>
          <li><a href="">환불규정</a></li>
        </ul>
        <ul class="life">
          <li><a href="">생활일정</a></li>
          <li><a href="">생활수칙</a>
            <ul>
              <li><a href="">생활수칙</a></li>
              <li><a href="">입사필수안전교육</a></li>
            </ul>
          </li>
          <li><a href="">상벌점기준표</a>
            <ul>
              <li><a href="">상점기준</a></li>
              <li><a href="">벌점기준</a></li>
            </ul>
          </li>
          <li><a href="">우편 및 인터넷 안내</a></li>
          <li><a href="">식당안내</a></li>
        </ul>
        <ul class="board">
          <li><a href="">공지사항</a>
            <ul>
              <li><a href="">학생기숙사</a></li>
              <li><a href="">사생자치회</a></li>
            </ul>
          </li>
          <li><a href="">서식자료실</a></li>
          <li><a href="">FAQ</a></li>
          <li><a href="">불편/수리 요청</a></li>
          <li><a href="">나눔 장터</a></li>
          <li><a href="">분실물</a></li>
        </ul>
        <ul class="reserve">
          <li><a href="">상담 및 면담 신청</a></li>
          <li><a href="">스터디룸 예약</a></li>
          <li><a href="">입사/퇴사 신청</a>
            <ul>
              <li><a href="">입사신청</a></li>
              <li><a href="">퇴사신청</a></li>
            </ul>
          </li>
          <li><a href="">외박신청</a></li>
        </ul>
      </div>
    </div>
  </header>
</template>

<script>
  export default {
    data() {
      return {
        active: false
      }
    },
    methods: {
      mouseOver() {
        this.active = true
      },
      mouseLeave() {
        this.active = false
      }
    }
  }
</script>

<style scoped lang="less">
header {
  background-color: white;
  .content_container {
    width: 1080px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    &.blue {
      margin: 0 60px;
    }
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
  .drop_menu {
    box-shadow: 2px 5px 20px rgba(133, 133, 133, 0.25);
    position: relative;
    .blue_container {
      background-color: #3675c7;
      height: 50px;
      line-height: 50px;
      padding: 6px;
      .title {
        width: 1080px;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
        text-align: center;
        a {
          display: inline-block;
          flex-grow: 1;
          flex-basis: 0;
          //width: 50px;
          font-size: 20px;
          font-weight: 600;
          color: white;
        }
      }
    }
    .drop.content_container {
      position: absolute;
      background-color: white;
      left: 50%;
      transform: translate(-50%);
      ul {
        flex-grow: 1;
        flex-basis: 0;
        li {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: start;
          padding: 7px 0;
          a {
            color: #222222;
            &:hover {
              color: #3675c7;
            }
          }
        }
      }
    }
  }
}
</style>
