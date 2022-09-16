<template>
<div class="wrapper_list">
    <table>
        <thead>
            <th>번호</th>
            <th>글내용</th>
            <th>작성일</th>
            <th>작성자</th>
            <th>조회수</th>
            <th></th>
        </thead>
        <tbody>
            <tr v-for="(item, index) in listItem" :key="index">
                <td>{{item.no}}</td>
                <td class="title">{{item.title}}</td>
                <td>{{item.date}}</td>
                <td>{{item.writer}}</td>
                <td>{{item.view}}</td>
                <td></td>
            </tr>
        </tbody>
    </table>
</div>
</template>

<script>
export default {
    props: {
        listItem: {
            type: Array,
            default: () =>  {
                return [
                    {
                    no: "no",
                    title: "title",
                    date: "date",
                    writer: "writer",
                    view: "view"
                    }
                ];
            }
        }
    }
}
</script>

<style lang="less" scoped>
.wrapper_list {
    table {
        width: 100%;
        thead {
            background-color: #336EB4;
            th {
                color: #fff;
                padding: 12px;
            }
        }
        tbody {
            tr {
                border-bottom: 1px solid #858585;
                td {
                    text-align: center;
                    padding: 20px;
                    &.title:hover{
                        color: #336EB4;
                        cursor: pointer;
                    }
                }
            }
        }
    }
}
</style>