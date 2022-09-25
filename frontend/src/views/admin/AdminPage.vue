<template>
  <div class="wrapper">
    <div class="left_container">
      <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
      <PageTitle :title="title" :add="add"></PageTitle>
      <Search></Search>
      <BoardList v-if="this.$route.name === 'adminuser' || this.$route.name === 'adminpoint'" :listItem="userList" :listTitle="userTitle" :total="total">
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminstudy'" :listItem="studyList" :listTitle="studyTitle" :total="total">
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminsleep'" :listItem="sleepList" :listTitle="sleepTitle" :total="total">
      </BoardList>
      <BoardList v-else-if="$route.name === 'admininout'" :listItem="inoutList" :listTitle="inoutTitle" :total="total">
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminconsulting'" :listItem="consultingList" :listTitle="consultingTitle"
        :total="total"></BoardList>
      <BoardList v-else-if="$route.name === 'adminlife'" :con_title="life_con_title" :listItem="lifeList" :listTitle="lifeTitle" :total="total">
      </BoardList>
    </div>
  </div>
</template>
  
<script>
import PageTitle from "@/components/AdminPageTitle.vue";
import SidebarCom from "../../components/AdminSidebarCom.vue";
import Search from "../../components/AdminSearch.vue";
import BoardList from "../../components/AdminBoardList.vue";


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
      add: "사용자 추가",
      searchtotal: "전체 사용자 검색",
      searchtitle: "제목 검색",
      total: 521,

      userTitle: ["학번", "이름", "소속학과", "상벌점", "상벌점 추가내역", "거주 기숙사"],
      userList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "인문학부",
          point: "+5",
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
    BoardList,
    Search
  },
  created() {
    this.routeCheck();
  },
  methods: {
    routeCheck() {
      this.activeReset();
      if (this.$route.name === "adminuser") {
        this.title = "관리자페이지 > 사용자 관리";
        this.side[0].active = true;
      } else if (this.$route.name === "adminpoint") {
        this.title = "관리자페이지 > 상벌점 관리";
        this.side[1].active = true;
      } else if (this.$route.name === "adminstudy") {
        this.title = "관리자페이지 > 스터디룸 예약 관리";
        this.side[2].active = true;
      } else if (this.$route.name === "adminsleep") {
        this.title = "관리자페이지 > 외박 신청 현황";
        this.side[3].active = true;
      } else if (this.$route.name === "admininout") {
        this.title = "관리자페이지 > 입사/퇴사 관리";
        this.side[4].active = true;
      } else if (this.$route.name === "adminconsulting") {
        this.title = "관리자페이지 > 상담 신청 현황";
        this.side[5].active = true;
      } else if (this.$route.name === "adminlife") {
        this.title = "관리자페이지 > 생활 일정 관리";
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
  width: 1080px;
  display: flex;
  margin-bottom: -30px;

  .left_container {
    z-index: 50;
  }

  .right_container {
    z-index: 1;
  }
}
</style>
  