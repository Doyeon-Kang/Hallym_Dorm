<template>
  <div class="login">
    <div class="loginbox">
      <div class="logo">
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
      <a href="#" class="findpw">비밀번호 찾기</a>
      <div class="loginbtnbox loginbtn">로그인</div>
      <div class="loginbtnbox joinbtn" >회원가입</div>
    </div>
  </div>
</template>

<style>
  .login{
    width: 825px;
    height: 830px;
    margin:0 auto;
    margin-top: 200px;
    box-shadow: 0px 0px 15px #bababa;
  }

  .loginbox{
    width: 525px;
    height: 700px;
    margin: 0 auto;
    padding-top: 130px;
  }

  .logo{
    width: 280px;
    margin: 0 auto;
    margin-bottom: 10px;
  }

  .univ {
    color: #222222;
    font-size: 20px;
    font-weight: bold;
    float: right;
  }
  
  .logintitle{
    clear: both;
    color: #336EB4;
    text-align: center;
    font-size: 50px;
    font-weight: bold;
    margin-bottom: 70px;
  }

  .login_id, .login_pw{
    color: #00B6AD;
    font-size: 18px; 
    font-weight: bold;
  }

  .login_id{
    margin-bottom: 50px;
  }

  .login_pw{
    margin-bottom: 5px;
  }

  .loginbox .login_text{
    margin-bottom: 10px;
  }

  .login_id input, .login_pw input{
    width: 520px;
    height: 40px;
    border: none;
    border-bottom: 2px solid #00B6AD;
    font-size: 17px;
  }

  .loginguide, .findpw{
    color: #858585;
    font-weight: bold;
  }

  .loginguide{
    float: left;
  }
  
  .findpw{
    float: right;
  }

  .loginbtnbox{
    clear: both;
    width: 525px;
    height: 65px;
    font-size: 26px;
    font-weight: bold;
    text-align: center;
    line-height: 65px;
  }

  .loginbtnbox:hover{
    cursor: pointer;
  }

  .loginbtn{
    background-color: #00B6AD;
    color: #FFFFFF;
    margin-top: 80px;
  }

  .joinbtn{
    background-color: #EBECF0;
    color: #858585;
    margin-top: 15px;
  }
</style>