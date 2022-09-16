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
            <tr v-for="item in paginatedData" :key="item.no">
                <td>{{item.no}}</td>
                <td class="title">{{item.title}}</td>
                <td>{{item.date}}</td>
                <td>{{item.writer}}</td>
                <td>{{item.view}}</td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>
</div>
</template>

<script>
export default {
    data () {
        return {
            pageNum: 0
        }
    },
    props: {
        listItem: {
            type: Array,
            required: true
        },
        pageSize: {
            type: Number,
            required: false,
            default: 5
        }
    },
    methods: {
        nextPage () {
            this.pageNum += 1;
        },
        prevPage () {
            this.pageNum -= 1;
        }
    },
    computed: {
        pageCount () {
            let listLeng = this.listItem.length,
                listSize = this.pageSize,
                page = Math.floor(listLeng / listSize);
            if (listLeng % listSize > 0) page += 1;
            return page;
        },
        paginatedData () {
            const start = this.pageNum * this.pageSize,
                    end = start + this.pageSize;
            return this.listItem.slice(start, end);
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
    .btn-cover {
        margin-top: 1.5rem;
        text-align: center;
        .page-btn {
            width: 4rem;
            height: 2rem;
            letter-spacing: 0.5px;
            border: 1px solid #C0C0C0;
            background-color: #fff;
            border-radius: 5px;
            color: #858585;
            font-weight: 800;
            &:hover {
                cursor: pointer;
            }
            &[disabled] {
                
            }
        }
        .page-count {
            padding: 0 1rem;
        }
    }
}
</style>