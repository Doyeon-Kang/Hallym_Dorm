<template>
    <div>
        <form action="">
            <!-- <select v-model="category">
                <option value="community">공지사항-학생기숙사</option>
                <option value="notice1">공지사항-사생자치회</option>
                <option value="news">서식자료실</option>
                <option value="repair">불편/수리 요청</option>
                <option value="store">나눔 장터</option>
                <option value="lost">분실물</option>
            </select> -->
            <div class="division">제목</div>
            <input class="title" type="text" v-model="title" placeholder="글 제목을 입력하세요.">
            <!-- https://github.com/davidroyer/vue2-editor 참고-->
            <div class="division">내용</div>
            <VueEditor 
            v-model="content"
            placeholder="내용을 입력하세요."
            id="editor"
            ></VueEditor>
            <div class="buttons">
                <input id="submit_btn" type="button" value="수정완료" @click="modifyArticle()">
                <input id="cancle_btn" type="button" @click="this.$router.go(-1)" value="취소">
            </div>
        </form>
    </div>
        
    </template>
    
    <script>
    /* eslint-disable */
    import { VueEditor } from "vue3-editor";
    import NoticeDataService from "@/services/NoticeDataService";
    import NewsDataService from "@/services/NewsDataService";
    import RepairDataService from "@/services/RepairDataService";
    import StoreDataService from "@/services/StoreDataService";
    import LostDataService from "@/services/LostDataService";
    
    export default {
        data() {
            return {
                no: this.$route.query.no,
                category: this.$route.query.category,
                title: "",
                content: "",
                prevRoute: null
            }
        },
        components: {
            VueEditor
        },
        created() {
            this.init()
        },
        methods: {
            init() {
                if (this.category == 1 || this.category == 2) {
                    NoticeDataService.get(this.no).then(data => {
                        let res = data.data
                        this.title = res.title
                        this.content = res.content
                    })
                } else if (this.category == 3) {
                    NewsDataService.get(this.no).then(data => {
                        let res = data.data
                        this.title = res.title
                        this.content = res.content
                    })
                } else if (this.category == 4) {
                    RepairDataService.get(this.no).then(data => {
                        let res = data.data
                        this.title = res.title
                        this.content = res.content
                    })
                }  else if (this.category == 5) {
                    StoreDataService.get(this.no).then(data => {
                        let res = data.data
                        this.title = res.title
                        this.content = res.content
                    })
                } else if (this.category == 6) {
                    LostDataService.get(this.no).then(data => {
                        let res = data.data
                        this.title = res.title
                        this.content = res.content
                    })
                } else {
                }
            },
            modifyArticle() {
                let data = {
                    writerStudentNo: this.user.studentno,
                    writer_name: this.user.name,
                    title: this.title,
                    content: this.content
                }
                if (this.category == 1) {
                    NoticeDataService.update(this.no, data).then(res => {
                        alert("수정 완료되었습니다.")
                        this.$router.push(`/community/view/?no=${this.no}`);
                    })
                } else if (this.category == 2){
                    data.notice1 = true
                    NoticeDataService.update(this.no, data).then(res => {
                        alert("수정 완료되었습니다.")
                        this.$router.push(`/community/notice1/view/?no=${this.no}`);
                    })
                } else if (this.category == 3) {
                    NewsDataService.update(this.no, data).then(res => {
                        alert("수정 완료되었습니다.")
                        this.$router.push(`/community/data/view/?no=${this.no}`);
                    })
                } else if (this.category == 4) {
                    RepairDataService.update(this.no, data).then(res => {
                        alert("수정 완료되었습니다.")
                        this.$router.push(`/community/repair/view/?no=${this.no}`);
                    })
                } else if (this.category == 5) {
                    StoreDataService.update(this.no, data).then(res => {
                        alert("수정 완료되었습니다.")
                        this.$router.push(`/community/market/view/?no=${this.no}`);
                    })
                } else if (this.category == 6) {
                    LostDataService.update(this.no, data).then(res => {
                        alert("수정 완료되었습니다.")
                        this.$router.push(`/community/lost/view/?no=${this.no}`);
                    })
                } else {
    
                }
                
            }
        },
        computed: {
            user() {
                return this.$store.state.auth.user
            }
        }
    }
    </script>
    
    <style lang="less" scoped>
    select {
        width: 180px;
        height: 30px;
        margin-bottom: 20px;
        border-radius: 0px;
        border: 1px solid #c0c0c0;
    }
    .title {
        display: block;
        margin-bottom: 20px;
        width: 99%;
        height: 24px;
        border: 1px solid #c0c0c0;
        border-radius: 0px;
    }
    .division {
        font-size: 14px;
        font-weight: 600;
        margin-bottom: 4px;
    }
    .buttons {
        margin-top: 30px;
        input {
            padding: 14px 20px;
            margin-left: 14px;
            border: none;
            font-weight: 600;
            float: right;
            &:hover {
                cursor: pointer;
            }
            &#cancle_btn {
                background-color: #858585;
                color: #fff;
            }
            &#submit_btn {
                background-color: #336EB4;
                color: #fff;
            }
        }
    }
    
    </style>