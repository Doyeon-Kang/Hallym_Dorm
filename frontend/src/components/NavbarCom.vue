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
          <a v-if="!loggedIn" href="/login">로그인</a>
          <a v-else @click="logout()">로그아웃</a>
          <a v-if="loggedIn" href="/mypage">마이페이지</a>
          <a href="/admin/user">최고관리자</a>
        </div>
      </div>
    </div>
    <div class="drop_menu" @mouseover="mouseOver" @mouseleave="mouseLeave">
      <div class="blue_container">
        <div class="title">
          <a href="/intro">생활관소개</a>
          <a href="/inform">입퇴사안내</a>
          <a href="/life">생활안내</a>
          <a href="/community">게시판</a>
          <a href="/reserve">신청 및 예약</a>
        </div>
      </div>
      <div class="drop content_container" v-show="active">
        <ul class="intro">
          <li><a href="/intro">학생생활관 소개</a></li>
          <li><a href="/intro/greeting">인사말/연혁</a></li>
          <li><a href="/intro/admin">행정실</a></li>
          <li><a href="/intro/management">관리실 & 시설운영실</a></li>
          <li><a href="/intro/judge">사감단</a></li>
          <li><a href="/intro/facility">건물소개</a></li>
          <li><a href="/intro/status">시설현황</a></li>
          <li><a href="/intro/detail">시설소개</a></li>
          <li><a href="/intro/map">건물안내도</a></li>
          <li><a href="/intro/location">오시는 길</a></li>
        </ul>
        <ul class="inout">
          <li><a href="/inform">입사안내</a></li>
          <li><a href="/inform/join">선발기준</a></li>
          <li><a href="/inform/leave">퇴사안내</a></li>
          <li><a href="/inform/refund">환불규정</a></li>
        </ul>
        <ul class="life">
          <li><a href="/life">생활일정</a></li>
          <li class="sub_menu">
            <a href="/life/rules"
              >생활수칙 ></a
            >
            <ul>
              <li><a href="/life/rules">생활수칙</a></li>
              <li><a href="/life/safe">입사필수안전교육</a></li>
            </ul>
          </li>
          <li class="sub_menu">
            <a href=""
              >상벌점기준표 >
            </a>
            <ul>
              <li><a href="/life/prize">상점기준</a></li>
              <li><a href="/life/penalty">벌점기준</a></li>
            </ul>
          </li>
          <li><a href="/life/post">우편 및 인터넷 안내</a></li>
          <li><a href="/life/cafeteria">식당안내</a></li>
        </ul>
        <ul class="board">
          <li class="sub_menu">
            <a href="/community"
              >공지사항 >
            </a>
            <ul>
              <li><a href="/community">학생기숙사</a></li>
              <li><a href="/community/notice1">사생자치회</a></li>
            </ul>
          </li>
          <li><a href="/community/data">서식자료실</a></li>
          <li><a href="/community/faq">FAQ</a></li>
          <li><a href="/community/repair">불편/수리 요청</a></li>
          <li><a href="/community/market">나눔 장터</a></li>
          <li><a href="/community/lost">분실물</a></li>
        </ul>
        <ul class="reserve">
          <li><a href="/reserve">상담 및 면담 신청</a></li>
          <li><a href="/reserve/study">스터디룸 예약</a></li>
          <li class="sub_menu">
            <a href="/reserve/in"
              >입사/퇴사 신청 > 
            </a>
            <ul>
              <li><a href="/reserve/in">입사신청</a></li>
              <li><a href="/reserve/out">퇴사신청</a></li>
            </ul>
          </li>
          <li><a href="/reserve/sleep">외박신청</a></li>
        </ul>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      active: false,
      expand: true,
    };
  },
  methods: {
    mouseOver() {
      this.active = true;
    },
    mouseLeave() {
      this.active = false;
    },
    logout() {
      this.$router.replace({
        path: '/logout'
      })
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn
    }
  },
  watch: {
    loggedIn() {
      return this.loggedIn;
    }
  }
};
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
      display: flex;
      align-items: center;
      a {
        color: #222222;
        font-weight: 500;
        font-size: 14px;
        &:hover {
          color: #3675c7;
          cursor: pointer;
        }
        &::after {
          margin: 0 8px;
          content: "|";
          color: #858585;
          font-weight: 400;
        }
        &:last-child::after {
          content: none;
        }
      }
    }
  }
  .drop_menu {
    //box-shadow: 2px 5px 20px rgba(133, 133, 133, 0.25);
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
      box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
      ul {
        flex-grow: 1;
        flex-basis: 0;
        border-right: 1px solid #f0f0f0;
        margin: 0;
        padding: 20px 0;
        &:last-child {
          border: none;
        }
        li {
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: start;
          padding: 7px 0;
          &.sub_menu {
            line-height: 20px;
            ul {
              display: none;
              padding: 0px;
              li a {
                color: #3675c7;
                font-size: 14px;
                &:hover {
                  font-weight: 600;
                }
              }
            }
            &:hover ul {
              display: block;
            }
          }
          a {
            color: #222222;
            position: relative;
            span {
              position: absolute;
            }
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
