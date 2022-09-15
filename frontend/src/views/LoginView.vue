<template>
  <div class="login">
    <div class="loginbox">
      <div class="logo" @click="this.$router.push('/')">
        <img src="@/assets/logo.png" alt="" />
        <img src="@/assets/univ.png" alt="" />
        <div class="univ">| Dormitory</div>
      </div>
      <div class="logintitle">학생생활관 로그인</div>
      <div class="login_id">
        <div class="login_text">아이디</div>
        <input type="text" v-model="id" placeholder="아이디를 입력해주세요.">
      </div>
      <div class="login_pw">
        <div class="login_text">비밀번호</div>
        <input type="password" v-model="pwd" placeholder="비밀번호를 입력해주세요.">
      </div>
      <div class="loginguide">※아이디는 한림대학교 학번과 동일함.</div>
      <a href="/findpw" class="findpw">비밀번호 찾기</a>
      <div class="loginbtnbox loginbtn">로그인</div>
      <div class="loginbtnbox joinbtn" @click="this.$router.push('join')">회원가입</div>
    </div>
  </div>
</template>

<style scoped lang="less">
.login {
  width: 625px;
  height: 680px;
  margin: 100px auto;
  box-shadow: 0px 0px 10px #bababa;
  color: #222222;

  .loginbox {
    width: 400px;
    margin: 0 auto;
    padding-top: 80px;

    .logo {
      width: 190px;
      margin: 0 auto;
      margin-bottom: 10px;

      img{
        width:60px;
      }

      .univ {
        font-size: 12px;
        font-weight: bold;
        line-height: 40px;
        float: right;
      }
    }

    .logintitle {
      clear: both;
      color: #336EB4;
      text-align: center;
      font-size: 40px;
      font-weight: bold;
      margin-bottom: 60px;
    }

    .login_id,.login_pw {
      color: #00B6AD;
      font-size: 17px;
      font-weight: bold;
    }

    .login_id {
      margin-bottom: 30px;
    }

    .login_pw {
      margin-bottom: 5px;
    }

    .login_id input,.login_pw input {
      width: 396px;
      height: 30px;
      border: none;
      border-bottom: 2px solid #00B6AD;
      margin-top: 10px;
      font-size: 14px;
    }

    .loginguide,.findpw {
      color: #858585;
      font-weight: bold;
      font-size: 13px;
    }

    .loginguide {
      float: left;
    }

    .findpw {
      float: right;
    }

    .loginbtnbox {
      clear: both;
      width: 400px;
      height: 50px;
      font-size: 18px;
      font-weight: bold;
      text-align: center;
      line-height: 50px;
    }

    .logo:hover, .loginbtnbox:hover {
      cursor: pointer;
    }

    .loginbtn {
      background-color: #00B6AD;
      color: #FFFFFF;
      margin-top: 80px;
    }

    .joinbtn {
      background-color: #EBECF0;
      color: #858585;
      margin-top: 15px;
    }
  }
}
</style>