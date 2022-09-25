<template>
    <div class="wrapper_list">
        <div class="con_box" v-show="$route.name === 'adminlife'" >
            <div class="con_title">{{con_title}}</div>
            <table>
                <tr v-for="(title, index) in listTitle" :key="index">
                    <td>{{ title }}</td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="top">
            <span>전체 사용자 {{total}}명</span>
            <span>정렬
                <select name="item">
                    <option value="학번" selected>학번순</option>
                    <option value="이름">이름순</option>
                    <option value="소속학과">소속학과순</option>
                    <option value="상벌점">상벌점순</option>
                    <option value="상벌점 추가내역">상벌점 추가내역순</option>
                    <option value="거주 기숙사">거주 기숙사순</option>
                </select>
            </span>
        </div>
        <table>
            <thead>
                <th><input type="checkbox" v-model="checkedAll" /></th>
                <th v-for="(title, index) in listTitle" :key="index">
                    {{ title }}
                </th>
                <th></th>
            </thead>
            <tbody>
                <tr v-for="item in listItem" :key="item.no" @click="this.$router.push(item.url)">
                    <td><input type="checkbox" class="check" /></td>
                    <td v-for="(text, index) in objectKey(item)" :key="index">
                        {{ text }}
                    </td>
                    <td><input type="button" value="자세히"
                        v-show="$route.name === 'adminuser' || 
                            $route.name === 'adminpoint' || 
                            $route.name === 'admininout'" /></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
    
<script>
export default {
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
        total: {
            type: Number,
            default: 0
        },
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
};
</script>
    
<style lang="less" scoped>
.wrapper_list {
    margin-left: 10px;

    .con_box{
        width: 100%;
        margin: 10px 0;
        box-shadow: 0px 0px 8px 3px #cecece;

        .con_title{
            background-color: #447EC3;
            color: #fff;
        }
    }

    .top {
        display: flex;
        width: 100%;
        color: #fff;
        font-size: 12px;
        background-color: #336EB4;

        span {
            padding: 13px 0px 13px 40px;
        }

        select {
            width: 120px;
            color: #858585;
            font-size: 10px;
            padding: 2px;
        }
    }

    table {
        width: 100%;
        font-size: 15px;

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
                    background-color: #ededed;
                    text-align: center;
                    padding: 15px;
                    color: #222;
                    font-size: 0.8em;

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