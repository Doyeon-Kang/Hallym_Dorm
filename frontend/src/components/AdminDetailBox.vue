<template>
    <div>
        <!--사용자 관리 자세히-->
        <div v-if="$route.path === '/admin/user/detail'" class="con_box" > 
            <div class="con_box_cont">
                <div class="con_title">사용자 정보</div>
                <div class="container" :class="{'status': !status}">
                    <table>
                        <tr v-for="(info, index) in user" :key="index">
                            <th>{{ toKoreanUser(index) }} </th>
                            <td>{{ info }}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!--상벌점 내역 자세히-->
        <div v-else-if="$route.path === '/admin/point/detail'" class="con_box">
            <div class="con_box_cont">
                <div class="con_title">상벌점 내역</div>
                <div class="container out">
                    <table>
                        <tr v-for="(info, index) in point" :key="index">
                            <th>{{ toKoreanPoint(index) }}</th>
                            <td>{{ info }}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!--상담 신청 현황 자세히-->
        <div v-else-if="$route.path === '/admin/consulting/detail'" class="con_box">
            <div class="con_box_cont">
                <div class="con_title">상담 신청 정보</div>
                <div class="container consult">
                    <table>
                        <tr v-for="(info, index) in consult" :key="index">
                            <th>{{ index }}</th>
                            <td>{{ info }}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!--입퇴사 신청 현황 자세히-->
        <div v-else class="con_box">
            <div class="con_box_cont">
                <div class="con_title">
                    <span v-if="this.$route.query.category === 'in'">입사 신청 정보</span>
                    <span v-else>퇴사 신청 정보</span>
                </div>
                <div class="container" :class="{out: this.$route.query.category === 'out'}">
                    <table>
                        <tr v-for="(info, index) in inout" :key="index">
                            <th>{{ toKoreanJoinResign(index) }}</th>
                            <td>{{ info }}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </template>
    
    <script>
    import UserInfoDataService from "@/services/UserInfoDataService"
    import UserPointDataService from "@/services/UserPointDataService";
    import ApplyConsultDataService from "@/services/ApplyConsultDataService"
    import ApplyJoinDataService from "@/services/ApplyJoinDataService";
    import ApplyResignDataService from "@/services/ApplyResignDataService";

    export default {
        // eslint-disable-next-line
        name: 'admindetail', 
        data() {
            return {
                studentno: "",
                role: "",
                id: "",
                status: true,
                user: {},
                point: {},
                consult: {},
                inout: {}
            }
        },
        methods: {
            userInit() {
                if(this.role == 'ROLE_USER_MEMBER' || this.role == 'ROLE_ADMIN') {
                    UserInfoDataService.getInfo(this.studentno).then(data => {
                        let res = data.data
                        this.user = res
                    })
                } else {
                    this.user.status = "입사 신청이 완료되지 않은 계정입니다."
                    this.status = false
                }
            },
            pointInit() {
                UserPointDataService.get(this.id).then(data => {
                    console.log(data)
                    console.log('id', this.id)
                    let res = data.data
                    this.point = res
                })
            },
            consultInit() {
                ApplyConsultDataService.get(this.id).then(data => {
                    let res = data.data
                    this.consult = res
                })
            },
            inoutInit() {
                if(this.$route.query.category==='in') { //입사 신청 상세 페이지
                    ApplyJoinDataService.get(this.id).then(data => {
                        let res = data.data
                        this.inout = res
                    })
                } else { //퇴사 신청 상세 페이지
                    ApplyResignDataService.get(this.id).then(data => {
                        let res = data.data
                        this.inout = res
                    })
                }
            }, toKoreanUser(entry) {
                if(entry === "id") return "아이디"
                else if (entry === "english_name") return "영문이름"
                else if (entry === "chinese_name") return "한자이름"
                else if (entry === "grade") return "학년"
                else if (entry === "gender") return "성별"
                else if (entry === "nationality") return "국적"
                else if (entry === "department") return "대학"
                else if (entry === "major") return "학과"
                else if (entry === "student_status") return "학적상태"
                else if (entry === "address") return "주소"
                else if (entry === "phone") return "연락처"
                else if (entry === "guardian_name") return "보호자 성명"
                else if (entry === "guardian_relation") return "보호자 관계"
                else if (entry === "guardian_phone") return "보호자 연락처"
                else if (entry === "landline") return "자택 전화"
                else if (entry === "plusPoint") return "상점"
                else if (entry === "minusPoint") return "벌점"
                else if (entry === "res_fac") return "거주관"
                else if (entry === "res_room") return "거주호실"
                else return entry
            }, toKoreanPoint(entry) {
                if(entry === "id") return "아이디"
                else if(entry === "studentNo") return "학번"
                else if(entry === "name") return "이름"
                else if(entry === "department") return "소속학과"
                else if(entry === "reason") return "상/벌점 사유"
                else if(entry === "date_receive") return "입력날짜"
                else if(entry === "date") return "처리날짜"
                else if(entry === "plusPoint") return "상점"
                else if(entry === "minusPoint") return "벌점"
                else return entry
            }, toKoreanJoinResign(entry) {
                if(entry === "id") return "아이디"
                else if(entry === "studentNo") return "학번"
                else if(entry === "name") return "이름"
                else if(entry === "department") return "소속학과"
                else if (entry === "english_name") return "영문이름"
                else if (entry === "chinese_name") return "한자이름"
                else if (entry === "grade") return "학년"
                else if (entry === "gender") return "성별"
                else if (entry === "nationality") return "국적"
                else if (entry === "major") return "학과"
                else if (entry === "student_status") return "학적상태"
                else if (entry === "address") return "주소"
                else if (entry === "phone") return "연락처"
                else if (entry === "guardian_name") return "보호자 성명"
                else if (entry === "guardian_relation") return "보호자 관계"
                else if (entry === "guardian_phone") return "보호자 연락처"
                else if (entry === "landline") return "자택 전화"
                else if (entry === "date") return "입력날짜"
                else if (entry === "hope_fac_1") return "희망시설1"
                else if (entry === "hope_fac_2") return "희망시설2"
                else if (entry === "hope_fac_3") return "희망시설3"
                else if (entry === "hope_fac_4") return "희망시설4"
                else if (entry === "hope_fac_5") return "희망시설5"
                else if (entry === "hope_fac_6") return "희망시설6"
                else if (entry === "period") return "사용기간"
                else if (entry === "date_join") return "입사 날짜"
                else if (entry === "res_date") return "퇴사 날짜"
                else if (entry === "res_reason") return "퇴사 사유"
                else if (entry === "single_yn") return "1인실 사용신청"
                else if (entry === "pri_ent") return "우선입사 대상자"
                else if (entry === "fgn_mate") return "외국인 룸메이트"
                else if (entry === "accpm_ent") return "동반 입실자"
                else if (entry === "par_mes_yn") return "보호자 문자 수신사항"
                else if (entry === "approved") return "승인"
                else if (entry === "res_fac") return "거주관"
                else if (entry === "res_room") return "거주호실"

                else return entry
            }
        },  
        created() {
            console.log(this.$route.name)
            if(this.$route.name === 'admindetail') {
                this.studentno = this.$route.query.studentno
                this.role = this.$route.query.role
                this.userInit()
            } else if(this.$route.name === 'pointdetail') {
                this.id = this.$route.query.id
                this.pointInit()
            } else if(this.$route.name === 'consultdetail') {
                this.id = this.$route.query.id
                this.consultInit()
            } else {
                this.id = this.$route.query.id
                this.inoutInit()
            }
            
        }
    }
    </script>
    
    <style lang="less" scoped>
    .con_box {
        width: 100%;
    
        .con_box_cont {
            margin: 10px 0 10px 10px;
            box-shadow: 0px 0px 8px 3px #cecece;
    
            .con_title {
                background-color: #447EC3;
                font-size: 14px;
                color: #fff;
                padding: 10px;
            }
    
            .container {
                width: 100%;
                padding: 50px 70px;

                &.status {
                    padding: 100px 70px;
                    margin-bottom: 700px;
                }
                &.out {
                    padding: 100px 70px;
                    margin-bottom: 250px;
                }
                &.consult {
                    padding: 100px 70px;
                    margin-bottom: 50px;
                }
    
                table {
                    width: 60%
                    ;
                    th,
                    td {
                        padding: 8px 20px;
                    }
    
                    th {
                        border-right: 3px solid #C0C0C0;
                        text-align: right;
                        font-size: 14px;
                        color: #222;
                    }
    
                    td {
                        width: 100%;
                        display: flex;
    
                        form {
                            width: 100%;
                            display: flex;
    
                            select {
                                border: 1px solid #cecece;
                                padding: 0 10px;
                                margin-right: 10px;
                            }
                        }
    
                        input {
                            width: 100%;
                            border: 0;
                            padding: 7px;
                            font-size: 11px;
                            background-color: #DBECFF;
                            color: #222;
                        }
    
                        #colorbox {
                            width: 100%;
                            display: flex;
    
                            input[type=color] {
                                width: 10%;
                                margin-left: 2%;
                                padding: 0 5px;
                            }
                        }
    
                        .smallbox{
                            width: 28%;
                        }
    
                        input[type=date] {
                            width: 40%;
                        }
    
                        input[type=submit] {
                            width: 20%;
                            border: 0;
                            padding: 10px 0;
                            color: #fff;
                            text-align: center;
                            font-weight: bold;
                            font-size: 70%;
                            background-color: #336EB4;
    
                            &:hover {
                                cursor: pointer;
                            }
                        }
                    }
                }
            }
        }
    
    }
    </style>