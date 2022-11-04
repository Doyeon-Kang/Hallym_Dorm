<template>
    <div class="wrapper">
        <div class="container">
            <PageTitle :title="title"></PageTitle>

            <div class="wrapper_detail">
                <hr>
                <div class="detail_top">
                    <div class="detail_title">{{ writerinfo.title }}</div>
                    <div class="detail_info">
                        <div>{{ writerinfo.writer }}</div>
                        <div>{{ writerinfo.date }}</div>
                        <div>{{ writerinfo.read_cnt }}</div>
                    </div>
                </div>
                <hr>
                <div class="detail_cont" v-html="writerinfo.cont"></div>
                <div class="btn_list">
                    <input v-if="writerinfo.writer === user.name" class="modify" type="button" value="수정" @click="updateArticle()" />
                    <input v-if="writerinfo.writer === user.name" class="delete" type="button" value="삭제" @click="deleteArticle()"/>
                    <input class="writelist" type="button" value="글목록" @click="$router.push('/community')" />
                </div>
                <div class="comments">
                    <div class="comments_input">
                        <div class="comments_title" >댓글</div>
                        <div class="comments_box">
                            <textarea class="comments_cont"></textarea>
                            <input class="comments_btn" type="button" value="등록"/>
                        </div>
                    </div>
                    <div class="comments_list" v-for="(text, index) in commentslist" :key="index">
                        <div class="comments_listbox">
                            <div class="comments_info">
                                <div class="comments_writer">{{ text.writer }}</div>
                                <div class="comments_date">{{ text.date }}</div>
                            </div>
                            <div class="comments_cont">{{ text.cont }}</div>
                            <div class="reply">
                                <div class="reply_list" @click="visiblereplylist">답글 보기 ({{ seccommentscnt }})</div>
                                <div class="reply_input" @click="visiblereplyinput">답글 달기</div>
                            </div>
                        </div>
                        <hr>
                        <div class="reply_listbox" v-for="(text, index) in seccommentslist" :key="index" v-show="isStatusOnList">
                            <div class="reply_contbox">
                                <div class="reply_info">
                                    <div class="reply_writer">{{ text.writer }}</div>
                                    <div class="reply_date">{{ text.date }}</div>
                                </div>
                                <div class="reply_cont">{{ text.cont }}</div>
                            </div>
                            <hr>
                        </div>
                    </div>
                    <div class="reply_write" v-show="isStatusOnInput">
                        <div class="reply_title">답글</div>
                        <div class="reply_box">
                            <textarea class="reply_cont_box"></textarea>
                            <div class="reply_btn">
                                <input class="reply_submit" type="button" value="등록" />
                                <input class="reply_delete" type="button" value="삭제" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
import PageTitle from "@/components/PageTitle.vue";
//import ViewDetailComVue from "@/components/ViewDetailCom.vue";
import NoticeDataService from "@/services/NoticeDataService";
import NewsDataService from "@/services/NewsDataService";
import RepairDataService from "@/services/RepairDataService";

export default {
    data() {
        return {
            no: this.$route.query.no,
            category: 0,
            isStatusOnList: false,
            isStatusOnInput: false,
            writerinfo: {
                title: "",
                writer: "",
                date: "",
                read_cnt: 0,
                cont : ''
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
            seccommentscnt: 2,
            seccommentslist: [
                {
                    writer: "황땡땡",
                    date: "2022.10.12 00:59",
                    cont: "유용한 정보 감사합니다.",
                }
            ],
        }
    },
    components: {
        PageTitle,
        // ViewDetailComVue,
    },
    created() {
        this.routeCheck();
        this.init()
    },
    mounted() {
    },
    methods: {
        routeCheck() {
            if (this.$route.name === "communityNo") {
                this.title = "공시사항 > 학생기숙사 > 게시글 상세보기";
                this.category = 1
            } else if (this.$route.name === "notice1No") {
                this.title = "공시사항 > 사생자치회 > 게시글 상세보기";
                this.category = 2
            } else if (this.$route.name === "dataNo") {
                this.title = "게시판 > 서식자료실 > 게시글 상세보기";
                this.category = 3
            } else if (this.$route.name === "repairNo") {
                this.title = "게시판 > 불편/수리 요청 > 게시글 상세보기";
                this.category = 4
            } else if (this.$route.name === "marketNo") {
                this.title = "게시판 > 나눔 장터 > 게시글 상세보기";
                this.category = 5
            } else if (this.$route.name === "lostNo") {
                this.title = "게시판 > 분실물 > 게시글 상세보기";
                this.category = 6
            } else {
                this.title = "Error Page";
            }
        },
        init() {
            if (this.$route.name === 'communityNo' || this.$route.name === 'notice1No') {
                NoticeDataService.get(this.no).then(data => {
                    let res = data.data
                    this.writerinfo.title = res.title
                    this.writerinfo.writer = res.writer_name
                    this.writerinfo.date = res.date
                    this.writerinfo.read_cnt = res.views
                    this.writerinfo.cont = res.content
                })
            } else if (this.$route.name === 'dataNo') {
                NewsDataService.get(this.no).then(data => {
                    let res = data.data
                    this.writerinfo.title = res.title
                    this.writerinfo.writer = res.writer_name
                    this.writerinfo.date = res.date
                    this.writerinfo.read_cnt = res.views
                    this.writerinfo.cont = res.content
                })
            } else if (this.$route.name === 'repairNo') {
                RepairDataService.get(this.no).then(data => {
                    let res = data.data
                    this.writerinfo.title = res.title
                    this.writerinfo.writer = res.writer_name
                    this.writerinfo.date = res.date
                    this.writerinfo.read_cnt = res.views
                    this.writerinfo.cont = res.content
                })
            } else {

            }
        }, 
        // goModify () { // 게시글 수정 - 현재 게시글 ID를 전송
        //     this.$router.push({name: 'modify-article', params: {articleID: this.no}}).catch(() => {})
        // },
        deleteArticle() {
            if (this.$route.name === 'communityNo' || this.$route.name === 'notice1No') {
                NoticeDataService.delete(this.no).then(data => {
                    alert("정상적으로 삭제되었습니다.")
                    this.$router.push('/community')
                })
            } else if (this.$route.name === 'dataNo') {
                NewsDataService.delete(this.no).then(data => {
                    alert("정상적으로 삭제되었습니다.")
                    this.$router.push('/community/data')
                })
            } else if (this.$route.name === 'repairNo') {
                RepairDataService.delete(this.no).then(data => {
                    alert("정상적으로 삭제되었습니다.")
                    this.$router.push('/community/repair')
                })
            } else {

            }
        },  
        updateArticle() {
            this.$router.push({
                name: 'modify-article',
                query: { 
                    category: this.category,
                    no: this.no,
                }
            })
        },
        visiblereplylist: function() {
            this.isStatusOnList = !this.isStatusOnList;
        },
        visiblereplyinput: function() {
            this.isStatusOnInput = !this.isStatusOnInput;
        },
    },
    computed: {
        user() {
            return this.$store.state.auth.user
        }
    }
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
                    font-size: 24px;
                    font-weight: bold;
                    margin-bottom: 12px;
                }

                .detail_info {
                    display: flex;
                    align-items: center;
                    font-size: 14px;

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
                margin: 25px 15px 100px;
            }

            .btn_list {
                display: flex;
                justify-content: flex-end;
                width: 100%;

                .modify, .delete, .writelist {
                    width: 7%;
                    height: 40px;
                    font-size: 15px;
                    color: #fff;
                    border: none;

                    &:hover {
                        cursor: pointer;
                    }
                }
                .modify {
                        background-color: #336EB4;
                    }

                .delete {
                    background-color: #D36060;
                    margin: 0 10px;
                }

                .writelist {
                    width: 10%;
                    background-color: #939393;
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
                            width: 90%;
                            font-size: 15px;
                            height: 80px;
                            resize: none;
                            padding: 5px;
                        }

                        .comments_btn {
                            width: 10%;
                            height: 92px;
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

                        .reply {
                            position: relative;
                            font-weight: bold;
                            font-size: 15px;

                            .reply_list {
                                color: #447EC3;
                                position: absolute;
                                bottom: 3px;

                                &:hover {
                                    cursor: pointer;
                                }
                            }

                            .reply_input {
                                color: #858585;
                                position: absolute;
                                bottom: 3px;
                                right: 10px;

                                &:hover {
                                    cursor: pointer;
                                }
                            }
                        }
                    }

                    .reply_listbox {
                        width: 100%;

                        .reply_contbox{
                            margin-left: 50px;
                            padding-top: 5px;
                            .reply_info {
                                display: flex;

                                .reply_writer {
                                    font-weight: bold;
                                    font-size: 18px;
                                }

                                .reply_date {
                                    color: #858585;
                                    font-size: 13px;
                                    line-height: 30px;
                                    margin-left: 20px;
                                }
                            }
                        }

                        .reply_cont {
                            margin: 10px 0;
                        }
                    }
                }

                .reply_write {
                    width: 100%;

                    .reply_title {
                        padding: 15px 0;
                        font-weight: bold;
                        font-size: 17px;
                    }

                    .reply_box {
                        .reply_cont_box {
                            width: 99%;
                            font-size: 15px;
                            height: 60px;
                            resize: none;
                            padding: 5px;
                        }

                        .reply_btn {
                            display: flex;
                            float: right;

                            input[type=button] {
                                width: 100%;
                                padding: 5px 10px;
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
                                    margin-left: 10px;
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
</style>