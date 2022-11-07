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
      <BoardList v-else-if="$route.name === 'adminconsulting'" :listItem="consultingList" :listTitle="consultingTitle"
        :totaluser="totaluser">
      </BoardList>
      <MiniBoardList v-else-if="$route.name === 'adminlife'" :listItem="lifeList" :listTitle="lifeTitle"
        :totallife="totallife">
      </MiniBoardList>
      <InoutCom v-show="$route.name === 'admininout'" :listItemin="inList" :listTitlein="inTitle" :listItemout="outList" :listTitleout="outTitle"
        :title_in="title_in" :title_out="title_out">
      </InoutCom>
    </div>
  </div>
</template>
  

  <script>
  import PageTitle from "@/components/AdminPageTitle.vue";
  import SidebarCom from "../../components/AdminSidebarCom.vue";
  import BoardList from "../../components/AdminBoardList.vue";
  
  import UserService from '../../services/user.service';

  export default {
    data() {
      return {
        content: '',
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
          { img: require("@/assets/admin_logout.png"), title: "로그아웃", path: "/admin/logout" },
        ],
        listItem: [
          {
            no: "1",
            title: "학번 이름",
            date: "2022.06.18",
            writer: "학생생활관",
            view: "78"
          },
        ]
      };
    },
    components: {
      PageTitle,
      SidebarCom,
      BoardList
  },
    created() {
      this.routeCheck();
    },
    mounted() {
      UserService.getAdminBoard().then(
        response => {
          console.log(response.data);
        }),
        error => {
          console.log(error.data);
          // not admin => go to error page
        }
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
        for (var i=0; i<this.side.length; i++) {
          this.side[i].active = false;
        }
      },
    },
  };

<script>
import PageTitle from "@/components/AdminPageTitle.vue";
import SidebarCom from "../../components/AdminSidebarCom.vue";
import Addbox from "../../components/AdminAddBoxCom.vue";
import BoardList from "../../components/AdminBoardList.vue";
import MiniBoardList from "../../components/AdminMiniBoardList.vue";
import InoutCom from "../../components/AdminInoutCom.vue";


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

      userTitle: ["학번", "이름", "소속학과", "상벌점", "새로운 요청글", "거주 기숙사"],
      userList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "전자공학과",
          point: "5",
          newwrite: "-",
          live: "2관",
        },
        {
          no: "20204237",
          name: "김땡땡",
          dep: "빅데이터학과",
          point: "4",
          newwrite: "-",
          live: "5관",
        },
        {
          no: "20191235",
          name: "박모씨",
          dep: "체육학과",
          point: "10",
          newwrite: "-",
          live: "1관",
        },
        {
          no: "20215236",
          name: "최빵빵",
          dep: "사회복지학과",
          point: "7",
          newwrite: "-",
          live: "6관",
        },
      ],

      pointTitle: ["학번", "이름", "소속학과", "상벌점", "상벌점 추가내역", "거주 기숙사"],
      pointList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "전자공학과",
          point: "5",
          addpoint: "-",
          live: "2관",
        },
        {
          no: "20204237",
          name: "김땡땡",
          dep: "빅데이터학과",
          point: "4",
          addpoint: "-",
          live: "5관",
        },
        {
          no: "20191235",
          name: "박모씨",
          dep: "체육학과",
          point: "10",
          addpoint: "-",
          live: "1관",
        },
        {
          no: "20215236",
          name: "최빵빵",
          dep: "사회복지학과",
          point: "7",
          addpoint: "-",
          live: "6관",
        },
      ],

      studyTitle: ["학번", "이름", "소속학과", "예약날짜", "예약시간", "선택좌석", "상태"],
      studyList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "전자공학과",
          date: "2022-08-17",
          time: "14:00 - 16:00",
          seat: "38번",
          status: "미승인",
        },
        {
          no: "20204237",
          name: "김땡땡",
          dep: "빅데이터학과",
          date: "2022-08-15",
          time: "13:00 - 15:00",
          seat: "51번",
          status: "승인",
        },
        {
          no: "20191235",
          name: "박모씨",
          dep: "체육학과",
          date: "2022-09-02",
          time: "13:00 - 14:00",
          seat: "22번",
          status: "승인",
        },
        {
          no: "20215236",
          name: "최빵빵",
          dep: "사회복지학과",
          date: "2022-08-28",
          time: "15:00 - 17:00",
          seat: "14번",
          status: "승인",
        },
      ],

      sleepTitle: ["학번", "이름", "소속학과", "신청날짜", "외박 기간", "신청 사유", "승인 상태"],
      sleepList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "전자공학과",
          date: "2022-07-07",
          term: "2022-07-08 ~ 2022-07-09",
          reason: "본가 방문",
          status: "미승인",
        },
        {
          no: "20204237",
          name: "김땡땡",
          dep: "빅데이터학과",
          date: "2022-06-19",
          term: "2022-07-01 ~ 2022-07-03",
          reason: "본가 방문",
          status: "승인",
        },
        {
          no: "20191235",
          name: "박모씨",
          dep: "체육학과",
          date: "2022-07-21",
          term: "2022-07-21 ~ 2022-07-22",
          reason: "본가 방문",
          status: "미승인",
        },
        {
          no: "20215236",
          name: "최빵빵",
          dep: "사회복지학과",
          date: "2022-07-21",
          term: "2022-07-28 ~ 2022-07-30",
          reason: "본가 방문",
          status: "승인",
        },
      ],

      inTitle: ["학번", "학년", "이름", "소속학과", "상벌점", "희망 1순위"],
      inList: [
        {
          no: "20201234",
          grade: "2",
          name: "홍길동",
          dep: "전자공학과",
          point: "5",
          hope: "2관",
        },
        {
          no: "20204237",
          grade: "3",
          name: "김땡땡",
          dep: "빅데이터학과",
          point: "4",
          hope: "8관",
        },
        {
          no: "20191235",
          grade: "4",
          name: "박모씨",
          dep: "체육학과",
          point: "10",
          hope: "6관",
        },
        {
          no: "20215236",
          grade: "1",
          name: "최빵빵",
          dep: "사회복지학과",
          point: "7",
          hope: "6관",
        },
      ],

      outTitle: ["학번", "이름", "소속학과", "거주 기숙사", "승인여부"],
      outList: [
        {
          no: "20201234",
          name: "홍길동",
          dep: "전자공학과",
          live: "8관 518호",
          status: "대기중",
        },
        {
          no: "20204237",
          name: "김땡땡",
          dep: "빅데이터학과",
          live: "5관 623호",
          status: "대기중",
        },
        {
          no: "20191235",
          name: "박모씨",
          dep: "체육학과",
          live: "7관 905호",
          status: "대기중",
        },
        {
          no: "20215236",
          name: "최빵빵",
          dep: "사회복지학과",
          live: "1관 1201호",
          status: "대기중",
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
        {
          no: "20204237",
          name: "김땡땡",
          find: "진로 상담",
          date: "2022-08-15",
          phone: "010-7575-6464",
          status: "예약 완료",
        },
        {
          no: "20191235",
          name: "박모씨",
          find: "진로 상담",
          date: "2022-07-28",
          phone: "010-2424-9292",
          status: "예약 완료",
        },
        {
          no: "20215236",
          name: "최빵빵",
          find: "개인 상담",
          date: "2022-07-12",
          phone: "010-8282-1313",
          status: "예약 완료",
        },
        
      ],

      life_con_title: "생활 일정 추가",
      lifeTitle: ["제목", "색상", "시작일자", "종료일자"],
      lifeList: [
        {
          title: "2022-2학기 수학 경진 대회",
          color: "#52FE81",
          start: "2022-09-22",
          end: "2022-10-21",
        },
        {
          title: "2022-2학기 수강 신청",
          color: "#FFFFFF",
          start: "2022-08-17",
          end: "2022-08-24",
        },
        {
          title: "2022-1학기 수강 신청",
          color: "#FFEE21",
          start: "2022-02-15",
          end: "2022-02-22",
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
    InoutCom,
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
