<template>
  <div class="wrapper">
    <div class="left_container">
      <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container" :key="componentKey0">
      <div class="titlebox">
        <h1>{{ title }}</h1>
        <div class="btn">
          <!--승인 및 관리 버튼-->
          <button class="add" v-if="this.$route.name === 'adminuser'"
          @click="this.$router.push('/admin/user/add')">{{ userManagement }}</button>
          <button class="add" v-else-if="this.$route.name === 'adminpoint'"
          @click="this.$router.push('/admin/point/add')">{{ pointManagement }}</button>
          <button class="add" v-else-if="this.$route.name === 'adminsleep'"
          @click="approveList(selectList)">{{ sleepApprove }}</button>

          <!--삭제 버튼-->
          <button class="del" v-if="$route.name !== 'adminpoint' && this.$route.name !== 'adminpointadd' && this.$route.name !== 'adminuseradd' && this.$route.name !== 'admininout' && this.$route.name !== 'adminconsulting' && this.$route.name !== 'inoutdetail' && this.$route.name !== 'admindetail' && this.$route.name !== 'consultdetail' && this.$route.name !== 'pointdetail'" @click="deleteUser(selectList)">삭제</button>
          <button class="del" v-else-if="$route.name === 'adminconsulting'">삭제</button>
          <button class="del" v-else-if="$route.name === 'admininout' && this.selectListIn.length !== 0" @click="deleteIn(selectListIn)">삭제</button>
          <button class="del" v-else-if="$route.name === 'admininout' && this.selectListOut.length !== 0" @click="deleteOut(selectListOut)">삭제</button>
          <button class="del" v-if="$route.name === 'admininout' && this.selectListIn.length === 0 && this.selectListOut.length === 0">삭제</button>
        </div>
      </div>

      <!-- 추가 박스 컴포넌트 -->
      <Addbox v-if="this.$route.name === 'adminuseradd'" :con_title="point_con_title" :listTitle="pointTitle"></Addbox>
      <Addbox v-else-if="this.$route.name === 'adminpointadd'" :con_title="point_con_title" :listTitle="pointTitle"></Addbox>
      <Addbox v-else-if="this.$route.name === 'adminlife'" :con_title="life_con_title" :listTitle="lifeTitle"></Addbox>

      <AdminDetailBox v-if="this.$route.name === 'admindetail' || this.$route.name === 'pointdetail'  || this.$route.name === 'consultdetail' || this.$route.name === 'inoutdetail'"></AdminDetailBox>
      
      <!-- 리스트 컴포넌트 -->
      <BoardList v-if="this.$route.name === 'adminuser'" :listItem="userList" :listTitle="userTitle" @setList="setList" :key="componentKey">
      </BoardList>
      <BoardList v-else-if="$route.name === 'admin'" :listItem="studyList" :listTitle="studyTitle"
      @setList="setList" :key="0"></BoardList>
      <MiniBoardList v-if="this.$route.name === 'adminuseradd'" :listItem="userList" :listTitle="userTitle" :key="componentKey">
      </MiniBoardList>
      <BoardList v-else-if="this.$route.name === 'adminstudy'" :listItem="studyList" :listTitle="studyTitle" @setList="setList" :key="componentKey1">
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminsleep'" :listItem="sleepList" :listTitle="sleepTitle"
      @setList="setList" :key="componentKey2" >
      </BoardList>
      <BoardList v-else-if="$route.name === 'adminconsulting'" :listItem="consultingList" :listTitle="consultingTitle"
      @setList="setList" :key="componentKey3">
      </BoardList>
      <MiniBoardList v-else-if="$route.name === 'adminlife'" :listItem="lifeList" :listTitle="lifeTitle"
        :totallife="totallife">
      </MiniBoardList>
      <BoardList v-else-if="this.$route.name === 'adminpoint'" :listItem="pointList" :listTitle="pointTitle" @setList="setList" :key="componentKey6">
      </BoardList>
      <MiniBoardList v-if="this.$route.name === 'adminpointadd'" :listItem="pointList" :listTitle="pointTitle" >
      </MiniBoardList>
      <InoutCom v-show="$route.name === 'admininout'" :listItemin="joinList" :listTitlein="inTitle" :listItemout="outList" :listTitleout="outTitle"
        :title_in="title_in" :title_out="title_out" :key="componentKey4" @setListIn="setListIn" @setListOut="setListOut">
      </InoutCom>
    </div>
  </div>
</template>
  
<script>
import SidebarCom from "../../components/AdminSidebarCom.vue";
import Addbox from "../../components/AdminAddBoxCom.vue";
import BoardList from "../../components/AdminBoardList.vue";
import MiniBoardList from "../../components/AdminMiniBoardList.vue";
import AdminDetailBox from "@/components/AdminDetailBox.vue";


import UserDataService from "@/services/UserDataService"
import ApplyStudyroomDataService from "@/services/ApplyStudyroomDataService";
import ApplySleepoutDataService from "@/services/ApplySleepoutDataService";
import ApplyJoinDataService from "@/services/ApplyJoinDataService";
import ApplyResignDataService from "@/services/ApplyResignDataService";
import ApplyConsultDataService from "@/services/ApplyConsultDataService";
import UserInfoDataService from "@/services/UserInfoDataService";
import UserPointDataService from "@/services/UserPointDataService";
import InoutCom from "../../components/AdminInoutCom.vue";

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
      componentKey0: 0,
      componentKey: 0,
      componentKey1: 0,
      componentKey2: 0,
      componentKey3: 0,
      componentKey4: 0,
      componentKey5: 0,
      componentKey6: 0,
      userManagement: "사용자 추가",
      useradd: "생성",

      pointManagement: "점수 관리",
      pointadd: "점수 부여",

      sleepApprove: "승인",

      searchtotal: "전체 사용자 검색",
      searchtitle: "제목 검색",

      title_in: "입사 관리",
      title_out: "퇴사 관리",

      userTitle: ["번호", "학번", "이름", "소속학과", "거주 기숙사", "사용자 권한"],
      userList: [],

      pointTitle: ["번호", "학번", "이름", "소속학과", "상벌점 추가내역"],
      pointList: [],

      studyTitle: ["번호", "학번", "이름", "소속학과", "예약날짜", "예약시간", "선택좌석"],
      studyList: [],

      sleepTitle: ["번호", "학번", "이름", "소속학과", "신청날짜", "외박 기간", "신청 사유", "승인 상태"],
      sleepList: [],

      inTitle: ["번호", "학번", "학년", "이름", "소속학과", "희망 1순위", "승인여부"],
      joinList: [],

      outTitle: ["번호", "학번", "이름", "소속학과", "승인여부"],
      outList: [],

      consultingTitle: ["번호", "학번", "이름", "상담분야", "신청일자", "전화번호"],
      consultingList: [
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
      selectList: [],
      selectListIn: [],
      selectListOut: []
    };
  },
  components: {
    SidebarCom,
    Addbox,
    BoardList,
    MiniBoardList,
    InoutCom,
    AdminDetailBox
  },
  created() {
    this.routeCheck();
    this.init();
  },
  methods: {
    setList(data) {
      this.selectList = data
    },
    setListIn(data) {
      this.selectListIn = data
    },
    setListOut(data) {
      this.selectListOut = data
    },  
    deleteUser(list) {
      if (list.length == 0) { // 리스트 행 없을 경우
          alert("삭제할 리스트 행을 선택해주세요.")
      } else { // 리스트 행 있는 경우
        if(this.$route.name === 'adminuser') { // 사용자 관리
          for (let i=0; i<list.length; i++) {
              UserDataService.delete(list[i].id).then(res => {    
                  console.log(res)
              })
          }
          alert('삭제 완료되었습니다.')
          window.location.reload(true)
        } else if(this.$route.name === 'adminstudy') { // 스터디룸 예약
          for (let i=0; i<list.length; i++) {
                ApplyStudyroomDataService.delete(list[i].id).then(res => {   
                    console.log(res)
                })
            }
            alert('삭제 완료되었습니다.')
            window.location.reload(true)
          } else if(this.$route.name === 'adminsleep') { // 외박 신청
          for (let i=0; i<list.length; i++) {
            ApplySleepoutDataService.delete(list[i].id).then(res => {   
                    console.log(res)
                })
            }
            alert('삭제 완료되었습니다.')
            window.location.reload(true)
        } else if(this.$route.name === 'adminconsulting') { // 상담 신청
          for (let i=0; i<list.length; i++) {
            ApplyConsultDataService.delete(list[i].id).then(res => {   
                    console.log(res)
                })
            }
            alert('삭제 완료되었습니다.')
            window.location.reload(true)
        }
      }    
    },
    deleteIn(list) {
      if (list.length == 0) { // 리스트 행 없을 경우
        alert("삭제할 리스트 행을 선택해주세요.")
      } else { // 리스트 행 있는 경우 
        for (let i=0; i<list.length; i++) {
          ApplyJoinDataService.delete(list[i]).then(res => {    
                console.log(res)
          })
        }
        alert('삭제 완료되었습니다.')
        window.location.reload(true)
      }
    },
    deleteOut(list) {
      if (list.length == 0) { // 리스트 행 없을 경우
        alert("삭제할 리스트 행을 선택해주세요.")
      } else { // 리스트 행 있는 경우 
        for (let i=0; i<list.length; i++) {
          ApplyResignDataService.delete(list[i]).then(res => {    
                console.log(res)
          })
        }
        alert('삭제 완료되었습니다.')
        window.location.reload(true)
      }
    },
    approveList(list) {
      let cnt = 0;

      if (list.length == 0) { // 리스트 행 없을 경우
          alert("승인할 리스트 행을 선택해주세요.")
      } else {
        for(let i=0; i<list.length; i++) {
          ApplySleepoutDataService.updateApprove(list[i].id).then(res => {
            console.log(res)
            cnt++
          })
        }
        if(cnt == 0) {
          alert('이미 모두 승인 처리되어 있습니다.')
        } else {
          alert(cnt+'건이 승인 처리되었습니다.')
          window.location.reload(true)
        }
      }
    },
    async init() {
      await UserDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id
          list[i].no = res[i].studentno // 학번
          list[i].name = res[i].name // 이름
          list[i].dep = "-" // 학과
          list[i].live = "-" // 거주 기숙사
          list[i].auth = res[i].roles[0].name // 사용자 권한

          // USER_MEMBER일 경우(이후 ADMIN 조건은 제거!)
          if(list[i].auth === "ROLE_USER_MEMBER") {
            UserInfoDataService.getInfo(res[i].studentno).then(res => {
              list[i].dep = res.data.department
              list[i].live = res.data.res_fac + "관 " +res.data.res_room +"호실"
              this.componentKey += 1
            })
          }
          this.componentKey += 1
        }
        this.userList = list
        this.componentKey += 1
      })
      await ApplyStudyroomDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id // 번호
          list[i].no = res[i].studentNo // 학번
          list[i].name = res[i].name // 이름
          list[i].dep = res[i].department // 학과
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
          list[i].seat = res[i].seat +"번 좌석" // 좌석
        }
        this.studyList = list
        this.componentKey1 += 1
      })
      await UserPointDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id
          list[i].no = res[i].studentNo
          list[i].name = res[i].name
          list[i].dep = res[i].department

          if(res[i].plusPoint !== 0) {
            list[i].addpoint = res[i].plusPoint
          } else {
            list[i].addpoint = res[i].minusPoint * (-1)
          }
          this.componentKey6 += 1
        }
        this.pointList = list
        this.componentKey6 += 1
      })
      await ApplySleepoutDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id
          list[i].no = res[i].studentNo
          list[i].name = res[i].name
          list[i].dep = res[i].department
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
        this.componentKey2 += 1
      })
      await ApplyJoinDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id
          list[i].no = res[i].studentNo
          list[i].grade = res[i].grade
          list[i].name = res[i].name
          list[i].dep = res[i].department
          list[i].hope = res[i].hope_fac_1
          if (res[i].approved === true) {
            list[i].status = "승인"
          } else {
            list[i].status = "미승인"
          }
        }
        this.joinList = list
        this.componentKey4 += 1
      })
      await ApplyResignDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id
          list[i].no = res[i].studentNo
          list[i].name = res[i].name
          list[i].dep = res[i].department
          //list[i].live = "-"
          if (res[i].approved === true) {
            list[i].status = "승인"
          } else {
            list[i].status = "미승인"
          }
        }
        this.outList = list
        this.componentKey4 += 1
      })
      await ApplyConsultDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].id = res[i].id // 번호
          list[i].no = res[i].studentNo // 학번
          list[i].name = res[i].name // 이름
          list[i].find = res[i].subject // 상담분야
          list[i].date = res[i].date // 신청일자
          list[i].phone = "-" // 전화번호
        }
        this.consultingList = list
        this.componentKey3 += 1
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
      } else if (this.$route.name === "admininout" || this.$route.name === 'inoutdetail') {
        this.title = "관리자페이지 > 입사/퇴사 관리";
        this.side[0].img = require("@/assets/admin_user.png");
        this.side[1].img = require("@/assets/admin_point.png");
        this.side[2].img = require("@/assets/admin_calendar.png");
        this.side[3].img = require("@/assets/admin_night.png");
        this.side[4].img = require("@/assets/admin_inout_white.png");
        this.side[5].img = require("@/assets/admin_counseling.png");
        this.side[6].img = require("@/assets/admin_schedule.png");
        this.side[4].active = true;
      } else if (this.$route.name === "adminconsulting" || this.$route.name === 'consultdetail') {
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
        this.title = "상세정보";
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

.titlebox {
  justify-content: space-between;
  display: flex;
  border-bottom: solid 2px #e2e2e2;

  h1 {
    margin: 0;
    color: #222;
    padding: 17px 30px;
    font-size: 22px;
  }

  .btn {
    margin: auto 0;

    button {
      font-size: 15px;
      border: 0;
      color: #fff;
      padding: 8px 10px;
    }

    .add {
      background-color: #00B6AD;
      margin-right: 20px;

      &:hover {
        cursor: pointer;
      }
    }

    .del {
      margin-right: 10px;
      background-color: #DD6464;

      &:hover {
        cursor: pointer;
      }
    }
  }

}
</style>
