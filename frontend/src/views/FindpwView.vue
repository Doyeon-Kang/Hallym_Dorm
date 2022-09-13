<template>
    <div class="findpw">
      <div class="findpwbox">
        <div class="logo">
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
      </div>
    </div>
  </template>
  
  <style scoped lang="less">
  .findpw {
    width: 825px;
    height: 830px;
    margin: 200px auto;
    box-shadow: 0px 0px 15px #bababa;
    color: #222222;
  
    .findpwbox {
      width: 525px;
      height: 700px;
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
  
      .findpwtitle {
        clear: both;
        color: #336EB4;
        text-align: center;
        font-size: 50px;
        font-weight: bold;
        margin-bottom: 70px;
      }
  
      .findpw_id,.findpw_email {
        color: #00B6AD;
        font-size: 18px;
        font-weight: bold;
      }
  
      .findpw_id {
        margin-bottom: 50px;
      }
  
      .findpw_email {
        margin-bottom: 5px;
      }
  
      .findpw_text {
        margin-bottom: 10px;
      }
  
      input {
        width: 520px;
        height: 40px;
        border: none;
        border-bottom: 2px solid #00B6AD;
        font-size: 17px;
      }
  
      .findpwguide{
        color: #858585;
        font-weight: bold;
        float: left;
      }
  
      .findpwbtnbox {
        clear: both;
        width: 525px;
        height: 65px;
        font-size: 26px;
        font-weight: bold;
        text-align: center;
        line-height: 65px;
      }
  
      .findpwbtnbox:hover {
        cursor: pointer;
      }
  
      .findpwbtn {
        background-color: #00B6AD;
        color: #FFFFFF;
        margin-top: 100px;
      }
    }
  }
  </style>