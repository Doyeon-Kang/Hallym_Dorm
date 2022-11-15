<template>
<div>
    <form action="">
        <select v-model="category">
            <option value="community">공지사항-학생기숙사</option>
            <option value="notice1">공지사항-사생자치회</option>
            <option value="news">서식자료실</option>
            <option value="repair">불편/수리 요청</option>
            <option value="store">나눔 장터</option>
            <option value="lost">분실물</option>
        </select>
        <div class="img_input" v-show="category === 'store' || category === 'lost'">
            <input type="file" multiple accept="image/*" @change="fileChange"/>
        </div>
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
            <input id="submit_btn" type="button" value="작성완료" @click="createArticle()">
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
import StorePhotoService from "@/services/StorePhotoService";
import LostPhotoService from "@/services/LostPhotoService";

export default {
    data() {
        return {
            category: "",
            title: "",
            content: "",
            prevRoute: null,
            file: [],
        }
    },
    components: {
        VueEditor
    },
    created() {
    },
    methods: {
        fileChange(event) {
            const file = event.target.files;
            console.log(file)
            let validation = true;
            let message = '';

            for(let i=0; i<file.length; i++) {
                if (file[i].size > 1024 * 1024 * 2) {
                    message = `${message}, 파일은 용량은 2MB 이하만 가능합니다.`;
                    validation = false;
                }

                if (file[i].type.indexOf('image') < 0) {
                    message = `${message}, 이미지 파일만 업로드 가능합니다.`;
                    validation = false;
                }
            }

            if (validation) {
                this.file = file
            } else {
                this.file = [];
                alert(message);
            }
        },  
        showValue(target) {
            console.log(target.calue)
            console.log(target.options[target.selectedIndex].text)
        },
        async createArticle() {
            let data = {
                writer_studentno: this.user.studentno,
                writer_name: this.user.name,
                title: this.title,
                content: this.content
            }
            if (this.category === 'community') {
                await NoticeDataService.create(data).then(res => {
                    alert("작성 완료되었습니다.")
                    //this.$router.push('/community');
                })
            } else if (this.category === 'notice1'){
                data.notice1 = true
                await NoticeDataService.create(data).then(res => {
                    alert("작성 완료되었습니다.")
                    //this.$router.push('/community/notice1');
                })
            } else if (this.category === 'news') {
                await NewsDataService.create(data).then(res => {
                    alert("작성 완료되었습니다.")
                    //this.$router.push('/community/data');
                })
            } else if (this.category === 'repair') {
                await RepairDataService.create(data).then(res => {
                    alert("작성 완료되었습니다.")
                    //this.$router.push('/community/repair');
                })
            } else if (this.category === 'store') {
                var id;
                await StoreDataService.create(data).then(res => {
                    alert("작성 완료되었습니다.")
                    id = res.data.id
                    console.log(res)
                    // if (this.file !== []) {
                    //     StorePhotoService.create(res.data.id, this.file)
                    // }
                    //this.$router.push('/community/market');
                })
                if (this.file !== []) {
                    await StorePhotoService.create(id, this.file).then(res => {
                        console.log(res);
                    })
                }  
            } else if (this.category === 'lost') {
                var id
                await LostDataService.create(data).then(res => {
                    alert("작성 완료되었습니다.")
                    id = res.data.id
                    // if (this.file !== []) {
                    //     LostPhotoService.create(res.data.id, this.file)
                    // }
                    //this.$router.push('/community/lost');
                })
                if (this.file !== []) {
                    await LostPhotoService.create(id, this.file)
                }
            } else {

            }
            this.$router.go('')
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
.img_input {
    margin-bottom: 20px;
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