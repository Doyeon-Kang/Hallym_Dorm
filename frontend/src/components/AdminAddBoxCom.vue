<template>
<div>
    <div class="con_box" v-show="$route.name === 'adminuseradd'">
        <div class="con_box_cont">
            <div class="con_title">{{con_title}}사용자 추가</div>
            <div class="container">
                <table>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" v-model="user.name" placeholder="Ex) 홍길동"></td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td><input type="text" v-model="user.studentno" placeholder="Ex) 20221234"></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="text" v-model="user.password" placeholder="비밀번호는 8자리 이상"></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" v-model="user.email" placeholder="a@naver.com"></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="submit" value="생성하기" @click="addUser(user)"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="con_box" v-show="$route.name === 'adminpointadd'">
        <div class="con_box_cont">
            <div class="con_title">{{con_title}}상벌점 입력</div>
            <div class="container">
                <table>
                    <tr>
                        <th>학생 학번</th>
                        <td><input type="text" v-model="point.studentNo" placeholder="20221234"></td>
                    </tr>
                    <tr>
                        <th>상/벌점 사유</th>
                        <td><input type="text" v-model="point.reason" placeholder="Ex) 프로그램 참여, 무단 외박"></td>
                    </tr>
                    <tr>
                        <th>입력 날짜</th>
                        <td><input type="date" v-model="point.date_receive"></td>
                    </tr>
                    <tr>
                        <th>상/벌점 점수</th>
                        <td>
                            <form>
                                <select name="pointadd" v-model="point_category">
                                    <option value="상점" selected>상점</option>
                                    <option value="벌점">벌점</option>
                                </select>
                                <input type="text" class="smallbox" placeholder="점수 입력 Ex) 2" v-model="point_score">
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="submit" value="입력하기" @click="addPoint(point)"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="con_box" v-show="$route.name === 'adminlife'">
        <div class="con_box_cont">
            <div class="con_title">{{con_title}}생활 일정 관리</div>
            <div class="container">
                <table>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" placeholder="Ex) 기숙사 간식 사업 기간"></td>
                    </tr>
                    <tr>
                        <th>색상</th>
                        <td>
                            <div id="colorbox">
                                <input type="text" class="smallbox" placeholder="Ex) #FFFFFF" v-model="inputcolor">
                                <input type="color" :value="inputcolor">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>시작 날짜</th>
                        <td><input type="date"></td>
                    </tr>
                    <tr>
                        <th>종료 날짜</th>
                        <td><input type="date"></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="submit" value="입력하기"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import UserPointDataService from '@/services/UserPointDataService'

export default {
    data() {
        return {
            user: {
                studentno: "",
                name: "",
                password: "",
                email: ""
            },
            point: {
                studentNo: "", // 학번
                reason : "", // 사유
                date_receive : "", // 입력 날짜
                plusPoint: 0, // 상점
                minusPoint: 0 // 벌점
            },
            point_category: "",
            point_score: 0
        }
    },
    methods: {
        addUser(user) {
            console.log(user)
            this.message = ''
            this.successful = false
            this.loading = true

            this.$store.dispatch("auth/register", user).then(
                (data) => {
                    this.message = data.message
                    this.successful = true
                    this.loading = false
                    alert("정상적으로 추가되었습니다.")
                    this.$router.go("")
                },
                (error) => {
                    this.message =
                        (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                        error.message ||
                        error.toString()
                    this.successful = false
                    this.loading = false
                }
            )
        },
        addPoint(point) {
            if(this.point_category == "") {
                alert("상벌점 여부를 선택해주세요.")
            } else {
                if(this.point_category == "상점") {
                    this.point.plusPoint = parseInt(this.point_score)
                    this.point.minusPoint = 0
                } else {
                    this.point.minusPoint = parseInt(this.point_score)
                    this.point.plusPoint = 0
                }
                UserPointDataService.create(point).then(res => {
                    console.log(res)
                })
                alert('입력 완료되었습니다.')
                window.location.reload(true)
            }
            

        }
    },
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