<template>
    <div class="mypage">
        <div class="profile box">
            <div class="profile_box box innerbox">
                <div class="profile_title">내 프로필</div><br>
                <img src="@/assets/profile.png" alt="" />
                <div class="pro_info">
                    이름 : 홍길동<br>
                    학번 : 20201234<br>
                    거주관 : X관 XXX호<br>
                </div>
            </div>
        </div>
        <div class="mywrite box">
            <div class="mypage_subtitle">
                <img src="@/assets/write.png" alt="" />내가 작성한 글
                <div class="more_details" @click="this.$router.push('community')">더보기 ▶</div>
            </div>
            <div class="mywrite_box box innerbox">
                <ul>
                    <li>[분실물] 공책 잃어 버리신 분</li>
                    <li>[나눔장터] 샴푸 필요하신 분</li>
                    <li>[나눔장터] 필기구 남는 거 나눔합니다</li>
                    <li>[분실물] 식당에서 보조 배터리 잃어 버리신 분</li>
                    <li>[나눔장터] 쿠키 드실 분 있나요?</li>
                    <li>[분실물] 가방 두고 가신 분</li>
                </ul>
            </div>
        </div>
        <div class="point box">
            <div class="mypage_subtitle">
                <img src="@/assets/pointplus.png" alt="" />상벌점 내역
                <div class="more_details">합계 : 5점</div>
            </div>
            <div class="point_box box innerbox">
                <ul>
                    <li><div class="pointbox point_plus">+3점</div>프로그램 참가 (2022.08.18)</li>
                    <li><div class="pointbox point_minus">-3점</div>무단 외박 (2022.07.01)</li>
                    <li><div class="pointbox point_plus">+3점</div>프로그램 참가 (2022.05.21)</li>
                    <li><div class="pointbox point_plus">+3점</div>프로그램 참가 (2022.05.10)</li>
                </ul>
            </div>
        </div>
        <div class="reservation box">
            <div class="mypage_subtitle">
                <img src="@/assets/reservation.png" alt="" />예약 현황
            </div>
            <div class="reservation_box box innerbox">
                <div class="reservation_minibox">
                    <div class="reservationtitle">
                        8관 스터디룸 예약 현황
                        <div class="more_details" @click="this.$router.push('')">더보기 ▶</div>
                    </div>
                    <div class="studyroom">
                        <ul>
                            <li><div class="usebox">이용 중</div></li>
                            <li><div class="usebox">이용 완료</div></li>
                            <li><div class="usebox">이용 완료</div></li>
                        </ul>
                    </div>
                </div>
                <div class="reservation_minibox">
                    <div class="reservationtitle">
                        상담 신청
                        <div class="more_details" @click="this.$router.push('')">더보기 ▶</div>
                    </div>
                    <div class="counseling">
                        <ul>
                            <li><div class="usebox">이용 중</div></li>
                            <li><div class="usebox">이용 완료</div></li>
                            <li><div class="usebox">이용 완료</div></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="night box">
            <div class="mypage_subtitle">
                <img src="@/assets/night.png" alt="" />외박 신청 현황
                <div class="more_details" @click="this.$router.push('sleep')">더보기 ▶</div>
            </div>
            <div class="night_box box innerbox"></div>
        </div>
    </div>
  </template>

<style scoped lang="less">
.mypage{
    margin: 100px auto;
    width: 100%;
    height: 700px;
    img{
        margin-right: 10px;
        height: 20px;
    }
    .box{
        border-radius: 10px;
        background-color: white;
        .innerbox{
            margin: 0 auto;
        }
    }
    .more_details{
        float: right;
        font-size: 16px;
        line-height: 33px;
        margin-right: 25px;
    }
    .more_details:hover {
        cursor: pointer;
    }
    .mypage_subtitle{
        color: white;
        font-weight: bold;
        font-size: 23px;
        margin: 20px 0px 20px 20px;
    }
    .profile{
        background-color: white;
        width: 330px;
        height: 340px;
        float: left;
        margin-bottom: 20px;
        box-shadow: 0px 0px 10px #bababa;
        text-align: center;
        .profile_box{
            margin-top: 40px;
            .profile_title{
                font-weight: bold;
                font-size: 20px;
                margin-bottom: -15px;
            }
            img{
                width: 150px;
                height: 160px;
            }
        }
    }
    .mywrite{
        background-color: #54AEAD;
        width: 730px;
        height: 340px;
        float: right;
        margin-bottom: 20px;
        .mywrite_box{
            width: 715px;
            height: 260px;
            ul{
                padding-top: 5px;
                list-style: none;
                li{
                    margin-left: 15px;
                    margin-top: 15px;
                    font-size: 18px;
                }
            }
        }
    }
    .point{
        background-color: #648DDD;
        width: 330px;
        height: 340px;
        float: left;
        margin-right: 20px;
        .point_box{
            width: 315px;
            height: 260px;
            ul{
                padding-top: 10px;
                list-style: none;
                li{
                    margin-top: 10px;
                    margin-left: 10px;
                    line-height: 25px;
                    div{
                        display: inline-block;
                        text-align: center;
                        color: white;
                        width: 50px;
                        height: 25px;
                        margin-right: 10px;
                        border-radius: 8px;
                    }
                    .point_plus{
                        background-color: #648DDD;
                    }
                    .point_minus{
                        background-color: #DD6464;
                    }
                }
            }
        }
        
    }
    .reservation{
        background-color: #00B6AD;
        width: 355px;
        height: 340px;
        float: left;
        margin-right: 20px;
        .reservation_box{
            width: 340px;
            height: 260px;
            .reservation_minibox{
                padding-top: 10px;
                margin: 0 15px;
                .reservationtitle{
                    width: 100%;
                    height: 40px;
                    color: #00B6AD;
                    font-size: 19px;
                    font-weight: bold;
                    line-height: 30px;
                    float: left;
                    .more_details{
                        font-size: 14px;
                        margin: 0;
                        float: right;
                    }
                }
            }
        }
    }
    .night{
        background-color: #66C6C4;
        width: 355px;
        height: 340px;
        float: left;
        .night_box{
            width: 340px;
            height: 260px;
        }
    }
}
</style>