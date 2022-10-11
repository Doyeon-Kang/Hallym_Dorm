<template>
<div class="wrapper">
    <div class="left_container">
        <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
        <PageTitle :title="title"></PageTitle>
    </div>  
</div>
    
</template>

<script>
import SidebarCom from '@/components/SidebarCom.vue';
import PageTitle from "@/components/PageTitle.vue";

export default {
    data() {
        return {
            title: "String",
            pageName: "신청 및 예약",
            // 사이드바 정보
            side: [
                { title: "상담 및 면담신청", path: "/reserve", active: true},
                { title: "스터디룸 예약", path: "/reserve/study" },
                {
                title: "입사/퇴사 신청",
                path: "/reserve/in",
                semi: true,
                semiTitle: [
                    { title: "입사신청", path: "/reserve/in", active: false },
                    { title: "퇴사신청", path: "/reserve/out", active: false },
                ],
                },
                { title: "외박 신청", path: "/reserve/sleep" },
                
            ],
        }
    },
    components: { SidebarCom, PageTitle},
    created() {
        this.routeCheck();
    },
    methods: {
        routeCheck() {
        this.activeReset();
        this.side[2].semi = false;
        if (this.$route.name === "consult") {
            this.title = "신청 및 예약 > 상담 및 면담 신청";
            this.side[0].active = true;
        }  else if (this.$route.name === "study") {
            this.title = "신청 및 예약 > 스터디룸 예약";
            this.side[1].active = true;
        } else if (this.$route.name === "in") {
            this.title = "신청 및 예약 > 입사 신청";
            this.side[2].active = true;
            this.side[2].semiTitle[0].active = true;
            this.side[2].semiTitle[1].active = false;
            this.side[2].semi = true;
        } else if (this.$route.name === "out") {
            this.title = "신청 및 예약 > 퇴사 신청";
            this.side[2].active = true;
            this.side[2].semiTitle[0].active = false;
            this.side[2].semiTitle[1].active = true;
            this.side[2].semi = true;
        }  else if (this.$route.name === "sleep") {
            this.title = "신청 및 예약 > 외박 신청";
            this.side[3].active = true;
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
        $route(to) {
        this.routeCheck();
        if (to.path == "/reserve/in" || to.path == "/reserve/out") {
            this.side[2].semi = true;
            if (to.path == "/reserve/in") {
            this.side[2].semiTitle[0].active = true;
            this.side[2].semiTitle[1].active = false;
            } else {
            this.side[2].semiTitle[1].active = true;
            this.side[2].semiTitle[0].active = false;
            }
        } else {
            for (var i = 0; i < this.side.length; i++) {
            this.side[i].semi = false;
            }
        }
        },
    },
}
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