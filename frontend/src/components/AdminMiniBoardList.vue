<template>
    <div class="wrapper_list">
        <div class="top">
            <span v-show="$route.name !== 'adminlife'">전체 사용자 {{this.listItem.length}}명</span>
            <span v-show="$route.name === 'adminlife'">선택한 일정 {{this.selectList.length}}개</span>
            <!-- <span>정렬
                <button @click="sortName()" v-show="
                $route.name === 'adminpointadd'">이름순</button>
                <button @click="sortNo()" v-show="
                $route.name === 'adminpointadd'">학번순</button>
                <button @click="sortDep()" v-show="
                $route.name === 'adminpointadd'">소속학과순</button>
                <button @click="sortPoint()" v-show="
                $route.name === 'adminpointadd'">상벌점순</button>
                <button @click="sortLive()" v-show="
                $route.name === 'adminpointadd'">거주 기숙사순</button>
                <button @click="sortTitle()" v-show="
                $route.name === 'adminlife'">제목순</button>
                <button @click="sortColor()" v-show="
                $route.name === 'adminlife'">색상순</button>
                <button @click="sortStart()" v-show="
                $route.name === 'adminlife'">시작일자순</button>
                <button @click="sortEnd()" v-show="
                $route.name === 'adminlife'">종료일자순</button>
            </span> -->
        </div>
        <div style="height: 92%; overflow: auto">
            <table>
                <thead>
                    <th><input type="checkbox" v-model="checkAll" value="all" /></th>
                    <th v-for="(title, index) in listTitle" :key="index">
                        {{ title }}
                    </th>
                    <th></th>
                </thead>
                <tbody>
                    <tr v-for="item in listItem" :key="item.no" @click="this.$router.push(item.url)">
                        <td><input type="checkbox" class="check" :value="item.no" v-model="selectList" /></td>

                        <td v-for="(text, index) in objectKey(item)" :key="index">
                            {{ text }}
                        </td>
                        <td><input type="button" value="자세히" v-show="$route.name === 'adminuser' ||
                        $route.name === 'adminuseradd' ||
                        $route.name === 'adminpoint' ||
                        $route.name === 'adminpointadd' ||
                        $route.name === 'admininout'" /></td>
                    </tr>
                </tbody>
            </table>
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
            sortedName: 1,
            sortedNo: 1,
            sortedDep: 1,
            sortedPoint: 1,
            sortedLive: 1,
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
        if (this.listItem) 
            this.checkList = this.listItem.map(item => item.no);
    },
    methods: {
        objectKey(ob) {
            let array = [];
            for (let key in ob) {
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
                this.selectList = e ? this.listItem : [];
            }
        }
    },
    watch: {
        sortedName() {
            this.listArray.sort(function (a, b) {
                return a.name.localeCompare(b.name)
            });
            this.sortedNo = 1
            this.sortedDep = 1
            this.sortedPoint = 1
            this.sortedLive = 1
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
    margin-left: 10px;
    background-color: #ededed;
    height: 500px;

    .top {
        display: flex;
        width: 100%;
        height: 8%;
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

    }

    table {
        width: 100%;
        font-size: 80%;
        height: 92%;
        thead {
            background-color: #447EC3;
            position: sticky;
            th {
                color: #fff;
                padding: 10px;
            }
        }

        tbody {
            //display: inline-block;
            width: 100%;
            overflow-y: scroll;
            
            tr {
                //display: block;
                border-bottom: 1px solid #858585;
                //height: 20px; 
                //width: 100%;
                td {
                    text-align: center;
                    padding: 20px 15px;
                    color: #222;
                    font-size: 100%;
                    //display: inline-block;
                    height: 20px;

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
</style>