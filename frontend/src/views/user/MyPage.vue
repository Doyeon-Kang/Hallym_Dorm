<template>
    <div class="mypage_container">
        <!-- <button @click="logOut()">Logout</button> -->
        <div class="mypage">
            <div class="profile box">
                <div class="profile_box box innerbox">
                    <div class="profile_title">내 프로필</div><br>
                    <img src="@/assets/profile.png" alt="이미지" />
                    <div class="pro_info">
                        이름 : {{ this.user.name }}<br>
                        학번 : {{ this.user.studentno }}<br>
                        거주관 : {{ this.userinfo.res_fac }}관 {{ this.userinfo.res_room }}호<br>
                    </div>
                </div>
            </div>
            <div class="mywrite box">
                <div class="mypage_subtitle">
                    <div class="subtitle_container">
                        <img src="@/assets/write.png" alt="이미지" />
                        내가 작성한 글
                    </div>
                    <a href="/mypage/myassey" class="more_details">더보기 ▶</a>
                </div>
                <div class="mywrite_box box innerbox">
                    <div class="item" v-for="item in mywrite_Data" :key="item">

                        <div class="catetitle">
                            <!-- <div class="category">{{ item.category }}</div> -->
                            <div class="title">{{ item.title }}</div>
                        </div>
                        <div class="date">{{ item.date }}</div>
                    </div>
                </div>
            </div>
            <div class="pointbox box">
                <div class="mypage_subtitle">
                    <div class="subtitle_container">
                        <img src="@/assets/pointplus.png" alt="이미지" />
                        상벌점 내역
                    </div>
                    <a href="/mypage/mypoint" class="more_details">합계 : {{ this.userinfo.plusPoint +
                            this.userinfo.minusPoint
                    }}점</a>
                </div>
                <div class="point_box box innerbox">
                    <div class="item" v-for="item in point_Data" :key="item">

                        <div class="catetitle">
                            <div class="point">{{ item.point }}</div>
                            <div class="title">{{ item.title }}</div>
                        </div>
                        <div class="date">{{ item.date }}</div>
                    </div>
                </div>
            </div>
            <div class="reservation box">
                <div class="mypage_subtitle">
                    <div class="subtitle_container">
                        <img src="@/assets/reservation.png" alt="이미지" />
                        예약 현황
                    </div>
                </div>
                <div class="reservation_box box innerbox">
                    <div class="reservation_minibox">
                        <div class="reservationtitle">
                            <div class="subtitle_container">
                                8관 스터디룸 예약 현황
                            </div>
                            <a href="/mypage/mystudy" class="more_details">더보기 ▶</a>
                        </div>
                        <div class="studyroom">
                            <div class="item" v-for="item in studyroom_Data" :key="item">

                                <div class="catetitle">
                                    <div class="date">{{ item.date }}</div>
                                    <div class="seat">{{ item.seat }}번</div>
                                </div>
                                <div class="use">{{ item.status }}</div>
                            </div>
                        </div>
                    </div>
                    <div class="reservation_minibox">
                        <div class="reservationtitle">
                            <div class="subtitle_container">
                                상담 신청 현황
                            </div>
                            <a href="/mypage/myconsulting" class="more_details">더보기 ▶</a>
                        </div>
                        <div class="counseling">
                            <div class="item" v-for="item in consult_Data" :key="item">

                                <div class="catetitle">
                                    <div class="title">{{ item.topic }}</div>
                                </div>
                                <div class="date">{{ item.date }}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="night box">
                <div class="mypage_subtitle">
                    <div class="subtitle_container">
                        <img src="@/assets/night.png" alt="이미지" />
                        외박 신청 현황
                    </div>
                    <a href="/mypage/mysleep" class="more_details">더보기 ▶</a>
                </div>
                <div class="night_box box innerbox">
                    <div class="subtitle_container">
                        <div class="inout">나가는날</div>
                        <div class="inout">들어올날</div>
                        <div class="inout">진행상태</div>
                    </div>
                    <div class="item" v-for="item in sleepout_Data" :key="item">

                        <div class="outdate">{{ item.outdate }}</div>
                        <div class="indate">{{ item.indate }}</div>
                        <div class="status">{{ item.approved }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import UserInfoDataService from "@/services/UserInfoDataService";
import NoticeDataService from "@/services/NoticeDataService";
import RepairDataService from "@/services/RepairDataService";
import StoreDataService from "@/services/StoreDataService";
import LostDataService from "@/services/LostDataService";
import ApplyConsultDataService from "@/services/ApplyConsultDataService";
import ApplyStudyroomDataService from "@/services/ApplyStudyroomDataService";

export default {
    name: "MyPageView",
    data() {
        return {
            mywrite_item: [],
            point_item: [
                {
                    point: "+3",
                    title: "프로그램 참가",
                    date: "2022.07.21",
                },
                {
                    point: "-2",
                    title: "무단 외박",
                    date: "2022.07.01",
                },
                {
                    point: "+2",
                    title: "프로그램 참가",
                    date: "2022.05.21",
                },
                {
                    point: "+3",
                    title: "프로그램 참가",
                    date: "2022.05.10",
                },
                {
                    point: "+3",
                    title: "프로그램 참가",
                    date: "2022.05.10",
                },
                {
                    point: "+3",
                    title: "프로그램 참가",
                    date: "2022.05.10",
                },
                {
                    point: "+3",
                    title: "프로그램 참가",
                    date: "2022.05.10",
                },
            ],
            studyroom_item: [],
            consult_item: [],
            sleepout_item: [],
            userinfo: [],
        };
    },
    computed: {
        user() {
            return this.$store.state.auth.user;
        },
        mywrite_Data() {
            return this.mywrite_item.slice(-7);
        },
        point_Data() {
            return this.point_item.slice(-5);
        },
        studyroom_Data() {
            return this.studyroom_item.slice(-3);
        },
        consult_Data() {
            return this.consult_item.slice(-3);
        },
        sleepout_Data() {
            return this.sleepout_item.slice(-7);
        },
    },
    components: {},
    mounted() {
        // this.user = this.loggedUser;
    },
    created() {
        this.init()
    },
    methods: {
        logOut() {
            this.$store.dispatch('auth/logout');
            this.$router.push('/login');
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
                getinfo.plusPoint = res.plusPoint
                getinfo.minusPoint = res.minusPoint
                getinfo.res_fac = res.res_fac
                getinfo.res_room = res.res_room

                this.userinfo = getinfo
            })

            // 공지사항
            NoticeDataService.getMy(this.user.studentno).then(resolveData => {
                let res = resolveData.data
                let list = []

                for (let i = 0; i < res.length; i++) {
                    if(this.user.studentno == res[i].writerStudentNo){
                        list.push({})
                        list[i].no = res[i].id
                        list[i].title = res[i].title
                        list[i].date = res[i].date
                    }
                }
                this.mywrite_item += list
            })
            
            // 사생자치회
            NoticeDataService.getMy1(this.user.studentno).then(resolveData => {
                let res = resolveData.data
                let list = []

                for (let i = 0; i < res.length; i++) {
                    if(this.user.studentno == res[i].writerStudentNo){
                        list.push({})
                        list[i].no = res[i].id
                        list[i].title = res[i].title
                        list[i].date = res[i].date
                    }
                }
                this.mywrite_item += list
            })
            // 불편/수리
            RepairDataService.getMy(this.user.studentno).then(resolveData => {
                let res = resolveData.data
                let list = []

                for (let i = 0; i < res.length; i++) {
                    if(this.user.studentno == res[i].writerStudentNo){
                        list.push({})
                        list[i].no = res[i].id
                        list[i].title = res[i].title
                        list[i].date = res[i].date
                    }
                }
                this.mywrite_item += list
            })
            // 나눔장터
            StoreDataService.getMy(this.user.studentno).then(resolveData => {
                let res = resolveData.data
                let list = []

                for (let i = 0; i < res.length; i++) {
                    if(this.user.studentno == res[i].writerStudentNo){
                        list.push({})
                        list[i].no = res[i].id
                        list[i].title = res[i].title
                        list[i].date = res[i].date
                    }
                }
                this.mywrite_item += list
            })
            // 분실물
            LostDataService.getMy(this.user.studentno).then(resolveData => {
                let res = resolveData.data
                let list = []

                for (let i = 0; i < res.length; i++) {
                    if(this.user.studentno == res[i].writerStudentNo){
                        list.push({})
                        list[i].no = res[i].id
                        list[i].title = res[i].title
                        list[i].date = res[i].date
                    }
                }
                this.mywrite_item += list
            })

            // 사용자 외박 정보 가져오기
            UserInfoDataService.getSleepout(this.user.studentno).then(sleepooutData => {
                let res = sleepooutData.data
                let list = []

                for (let i = 0; i < res.length; i++) {
                    list.push({})
                    list[i].no = res[i].id
                    list[i].reason = res[i].reason
                    list[i].outdate = res[i].date_sleepout

                    //들어올 날에 하루 더하기
                    let sleepout_intdate = new Date(res[i].date_sleepout)
                    let year = sleepout_intdate.getFullYear();
                    let month = sleepout_intdate.getMonth() + 1;
                    let day = sleepout_intdate.getDate() + 1;
                    if (month == 12 && day > 31) {
                        year = sleepout_intdate.getFullYear() + 1;
                        month = 1;
                        day = 1;
                    } else {
                        if (((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31) || (month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                            month = sleepout_intdate.getMonth() + 2;
                            day = 1;
                        } else {
                            month = sleepout_intdate.getMonth() + 1;
                            day = sleepout_intdate.getDate() + 1;
                        }
                    }
                    if (month < 10) month = '0' + month;
                    if (day < 10) day = '0' + day;

                    list[i].indate = year + '-' + month + '-' + day

                    if (res[i].approved) {
                        list[i].approved = "승인 완료"
                    } else {
                        list[i].approved = "승인 대기"
                    }
                }
                this.sleepout_item = list
            })

            //사용자 사용 중인 스터디룸 정보 가져오기
            ApplyStudyroomDataService.getAll().then(item => {
                let res = item.data
                let list = [], studyIndex = 0

                for (let i = 0; i < res.length; i++) {
                    if (res[i].studentNo == this.user.studentno) {
                        list.push({})
                        list[studyIndex].status = ''
                        list[studyIndex].date = res[i].date.slice(0, 10)
                        list[studyIndex].seat = res[i].seat
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
                        } if(res[i].timeslot2){
                            if(res[i].timeslot3){
                                list[studyIndex].status += " 외 2건"
                            } else {
                                list[studyIndex].status += " 외 1건"
                            }
                        }
                        studyIndex++
                    }
                }

                this.studyroom_item = list
            })

            // 사용자 상담 정보 가져오기
            ApplyConsultDataService.getAll().then(consultData => {
                let res = consultData.data
                let list = [], resindex = 0

                for (let i = 0; i < res.length; i+=5) {
                    if (this.user.studentno == res[i].studentNo) {
                        if (list.length == 0) {
                            list.push({})
                            list[resindex].topic = res[i].topic
                            list[resindex].date = res[i].date.slice(0, 10)
                            resindex++
                        } else {
                            if (res[i].date == res[i - 1].date && res[i].studentNo == res[i - 1].studentNo
                            && res[i].topic == res[i - 1].topic && res[i].subject == res[i - 1].subject) {
                                continue
                            } else {
                                list.push({})
                                list[resindex].topic = res[i].topic
                                list[resindex].date = res[i].date.slice(0, 10)
                                resindex++
                            }
                        }
                    }
                }
                this.consult_item = list
            })
        }
    }
};
</script>

<style scoped lang="less">
.mypage_container {
    width: 1080px;
    display: flex;

    .mypage {
        margin: 80px auto;
        width: 100%;

        img {
            margin-right: 10px;
            height: 20px;
        }

        .box {
            border-radius: 10px;
            background-color: white;

            .innerbox {
                margin: 0 auto;
            }
        }

        .more_details {
            font-size: 16px;
            margin-right: 25px;
        }

        .more_details:hover {
            cursor: pointer;
        }

        .mypage_subtitle {
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
            font-weight: bold;
            font-size: 23px;
            margin: 20px 0px 20px 20px;

            a {
                color: white;
            }
        }

        .profile {
            background-color: white;
            width: 31%;
            height: 360px;
            float: left;
            margin-bottom: 1%;
            box-shadow: 0px 0px 10px #bababa;
            text-align: center;

            .profile_box {
                margin-top: 40px;

                .profile_title {
                    font-weight: bold;
                    font-size: 20px;
                    margin-bottom: -15px;
                }

                img {
                    width: 150px;
                    height: 160px;
                }
            }
        }

        .mywrite {
            background-color: #54AEAD;
            width: 67%;
            height: 360px;
            float: right;
            margin-bottom: 1%;

            .mywrite_box {
                width: 95%;
                height: 72%;
                padding: 10px 20px 10px 0;

                .item {
                    display: flex;
                    justify-content: space-between;
                    padding: 5px 5px;
                    margin-top: 5px;
                    font-size: 16px;

                    .catetitle {
                        display: flex;

                        &:hover {
                            cursor: pointer;

                            & .category,
                            .title {
                                color: #54AEAD;
                            }
                        }

                        .category,
                        .title {
                            color: #222222;
                            margin-left: 5px;
                        }
                    }

                    .date {
                        color: #858585;
                    }
                }
            }
        }

        .pointbox {
            background-color: #648DDD;
            width: 31%;
            height: 400px;
            float: left;
            margin-right: 2%;

            .point_box {
                width: 95%;
                height: 80%;

                .item {
                    display: flex;
                    justify-content: space-between;
                    padding: 15px 10px;
                    font-size: 15px;

                    .catetitle {
                        display: flex;

                        .point {
                            padding: 2px 10px;
                            color: white;
                            background-color: #648DDD;
                            border-radius: 10px;
                        }

                        .point {
                            color: white;
                            margin-left: 5px;
                        }

                        .title {
                            color: #222222;
                            margin-left: 5px;
                        }
                    }

                    .date {
                        color: #858585;
                    }
                }
            }

        }

        .reservation {
            background-color: #00B6AD;
            width: 33%;
            height: 400px;
            float: left;
            margin-right: 1%;

            .reservation_box {
                width: 95%;
                height: 80%;

                .reservation_minibox {
                    padding-top: 5px;
                    height: 155px;
                    margin: 0 15px;

                    .reservationtitle {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        width: 100%;
                        color: #00B6AD;
                        font-size: 19px;
                        font-weight: bold;
                        float: left;

                        .more_details {
                            color: #00B6AD;
                            font-size: 14px;
                            margin: 0;
                        }
                    }

                    .studyroom {
                        .item {
                            display: flex;
                            justify-content: space-between;
                            padding: 8px 5px;
                            font-size: 16px;

                            .catetitle {
                                display: flex;

                                .date {
                                    color: #858585;
                                }

                                .seat {
                                    color: #222222;
                                    margin-left: 30px;
                                }
                            }

                            .use {
                                color: #222222;
                                font-size: 14px;
                            }
                        }
                    }

                    .counseling {
                        .item {
                            display: flex;
                            justify-content: space-between;
                            padding: 8px 5px;
                            font-size: 15px;

                            .catetitle {
                                display: flex;

                                .res,
                                .date {
                                    color: #222222;
                                    margin-left: 25px;
                                }
                            }

                            .date {
                                color: #858585;
                            }
                        }
                    }
                }
            }
        }

        .night {
            background-color: #66C6C4;
            width: 33%;
            height: 400px;
            float: left;

            .night_box {
                width: 95%;
                height: 80%;

                .subtitle_container {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    padding: 8px 25px;
                    color: #00B6AD;
                    font-size: 16px;
                    font-weight: bold;
                }

                .item {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    padding: 8px 20px;
                    font-size: 15px;
                    color: #222222;
                }
            }
        }
    }
}
</style>