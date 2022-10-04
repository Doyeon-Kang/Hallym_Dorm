<template>
  <div class="wrapper">
    <div class="left_container">
      <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
      <PageTitle v-if="this.$route.name === 'adminuser'" :title="title" :add="userManagement">
      </PageTitle>
      <PageTitle v-else-if="this.$route.name === 'adminuseradd'" :title="title" :add="useradd">
      </PageTitle>
      <PageTitle v-else-if="this.$route.name === 'adminpoint'" :title="title" :add="pointManagement">
      </PageTitle>
      <PageTitle v-else-if="this.$route.name === 'adminpointadd'" :title="title" :add="pointadd">
      </PageTitle>
      <PageTitle v-else-if="this.$route.name === 'adminstudy' ||
      this.$route.name === 'adminsleep' ||
      this.$route.name === 'admininout' ||
      this.$route.name === 'adminconsulting' ||
      this.$route.name === 'adminlife'" :title="title">
      </PageTitle>

      <Addbox v-if="this.$route.name === 'adminpointadd'" :con_title="point_con_title" :listTitle="pointTitle"></Addbox>
      <Addbox v-else-if="this.$route.name === 'adminlife'" :con_title="life_con_title" :listTitle="lifeTitle"></Addbox>

      <Search v-show="$route.name !== 'admininout'"></Search>

      <BoardList v-if="this.$route.name === 'adminuser' || this.$route.name === 'adminuseradd'" :listItem="userList"
        :listTitle="userTitle" :totaluser="totaluser">
      </BoardList>
      <BoardList v-else-if="this.$route.name === 'adminpoint'" :listItem="pointList" :listTitle="pointTitle"
        :totaluser="totaluser">
      </BoardList>
      <MiniBoardList v-if="this.$route.name === 'adminpointadd'" :listItem="pointList" :listTitle="pointTitle"
        :totaluser="totaluser">
      </MiniBoardList>
      <BoardList v-else-if="$route.name === 'adminstudy'" :listItem="studyList" :listTitle="studyTitle"
        :totaluser="totaluser">
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminsleep'" :listItem="sleepList" :listTitle="sleepTitle"
        :totaluser="totaluser">
      </BoardList>
      <BoardList v-else-if="$route.name === 'admininout'" :listItem="inoutList" :listTitle="inoutTitle"
        :totaluser="totaluser" :title_in="title_in" :title_out="title_out">
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminconsulting'" :listItem="consultingList" :listTitle="consultingTitle"
        :totaluser="totaluser">
      </BoardList>
      <MiniBoardList v-else-if="$route.name === 'adminlife'" :listItem="lifeList" :listTitle="lifeTitle"
        :totallife="totallife">
      </MiniBoardList>
    </div>
  </div>
</template>
  
<script>
import PageTitle from "@/components/AdminPageTitle.vue";
import SidebarCom from "../../components/AdminSidebarCom.vue";
import Addbox from "../../components/AdminAddBoxCom.vue";
import Search from "../../components/AdminSearch.vue";
import BoardList from "../../components/AdminBoardList.vue";
import MiniBoardList from "../../components/AdminMiniBoardList.vue";


export default {
  data() {
    return {
      title: "String",
      pageName: "관리자페이지",
      side: [
        { img: require("@/assets/admin_user.png"), title: "입사자 현황", path: "/admin/user" },
        { img: require("@/assets/admin_point.png"), title: "상벌점 관리", path: "/admin/point" },
        { img: require("@/assets/admin_calendar.png"), title: "스터디룸 예약 관리", path: "/admin/study" },
        { img: require("@/assets/admin_night.png"), title: "외박 신청 현황", path: "/admin/sleep" },
        { img: require("@/assets/admin_inout.png"), title: "입사/퇴사 관리", path: "/admin/inout" },
        { img: require("@/assets/admin_counseling.png"), title: "상담 신청 현황", path: "/admin/consulting" },
        { img: require("@/assets/admin_schedule.png"), title: "생활 일정 관리", path: "/admin/life" },
        { img: require("@/assets/admin_logout.png"), title: "로그아웃", path: "/logout" },
      ],
      userManagement: "사용자 관리",
      useradd: "사용자 추가",
      pointManagement: "점수 관리",
      pointadd: "점수 부여",
      searchtotal: "전체 사용자 검색",
      searchtitle: "제목 검색",
      title_in: "입사 관리",
      title_out: "퇴사 관리",
      totaluser: 521,
      totallife: 5,

      userTitle: ["학번", "이름", "소속학과", "상벌점", "새로운 요청글", "거주 기숙사"],
      userList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "인문학부",
          point: "+5",
          newwrite: "-",
          live: "2관",
        },
        {
          no: "20201235",
          name: "김땡땡",
          dep: "인문학부",
          point: "+2",
          newwrite: "-",
          live: "4관",
        },
        {
          no: "20201236",
          name: "박모씨",
          dep: "인문학부",
          point: "+3",
          newwrite: "-",
          live: "4관",
        },
        {
          no: "20201237",
          name: "최빵빵",
          dep: "인문학부",
          point: "+3",
          newwrite: "-",
          live: "3관",
        },
      ],

      pointTitle: ["학번", "이름", "소속학과", "상벌점", "상벌점 추가내역", "거주 기숙사"],
      pointList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "인문학부",
          point: "+5",
          addpoint: "-",
          live: "2관",
        },
        {
          no: "20201235",
          name: "김땡땡",
          dep: "인문학부",
          point: "+2",
          addpoint: "-",
          live: "2관",
        },
        {
          no: "20201236",
          name: "박모씨",
          dep: "인문학부",
          point: "+3",
          addpoint: "-",
          live: "2관",
        },
        {
          no: "20201237",
          name: "최빵빵",
          dep: "인문학부",
          point: "+3",
          addpoint: "-",
          live: "2관",
        },
        {
          no: "20201237",
          name: "최빵빵",
          dep: "인문학부",
          point: "+3",
          addpoint: "-",
          live: "2관",
        },
      ],

      studyTitle: ["학번", "이름", "소속학과", "예약날짜", "예약시간", "선택좌석", "상태"],
      studyList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "인문학부",
          date: "2022-08-17",
          time: "13:00 - 15:00",
          seat: "51번",
          status: "미승인",
        },
      ],

      sleepTitle: ["학번", "이름", "소속학과", "신청날짜", "외박 기간", "신청 사유", "승인 상태"],
      sleepList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "인문학부",
          date: "2022-07-07",
          term: "2022-07-08 ~ 2022-07-09",
          reason: "본가 방문",
          status: "미승인",
        },
      ],

      inoutTitle: ["학번", "학년", "이름", "소속학과", "상점", "희망 1순위"],
      inoutList: [
        {
          no: "20201234",
          grade: "3",
          name: "홍길동",
          dep: "인문학부",
          point: "10",
          hope: "-",
        },
      ],

      consultingTitle: ["학번", "이름", "상담분야", "신청일자", "전화번호", "진행상태"],
      consultingList: [
        {
          no: "20201234",
          name: "홍길동",
          find: "심리 상담",
          date: "2022-08-17",
          phone: "010-1234-5678",
          status: "예약 대기 중",
        },
      ],

      life_con_title: "생활 일정 추가",
      lifeTitle: ["제목", "색상", "시작일자", "종료일자"],
      lifeList: [
        {
          title: "2022-2학기 수강 신청",
          color: "#FFFFFF",
          start: "2022-08-17",
          end: "2022-08-24",
        },
      ],
    };
  },
  components: {
    PageTitle,
    SidebarCom,
    Addbox,
    BoardList,
    MiniBoardList,
    Search
  },
  created() {
    this.routeCheck();
  },
  methods: {
    routeCheck() {
      this.activeReset();
      if (this.$route.name === "adminuser") {
        this.title = "관리자페이지 > 입사자 현황";
        this.side[0].img = require("@/assets/admin_user_white.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[0].active = true;
      } else if (this.$route.name === "adminuseradd") {
        this.title = "관리자페이지 > 입사자 현황  > 사용자 관리";
        this.side[0].img = require("@/assets/admin_user_white.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[0].active = true;
      } else if (this.$route.name === "adminpoint") {
        this.title = "관리자페이지 > 상벌점 관리";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point_white.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[1].active = true;
      } else if (this.$route.name === "adminpointadd") {
        this.title = "관리자페이지 > 상벌점 관리 > 상벌점 부여";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point_white.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[1].active = true;
      } else if (this.$route.name === "adminstudy") {
        this.title = "관리자페이지 > 스터디룸 예약 관리";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar_white.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[2].active = true;
      } else if (this.$route.name === "adminsleep") {
        this.title = "관리자페이지 > 외박 신청 현황";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night_white.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[3].active = true;
      } else if (this.$route.name === "admininout") {
        this.title = "관리자페이지 > 입사/퇴사 관리";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout_white.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[4].active = true;
      } else if (this.$route.name === "adminconsulting") {
        this.title = "관리자페이지 > 상담 신청 현황";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling_white.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[5].active = true;
      } else if (this.$route.name === "adminlife") {
        this.title = "관리자페이지 > 생활 일정 관리";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule_white.png");
        this.side[6].active = true;
      } else if (this.$route.name === "adminlogout") {
        this.title = "관리자페이지 > 로그아웃";
        this.side[7].active = true;
      } else {
        this.title = "Error Page";
      }
    },
    activeReset() {
      for (var i = 0; i < this.side.length; i++) {
        this.side[i].active = false;
      }
    }
  },
  watch: {
    $route() {
      this.routeCheck();
    },
  },
};
</script>
  
<style lang="less" scoped>
.wrapper {
  display: flex;
  margin-bottom: -30px;

  .left_container {
    z-index: 50;
  }

  .right_container {
    z-index: 1;
    width: 100%;
  }
}
</style>