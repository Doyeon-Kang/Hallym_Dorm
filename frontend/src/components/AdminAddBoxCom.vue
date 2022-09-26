<template>
    <div class="con_box" v-show="$route.name === 'adminpointadd'">
        <div class="con_box_cont">
            <div class="con_title">{{con_title}}상벌점 입력</div>
            <div class="container">
                <table>
                    <!--<tr v-for="(title, index) in listTitle" :key="index">
                        <th>{{ title }}</th>
                        <td></td>
                    </tr>-->

                    <tr>
                        <th>상/벌점 사유</th>
                        <td><input type="text" placeholder="Ex) 프로그램 참여, 무단 외박"></td>
                    </tr>
                    <tr>
                        <th>입력 날짜</th>
                        <td><input type="date"></td>
                    </tr>
                    <tr>
                        <th>상/벌점 점수</th>
                        <td>
                            <form>
                                <select name="pointadd">
                                    <option value="상점" selected>상점</option>
                                    <option value="벌점">벌점</option>
                                </select>
                                <input type="text" placeholder="점수 입력 Ex) 2">
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input type="submit" value="입력하기"></td>
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
                    <!--<tr v-for="(title, index) in listTitle" :key="index">
                        <th>{{ title }}</th>
                        <td></td>
                    </tr>-->

                    <tr>
                        <th>제목</th>
                        <td><input type="text" placeholder="Ex) 기숙사 간식 사업 기간"></td>
                    </tr>
                    <tr>
                        <th>색상</th>
                        <td><input type="text" placeholder="Ex) #FFFFFF"></td>
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
</template>

<!--<script>
export default {
    props: {
        con_title: {
            type: String,
        },
        listTitle: {
            type: Array,
            required: true,
        },
    },
};
</script>-->

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
            padding: 50px 70px;

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
                width: 70%;

                form{
                    width: 100%;
                    display: flex;

                    select{
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

                input[type=submit] {
                    width: 40%;
                    border: 0;
                    padding: 10px 15px;
                    color: #fff;
                    text-align: center;
                    font-weight: bold;
                    font-size: 12px;
                    background-color: #336EB4;

                    &:hover{
                        cursor: pointer;
                    }
                }
            }
        }
    }

}
</style>