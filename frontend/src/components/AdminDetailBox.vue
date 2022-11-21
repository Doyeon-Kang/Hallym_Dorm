<template>
    <div>
        <!--사용자 관리 자세히-->
        <div v-if="$route.path === '/admin/user/detail'" class="con_box" > 
            <div class="con_box_cont">
                <div class="con_title">사용자 정보</div>
                <div class="container" :class="{'status': !status}">
                    <table>
                        <tr v-for="(info, index) in user" :key="index">
                            <th>{{ toKorean(index) }} </th>
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
                            <th>{{ index }}</th>
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
                            <th>{{ index }}</th>
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
            }, toKorean(entry) {
//                 `id` INT NOT NULL AUTO_INCREMENT,
                //   `english_name` VARCHAR(50) COMMENT '영문이름',
                //   `chinese_name` VARCHAR(10) COMMENT '한자이름',
                //   `grade` INT NULL COMMENT '학년',
                //   `gender` VARCHAR(1) NULL COMMENT '성별',
                //   `nationality` VARCHAR(50) NULL COMMENT '국적',
                //   `department` VARCHAR(50) NULL COMMENT '대학',
                //   `major` VARCHAR(50) NULL COMMENT '학과',
                //   `student_status` VARCHAR(20) NULL COMMENT '학적상태',
                //   `address` VARCHAR(200) NULL COMMENT '주소',
                //   `phone` VARCHAR(20) NULL COMMENT '연락처',
                //   `guardian_name` VARCHAR(50) COMMENT '보호자 성명',
                //   `guardian_relation` VARCHAR(15) COMMENT '보호자 관계',
                //   `guardian_phone` VARCHAR(20) COMMENT '보호자 연락처',
                //   `landline` VARCHAR(20) COMMENT '자택 전화',
                //   `plusPoint` INT NULL COMMENT '상점',
                //   `minusPoint` INT NULL COMMENT '벌점',
                //   `res_fac` INT NULL COMMENT '거주관',
                //   `res_room` INT NULL COMMENT '거주호실',
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