<template>
    <div class="findpw">
      <div class="findpwbox">
        <div class="logo" @click="this.$router.push('/')">
          <img src="@/assets/logo.png" alt="" />
          <img src="@/assets/univ.png" alt="" />
          <div class="univ">| Dormitory</div>
        </div>
        <div class="findpwtitle">비밀번호 찾기</div>
        <div class="findpw_id">
          <div class="findpw_text">아이디</div>
          <input type="text" v-model="id" placeholder="아이디를 입력해주세요.">
        </div>
        <div class="findpw_email">
          <div class="findpw_text">본인 확인 이메일</div>
          <input type="text" v-model="email" placeholder="이메일을 입력해주세요.">
        </div>
        <div class="findpwguide">※가입하신 이메일로 임시 비밀번호를 전송합니다.</div>
        <div class="findpwbtnbox findpwbtn">확인</div>
        <div class="loginbtn" @click="this.$router.push('login')">◀ 로그인 페이지</div>
        <div class="homebtn" @click="this.$router.push('/')">홈페이지 ▶</div>
      </div>
    </div>
  </template>
  
  <style scoped lang="less">
  .findpw {
    width: 625px;
    height: 630px;
    margin: 100px auto;
    box-shadow: 0px 0px 10px #bababa;
    color: #222222;
  
    .findpwbox {
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
  
      .findpwtitle {
        clear: both;
        color: #336EB4;
        text-align: center;
        font-size: 40px;
        font-weight: bold;
        margin-bottom: 60px;
      }
  
      .findpw_id,.findpw_email {
        color: #00B6AD;
        font-size: 17px;
        font-weight: bold;
      }
  
      .findpw_id {
        margin-bottom: 30px;
      }
  
      .findpw_email {
        margin-bottom: 5px;
      }
  
      .findpw_text {
        margin-bottom: 5px;
      }
  
      input {
        width: 396px;
        height: 30px;
        border: none;
        border-bottom: 2px solid #00B6AD;
        font-size: 14px;
      }
  
      .findpwguide{
        color: #858585;
        font-weight: bold;
        font-size: 13px;
        float: left;
      }
  
      .findpwbtnbox {
        clear: both;
        width: 400px;
        height: 50px;
        font-size: 18px;
        font-weight: bold;
        text-align: center;
        line-height: 50px;
      }
  
      .logo:hover, .findpwbtnbox:hover {
        cursor: pointer;
      }
  
      .findpwbtn {
        background-color: #00B6AD;
        color: #FFFFFF;
        margin-top: 70px;
      }
      
      .loginbtn, .homebtn{
          color: #858585;
          font-weight: bold;
          margin-top: 10px;
          font-size: 13px;
        }

        .loginbtn{
          float: left;
        }
        
        .homebtn{
          float: right;
        }

      .homebtn:hover, .loginbtn:hover{
        cursor: pointer;
      }
    }
  }
  </style>