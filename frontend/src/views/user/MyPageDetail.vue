<template>
  <div class="wrapper">
    <div class="left_container">
      <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
      <PageTitle :title="title"></PageTitle>
      <BoardList
        v-if="$route.name === 'myassey'"
        :listItem="asseyList"
        :listTitle="asseyTitle"
      ></BoardList>
      <BoardList
        v-else-if="$route.name === 'myconsulting'"
        :listItem="consultList"
        :listTitle="consultTitle"
      ></BoardList>
      <BoardList
        v-else-if="$route.name === 'mystudy'"
        :listItem="studyList"
        :listTitle="studyTitle"
      ></BoardList>
      <BoardList
        v-else-if="$route.name === 'mysleep'"
        :listItem="sleepList"
        :listTitle="sleepTitle"
      ></BoardList>
      <BoardList
        v-else-if="$route.name === 'mypoint'"
        :listItem="pointList"
        :listTitle="pointTitle"
      ></BoardList>
    </div>
  </div>
</template>

<script>
import PageTitle from "@/components/PageTitle.vue";
import SidebarCom from "../../components/SidebarCom.vue";
import BoardList from "../../components/BoardList.vue";

export default {
  data() {
    return {
      title: "String",
      pageName: "마이페이지",
      side: [
        { title: "내가 작성한 글", path: "/mypage/myassey" },
        { title: "상담 신청", path: "/mypage/myconsulting" },
        { title: "스터디룹 예약", path: "/mypage/mystudy" },
        { title: "외박 신청", path: "/mypage/mysleep" },
        { title: "상벌점 내역", path: "/mypage/mypoint" },
      ],
      asseyTitle: ["번호", "글제목", "작성일", "작성게시판"],
      asseyList: [
        {
          no: "1",
          title: "공책 잃어버리신 분",
          date: "2022.06.18",
          board: "분실물 게시판",
          url: "",
        },
        {
          no: "2",
          title: "공책 잃어버리신 분",
          date: "2022.06.18",
          board: "분실물 게시판",
          url: "",
        },
        {
          no: "3",
          title: "공책 잃어버리신 분",
          date: "2022.06.18",
          board: "분실물 게시판",
          url: "",
        },
        {
          no: "4",
          title: "쿠키 드실 분 있나요?",
          date: "2022.06.18",
          board: "나눔장터 게시판",
          url: "",
        },
        {
          no: "5",
          title: "쿠키 드실 분 있나요?",
          date: "2022.06.18",
          board: "나눔장터 게시판",
          url: "",
        },
        {
          no: "6",
          title: "공책 잃어버리신 분",
          date: "2022.06.18",
          board: "분실물 게시판",
          url: "",
        },
        {
          no: "7",
          title: "공책 잃어버리신 분",
          date: "2022.06.18",
          board: "분실물 게시판",
          url: "",
        },
      ],
      consultTitle: [
        "번호",
        "상담분야",
        "상담자",
        "상담날짜",
        "상담시간",
        "진행상태",
      ],
      consultList: [{}],
      studyTitle: [
        "번호",
        "내 좌석",
        "신청날짜",
        "신청시간",
        "진행상태",
        "비고",
      ],
      studyList: [{}],
      sleepTitle: ["번호", "글제목", "진행상태", "처리일자"],
      sleepList: [{}],
      pointTitle: [
        "번호",
        "년도-학기",
        "구분",
        "점수",
        "사유",
        "발생일",
        "입력일",
      ],
      pointList: [{}],
    };
  },
  components: {
    PageTitle,
    SidebarCom,
    BoardList,
  },
  created() {
    this.routeCheck();
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
