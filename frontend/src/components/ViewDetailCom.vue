<template>
    <div class="wrapper_detail">
        <hr>
        <div>
            <div>2022년 공지사항</div>
            <div class="right">
                <div>{{작성자}}</div>
                <div>날짜</div>
                <div>조회수</div>
            </div>
        </div>
        <hr>
        내용
        <hr>
        <div><input type="button" value="글목록" /></div>
        <div>
            <div>댓글</div>
            <div>
                <div><textarea></textarea></div>
                <div><input type="button" value="등록" /></div>
            </div>
        </div>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
        };
    },
    props: {
        listTitle: {
            type: Array,
            required: true,
        },
        listItem: {
            type: Array,
            required: true,
        },
        photoList: {
            type: Array,
            default: () => [
                {
                    photo: "",
                    title: "",
                    writer: "",
                    date: "",
                    comment_cnt: "",
                },
            ],
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
    computed: {
    },
};
</script>
  
<style lang="less" scoped>
.wrapper_detail {
    width: 1080px;
    color: #222;

    hr {
        width: 100%;
    }

    .right {
        display: flex;
        align-items: center;

        div {
            &::after {
                margin: 0 8px;
                content: "|";
                color: #858585;
                font-weight: 400;
            }

            &:last-child::after {
                content: none;
            }
        }
    }
}
</style>