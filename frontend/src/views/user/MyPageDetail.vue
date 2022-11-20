<template>
  <div class="wrapper">
    <div class="left_container">
      <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
      <PageTitle :title="title"></PageTitle>
      <BoardList v-if="$route.name === 'myassey'" :listItem="asseyList" :listTitle="asseyTitle"></BoardList>
      <BoardList v-else-if="$route.name === 'myconsulting'" :listItem="consultList" :listTitle="consultTitle">
      </BoardList>
      <BoardList v-else-if="$route.name === 'mystudy'" :listItem="studyList" :listTitle="studyTitle"></BoardList>
      <BoardList v-else-if="$route.name === 'mysleep'" :listItem="sleepList" :listTitle="sleepTitle"></BoardList>
      <BoardList v-else-if="$route.name === 'mypoint'" :listItem="pointList" :listTitle="pointTitle"></BoardList>
    </div>
  </div>
</template>

<script>
import PageTitle from "@/components/PageTitle.vue";
import SidebarCom from "../../components/SidebarCom.vue";
import BoardList from "../../components/BoardList.vue";
import UserInfoDataService from "@/services/UserInfoDataService";
import UserPointDataService from "@/services/UserPointDataService";
import NoticeDataService from "@/services/NoticeDataService";
import RepairDataService from "@/services/RepairDataService";
import StoreDataService from "@/services/StoreDataService";
import LostDataService from "@/services/LostDataService";
import ApplyConsultDataService from "@/services/ApplyConsultDataService";
import ApplyStudyroomDataService from "@/services/ApplyStudyroomDataService";

export default {
  data() {
    return {
      title: "String",
      pageName: "마이페이지",
      side: [
        { title: "내가 작성한 글", path: "/mypage/myassey" },
        { title: "상담 신청", path: "/mypage/myconsulting" },
        { title: "스터디룸 예약", path: "/mypage/mystudy" },
        { title: "외박 신청", path: "/mypage/mysleep" },
        { title: "상벌점 내역", path: "/mypage/mypoint" },
      ],
      asseyTitle: ["번호", "글제목", "작성일", "작성게시판"],
      asseyList: [],
      consultTitle: [
        "번호",
        "상담분야",
        "상담자",
        "상담날짜",
        "상담시간",
        "진행상태",
      ],
      consultList: [],
      studyTitle: [
        "번호",
        "내 좌석",
        "신청날짜",
        "신청시간",
        "진행상태",
        "비고",
      ],
      studyList: [],
      sleepTitle: [
        "번호",
        "글제목",
        "진행상태",
        "처리일자"],
      sleepList: [],
      pointTitle: [
        "번호",
        "년도-학기",
        "구분",
        "점수",
        "사유",
        "발생일",
        "입력일",
      ],
      pointList: [],
    };
  },
  components: {
    PageTitle,
    SidebarCom,
    BoardList,
  },
  created() {
    this.routeCheck();
    this.init();
  },
  computed: {
    user() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    routeCheck() {
      this.activeReset();
      if (this.$route.name === "myassey") {
        this.title = "마이페이지 > 내가 작성한 글";
        this.semiTitle = ["학생회", "기숙사"];
        this.side[0].active = true;
      } else if (this.$route.name === "myconsulting") {
        this.title = "마이페이지 > 상담 신청 현황";
        this.side[1].active = true;
      } else if (this.$route.name === "mystudy") {
        this.title = "마이페이지 > 스터디룸 예약 현황";
        this.side[2].active = true;
      } else if (this.$route.name === "mysleep") {
        this.title = "마이페이지 > 외박 신청 현황";
        this.side[3].active = true;
      } else if (this.$route.name === "mypoint") {
        this.title = "마이페이지 > 상/벌점 내역";
        this.side[4].active = true;
      } else {
        this.title = "Error Page";
      }
    },
    activeReset() {
      for (var i = 0; i < this.side.length; i++) {
        this.side[i].active = false;
      }
    },
    init() {
      // 사용자 정보 가져오기
      UserInfoDataService.getInfo(this.user.studentno).then(item => {
        let res = item.data
        let getinfo = {}

        getinfo.english_name = res.english_name
        getinfo.chinese_name = res.chinese_name
        getinfo.grade = res.grade
        getinfo.gender = res.gender
        getinfo.nationality = res.nationality
        getinfo.department = res.department
        getinfo.major = res.major
        getinfo.student_status = res.student_status
        getinfo.phone = res.phone
        getinfo.address = res.address
        getinfo.guardian_name = res.guardian_name
        getinfo.guardian_relation = res.guardian_relation
        getinfo.guardian_phone = res.guardian_phone
        getinfo.landline = res.landline
        getinfo.point = res.point
        getinfo.res_fac = res.res_fac
        getinfo.res_room = res.res_room

        this.userinfo = getinfo
      })

      // 공지사항
      NoticeDataService.getMy(this.user.studentno).then(item => {
        let res = item.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].title = res[i].title
          list[i].date = res[i].date
          list[i].category = "공지사항"
        }

        for (let i = 0; i < list.length; i++) {
          this.asseyList.push(list[i])
        }
      })

      // 사생자치회
      NoticeDataService.getMy1(this.user.studentno).then(item => {
        let res = item.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].title = res[i].title
          list[i].date = res[i].date
          list[i].category = "사생자치회"
        }

        for (let i = 0; i < list.length; i++) {
          this.asseyList.push(list[i])
        }
      })
      // 불편/수리
      RepairDataService.getMy(this.user.studentno).then(item => {
        let res = item.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].title = res[i].title
          list[i].date = res[i].date
          list[i].category = "불편/수리"
        }

        for (let i = 0; i < list.length; i++) {
          this.asseyList.push(list[i])
        }
      })
      // 나눔장터
      StoreDataService.getMy(this.user.studentno).then(item => {
        let res = item.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].title = res[i].title
          list[i].date = res[i].date
          list[i].category = "나눔장터"
        }

        for (let i = 0; i < list.length; i++) {
          this.asseyList.push(list[i])
        }
      })
      // 분실물
      LostDataService.getMy(this.user.studentno).then(item => {
        let res = item.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].title = res[i].title
          list[i].date = res[i].date
          list[i].category = "분실물"
        }

        for (let i = 0; i < list.length; i++) {
          this.asseyList.push(list[i])
        }
      })

      // 사용자 상벌점 가죠오기
      UserPointDataService.getAll().then(item => {
        let res = item.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].year = res[i].date.slice(0,4) + "년도"
          list[i].empty = ''
          if (res[i].plusPoint > 0) {
            list[i].point = res[i].plusPoint
          } else if (res[i].minusPoint > 0) {
            list[i].point = res[i].minusPoint * -1
          }
          list[i].title = res[i].reason
          list[i].date_receive = res[i].date_receive
          list[i].date = res[i].date
        }

        this.pointList = list
      })

      //사용자 사용 중인 스터디룸 정보 가져오기
      ApplyStudyroomDataService.getAll().then(item => {
        let res = item.data
        let list = [], studyIndex = 0

        for (let i = 0; i < res.length; i++) {
          if (res[i].studentNo == this.user.studentno) {
            list.push({})
            list[studyIndex].no = res[i].id
            list[studyIndex].seat = res[i].seat
            list[studyIndex].date = res[i].date.slice(0, 10)
            list[studyIndex].status = ''
            if (res[i].timeslot1) {
              if (res[i].timeslot1 == 1) {
                list[studyIndex].status += "09:00-11:00"
              } else if (res[i].timeslot1 == 2) {
                list[studyIndex].status += "11:00-13:00"
              } else if (res[i].timeslot1 == 3) {
                list[studyIndex].status += "13:00-15:00"
              } else if (res[i].timeslot1 == 4) {
                list[studyIndex].status += "15:00-17:00"
              } else if (res[i].timeslot1 == 5) {
                list[studyIndex].status += "17:00-19:00"
              } else if (res[i].timeslot1 == 6) {
                list[studyIndex].status += "19:00-21:00"
              } else if (res[i].timeslot1 == 7) {
                list[studyIndex].status += "21:00-23:00"
              }
            } if (res[i].timeslot2) {
              if (res[i].timeslot3) {
                list[studyIndex].status += " 외 2건"
              } else {
                list[studyIndex].status += " 외 1건"
              }
            }
            studyIndex++
          }
        }

        this.studyList = list
      })

      // 사용자 상담 정보 가져오기
      ApplyConsultDataService.getAll().then(consultData => {
        let res = consultData.data
        let list = [], resindex = 0

        for (let i = 0; i < res.length; i += 5) {
          if (this.user.studentno == res[i].studentNo) {
            if (list.length == 0) {
              list.push({})
              list[resindex].no = res[i].id
              list[resindex].topic = res[i].topic
              list[resindex].name = res[i].name
              list[resindex].date = res[i].date.slice(0, 10)
              resindex++
            } else {
              if (res[i].date == res[i - 1].date && res[i].studentNo == res[i - 1].studentNo
                && res[i].topic == res[i - 1].topic && res[i].subject == res[i - 1].subject) {
                continue
              } else {
                list.push({})
                list[resindex].no = res[i].id
                list[resindex].topic = res[i].topic
                list[resindex].name = res[i].name
                list[resindex].date = res[i].date.slice(0, 10)
                resindex++
              }
            }
          }
        }
        this.consultList = list
      })

      // 사용자 외박 정보 가져오기
      UserInfoDataService.getSleepout(this.user.studentno).then(sleepooutData => {
        let res = sleepooutData.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].reason = res[i].reason
          if (res[i].approved) {
            list[i].approved = "승인 완료"
          } else {
            list[i].approved = "승인 대기"
          }
        }
        this.sleepList = list
      })
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
  margin: 100px 0 300px;
  display: flex;

  .left_container {
    width: 18%;
  }

  .right_container {
    width: 82%;
  }
}
</style>
