<template>
  <div>
    <PageTitle :title="title"></PageTitle>
  </div>
</template>

<script>
import PageTitle from "@/components/PageTitle.vue";

export default {
  data() {
    return {
      title: "String",
    };
  },
  components: {
    PageTitle,
  },
  created() {
    this.routeCheck();
  },
  methods: {
    routeCheck() {
      if (this.$route.name === "myassey") {
        this.title = "마이페이지 > 내가 작성한 글";
      } else if (this.$route.name === "myconsulting") {
        this.title = "마이페이지 > 상담 신청 현황";
      } else if (this.$route.name === "mystudy") {
        this.title = "마이페이지 > 스터디룸 예약 현황";
      } else if (this.$route.name === "mysleep") {
        this.title = "마이페이지 > 외박 신청 현황";
      } else if (this.$route.name === "mypoint") {
        this.title = "마이페이지 > 상/벌점 내역";
      } else {
        this.title = "Error Page";
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
