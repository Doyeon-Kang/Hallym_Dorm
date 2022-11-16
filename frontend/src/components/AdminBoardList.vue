<template>
    <div class="wrapper_list">
        <div class="searchbar">
            <div class="search_input">
                <img src="@/assets/search.png" alt="">
                <input type="text" v-model="keyword" placeholder="전체 사용자 검색"/>
            </div>
        </div>
        
        <div class="container">
            <div class="top">
                <span>전체 사용자 {{this.checkList.length}}명</span>
                <span>정렬
                    <button @click="sortName()">이름순</button>
                    <button @click="sortNo()">학번순</button>
                    <button @click="sortDep()" v-show="
                    $route.name !== 'adminconsulting'">소속학과순</button>
                    <button @click="sortPoint()" v-show="
                    $route.name === 'adminuser' ||
                    $route.name === 'adminuseradd' ||
                    $route.name === 'adminpoint'">상벌점순</button>
                    <button @click="sortLive()" v-show="
                    $route.name === 'adminuser' ||
                    $route.name === 'adminuseradd' ||
                    $route.name === 'adminpoint'">거주 기숙사순</button>
                    <button @click="sortDate()" v-show="
                    $route.name === 'adminstudy'">예약 날짜순</button>
                    <button @click="sortTime()" v-show="
                    $route.name === 'adminstudy'">예약 시간순</button>
                    <button @click="sortSeat()" v-show="
                    $route.name === 'adminstudy'">선택좌석순</button>
                    <button @click="sortDate()" v-show="
                    $route.name === 'adminsleep'">신청 날짜순</button>
                    <button @click="sortTerm()" v-show="
                    $route.name === 'adminsleep'">외박 기간순</button>
                    <button @click="sortFind()" v-show="
                    $route.name === 'adminconsulting'">상담분야순</button>
                    <button @click="sortDate()" v-show="
                    $route.name === 'adminconsulting'">신청일자순</button>
                    <button @click="sortPhone()" v-show="
                    $route.name === 'adminconsulting'">전화번호순</button>
                </span>
            </div>
            <div class="tablecon">
                <table>
                    <thead>
                        <th><input type="checkbox" v-model="checkAll" value="all" /></th>
                        <th v-for="(title, index) in listTitle" :key="index">
                            {{ title }}
                        </th>
                        <th></th>   
                    </thead>
                    <tbody>
                        <tr v-for="item in listSearch" :key="item.no" @click="this.$router.push(item.url)">
                            <td><input type="checkbox" class="check" :value="item" v-model="selectList"/></td>

                            <td v-for="(text, index) in objectKey(item)" :key="index">
                                {{ text }}
                            </td>
                            <td v-show="$route.name === 'adminuser' ||
                            $route.name === 'adminuseradd' ||
                            $route.name === 'adminpoint' ||
                            $route.name === 'adminpointadd'" @click="$router.push({
                                name: 'admindetail',
                                query: { studentno: item.no, role: item.auth},
                            })"><input type="button" value="자세히" /></td>
                            <td v-show="$route.name === 'adminconsulting'"><input type="button" value="자세히" @click="$router.push({
                                name: 'consultdetail',
                                query: { id: item.id}
                            })"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
    
<script>
export default {
    data() {
        return {
            checkList: [],
            selectList: [],  
            listArray: this.listItem,
            listItemFilter: this.listItem,
            keyword: '',
            sortedName: 1,
            sortedNo: 1,
            sortedDep: 1,
            sortedPoint: 1,
            sortedLive: 1,
            sortedDate: 1,
            sortedTime: 1,
            sortedSeat: 1,
            sortedTerm: 1,
            sortedHope: 1,
            sortedFind: 1,
            sortedPhone: 1,
            sortedTitle: 1,
            sortedColor: 1,
            sortedStart: 1,
            sortedEnd: 1,
        };
    },
    props: {
        con_title: {
            type: String,
        },
        listTitle: {
            type: Array,
            required: true,
        },
        listItem: {
            type: Array,
            required: true,
        },
    },
    mounted() {
        if (this.listItem) this.checkList = this.listItem.map(item => item.no);
    },  
    methods: {
        objectKey(ob) {
            let array = [];
            for (const key in ob) {
                if (key !== "url") array.push(ob[key]);
            }
            return array;
        },
        sortName() {
            this.sortedName = 0
        },
        sortNo() {
            this.sortedNo = 0
        },
        sortDep() {
            this.sortedDep = 0
        },
        sortPoint() {
            this.sortedPoint = 0
        },
        sortLive() {
            this.sortedLive = 0
        },
        sortDate() {
            this.sortedDate = 0
        },
        sortTime() {
            this.sortedTime = 0
        },
        sortSeat() {
            this.sortedSeat = 0
        },
        sortTerm() {
            this.sortedTerm = 0
        },
        sortHope() {
            this.sortedHope = 0
        },
        sortFind() {
            this.sortedFind = 0
        },
        sortPhone() {
            this.sortedPhone = 0
        },
        sortTitle() {
            this.sortedTitle = 0
        },
        sortColor() {
            this.sortedColor = 0
        },
        sortStart() {
            this.sortedStart = 0
        },
        sortEnd() {
            this.sortedEnd = 0
        },
        
    },
    computed: {
        checkAll: {
            get: function () {
                return this.checkList.length === this.selectList.length;
            },
            set: function (e) {
                this.selectList = e ? this.checkList : [];
            }
        },
        listSearch () {
            return this.listItemFilter.filter(item => {
                return item.name.toLowerCase().indexOf(this.keyword.toLowerCase()) > -1
            })
        },
    },
    watch: {
        selectList() { 
            this.$emit("setList", this.selectList); 
        },  
        sortedName() {
            this.listArray.sort(function (a, b) {
                return a.name.localeCompare(b.name)
            });
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedNo() {
            this.listArray.sort(function (a, b) {
                return a.no - b.no
            });
            this.sortedName = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedDep() {
            this.listArray.sort(function (a, b) {
                return a.dep.localeCompare(b.dep)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedPoint() {
            this.listArray.sort(function (a, b) {
                return b.point - a.point
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedLive() {
            this.listArray.sort(function (a, b) {
                return a.live.localeCompare(b.live)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedDate() {
            this.listArray.sort(function (a, b) {
                return a.date.localeCompare(b.date)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedTime() {
            this.listArray.sort(function (a, b) {
                return a.time.localeCompare(b.time)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedSeat() {
            this.listArray.sort(function (a, b) {
                return a.seat.localeCompare(b.seat)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedTerm() {
            this.listArray.sort(function (a, b) {
                return a.term.localeCompare(b.term)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedHope() {
            this.listArray.sort(function (a, b) {
                return a.hope.localeCompare(b.hope)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedFind() {
            this.listArray.sort(function (a, b) {
                return a.find.localeCompare(b.find)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedPhone() {
            this.listArray.sort(function (a, b) {
                return a.phone.localeCompare(b.phone)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedTitle() {
            this.listArray.sort(function (a, b) {
                return a.title.localeCompare(b.title)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedColor = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedColor() {
            this.listArray.sort(function (a, b) {
                return a.color.localeCompare(b.color)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedStart = 1
            this.sortedEnd = 1
        },
        sortedStart() {
            this.listArray.sort(function (a, b) {
                return a.start.localeCompare(b.start)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedEnd = 1
        },
        sortedEnd() {
            this.listArray.sort(function (a, b) {
                return a.end.localeCompare(b.end)
            });
            this.sortedName = 1
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
            this.sortedDate = 1
            this.sortedTime = 1
            this.sortedSeat = 1
            this.sortedTerm = 1
            this.sortedHope = 1
            this.sortedFind = 1
            this.sortedPhone = 1
            this.sortedTitle = 1
            this.sortedColor = 1
            this.sortedStart = 1
        },
    },
};
</script>
    
<style lang="less" scoped>
.wrapper_list {
    width: 100%;
    margin: 10px 0 0 10px;

    .searchbar {
        margin: 10px 0 10px 0;
        width: 100%;
        display: flex;

        .search_input {
            display: flex;
            width: 100%;
            margin-right: 10px;
            border: 1px solid #336EB4;

            img {
                width: 15px;
                padding: 12px;
            }

            input {
                width: 100%;
                border: none;
                padding: 10px 10px;
                font-size: 14px;
            }
        }
    }

    .top {
        display: flex;
        color: #fff;
        font-size: 80%;
        background-color: #336EB4;

        span {
            line-height: 20px;
            display: flex;
            padding: 13px 0px 13px 40px;

            button{
                color: #fff;
                background-color: #447EC3;
                margin: 0 5px;
                border: 0;

                &:hover{
                    cursor: pointer;
                }
            }
        }

        .searchbar {
            height: 25px;
            width: 10%;
            display: flex;

            .search_input {
                margin: 0;
                display: flex;
                width: 90%;
                border: 1px solid #447EC3;

                img {
                    width: 15px;
                    padding: 12px;
                }

                input {
                    width: 100%;
                    border: none;
                    padding: 10px 10px;
                    font-size: 11px;
                }
            }

            .search_button {
                width: 50%;

                button {
                    border-radius: 0;
                    width: 100%;
                    border: 0px;
                    height: 100%;
                    font-size: 13px;
                    font-weight: bold;
                    background-color: #447EC3;
                    color: #FFFFFF;
                }
            }
        }
    }

    .tablecon {
        height: 800px;
        background-color: #ededed;

        table {
            width: 100%;
            font-size: 80%;

            thead {
                background-color: #447EC3;

                th {
                    color: #fff;
                    padding: 10px;
                }
            }

            tbody {

                tr {
                    border-bottom: 1px solid #858585;

                    td {
                        text-align: center;
                        padding: 20px 15px;
                        color: #222;
                        font-size: 100%;

                        input {
                            border: 0;
                            background-color: #336EB4;
                            color: #fff;
                            padding: 8px 12px;
                        }

                        input[type=button]:hover {
                            cursor: pointer;
                        }
                    }

                    td:last-child {
                        padding: 5px 15px;
                    }
                }
            }
        }
    }
}
</style>