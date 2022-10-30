<template>
    <div class="wrapper">
        <div class="container">
            <PageTitle :title="title"></PageTitle>

            <div class="wrapper_detail">
                <hr>
                <div class="detail_top">
                    <div class="detail_title">{{writerinfo.title}}</div>
                    <div class="detail_info">
                        <div>{{writerinfo.writer}}</div>
                        <div>{{writerinfo.date}}</div>
                        <div>{{writerinfo.read_cnt}}</div>
                    </div>
                </div>
                <hr>
                <div class="detail_cont">{{writerinfo.cont}}</div>
                <hr>
                <div class="btn_list">
                    <input class="modify" type="button" value="수정" />
                    <input class="delete" type="button" value="삭제" />
                    <input class="writelist" type="button" value="글목록" />
                </div>
                <div class="comments">
                    <div class="comments_input">
                        <div class="comments_title">댓글</div>
                        <div class="comments_box">
                            <div class="comments_cont"><textarea></textarea></div>
                            <div class="comments_btn"><button>등록</button></div>
                        </div>
                    </div>
                    <div class="comments_list" v-for="(text, index) in commentslist" :key="index">
                        <div class="comments_listbox">
                            <div class="comments_info">
                                <div class="comments_writer">{{ text.writer }}</div>
                                <div class="comments_date">{{ text.date }}</div>
                            </div>
                            <div class="comments_cont">{{ text.cont }}</div>
                            <div class="comments_plus">답글 달기</div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
import PageTitle from "@/components/PageTitle.vue";
//import ViewDetailComVue from "@/components/ViewDetailCom.vue";

export default {
    data() {
        return {
            writerinfo: {
                title: "2022년 공지사항",
                writer: "이땡땡",
                date: "2022.10.11",
                read_cnt: 5,
                cont: `
                로렘 입숨(lorem ipsum; 줄여서 립숨, lipsum)은 출판이나 그래픽 디자인 분야에서 폰트, 타이포그래피, 레이아웃 같은 그래픽 요소나 시각적 연출을 보여줄 때 사용하는 표준 채우기 텍스트로, 최종 결과물에 들어가는 실제적인 문장 내용이 채워지기 전에 시각 디자인 프로젝트 모형의 채움 글로도 이용된다. 이런 용도로 사용할 때 로렘 입숨을 그리킹(greeking)이라고도 부르며, 때로 로렘 입숨은 공간만 차지하는 무언가를 지칭하는 용어로도 사용된다.
                로렘 입숨(lorem ipsum; 줄여서 립숨, lipsum)은 출판이나 그래픽 디자인 분야에서 폰트, 타이포그래피, 레이아웃 같은 그래픽 요소나 시각적 연출을 보여줄 때 사용하는 표준 채우기 텍스트로, 최종 결과물에 들어가는 실제적인 문장 내용이 채워지기 전에 시각 디자인 프로젝트 모형의 채움 글로도 이용된다. 이런 용도로 사용할 때 로렘 입숨을 그리킹(greeking)이라고도 부르며, 때로 로렘 입숨은 공간만 차지하는 무언가를 지칭하는 용어로도 사용된다.`
            },
            commentslist: [
                {
                    writer: "김땡땡",
                    date: "2022.10.11 07:20",
                    cont: "유용한 정보 감사합니다.",
                    comments_cnt: 3,
                },
                {
                    writer: "박땡땡",
                    date: "2022.10.11 16:17",
                    cont: "유용한 정보 감사합니다.",
                    comments_cnt: 3,
                },
                {
                    writer: "최땡땡",
                    date: "2022.10.12 00:55",
                    cont: "유용한 정보 감사합니다.",
                    comments_cnt: 3,
                },
            ],
        }
    },
    components: {
        PageTitle,
        // ViewDetailComVue,
    },
    created() {
        this.routeCheck();
    },
    mounted() {
        this.fnGetView();
    },
    methods: {
        routeCheck() {
            if (this.$route.name === "communityNo") {
                this.title = "공시사항 > 학생기숙사 > 게시글 상세보기";
            } else if (this.$route.name === "notice1No") {
                this.title = "공시사항 > 사생자치회 > 게시글 상세보기";
            } else if (this.$route.name === "dataNo") {
                this.title = "게시판 > 서식자료실 > 게시글 상세보기";
            } else if (this.$route.name === "repairNo") {
                this.title = "게시판 > 불편/수리 요청 > 게시글 상세보기";
            } else if (this.$route.name === "marketNo") {
                this.title = "게시판 > 나눔 장터 > 게시글 상세보기";
            } else if (this.$route.name === "lostNo") {
                this.title = "게시판 > 분실물 > 게시글 상세보기";
            } else {
                this.title = "Error Page";
            }
        },
    },
    watch: {
    },
};
</script>
  
<style lang="less" scoped>
.wrapper {
    margin: 100px 0;
    width: 1080px;
    display: flex;
    color: #222;

    .container {
        width: 100%;

        .wrapper_detail {
            .detail_top {
                margin: 25px 15px;

                .detail_title {
                    font-size: 18px;
                    font-weight: bold;
                    margin-bottom: 12px;
                }

                .detail_info {
                    display: flex;
                    align-items: center;
                    font-size: 11px;

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

            .detail_cont {
                margin: 25px 15px;
            }

            .btn_list {
                display: flex;
                float: right;

                input[type=button] {
                    float: right;
                    width: 80px;
                    height: 40px;
                    font-size: 15px;
                    color: #fff;
                    border: none;

                    &:hover {
                        cursor: pointer;
                    }

                    &:nth-child(1) {
                        background-color: #336EB4;
                    }

                    &:nth-child(2) {
                        background-color: #D36060;
                        margin: 0 10px;
                    }

                    &:nth-child(3) {
                        width: 140px;
                        background-color: #939393;
                    }
                }
            }

            .comments {
                clear: both;

                .comments_input {
                    .comments_title {
                        padding: 15px 0;
                        font-weight: bold;
                        font-size: 18px;
                    }

                    .comments_box {
                        display: flex;

                        textarea {
                            width: 917.5px;
                            font-size: 15px;
                            height: 80px;
                            resize: none;
                            padding: 5px;
                        }

                        button {
                            width: 140px;
                            height: 90px;
                            font-size: 15px;
                            margin-left: 10px;
                            background-color: #336EB4;
                            color: #fff;
                            border: none;

                            &:hover {
                                cursor: pointer;
                            }
                        }
                    }
                }

                .comments_list {
                    width: 100%;
                    margin: 20px 0;

                    .comments_listbox {
                        position: relative;
                        padding: 0 15px;

                        .comments_info {
                            display: flex;

                            .comments_writer {
                                font-weight: bold;
                                font-size: 18px;
                            }

                            .comments_date {
                                color: #858585;
                                font-size: 13px;
                                line-height: 30px;
                                margin-left: 20px;
                            }
                        }

                        .comments_cont {
                            margin: 10px 0;
                            padding-bottom: 25px;
                        }

                        .comments_plus {
                            color: #858585;
                            font-weight: bold;
                            font-size: 15px;
                            position: absolute;
                            right: 15px;
                            bottom: 1px;

                            &:hover {
                                cursor: pointer;
                            }
                        }
                    }
                }
            }
        }
    }
}
</style>