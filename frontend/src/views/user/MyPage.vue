<template>
  <div class="mypage">
    <div class="profile box">
      <div class="profile_box box innerbox">
        <div class="profile_title">내 프로필</div>
        <br />
        <img src="@/assets/profile.png" alt="" />
        <div class="pro_info">
          이름 : 홍길동<br />
          학번 : 20201234<br />
          거주관 : X관 XXX호<br />
        </div>
      </div>
    </div>
    <div class="mywrite box">
      <div class="mypage_subtitle">
        <img src="@/assets/write.png" alt="" />내가 작성한 글
        <div class="more_details" @click="this.$router.push('mypage/myassey')">
          더보기 ▶
        </div>
      </div>
      <div class="mywrite_box box innerbox"></div>
    </div>
    <div class="point box">
      <div class="mypage_subtitle">
        <img src="@/assets/pointplus.png" alt="" />상벌점 내역
        <div class="more_details">합계 : 5점</div>
      </div>
      <div class="point_box box innerbox"></div>
    </div>
    <div class="reservation box">
      <div class="mypage_subtitle">
        <img src="@/assets/reservation.png" alt="" />예약 현황
      </div>
      <div class="reservation_box box innerbox"></div>
    </div>
    <div class="night box">
      <div class="mypage_subtitle">
        <img src="@/assets/night.png" alt="" />외박 신청 현황
        <div class="more_details" @click="this.$router.push('mypage/mysleep')">
          더보기 ▶
        </div>
      </div>
      <div class="night_box box innerbox"></div>
    </div>
  </div>
</template>

<style scoped lang="less">
.mypage {
  margin: 100px auto;
  width: 100%;
  height: 700px;
  img {
    margin-right: 10px;
    height: 20px;
  }
  .box {
    border-radius: 10px;
    background-color: white;
    .innerbox {
      margin: 0 auto;
    }
  }
  .more_details {
    float: right;
    font-size: 16px;
    line-height: 33px;
    margin-right: 25px;
  }
  .more_details:hover {
    cursor: pointer;
  }
  .mypage_subtitle {
    color: white;
    font-weight: bold;
    font-size: 23px;
    margin: 20px 0px 20px 20px;
  }
  .profile {
    background-color: white;
    width: 330px;
    height: 340px;
    float: left;
    margin-bottom: 20px;
    box-shadow: 0px 0px 10px #bababa;
    text-align: center;
    .profile_box {
      margin-top: 40px;
      .profile_title {
        font-weight: bold;
        font-size: 20px;
        margin-bottom: -15px;
      }
      img {
        width: 150px;
        height: 160px;
      }
    }
  }
  .mywrite {
    background-color: #54aead;
    width: 730px;
    height: 340px;
    float: right;
    margin-bottom: 20px;
    .mywrite_box {
      width: 715px;
      height: 262px;
    }
  }
  .point {
    background-color: #648ddd;
    width: 330px;
    height: 340px;
    float: left;
    margin-right: 20px;
    .point_box {
      width: 315px;
      height: 262px;
    }
  }
  .reservation {
    background-color: #00b6ad;
    width: 355px;
    height: 340px;
    float: left;
    margin-right: 20px;
    .reservation_box {
      width: 340px;
      height: 262px;
    }
  }
  .night {
    background-color: #66c6c4;
    width: 355px;
    height: 340px;
    float: left;
    .night_box {
      width: 340px;
      height: 262px;
    }
  }
}
</style>
