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

      <!-- 추가 박스 컴포넌트 -->
      <Addbox v-if="this.$route.name === 'adminuseradd'" :con_title="point_con_title" :listTitle="pointTitle"></Addbox>
      <Addbox v-else-if="this.$route.name === 'adminpointadd'" :con_title="point_con_title" :listTitle="pointTitle"></Addbox>
      <Addbox v-else-if="this.$route.name === 'adminlife'" :con_title="life_con_title" :listTitle="lifeTitle"></Addbox>

      <!-- 리스트 컴포넌트 -->
      <BoardList v-if="this.$route.name === 'adminuser'" :listItem="userList"
        :listTitle="userTitle" :totaluser="totaluser">
      </BoardList>
      <MiniBoardList v-if="this.$route.name === 'adminuseradd'" :listItem="userList" :listTitle="userTitle"
        :totaluser="totaluser">
      </MiniBoardList>
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
import Addbox from "../../components/AdminAddBoxCom.vue";
import BoardList from "../../components/AdminBoardList.vue";
import MiniBoardList from "../../components/AdminMiniBoardList.vue";
import InoutCom from "../../components/AdminInoutCom.vue";

import UserDataService from "@/services/UserDataService"
import ApplyStudyroomDataService from "@/services/ApplyStudyroomDataService";
import ApplySleepoutDataService from "@/services/ApplySleepoutDataService";
import ApplyJoinDataService from "@/services/ApplyJoinDataService";
import ApplyResignDataService from "@/services/ApplyResignDataService";


export default {
  data() {
    return {
      title: "String",
      pageName: "관리자페이지",
      side: [
        { img: require("@/assets/admin_user.png"), title: "사용자 관리", path: "/admin/user" },
        { img: require("@/assets/admin_point.png"), title: "상벌점 관리", path: "/admin/point" },
        { img: require("@/assets/admin_calendar.png"), title: "스터디룸 예약 관리", path: "/admin/study" },
        { img: require("@/assets/admin_night.png"), title: "외박 신청 현황", path: "/admin/sleep" },
        { img: require("@/assets/admin_inout.png"), title: "입사/퇴사 관리", path: "/admin/inout" },
        { img: require("@/assets/admin_counseling.png"), title: "상담 신청 현황", path: "/admin/consulting" },
        // { img: require("@/assets/admin_schedule.png"), title: "생활 일정 관리", path: "/admin/life" },
        { img: require("@/assets/admin_logout.png"), title: "로그아웃", path: "/logout" },
      ],
      userManagement: "사용자 추가",
      useradd: "생성",

      pointManagement: "점수 관리",
      pointadd: "점수 부여",

      searchtotal: "전체 사용자 검색",
      searchtitle: "제목 검색",

      title_in: "입사 관리",
      title_out: "퇴사 관리",

      userTitle: ["학번", "이름", "소속학과", "거주 기숙사", "사용자 권한"],
      userList: [],

      pointTitle: ["학번", "이름", "소속학과", "상벌점", "상벌점 추가내역", "거주 기숙사"],
      pointList: [
        // {
        //   no: "20201234",
        //   name: "홍길동",
        //   dep: "전자공학과",
        //   point: "5",
        //   addpoint: "-",
        //   live: "2관",
        // }
      ],

      studyTitle: ["학번", "이름", "소속학과", "예약날짜", "예약시간", "선택좌석"],
      studyList: [],
      sleepTitle: ["학번", "이름", "소속학과", "신청날짜", "외박 기간", "신청 사유", "승인 상태"],
      sleepList: [],

      inTitle: ["학번", "학년", "이름", "소속학과", "상벌점", "희망 1순위"],
      inList: [
        // {
        //   no: "20201234",
        //   grade: "2",
        //   name: "홍길동",
        //   dep: "전자공학과",
        //   point: "5",
        //   hope: "8관",
        // }
      ],

      outTitle: ["학번", "이름", "소속학과", "거주 기숙사", "승인여부"],
      outList: [
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
  beforeCreate() {
    
  },
  created() {
    this.routeCheck();
    this.init();
  },
  methods: {
    init() {
      UserDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = res[i].studentno // 학번
          list[i].name = res[i].name // 이름
          list[i].dep = "-" // 학과
          list[i].live = "-" // 거주 기숙사
          list[i].auth = res[i].roles[0].name // 사용자 권한
          //if(list[i].auth === "ROLE_USER_MEMBER")
        }
        this.userList = list
      })
      ApplyStudyroomDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = "-" // 학번
          list[i].name = "-" // 이름
          list[i].dep = "-" // 학과
          list[i].date = res[i].date // 신청일자
          list[i].time = "" // 사용시간
          if (res[i].timeslot1 === 1) {
            list[i].time += "09:00~11:00"
          } else if (res[i].timeslot1 === 2) {
            list[i].time += "11:00~13:00"
          } else if (res[i].timeslot1 === 3) {
            list[i].time += "13:00~15:00"
          } else if (res[i].timeslot1 === 4) {
            list[i].time += "15:00~17:00"
          } else if (res[i].timeslot1 === 5) {
            list[i].time += "17:00~19:00"
          } else if (res[i].timeslot1 === 6) {
            list[i].time += "19:00~21:00"
          } else if (res[i].timeslot1 === 7) {
            list[i].time += "21:00~23:00"
          } else {
            list[i].time += ""
          }

          if (res[i].timeslot2 === 1) {
            list[i].time += " | 09:00~11:00"
          } else if (res[i].timeslot2 === 2) {
            list[i].time += " | 11:00~13:00"
          } else if (res[i].timeslot2 === 3) {
            list[i].time += " | 13:00~15:00"
          } else if (res[i].timeslot2 === 4) {
            list[i].time += " | 15:00~17:00"
          } else if (res[i].timeslot2 === 5) {
            list[i].time += " | 17:00~19:00"
          } else if (res[i].timeslot2 === 6) {
            list[i].time += " | 19:00~21:00"
          } else if (res[i].timeslot2 === 7) {
            list[i].time += " | 21:00~23:00"
          } else {
            list[i].time += ""
          }

          if (res[i].timeslot3 === 1) {
            list[i].time += " | 09:00~11:00"
          } else if (res[i].timeslot3 === 2) {
            list[i].time += " | 11:00~13:00"
          } else if (res[i].timeslot3 === 3) {
            list[i].time += " | 13:00~15:00"
          } else if (res[i].timeslot3 === 4) {
            list[i].time += " | 15:00~17:00"
          } else if (res[i].timeslot3 === 5) {
            list[i].time += " | 17:00~19:00"
          } else if (res[i].timeslot3 === 6) {
            list[i].time += " | 19:00~21:00"
          } else if (res[i].timeslot3 === 7) {
            list[i].time += " | 21:00~23:00"
          } else {
            list[i].time += ""
          }
          list[i].seat = "-" // 좌석
        }
        this.studyList = list
      })
      ApplySleepoutDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          //list[i].id = res[i].id
          list[i].no = "-"
          list[i].name = "-"
          list[i].dep = "-"
          list[i].date = res[i].date
          list[i].term = res[i].date_sleepout
          list[i].reason = res[i].reason
          if (res[i].approved === true) {
            list[i].status = "승인"
          } else {
            list[i].status = "미승인"
          }
        }
        this.sleepList = list
      })
      ApplyJoinDataService.getAll().then(resolveData => {
        let res = resolveData.data
        console.log("in: ", res)
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = "-"
          list[i].grade = res[i].grade
          list[i].name = "-"
          list[i].dep = res[i].department
          list[i].point = "-"
          list[i].hope = res[i].hope_fac_1
        }
        this.inList = list
        console.log(this.inList)
      })
      ApplyResignDataService.getAll().then(resolveData => {
        let res = resolveData.data
        console.log("out: ", res)
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = "-"
          list[i].name = "-"
          list[i].dep = "-"
          list[i].live = "-"
          if (res[i].approved === true) {
            list[i].status = "승인"
          } else {
            list[i].status = "대기중"
          }
        }
        this.outList = list
        console.log(this.outList)
      })
    },
    routeCheck() {
      this.activeReset();
      if (this.$route.name === "adminuser") {
        this.title = "관리자페이지 > 사용자 관리";
        this.side[0].img = require("@/assets/admin_user_white.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[0].active = true;
      } else if (this.$route.name === "adminuseradd") {
        this.title = "관리자페이지 > 사용자 관리  > 사용자 추가";
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
    }
  }
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
