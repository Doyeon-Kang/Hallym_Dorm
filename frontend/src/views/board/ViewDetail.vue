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
                        <div>조회수: {{ writerinfo.read_cnt }}</div>
                    </div>
                </div>
                <hr>
                <div class="photo" v-show="photo_board">
                    <img v-for="(photo, index) in photo" :key="index" :src="photo" alt="사진">
                </div>
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
                            <textarea class="comments_cont" v-model="newComment"></textarea>
                            <input class="comments_btn" type="button" value="등록" @click="createComment()"/>
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
                                <div class="reply_list" @click="visiblereplylist(text)">답글 보기 ({{ text.SecCnt }})</div>
                                <div class="reply_input" @click="visiblereplyinput(text)">답글 달기</div>
                                <!-- <div class="edit_btn" >수정</div> -->
                                <div class="delete_btn" @click="deleteComment(text.no)">삭제</div>
                            </div>
                        </div>
                        <hr>
                        <div class="reply_listbox" v-for="(text2, index) in text.seccommentslist" :key="index" v-show="text.sub_show">
                            <div class="reply_contbox">
                                <div class="reply_info">
                                    <div class="reply_writer">> {{ text2.writer }}</div>
                                    <div class="reply_date">{{ text2.date }}</div>
                                </div>
                                <div class="reply_cont">{{ text2.cont }}</div>
                                
                            </div>
                            <hr>
                        </div>
                        <div class="reply_write" v-show="text.input_show">
                        <div class="reply_title">답글</div>
                        <div class="reply_box">
                            <textarea class="reply_cont_box" v-model="newSubComment"></textarea>
                            <div class="reply_btn">
                                <input class="reply_submit" type="button" value="등록" @click="createSubComment(text.no)"/>
                                <input class="reply_delete" type="button" value="삭제" />
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
/* eslint-disable */
import PageTitle from "@/components/PageTitle.vue";
//import ViewDetailComVue from "@/components/ViewDetailCom.vue";
import NoticeDataService from "@/services/NoticeDataService";
import NewsDataService from "@/services/NewsDataService";
import RepairDataService from "@/services/RepairDataService";
import StoreDataService from "@/services/StoreDataService";
import LostDataService from "@/services/LostDataService";

import NoticeCommentService from "@/services/NoticeCommentService";
import NewsCommentService from "@/services/NewsCommentService";
import RepairCommentService from "@/services/RepairCommentService";
import StoreCommentService from "@/services/StoreCommentService";
import LostCommentService from "@/services/LostCommentService";

import NoticeSubcommentService from "@/services/NoticeSubcommentService";
import NewsSubcommentService from "@/services/NewsSubcommentService";
import RepairSubcommentService from "@/services/RepairSubcommentService";
import StoreSubcommentService from "@/services/RepairSubcommentService";
import LostSubcommentService from "@/services/RepairSubcommentService";

import StorePhotoService from "@/services/StorePhotoService";
import LostPhotoService from "@/services/LostPhotoService";

export default {
    data() {
        return {
            no: this.$route.query.no,
            newComment: "",
            newSubComment: "",
            category: 0,
            writerinfo: {
                title: "",
                writer: "",
                date: "",
                read_cnt: 0,
                cont : ''
            },
            commentslist: [],
            seccommentslist: [],
            photo_board: false,
            photo: [],
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
                NoticeCommentService.getAll(this.no).then(data => {
                    let res = data.data
                    for(let i=0; i<res.length; i++) {
                        this.commentslist.push({})
                        this.commentslist[i].no = res[i].id
                        this.commentslist[i].writer = res[i].writer_name
                        this.commentslist[i].date = res[i].createdDate
                        this.commentslist[i].cont = res[i].content
                        this.commentslist[i].sub_show = false // 대댓글 보기
                        this.commentslist[i].input_show = false // 대댓글 입력창

                        NoticeSubcommentService.getAll(this.no, this.commentslist[i].no).then(data2 => {
                            let res2 = data2.data
                            this.commentslist[i].SecCnt = res2.length
                            this.commentslist[i].seccommentslist = []
                            for(let j=0; j<res2.length; j++) {
                                this.commentslist[i].seccommentslist.push({})
                                this.commentslist[i].seccommentslist[j].no = res2[j].id
                                this.commentslist[i].seccommentslist[j].writer = res2[j].writer_name
                                this.commentslist[i].seccommentslist[j].date = res2[j].createdDate
                                this.commentslist[i].seccommentslist[j].cont = res2[j].content
                            }
                        })
                    }
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
                NewsCommentService.getAll(this.no).then(data => {
                    let res = data.data
                    for(let i=0; i<res.length; i++) {
                        this.commentslist.push({})
                        this.commentslist[i].no = res[i].id
                        this.commentslist[i].writer = res[i].writer_name
                        this.commentslist[i].date = res[i].createdDate
                        this.commentslist[i].cont = res[i].content
                        this.commentslist[i].sub_show = false // 대댓글 보기
                        this.commentslist[i].input_show = false // 대댓글 입력창

                        NewsSubcommentService.getAll(this.no, this.commentslist[i].no).then(data2 => {
                            let res2 = data2.data
                            this.commentslist[i].SecCnt = res2.length
                            this.commentslist[i].seccommentslist = []
                            for(let j=0; j<res2.length; j++) {
                                this.commentslist[i].seccommentslist.push({})
                                this.commentslist[i].seccommentslist[j].no = res2[j].id
                                this.commentslist[i].seccommentslist[j].writer = res2[j].writer_name
                                this.commentslist[i].seccommentslist[j].date = res2[j].createdDate
                                this.commentslist[i].seccommentslist[j].cont = res2[j].content
                            }
                        })
                    }
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
                RepairCommentService.getAll(this.no).then(data => {
                    let res = data.data
                    console.log(res)
                    for(let i=0; i<res.length; i++) {
                        this.commentslist.push({})
                        this.commentslist[i].no = res[i].id
                        this.commentslist[i].writer = res[i].writer_name
                        this.commentslist[i].date = res[i].createdDate
                        this.commentslist[i].cont = res[i].content
                        this.commentslist[i].sub_show = false // 대댓글 보기
                        this.commentslist[i].input_show = false // 대댓글 입력창

                        RepairSubcommentService.getAll(this.no, this.commentslist[i].no).then(data2 => {
                            let res2 = data2.data
                            this.commentslist[i].SecCnt = res2.length
                            this.commentslist[i].seccommentslist = []
                            for(let j=0; j<res2.length; j++) {
                                this.commentslist[i].seccommentslist.push({})
                                this.commentslist[i].seccommentslist[j].no = res2[j].id
                                this.commentslist[i].seccommentslist[j].writer = res2[j].writer_name
                                this.commentslist[i].seccommentslist[j].date = res2[j].createdDate
                                this.commentslist[i].seccommentslist[j].cont = res2[j].content
                            }
                        })
                    }
                })
            } else if (this.$route.name === 'marketNo') {
                this.photo_board = true
                StoreDataService.get(this.no).then(data => {
                    let res = data.data
                    this.writerinfo.title = res.title
                    this.writerinfo.writer = res.writer_name
                    this.writerinfo.date = res.date
                    this.writerinfo.read_cnt = res.views
                    this.writerinfo.cont = res.content
                })

                StorePhotoService.getAll(this.no).then(photo_data => {
                    for(let i=0; i<photo_data.data.length; i++) {
                        this.photo[i] = photo_data.data[i].url
                    }
                })
                StoreCommentService.getAll(this.no).then(data => {
                    let res = data.data
                    console.log(res)
                    for(let i=0; i<res.length; i++) {
                        this.commentslist.push({})
                        this.commentslist[i].no = res[i].id
                        this.commentslist[i].writer = res[i].writer_name
                        this.commentslist[i].date = res[i].createdDate
                        this.commentslist[i].cont = res[i].content
                        this.commentslist[i].sub_show = false // 대댓글 보기
                        this.commentslist[i].input_show = false // 대댓글 입력창

                        StoreSubcommentService.getAll(this.no, this.commentslist[i].no).then(data2 => {
                            let res2 = data2.data
                            this.commentslist[i].SecCnt = res2.length
                            console.log(data2)
                            this.commentslist[i].seccommentslist = []
                            for(let j=0; j<res2.length; j++) {
                                this.commentslist[i].seccommentslist.push({})
                                this.commentslist[i].seccommentslist[j].no = res2[j].id
                                this.commentslist[i].seccommentslist[j].writer = res2[j].writer_name
                                this.commentslist[i].seccommentslist[j].date = res2[j].createdDate
                                this.commentslist[i].seccommentslist[j].cont = res2[j].content
                            }
                        })
                    }
                })
            } else if (this.$route.name === 'lostNo') {
                this.photo_board = true
                LostDataService.get(this.no).then(data => {
                    let res = data.data
                    this.writerinfo.title = res.title
                    this.writerinfo.writer = res.writer_name
                    this.writerinfo.date = res.date
                    this.writerinfo.read_cnt = res.views
                    this.writerinfo.cont = res.content
                })
                LostPhotoService.getAll(this.no).then(photo_data => {
                    for(let i=0; i<photo_data.data.length; i++) {
                        this.photo[i] = photo_data.data[i].url
                    }
                    
                })
                LostCommentService.getAll(this.no).then(data => {
                    let res = data.data
                    for(let i=0; i<res.length; i++) {
                        this.commentslist.push({})
                        this.commentslist[i].no = res[i].id
                        this.commentslist[i].writer = res[i].writer_name
                        this.commentslist[i].date = res[i].createdDate
                        this.commentslist[i].cont = res[i].content
                        this.commentslist[i].sub_show = false // 대댓글 보기
                        this.commentslist[i].input_show = false // 대댓글 입력창

                        LostSubcommentService.getAll(this.no, this.commentslist[i].no).then(data2 => {
                            
                            let res2 = data2.data
                            console.log(res2)
                            this.commentslist[i].SecCnt = res2.length
                            this.commentslist[i].seccommentslist = []
                            for(let j=0; j<res2.length; j++) {
                                this.commentslist[i].seccommentslist.push({})
                                this.commentslist[i].seccommentslist[j].no = res2[j].id
                                this.commentslist[i].seccommentslist[j].writer = res2[j].writer_name
                                this.commentslist[i].seccommentslist[j].date = res2[j].createdDate
                                this.commentslist[i].seccommentslist[j].cont = res2[j].content
                            }
                        })
                    }
                })
            } else {

            }
        }, 
        // 게시글 삭제
        deleteArticle() {
            if (this.$route.name === 'communityNo' || this.$route.name === 'notice1No') {
                NoticeDataService.delete(this.no).then(data => {
                    alert("정상적으로 삭제되었습니다.")
                    this.$router.go('')
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
            } else if (this.$route.name === 'marketNo') {
                StoreDataService.delete(this.no).then(data => {
                    alert("정상적으로 삭제되었습니다.")
                    this.$router.push('/community/market')
                })
            } else if (this.$route.name === 'lostNo') {
                LostDataService.delete(this.no).then(data => {
                    alert("정상적으로 삭제되었습니다.")
                    this.$router.push('/community/lost')
                })
            } else {

            }
        },  
        // 게시글 수정
        updateArticle() {
            this.$router.push({
                name: 'modify-article',
                query: { 
                    category: this.category,
                    no: this.no,
                }
            })
        },
        // 댓글 생성
        createComment() {
            let req = {}
            req.writer_studentno = this.user.studentno
            req.writer_name = this.user.name
            req.content = this.newComment

            if (this.$route.name === 'communityNo' || this.$route.name === 'notice1No') {                
                NoticeCommentService.create(this.no, req)
            } else if (this.$route.name === 'dataNo') {
                NewsCommentService.create(this.no, req)
            } else if (this.$route.name === 'repairNo') {
                RepairCommentService.create(this.no, req)
            } else if (this.$route.name === 'marketNo') {
                StoreCommentService.create(this.no, req)
            } else if (this.$route.name === 'lostNo') {
                LostCommentService.create(this.no, req)
            } else {

            }
            this.$router.go('')
        },
        // 댓글 삭제
        deleteComment(no) {
            if (this.$route.name === 'communityNo' || this.$route.name === 'notice1No') {
                NoticeCommentService.delete(no)
                NoticeSubcommentService.deleteAll(this.no, no)
            } else if (this.$route.name === 'dataNo') {
                NewsCommentService.delete(no)
                NewsSubcommentService.deleteAll(this.no, no)
            } else if (this.$route.name === 'repairNo') {
                RepairCommentService.delete(no)
                RepairSubcommentService.deleteAll(this.no, no)
            } else if (this.$route.name === 'marketNo') {
                StoreCommentService.delete(no)
                StoreSubcommentService.deleteAll(this.no, no)
            } else if (this.$route.name === 'lostNo') {
                LostCommentService.delete(no)
                LostSubcommentService.deleteAll(this.no, no)
            } else {

            }
            alert("댓글이 삭제되었습니다.")
            this.$router.go('')
        },
        // 대댓글 생성
        createSubComment(sub_id) {
            let req = {}
            req.writer_studentno = this.user.studentno
            req.writer_name = this.user.name
            req.content = this.newSubComment

            if (this.$route.name === 'communityNo' || this.$route.name === 'notice1No') {                
                NoticeSubcommentService.create(this.no, sub_id, req)
            } else if (this.$route.name === 'dataNo') {
                NewsSubcommentService.create(this.no, sub_id, req)
            } else if (this.$route.name === 'repairNo') {
                RepairSubcommentService.create(this.no, sub_id, req)
            } else if (this.$route.name === 'marketNo') {
                StoreSubcommentService.create(this.no, sub_id, req)
            } else if (this.$route.name === 'lostNo') {
                LostSubcommentService.create(this.no, sub_id, req)
            } else {

            }
            this.$router.go('')
        },
        visiblereplylist(comment) {
            this.com_id = comment.no
            comment.sub_show = !comment.sub_show
        },
        visiblereplyinput(comment) {
            comment.input_show = !comment.input_show
        },
    },
    computed: {
        user() {
            if (this.$store.state.auth.user == null) {
                let data = {
                    name: ''
                }
                return data
            } else {
                return this.$store.state.auth.user
            }
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

            .photo {
                img {
                    width: 400px;
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
                                right: 50px;

                                &:hover {
                                    cursor: pointer;
                                }
                            }
                            .edit_btn {
                                color: #336EB4;
                                position: absolute;
                                bottom: 3px;
                                right: 50px;
                                &:hover {
                                    cursor: pointer;
                                }
                            }
                            .delete_btn {
                                color: #D36060;
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