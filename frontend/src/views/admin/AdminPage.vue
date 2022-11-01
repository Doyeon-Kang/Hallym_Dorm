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
