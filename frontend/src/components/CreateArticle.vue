<template>
<div>
    <form action="">
        <select name="" id="">
            <option value="">공지사항-학생기숙사</option>
            <option value="">공지사항-사생자치회</option>
            <option value="">서식자료실</option>
            <option value="">불편/수리 요청</option>
            <option value="">나눔 장터</option>
            <option value="">분실물</option>
        </select>
        <div class="division">제목</div>
        <input class="title" type="text" placeholder="글 제목을 입력하세요.">
        <!-- https://github.com/davidroyer/vue2-editor 참고-->
        <div class="division">내용</div>
        <VueEditor 
        v-model="content"
        placeholder="내용을 입력하세요."
        id="editor"
        ></VueEditor>
        <div class="buttons">
            <input id="submit_btn" type="submit" value="작성완료">
            <input id="cancle_btn" type="button" @click="this.$router.go(-1)" value="취소">
        </div>
    </form>
</div>
    
</template>

<script>
import { VueEditor } from "vue3-editor";

export default {
    data() {
        return {
            content: "",
            prevRoute: null
        }
    },
    components: {
        VueEditor
    },
    routes: [
        
    ]
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