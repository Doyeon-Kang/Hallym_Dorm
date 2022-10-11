<template>
<div class="wrapper">
    <div class="left_container">
        <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
        <PageTitle :title="title"></PageTitle>
        <div class="content_wrap">
            <div v-if="$route.name === 'consult'" class='consult_container'>
                <form action="">
                    <table class="consult_table">
                        <tr>
                            <td>이름</td>
                            <td>홍길동</td>
                            <td>학번</td>
                            <td>20221234</td>
                        </tr>
                        <tr>
                            <td>연락처</td>
                            <td>
                                <select name="phone1" required>
                                    <option value="010">010</option>
                                    <option value="02">02</option>
                                    <option value="031">031</option>
                                    <option value="032">032</option>
                                    <option value="033">033</option>
                                    <option value="034">034</option>
                                    <option value="035">035</option>
                                    <option value="036">036</option>
                                </select> -
                                <input type='tel' name='phone2' required/> -
                                <input type='tel' name='phone3' required/>
                            </td>
                            <td>이메일</td>
                            <td>
                                <input type="email"
                                        pattern=".+@gmail\.com"
                                        placeholder="example@gmail.com"
                                        required>
                            </td>
                        </tr>
                        <tr>
                            <td>상담분야</td>
                            <td>
                                <select name="" id="">
                                    <option value="교우관계">교우관계</option>
                                </select>
                            </td>
                            <td>신청일자</td>
                            <td>{{ today }}</td>
                        </tr>
                        <tr>
                            <td>상담내용</td>
                            <td colspan="3">
                                <input type="text" size="100" maxlength="100" placeholder="100자 이내로 입력하세요">
                            </td>
                        </tr>
                        <tr>
                            <td>상담일정</td>
                            <td colspan="3">
                                <table class="time_table">
                                    <caption>상담 가능 시간표</caption>
                                    <tr>
                                        <td></td>
                                        <td>월</td>
                                        <td>화</td>
                                        <td>수</td>
                                        <td>목</td>
                                        <td>금</td>
                                    </tr>
                                    <tr>
                                        <td>9~10</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>10~11</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>11~12</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>12~13</td>
                                        <td colspan="5">점심시간</td>
                                    </tr>
                                    <tr>
                                        <td>13~14</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>14~15</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>15~16</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>16~17</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                    <tr>
                                        <td>17~18</td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                        <td><input type="checkbox" name="" id=""></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="background-color:#fff">
                                <input type="submit" value="신청하기">
                            </td>
                        </tr>
                    </table>
                    
                </form>
            </div>
            <div v-else-if="$route.name === 'study'" class='study_container'>
                
            </div>
            <div v-else-if="$route.name === 'in'" class='in_container'>
                
            </div>
            <div v-else-if="$route.name === 'out'" class='out_container'>
                
            </div>
            <div v-else-if="$route.name === 'sleep'" class='sleep_container'>
                
            </div>
            <div v-else>
                error_page
            </div>
        </div>
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
    computed: {
        today() {
            let date = new Date();
            return date.toLocaleDateString();
        }
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
        .content_wrap {
            .consult_container {
                .consult_table {
                    border-top: 1px solid #C0C0C0;
                    tr {
                        td {
                            padding: 20px;
                            border: 1px solid #C0C0C0;
                            border-collapse: collapse;
                        }
                        td:first-child, td:nth-child(3) {
                            background-color: #D9D9D9;
                            font-weight: 600;
                            text-align: center;
                        }
                    }
                    tr:nth-child(6) {
                        border: none;
                        td {
                            border: none;
                            padding: 20px 0;
                            input[type="submit"] {
                                float: right;
                                background-color: #336EB4;
                                color: #fff;
                                font-size: 18px;
                                padding: 20px;
                                border: none;
                                &:hover {
                                    cursor: pointer;
                                }
                            }
                        }
                    }
                    .time_table {
                        width: 100%;
                        caption {
                            font-weight: 700;
                            font-size: 20px;
                            margin-bottom: 10px;
                        }
                        tr, td {
                            text-align: center;
                            border: 1px solid #C0C0C0 !important;
                        }
                        td:nth-child(3) {
                            background-color: #fff;
                        }
                        tr:nth-child(2) {
                            td {
                                background-color: #EFEFEF;
                                font-weight: 300;
                            }
                        }
                    }
                }
                
            }
        }
    }
}
</style>