<template>
    <div class="wrapper_list">
        <div class="searchbar">
            <div class="search_input">
                <img src="@/assets/search.png" alt="">
                <input v-if="this.$route.name !== 'adminlife'" type="text" v-model="keyword" placeholder="전체 사용자 검색"
                    @keyup.enter="searchshow(keyword)" />
                <input v-if="this.$route.name === 'adminlife'" type="text" v-model="keyword" placeholder="일정 제목 검색"
                    @keyup.enter="searchshow(keyword)" />
            </div>
            <div class="search_button">
                <button @click="searchshow(keyword)">
                    검색
                </button>
            </div>
        </div>

        <div class="container">
            <div class="top">
                <span v-show="$route.name !== 'adminlife'">전체 사용자 {{totaluser}}명</span>
                <span v-show="$route.name === 'adminlife'">선택한 일정 {{totallife}}개</span>
                <span>정렬
                    <form>
                        <select name="item" @change="sortABC">
                            <option v-for="(title, index) in listTitle" :key="index" :value="title">
                                {{title}}</option>
                        </select>
                    </form>
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
                        <tr v-for="item in listItem" :key="item.no" @click="this.$router.push(item.url)">
                            <td><input type="checkbox" class="check" :value="item.no" v-model="selectList" /></td>

                            <td v-for="(text, index) in objectKey(item)" :key="index">
                                {{ text }}
                            </td>
                            <td><input type="button" value="자세히" v-show="$route.name === 'adminuser' ||
                            $route.name === 'adminuseradd' ||
                            $route.name === 'adminpoint' ||
                            $route.name === 'adminpointadd'" /></td>
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
        totaluser: {
            type: Number,
            default: 0
        },
        totallife: {
            type: Number,
            default: 0
        },
        title_in: {
            type: String,
        },
        title_out: {
            type: String,
        },
    },
    mounted() {
        if (this.listItem) this.checkList = this.listItem.map(item => item.no);
    },
    methods: {
        objectKey(ob) {
            let array = [];
            for (let key in ob) {
                if (key !== "url") array.push(ob[key]);
            }
            return array;
        },
        priceLow() {
            this.oneroom.sort(function (a, b) {
                return a.no - b.no
            });
        },
        priceHigh() {
            this.oneroom.sort(function (a, b) {
                return b.no - a.no
            });
        },
        sortABC() {
            this.oneroom.sort(function (a, b) {
                return a.title.localeCompare(b.title)
            });
        },
        sortBack() {
            this.oneroom = [...this.oneroomOriginal];
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
        }
    }
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
            width: 90%;
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

        .search_button {
            width: 8%;

            button {
                border-radius: 0;
                width: 100%;
                border: 0px;
                height: 100%;
                font-size: 16px;
                font-weight: bold;
                background-color: #3675C7;
                color: #FFFFFF;

                &:hover {
                    cursor: pointer;
                }
            }
        }
    }

    .top {
        display: flex;
        color: #fff;
        font-size: 80%;
        background-color: #336EB4;

        span {
            display: flex;
            padding: 13px 0px 13px 40px;

            select {
                margin-top: -5px;
                height: 25px;
                width: 120px;
                color: #858585;
                font-size: 70%;
                margin-left: 5px;
                padding: 2px;
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