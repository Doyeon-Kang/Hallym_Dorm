<template>
    <div class="wrapper_detail">
        메인
        <PageTitle :title="title"></PageTitle>
        <ViewDetailCom></ViewDetailCom>
    </div>
</template>
  
<script>
import PageTitle from "@/components/PageTitle.vue";
import ViewDetailCom from "@/components/ViewDetailCom.vue";

export default {
    data() {
    },
    components: {
        PageTitle,
        ViewDetailCom,
    },
    created() {
        this.routeCheck();
    },
    methods: {
        routeCheck() {
            this.activeReset();
            this.side[0].semi = false;
            if (this.$route.name === "communityNo") {
                this.title = "공시사항 > 학생기숙사 > 게시글 상세보기";
            } else if (this.$route.name === "notice1No") {
                this.title = "공시사항 > 사생자치회 > 게시글 상세보기";
            } else if (this.$route.name === "dataNo") {
                this.title = "게시판 > 서식자료실 > 게시글 상세보기";
            } else if (this.$route.name === "repairNo") {
                this.title = "게시판 > 불편/수리 요청 > 게시글 상세보기";
            } else if (this.$route.name === "marketNo") {
                this.title = "게시판 > 나눔 장터 > 게시글 상세보기";
            } else if (this.$route.name === "lostNo") {
                this.title = "게시판 > 분실물 > 게시글 상세보기";
            } else {
                this.title = "Error Page";
            }
        },
    },
    watch: {
        $route(to) {
        this.routeCheck();
        if (to.path == "/community" || to.path == "/community/notice1") {
            this.side[0].semi = true;
            if (to.path == "/community") {
            this.side[0].semiTitle[0].active = true;
            this.side[0].semiTitle[1].active = false;
            } else {
            this.side[0].semiTitle[1].active = true;
            this.side[0].semiTitle[0].active = false;
            }
        } else {
            for (var i = 0; i < this.side.length; i++) {
            this.side[i].semi = false;
            }
        }
        },
    },
};
</script>
  
<style lang="less" scoped>
.wrapper_detail {
    width: 100%;
    height: 1000px;
    background-color: aqua;
}
</style>
  