<template>
    <div class="mypage_container">
        <!-- <button @click="logOut()">Logout</button> -->
        <div class="mypage">
            <div class="profile box">
                <div class="profile_box box innerbox">
                    <div class="profile_title">내 프로필</div><br>
                    <img src="@/assets/profile.png" alt="이미지" />
                    <div class="pro_info">
                        이름 : {{this.user.name}}<br>
                        학번 : {{this.user.studentno}}<br>
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
                    <div class="item"
                        v-for="(item, index) in mywrite_item"
                        :key="index">
                        
                        <div class="catetitle">
                            <div class="category">{{ item.category }}</div>
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
                    <a href="/mypage/mypoint" class="more_details">합계 : {{ this.userinfo.point }}점</a>
                </div>
                <div class="point_box box innerbox">
                    <div class="item"
                        v-for="(item, index) in point_item"
                        :key="index">
                        
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
                            <div class="item"
                                v-for="(item, index) in studyroom_item"
                                :key="index">
                                
                                <div class="catetitle">
                                    <div class="date">{{ item.date }}</div>
                                    <div class="number">{{ item.number }}</div>
                                </div>
                                <div class="use">{{ item.use }}</div>
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
                            <div class="item"
                                v-for="(item, index) in counseling_item"
                                :key="index">
                                
                                <div class="catetitle">
                                    <div class="title">{{ item.title }}</div>
                                    <div class="date">{{ item.date }}</div>
                                </div>
                                <div class="res">{{ item.res }}</div>
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
                        <div class="inout">들어올날</div>
                        <div class="inout">나가는날</div>
                        <div class="inout">진행상태</div>
                    </div>
                    <div class="item"
                        v-for="(item, index) in sleepout_item"
                        :key="index">
                            
                        <div class="indate">{{ item.indate }}</div>
                        <div class="outdate">{{ item.outdate }}</div>
                        <div class="status">{{ item.approved }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </template>

<script>
import UserInfoDataService from "@/services/UserInfoDataService";

    export default {
        name: "MyPageView",
        data() {
            return {
                mywrite_item: [
                    {
                        category: "[분실물]",
                        title: "공책 잃어 버리신 분",
                        date: "2022.07.22",
                    },
                    {
                        category: "[나눔장터]",
                        title: "샴푸 필요하신 분",
                        date: "2022.07.03",
                    },
                    {
                        category: "[나눔장터]",
                        title: "필기구 남는 거 나눔합니다",
                        date: "2022.07.01",
                    },
                    {
                        category: "[분실물]",
                        title: "식당에서 보조 배터리 잃어 버리신 분",
                        date: "2022.06.28",
                    },
                    {
                        category: "[나눔장터]",
                        title: "쿠키 드실 분 있나요?",
                        date: "2022.06.05",
                    },
                    {
                        category: "[분실물]",
                        title: "가방 두고 가신 분",
                        date: "2022.05.15",
                    },
                ],
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
                ],
                studyroom_item: [
                    {
                        use: "이용 중",
                        number: "78",
                        date: "2022.07.22",
                    },
                    {
                        use: "이용 완료",
                        number: "78",
                        date: "2022.07.22",
                    },
                    {
                        use: "이용 완료",
                        number: "78",
                        date: "2022.07.22",
                    },
                ],
                counseling_item: [
                    {
                        res: "예약 대기 중",
                        title: "심리 상담",
                        date: "22/12/02 15:00",
                    },
                    {
                        res: "예약 완료",
                        title: "심리 상담",
                        date: "22/08/14 14:00",
                    },
                    {
                        res: "예약 완료",
                        title: "심리 상담",
                        date: "22/07/08 15:00",
                    },
                ],
                sleepout_item: [],
                userinfo: [],
            };
        },
        computed: {
            user() {
                return this.$store.state.auth.user;
            },
        },
        components: {},
        mounted () {
            // this.user = this.loggedUser;
        },
        created(){
            this.init()
        },
        methods: {
            logOut() {
                this.$store.dispatch('auth/logout');
                this.$router.push('/login');
            },
            init(){
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

                UserInfoDataService.getSleepout(this.user.studentno).then(sleepooutData => {
                    let res = sleepooutData.data
                    let list = []

                    for (let i=0; i<res.length; i++) {
                        list.push({})
                        list[i].no = res[i].id
                        list[i].reason = res[i].reason
                        list[i].outdate = res[i].date_sleepout

                        //들어올 날에 하루 더하기
                        let sleepout_intdate = new Date(res[i].date_sleepout)
                        let year = sleepout_intdate.getFullYear();
                        let month = sleepout_intdate.getMonth() + 1;
                        let day = sleepout_intdate.getDate() + 1;
                        if(month < 10) month = '0' + month;
                        if(day < 10) day = '0' + day;

                        list[i].indate = year + '-' + month + '-' + day

                        if(res[i].approved){
                            list[i].approved = "승인 완료"
                        }else{
                            list[i].approved = "승인 중"
                        }
                    }
                    this.sleepout_item = list
                })
            }
        }
    };
    </script>

<style scoped lang="less">
.mypage_container{
    width: 1080px;
    display: flex;
    .mypage{
        margin: 80px auto;
        width: 100%;
        img{
            margin-right: 10px;
            height: 20px;
        }
        .box{
            border-radius: 10px;
            background-color: white;
            .innerbox{
                margin: 0 auto;
            }
        }
        .more_details{
            font-size: 16px;
            margin-right: 25px;
        }
        .more_details:hover {
            cursor: pointer;
        }
        .mypage_subtitle{
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
            font-weight: bold;
            font-size: 23px;
            margin: 20px 0px 20px 20px;
            a{
                color: white;
            }
        }
        .profile{
            background-color: white;
            width: 31%;
            height: 52%;
            float: left;
            margin-bottom: 1%;
            box-shadow: 0px 0px 10px #bababa;
            text-align: center;
            .profile_box{
                margin-top: 40px;
                .profile_title{
                    font-weight: bold;
                    font-size: 20px;
                    margin-bottom: -15px;
                }
                img{
                    width: 150px;
                    height: 160px;
                }
            }
        }
        .mywrite{
            background-color: #54AEAD;
            width: 67%;
            height: 52%;
            float: right;
            margin-bottom: 1%;
            .mywrite_box{
                width: 95%;
                height: 72%;
                padding: 10px 20px 10px 0;
                .item {
                    display: flex;
                    justify-content: space-between;
                    padding: 5px 5px;
                    margin-top: 5px;
                    font-size: 16px;
                    .catetitle{
                        display: flex;
                        &:hover {
                            cursor: pointer;
                            & .category, .title {
                                color: #54AEAD;
                            }
                        }
                        .category, .title {
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
        .pointbox{
            background-color: #648DDD;
            width: 31%;
            height: 55%;
            float: left;
            margin-right: 2%;
            .point_box{
                width: 95%;
                height: 78%;
                .item {
                    display: flex;
                    justify-content: space-between;
                    padding: 8px 10px;
                    margin-top: 5px;
                    font-size: 15px;
                    .catetitle{
                        display: flex;
                        .point{
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
        .reservation{
            background-color: #00B6AD;
            width: 33%;
            height: 55%;
            float: left;
            margin-right: 1%;
            .reservation_box{
                width: 95%;
                height: 78%;
                .reservation_minibox{
                    padding-top: 5px;
                    margin: 0 15px;
                    .reservationtitle{
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        width: 100%;
                        color: #00B6AD;
                        font-size: 19px;
                        font-weight: bold;
                        float: left;
                        .more_details{
                            color: #00B6AD;
                            font-size: 14px;
                            margin: 0;
                        }
                    }
                    .studyroom{
                        .item {
                            display: flex;
                            justify-content: space-between;
                            padding: 8px 5px;
                            font-size: 16px;
                            .catetitle{
                                display: flex;
                                .number, .use {
                                    color: #222222;
                                    margin-left: 50px;
                                }
                            }
                            .date {
                                color: #858585;
                            }
                        }
                    }

                    .counseling{
                        .item {
                            display: flex;
                            justify-content: space-between;
                            padding: 8px 5px;
                            font-size: 15px;
                            .catetitle{
                                display: flex;
                                .res, .date {
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
        .night{
            background-color: #66C6C4;
            width: 33%;
            height: 55%;
            float: left;
            .night_box{
                width: 95%;
                height: 78%;
                .subtitle_container{
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    padding: 8px 25px;
                    color:#00B6AD;
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