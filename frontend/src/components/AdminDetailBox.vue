<template>
    <div>
        <!--사용자 관리 자세히-->
        <div v-if="$route.path !== '/admin/consulting/detail'" class="con_box" > 
            <div class="con_box_cont">
                <div class="con_title">{{con_title}}사용자 정보</div>
                <div class="container" :class="{'status': !status}">
                    <table>
                        <tr v-for="(info, index) in user" :key="index">
                            <th>{{ index }}</th>
                            <td>{{ info }}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!--상담 신청 현황 자세히-->
        <div v-else class="con_box">
            <div class="con_box_cont">
                <div class="con_title">{{con_title}}상담 신청 정보</div>
                <div class="container">
                    <table>
                        <tr v-for="(info, index) in consult" :key="index">
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
    import ApplyConsultDataService from "@/services/ApplyConsultDataService"

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
                consult: {}
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
            consultInit() {
                ApplyConsultDataService.get(this.id).then(data => {
                    let res = data.data[0]
                    this.consult = res
                })
            }
        },  
        created() {
            this.studentno = this.$route.query.studentno
            this.role = this.$route.query.role
            if(this.$route.name === 'admindetail') {
                this.userInit()
            } else {
                this.consultInit()
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