<template>
    <div class="join">
        <div class="joinbox">
            <div class="logo">
                <img src="@/assets/logo.png" alt="" />
                <img src="@/assets/univ.png" alt="" />
                <div class="univ">| Dormitory</div>
            </div>
            <div class="jointitle">회원가입</div>
            <div class="join_name">
                <div class="join_text">이름</div>
                <input type="text" v-model="name" placeholder="이름을 입력해주세요.">
            </div>
            <div class="join_id">
                <div class="join_text">아이디</div>
                <input type="text" v-model="id" placeholder="아이디를 입력해주세요.">
                <div class="joinguide">※아이디는 한림대학교 학번과 동일하게 입력.</div>
            </div>
            <div class="join_pw">
                <div class="join_text">비밀번호</div>
                <input type="password" v-model="pwd" placeholder="비밀번호를 입력해주세요.">
                <div class="joinguide">※비밀번호는 8자리 이상.</div>
            </div>
            <div class="join_pw_check">
                <div class="join_text">비밀번호 확인</div>
                <input type="password" v-model="pwd_check" placeholder="비밀번호를 다시 한 번 입력해주세요.">
            </div>
            <div class="join_email">
                <div class="join_text">이메일</div>
                <input type="text" v-model="email" placeholder="이메일을 입력해주세요.">
            </div>
            <div class="joinbtnbox joinbtn">회원가입</div>
            <div class="loginbtn" @click="this.$router.push('login')">◀ 로그인 페이지</div>
        </div>
    </div>
</template>
  
<style scoped lang="less">
.join {
    width: 825px;
    height: 1100px;
    margin: 130px auto;
    box-shadow: 0px 0px 15px #bababa;
    color: #222222;

    .joinbox {
        width: 525px;
        margin: 0 auto;
        padding-top: 130px;

        .logo {
            width: 280px;
            margin: 0 auto;
            margin-bottom: 10px;

            .univ {
                font-size: 20px;
                font-weight: bold;
                float: right;
            }
        }

        .jointitle {
            clear: both;
            color: #336EB4;
            text-align: center;
            font-size: 50px;
            font-weight: bold;
            margin-bottom: 70px;
        }

        .join_name,.join_id,.join_pw,.join_pw_check,.join_email {
            color: #00B6AD;
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        .join_id, .join_pw {
            margin-bottom: 50px;
        }

        .join_text {
            margin-bottom: 5px;
        }

        input {
            width: 520px;
            height: 40px;
            border: none;
            border-bottom: 2px solid #00B6AD;
            font-size: 17px;
        }

        .joinguide{
            color: #DD6464;
            float: left;
        }

        .joinbtnbox {
            clear: both;
            width: 525px;
            height: 65px;
            font-size: 26px;
            font-weight: bold;
            text-align: center;
            line-height: 65px;
        }

        .joinbtnbox:hover, .loginbtn:hover{
            cursor: pointer;
        }

        .joinbtn {
            background-color: #00B6AD;
            color: #FFFFFF;
            margin-top: 50px;
        }

        .loginbtn{
            color: #858585;
            font-weight: bold;
            margin-top: 15px;
        }
    }
}
</style>