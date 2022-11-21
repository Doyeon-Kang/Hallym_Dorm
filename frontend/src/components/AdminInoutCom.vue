<template>
    <div class="wrapper_list">
        <div class="container">
            <div class="top">
                <span class="inouttitle">{{title_in}}</span>
                <span>정렬
                    <button @click="sortNamein()">이름순</button>
                    <button @click="sortGradein()">학년순</button>
                    <button @click="sortNoin()">학번순</button>
                    <button @click="sortDepin()">소속학과순</button>
                    <button @click="sortPointin()">상벌점순</button>
                    <button @click="sortHopein()">희망 순위순</button>
                </span>
                <div class="searchbar">
                    <div class="search_input">
                        <input type="text" v-model="keywordin" placeholder="사용자 검색" />
                    </div>
                </div>
            </div>
            <div class="tablecon">
                <table>
                    <thead>
                        <th><input type="checkbox" v-model="checkAllin" value="all" /></th>
                        <th v-for="(title, index) in listTitlein" :key="index">
                            {{ title }}
                        </th>
                        <th></th>
                    </thead>
                    <tbody>
                        <tr v-for="item in listSearchin" :key="item.no" @click="this.$router.push(item.url)">
                            <td><input type="checkbox" class="check" :value="item.id" v-model="selectListin" /></td>

                            <td v-for="(text, index) in objectKey(item)" :key="index">
                                {{ text }}
                            </td>
                            <td><input type="button" value="자세히" @click="$router.push({
                                name: 'inoutdetail',
                                query: { id: item.id, category: 'in'}
                            })"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <button class="approvalbtn" @click="approveJoin(selectListin)">승인하기</button>
        </div>

        <div class="container">
            <div class="top">
                <span class="inouttitle">{{title_out}}</span>
                <span>정렬
                    <button @click="sortNameout()">이름순</button>
                    <button @click="sortNoout()">학번순</button>
                    <button @click="sortDepout()">소속학과순</button>
                    <!-- <button @click="sortLiveout()">거주 기숙사순</button> -->
                </span>
                <div class="searchbar">
                    <div class="search_input">
                        <input type="text" v-model="keywordout" placeholder="사용자 검색" />
                    </div>
                </div>
            </div>
            <div class="tablecon">
                <table>
                    <thead>
                        <th><input type="checkbox" v-model="checkAllout" value="all" /></th>
                        <th v-for="(title, index) in listTitleout" :key="index">
                            {{ title }}
                        </th>
                        <th></th>
                    </thead>
                    <tbody>
                        <tr v-for="item in listSearchout" :key="item.no" @click="this.$router.push(item.url)">
                            <td><input type="checkbox" class="check" :value="item.id" v-model="selectListout" /></td>

                            <td v-for="(text, index) in objectKey(item)" :key="index">
                                {{ text }}
                            </td>
                            <td><input type="button" value="자세히" @click="$router.push({
                                name: 'inoutdetail',
                                query: { id: item.id, category: 'out'}
                            })"/></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <button class="approvalbtn" @click="approveResign(selectListout)">승인하기</button>
        </div>
    </div>
</template>
    
<script>
import ApplyJoinDataService from "@/services/ApplyJoinDataService";
import ApplyResignDataService from "@/services/ApplyResignDataService";

export default {
    data() {
        return {
            checkListin: [],
            selectListin: [],
            checkListout: [],
            selectListout: [],
            listArrayin: this.listItemin,
            listArrayout: this.listItemout,
            listItemFilterIn: this.listItemin,
            listItemFilterOut: this.listItemout,
            keywordin: '',
            keywordout: '',
            sortedNamein: 1,
            sortedGradein: 1,
            sortedNoin: 1,
            sortedDepin: 1,
            sortedPointin: 1,
            sortedHopein: 1,
            sortedNameout: 1,
            sortedNoout: 1,
            sortedDepout: 1,
            sortedLiveout: 1,
        };
    },
    props: {
        con_title: {
            type: String,
        },
        listTitlein: {
            type: Array,
            required: true,
        },
        listItemin: {
            type: Array,
            required: true,
        },
        listTitleout: {
            type: Array,
            required: true,
        },
        listItemout: {
            type: Array,
            required: true,
        },
        title_in: {
            type: String,
        },
        title_out: {
            type: String,
        },
    },
    mounted() {
        if (this.listItemin) this.checkListin = this.listItemin.map(item => item.no);
        if (this.listItemout) this.checkListout = this.listItemout.map(item => item.no);
    },
    methods: {
        async approveJoin(list) {
            let cnt = 0;

            if (list.length == 0) { // 리스트 행 없을 경우
                alert("승인할 리스트 행을 선택해주세요.")
            } else {
                for(let i=0; i<list.length; i++) {
                    await ApplyJoinDataService.updateApprove(list[i]).then(res => {
                        ++cnt
                        console.log(res)
                    })
                }
                if(cnt == 0) {
                    alert('이미 모두 승인 처리되어 있습니다.')
                } else {
                    alert('입사 신청 ' + cnt+'건이 승인 처리되었습니다.')
                    window.location.reload(true)
                }
            }
        },
        async approveResign(list) {
            let cnt = 0;

            if (list.length == 0) { // 리스트 행 없을 경우
                alert("승인할 리스트 행을 선택해주세요.")
            } else {
                for(let i=0; i<list.length; i++) {
                    await ApplyResignDataService.updateApprove(list[i]).then(res => {
                        cnt = 1
                        console.log(res)
                    })
                }
                if(cnt === 0) {
                    alert('이미 모두 승인 처리되어 있습니다.')
                } else {
                    alert('퇴사 신청 ' + cnt+'건이 승인 처리되었습니다.')
                    window.location.reload(true)
                }
            }
        },
        objectKey(ob) {
            let array = [];
            for (let key in ob) {
                if (key !== "url") array.push(ob[key]);
            }
            return array;
        },
        sortNamein() {
            this.sortedNamein = 0
        },
        sortGradein() {
            this.sortedGradein = 0
        },
        sortNoin() {
            this.sortedNoin = 0
        },
        sortDepin() {
            this.sortedDepin = 0
        },
        sortPointin() {
            this.sortedPointin = 0
        },
        sortHopein() {
            this.sortedHopein = 0
        },
        sortNameout() {
            this.sortedNameout = 0
        },
        sortNoout() {
            this.sortedNoout = 0
        },
        sortDepout() {
            this.sortedDepout = 0
        },
        sortLiveout() {
            this.sortedLiveout = 0
        },
    },
    computed: {
        checkAllin: {
            get: function () {
                return this.checkListin.length === this.selectListin.length;
            },
            set: function (e) {
                this.selectListin = e ? this.checkListin : [];
            }
        },

        checkAllout: {
            get: function () {
                return this.checkListout.length === this.selectListout.length;
            },
            set: function (e) {
                this.selectListout = e ? this.checkListout : [];
            }
        },

        listSearchin () {
            return this.listItemFilterIn.filter(itemin => {
                return itemin.name.toLowerCase().indexOf(this.keywordin.toLowerCase()) > -1
            })
        },

        listSearchout () {
            return this.listItemFilterOut.filter(itemout => {
                return itemout.name.toLowerCase().indexOf(this.keywordout.toLowerCase()) > -1
            })
        },
    },
    watch: {
        selectListin() {
            this.$emit("setListIn", this.selectListin); 
            if(this.selectListin.length !== 0 && this.selectListout.length !== 0) {
                alert("입사/퇴사 중 한 종목의 체크박스만 선택해주세요.")
                this.selectListout = []
            }
        },  
        selectListout() {
            this.$emit("setListOut", this.selectListout); 
            if(this.selectListin.length !== 0 && this.selectListout.length !== 0) {
                alert("입사/퇴사 중 한 종목의 체크박스만 선택해주세요.")
                this.selectListin = []
            }
        }, 
        sortedNamein() {
            this.listArrayin.sort(function (a, b) {
                return a.name.localeCompare(b.name)
            });
            this.sortedGradein = 1
            this.sortedNoin = 1
            this.sortedDepin = 1
            this.sortedPointin = 1
            this.sortedHopein = 1
        },
        sortedGradein() {
            this.listArrayin.sort(function (a, b) {
                return b.grade - a.grade
            });
            this.sortedNamein = 1
            this.sortedNoin = 1
            this.sortedDepin = 1
            this.sortedTermin = 1
            this.sortedHopein = 1
        },
        sortedNoin() {
            this.listArrayin.sort(function (a, b) {
                return a.no - b.no
            });
            this.sortedNamein = 1
            this.sortedGradein = 1
            this.sortedDepin = 1
            this.sortedPointin = 1
            this.sortedHopein = 1
        },
        sortedDepin() {
            this.listArrayin.sort(function (a, b) {
                return a.dep.localeCompare(b.dep)
            });
            this.sortedNamein = 1
            this.sortedGradein = 1
            this.sortedNoin = 1
            this.sortedPointin = 1
            this.sortedHopein = 1
        },
        sortedPointin() {
            this.listArrayin.sort(function (a, b) {
                return b.point - a.point
            });
            this.sortedNamein = 1
            this.sortedGradein = 1
            this.sortedNoin = 1
            this.sortedDepin = 1
            this.sortedHopein = 1
        },
        sortedHopein() {
            this.listArrayin.sort(function (a, b) {
                return a.hope.localeCompare(b.hope)
            });
            this.sortedNamein = 1
            this.sortedGradein = 1
            this.sortedNoin = 1
            this.sortedDepin = 1
            this.sortedPointin = 1
        },
        sortedNameout() {
            this.listArrayout.sort(function (a, b) {
                return a.name.localeCompare(b.name)
            });
            this.sortedNoout = 1
            this.sortedDepout = 1
            this.sortedLiveout = 1
        },
        sortedNoout() {
            this.listArrayout.sort(function (a, b) {
                return a.no - b.no
            });
            this.sortedNameout = 1
            this.sortedDepout = 1
            this.sortedLiveout = 1
        },
        sortedDepout() {
            this.listArrayout.sort(function (a, b) {
                return a.dep.localeCompare(b.dep)
            });
            this.sortedNameout = 1
            this.sortedNoout = 1
            this.sortedLiveout = 1
        },
        sortedLiveout() {
            this.listArrayout.sort(function (a, b) {
                return a.live.localeCompare(b.live)
            });
            this.sortedNameout = 1
            this.sortedNoout = 1
            this.sortedDepout = 1
        },
    },
};
</script>
    
<style lang="less" scoped>
.wrapper_list {
    width: 100%;
    margin: 10px 0 0 10px;
    background-color: #ededed;

    .container {
        position: relative;
        height: 600px;

        button,
        input[type=button]:hover {
            cursor: pointer;
        }

        .top {
            position: relative;
            display: flex;
            color: #fff;
            font-size: 80%;
            background-color: #336EB4;

            .inouttitle {
                padding: 10px 0 0 30px;
                font-size: 18px;
                font-weight: bold;
            }

            span {
                line-height: 20px;
                display: flex;
                padding: 13px 0px 13px 40px;

                button {
                    color: #fff;
                    background-color: #447EC3;
                    margin: 0 5px;
                    border: 0;

                    &:hover {
                        cursor: pointer;
                    }
                }
            }

            .searchbar {
                position: absolute;
                right: 3%;
                height: 25px;
                width: 10%;
                margin: 10px 0 0 10px;
                display: flex;

                .search_input {
                    margin: 0;
                    display: flex;
                    width: 100%;
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
            }
        }

        .tablecon {
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
                        }

                        td:last-child {
                            padding: 5px 15px;
                        }
                    }
                }
            }
        }

        .approvalbtn {
            position: absolute;
            bottom: 10px;
            right: 30px;
            width: 8%;
            height: 50px;
            font-size: 15px;
            font-weight: bold;
            color: #fff;
            background-color: #00B6AD;
            border: 0;
        }
    }
}
</style>