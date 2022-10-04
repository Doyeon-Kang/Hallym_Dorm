<template>
    <div class="wrapper_list">
        <div class="top">
            <span v-show="$route.name !== 'adminlife'">전체 사용자 {{totaluser}}명</span>
            <span v-show="$route.name === 'adminlife'">선택한 일정 {{totallife}}개</span>
            <span>정렬
                <form>
                    <select name="item">
                        <option v-for="(title, index) in listTitle"
                        :key="index" :value="title">{{title}}</option>
                    </select>
                </form>
            </span>
        </div>
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
                    <td><input type="checkbox" class="check" :value="item.no" v-model="selectList"/></td>
                    
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
</template>
    
<script>
export default {
    data() {
        return{
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
    },
    computed: {
        checkAll: {
            get: function(){
                return this.checkList.length === this.selectList.length;
            },
            set: function(e){
                this.selectList = e ? this.checkList : [];
            }
        }
    }
};
</script>
    
<style lang="less" scoped>
.wrapper_list {
    width: 100%;
    margin-left: 10px;
    background-color: #ededed;
    height: 400px;

    .top {
        display: flex;
        width: 100%;
        color: #fff;
        font-size: 80%;
        background-color: #336EB4;

        span {
            display: flex;
            padding: 13px 0px 13px 40px;

            select {
                width: 120px;
                color: #858585;
                font-size: 70%;
                margin-left: 5px;
                padding: 2px;
            }
        }

    }

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
</style>