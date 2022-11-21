<template>

  <div class="content_container">
    <div class="left_content">
      <div class="notice_box">
        <div class="first_notice">
          <div class="top">
            <div class="board_title">공지사항</div>
            <a href="/community">더보기 ></a>
          </div>

          <div class="item_list">
            <div class="item" v-for="item in communityList_Data" :key="item"
              @click="$router.push({ path: this.$route.path + 'community/view', query: { no: item.no } })">
              <div class="title">[공지] {{ item.title }}</div>
              <div class="date">{{ item.date }}</div>
            </div>
          </div>
        </div>
        <div class="second_notice">
          <div class="top">
            <div class="board_title">사생자치회</div>
            <a href="/community/notice1">더보기 ></a>
          </div>
          <div class="item_list">
            <div class="item" v-for="item in noticeList_Data" :key="item"
              @click="$router.push({ path: this.$route.path + 'community/notice1/view', query: { no: item.no } })">
              <div class="title">[사생] {{ item.title }}</div>
              <div class="date">{{ item.date }}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="first_info_boxes">
        <div class="food_plan">
          <div class="title">학식표</div>
          <div class="content">
            <div class="item" v-for="(item, index) in foodList" :key="index">
              [학식표] {{ item.title }}
            </div>
            <div class="plus"><a href="/">자세히 ></a></div>
          </div>
        </div>
        <div class="shop_box">
          <div class="title">나눔장터</div>
          <div class="content">
            <div class="item" v-for="item in marketList_Data" :key="item"
              @click="$router.push({ path: this.$route.path + 'community/market/view', query: { no: item.id } })">
              [나눔] {{ item.title }}
            </div>
            <div class="plus"><a href="/community/market">자세히 ></a></div>
          </div>
        </div>
      </div>
      <div class="second_info_boxes">
        <div class="calendar_box">
          <div class="title">캘린더</div>
          <div class="content">
            <div class="item" v-for="(item, index) in calendarList" :key="index">
              [캘린더] {{ item.title }}
            </div>
            <div class="plus"><a href="/life">자세히 ></a></div>
          </div>
        </div>
        <div class="lost_box">
          <div class="title">분실물 게시판</div>
          <div class="content">
            <div class="item" v-for="item in lostList_Data" :key="item"
              @click="$router.push({ path: this.$route.path + 'community/lost/view', query: { no: item.id } })">
              [분실물] {{ item.title }}
            </div>
            <div class="plus"><a href="/community/lost">자세히 ></a></div>
          </div>
        </div>
      </div>
    </div>
    <div class="right_content">
      <div v-if="!loggedIn" class="login_box">
        <div class="top">
          <img src="@/assets/logo.png" alt="" />
          <img src="@/assets/univ.png" alt="" />
          <div class="univ">Dormitory</div>
        </div>
        <div class="description">
          더 많은 서비스를 이용하려면 로그인 하세요.
        </div>
        <div class="login_btn" @click="this.$router.push('login')">로그인</div>
        <div class="join"><a href="/join">회원가입</a></div>
      </div>
      <div v-else class="login_box login">
        <div class="top login">
          {{ user.name }} ({{ user.studentno }}) | {{ this.userinfo.res_fac }}관
        </div>
        <div class="box">
          <div class="title">
            <span>마이페이지</span>
            <span class="plus" @click="this.$router.push('/mypage')">+</span>
          </div>
          <div class="list">
            <span class="mint">상점/벌점 : </span>
            <span class="black">{{ this.userinfo.plusPoint }} / {{ this.userinfo.minusPoint }}</span>
          </div>
          <div class="list">
            <span class="mint">최근 외박 신청일자 : </span>
            <span class="black">{{ this.lastSleepout }}</span>
          </div>
          <div class="list">
            <span class="mint">최근 상담 신청일자 : </span>
            <span class="black">{{ this.lastConsult }}</span>
          </div>
        </div>
      </div>
      <div class="short_box">
        <table>
          <thead>
            <tr>
              <th>바로가기</th>
            </tr>
            <tr>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <a href="/reserve/in">
                  <img src="@/assets/입사신청.png" alt="입사신청" />
                  <span>입사신청</span>
                </a>
              </td>
              <td>
                <a href="/repair">
                  <img src="@/assets/불편수리.png" alt="불편수리" />
                  <span>불편/수리</span>
                </a>
              </td>
            </tr>
            <tr>
              <td>
                <a href="/intro">
                  <img src="@/assets/생활안내.png" alt="생활안내" />
                  <span>생활관소개</span>
                </a>
              </td>
              <td>
                <a href="/life">
                  <img class="small" src="@/assets/생활일정.png" alt="생활일정" />
                  <span>생활일정</span>
                </a>
              </td>
            </tr>
            <tr>
              <td>
                <a href="/community/faq">
                  <img src="@/assets/FAQ.png" alt="FAQ" />
                  <span>FAQ</span>
                </a>
              </td>
              <td>
                <a href="/intro/location">
                  <img class="small" src="@/assets/오시는길.png" alt="오시는길" />
                  <span>오시는길</span>
                </a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import UserInfoDataService from "@/services/UserInfoDataService";
import NoticeDataService from '@/services/NoticeDataService';
import StoreDataService from "@/services/StoreDataService";
import LostDataService from "@/services/LostDataService";
import ApplyConsultDataService from "@/services/ApplyConsultDataService";

export default {
  name: "HomeView",
  data() {
    return {
      communityList: [],
      noticeList: [],
      foodList: [
        { title: "오늘의 점심 (2022.11.10)" },
        { title: "오늘의 점심 (2022.11.12)" },
        { title: "오늘의 점심 (2022.11.13)" },],
      marketList: [],
      calendarList: [
        { title: "일정 안내 1" },
        { title: "일정 안내 2" },
        { title: "일정 안내 3" },
      ],
      lostList: [],
      userinfo: [],
      sleepout_item: [],
      lastSleepout: '-',
      lastConsult: '-',
    };
  },
  components: {},
  created() {
    this.init();
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn
    },
    user() {
      return this.$store.state.auth.user
    },
    communityList_Data() {
            return this.communityList.slice(-5);
        },
        noticeList_Data() {
            return this.noticeList.slice(-5);
        },
        marketList_Data() {
            return this.marketList.slice(-3);
        },
        lostList_Data() {
            return this.lostList.slice(-3);
        },
  },
  watch: {
    loggedIn() {
      return this.loggedIn;
    }
  },
  methods: {
    init() {
      // 공지사항
      NoticeDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id // 번호
          list[i].title = res[i].title // 글제목
          list[i].date = res[i].date // 작성일
          list[i].writer = res[i].writer_name // 작성자
          list[i].views = res[i].views // 조회수
        }
        this.communityList = list
      })
      // 사생자치회
      NoticeDataService.getAllNotice1().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id // 번호
          list[i].title = res[i].title // 글제목
          list[i].date = res[i].date // 작성일
          list[i].writer = res[i].writer_name // 작성자
          list[i].views = res[i].views // 조회수
        }
        this.noticeList = list
      })
      // 나눔장터
      StoreDataService.getAll().then(resolveData => {
        let res = resolveData.data

        for (let i = 0; i < res.length; i++) {
          this.marketList.push({})
          this.marketList[i].id = res[i].id
          this.marketList[i].title = res[i].title // 글제목
          this.marketList[i].date = res[i].date // 작성자
          this.marketList[i].writer = res[i].writer_name // 작성일
          this.marketList[i].views = res[i].views // 조회수
          // 나눔장터 사진
          // StorePhotoService.getAll(this.marketList[i].id).then(photo_data => {
          //   this.marketList[i].photo = photo_data.data[0].url
          // })
        }
      })
      // 분실물
      LostDataService.getAll().then(resolveData => {
        let res = resolveData.data

        for (let i = 0; i < res.length; i++) {
          this.lostList.push({})
          this.lostList[i].id = res[i].id
          this.lostList[i].title = res[i].title // 글제목
          this.lostList[i].date = res[i].date // 작성자
          this.lostList[i].writer = res[i].writer_name // 작성일
          this.lostList[i].views = res[i].views // 조회수
          // 분실물 사진
          // LostPhotoService.getAll(this.lostList[i].id).then(photo_data => {
          //   this.lostList[i].photo = photo_data.data[0].url
          // })
        }
      })

      if (this.loggedIn) {
        // 사용자 정보 가져오기
        UserInfoDataService.getInfo(this.user.studentno).then(item => {
          let res = item.data
          let getinfo = {}

          getinfo.english_name = res.english_name
          getinfo.chinese_name = res.chinese_name
          getinfo.grade = res.grade
          getinfo.gender = res.gender
          getinfo.nationality = res.nationality
          getinfo.department = res.department
          getinfo.major = res.major
          getinfo.student_status = res.student_status
          getinfo.phone = res.phone
          getinfo.address = res.address
          getinfo.guardian_name = res.guardian_name
          getinfo.guardian_relation = res.guardian_relation
          getinfo.guardian_phone = res.guardian_phone
          getinfo.landline = res.landline
          getinfo.plusPoint = res.plusPoint
          getinfo.minusPoint = res.minusPoint
          getinfo.res_fac = res.res_fac
          getinfo.res_room = res.res_room

          this.userinfo = getinfo
        })

        // 사용자 외박 정보 가져오기
        UserInfoDataService.getSleepout(this.user.studentno).then(sleepooutData => {
          let res = sleepooutData.data
          let list = []

        for (let i = 0; i < res.length; i++) {
          list.push({})
          list[i].no = res[i].id
          list[i].reason = res[i].reason
          list[i].outdate = res[i].date_sleepout
          list[i].date = res[i].date
          if (res[i].approved) {
            list[i].approved = "승인 완료"
          } else {
            list[i].approved = "승인 대기"
          }
        }
        
        this.lastSleepout = list[res.length - 1].date
      })

        // 사용자 상담 정보 가져오기
        ApplyConsultDataService.getAll().then(consultData => {
          let res = consultData.data

          for (let i = 0; i < res.length; i++) {
            if (this.user.studentno == res[i].studentNo) {
              this.lastConsult = res[i].date.slice(0, 10)
            }
          }
        })
      }
    }
  }
};
</script>

<style scoped lang="less">
.content_container {
  width: 1080px;
  margin: 30px auto;
  display: flex;

  .left_content {
    width: 78%;
    margin-right: 20px;

    .notice_box {
      display: flex;
      box-shadow: 2px 5px 20px rgba(133, 133, 133, 0.25);
      border-radius: 10px;
      height: 300px;
      padding: 20px 0;

      .first_notice {
        width: 50%;
        border-right: 1px solid #858585;

        .top {
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding-right: 20px;

          .board_title {
            font-weight: 700;
            font-size: 24px;
            color: #336eb4;
            margin-left: 20px;
          }

          a {
            font-size: 12px;
            color: #858585;

            &:hover {
              cursor: pointer;
              font-weight: 600;
            }
          }
        }

        .item_list {
          padding: 10px 20px;

          .item {
            display: flex;
            justify-content: space-between;
            padding: 10px 12px;
            margin-top: 8px;
            border: 1px solid #c0c0c0;
            font-size: 14px;

            &:first-child {
              margin-top: 0px;
            }

            &:hover {
              cursor: pointer;

              & .title {
                color: #336eb4;
              }
            }

            .title {
              color: #222222;
            }

            .date {
              color: #858585;
            }
          }
        }
      }

      .second_notice {
        padding-left: 20px;
        width: 50%;

        .top {
          display: flex;
          justify-content: space-between;
          align-items: center;
          padding-right: 20px;

          .board_title {
            font-weight: 700;
            font-size: 24px;
            color: #00b6ad;
          }

          a {
            font-size: 12px;
            color: #858585;

            &:hover {
              cursor: pointer;
              font-weight: 600;
            }
          }
        }

        .item_list {
          padding: 10px 20px 10px 0;

          .item {
            display: flex;
            justify-content: space-between;
            padding: 10px 12px;
            margin-top: 8px;
            border: 1px solid #c0c0c0;
            font-size: 14px;

            &:first-child {
              margin-top: 0px;
            }

            &:hover {
              cursor: pointer;

              & .title {
                color: #00b6ad;
              }
            }

            .title {
              color: #222222;
            }

            .date {
              color: #858585;
            }
          }
        }
      }
    }

    .first_info_boxes {
      display: flex;
      justify-content: space-between;
      height: 240px;
      margin-top: 20px;

      .food_plan {
        position: relative;
        background-color: white;
        width: 48%;
        box-shadow: 2px 5px 20px 2px rgba(133, 133, 133, 0.25);
        border-radius: 10px;

        .title {
          background-color: rgba(227, 122, 116, 0.5);
          border-radius: 10px 10px 0 0;
          padding: 5px;
          text-align: center;
          font-size: 16px;
          color: #222222;
          font-weight: 600;
        }

        .content {
          padding: 10px;

          .item {
            border: solid 1px #e37a74;
            border-radius: 10px;
            margin: 10px;
            padding: 10px 15px;
            font-size: 13px;
            color: #858585;

            &:hover {
              cursor: pointer;
              color: #e37a74;
            }
          }

          .plus {
            position: absolute;
            right: 20px;
            bottom: 15px;
            font-size: 12px;
            float: right;

            a {
              color: #e37a74;
              font-weight: 700;
            }
          }
        }
      }

      .shop_box {
        position: relative;
        background-color: white;
        width: 48%;
        box-shadow: 2px 5px 20px 2px rgba(133, 133, 133, 0.25);
        border-radius: 10px;

        .title {
          background-color: rgba(250, 222, 133, 0.5);
          border-radius: 10px 10px 0 0;
          padding: 5px;
          text-align: center;
          font-size: 16px;
          color: #222222;
          font-weight: 600;
        }

        .content {
          padding: 10px;

          .item {
            border: solid 1px #fade85;
            border-radius: 10px;
            margin: 10px;
            padding: 10px 15px;
            font-size: 13px;
            color: #858585;

            &:hover {
              cursor: pointer;
              color: #fade85;
            }
          }

          .plus {
            position: absolute;
            right: 20px;
            bottom: 15px;
            font-size: 12px;
            float: right;

            a {
              color: #fade85;
              font-weight: 700;
            }
          }
        }
      }
    }

    .second_info_boxes {
      display: flex;
      justify-content: space-between;
      height: 240px;
      margin-top: 20px;

      .calendar_box {
        position: relative;
        background-color: white;
        width: 48%;
        box-shadow: 2px 5px 20px 2px rgba(133, 133, 133, 0.25);
        border-radius: 10px;

        .title {
          background-color: rgba(135, 148, 242, 0.5);
          border-radius: 10px 10px 0 0;
          padding: 5px;
          text-align: center;
          font-size: 16px;
          color: #222222;
          font-weight: 600;
        }

        .content {
          padding: 10px;

          .item {
            border: solid 1px #8794f2;
            border-radius: 10px;
            margin: 10px;
            padding: 10px 15px;
            font-size: 13px;
            color: #858585;

            &:hover {
              cursor: pointer;
              color: #8794f2;
            }
          }

          .plus {
            position: absolute;
            right: 20px;
            bottom: 15px;
            font-size: 12px;
            float: right;

            a {
              color: #8794f2;
              font-weight: 700;
            }
          }
        }
      }

      .lost_box {
        position: relative;
        background-color: white;
        width: 48%;
        box-shadow: 2px 5px 20px 2px rgba(133, 133, 133, 0.25);
        border-radius: 10px;

        .title {
          background-color: rgba(145, 224, 177, 0.5);
          border-radius: 10px 10px 0 0;
          padding: 5px;
          text-align: center;
          font-size: 16px;
          color: #222222;
          font-weight: 600;
        }

        .content {
          padding: 10px;

          .item {
            border: solid 1px #91e0b1;
            border-radius: 10px;
            margin: 10px;
            padding: 10px 15px;
            font-size: 13px;
            color: #858585;

            &:hover {
              cursor: pointer;
              color: #91e0b1;
            }
          }

          .plus {
            position: absolute;
            right: 20px;
            bottom: 15px;
            font-size: 12px;
            float: right;

            a {
              color: #91e0b1;
              font-weight: 700;
            }
          }
        }
      }
    }
  }

  .right_content {
    width: 28%;

    .login_box {
      height: 210px;
      box-shadow: 2px 5px 20px rgba(133, 133, 133, 0.25);
      border-radius: 10px;
      padding: 30px 30px 0px;

      .top {
        display: flex;
        align-items: center;

        .univ {
          color: #222222;
          font-weight: 700;
          // &::before {
          //   content: "|";
          //   color: #858585;
          //   font-weight: 500;
          // }
        }
      }

      .description {
        color: #858585;
        text-align: center;
        font-size: 12px;
        margin-top: 10px;
      }

      .login_btn {
        background-color: #54aead;
        text-align: center;
        color: white;
        font-weight: 800;
        font-size: 24px;
        padding: 16px;
        margin-top: 20px;
        border-radius: 10px;

        &:hover {
          cursor: pointer;
        }
      }

      .join {
        text-align: end;
        margin-top: 5px;

        a {
          color: #858585;
          font-size: 12px;
          font-weight: 600;
        }
      }
    }

    .short_box {
      height: 400px;
      background-color: #336eb4;
      box-shadow: 2px 5px 20px rgba(133, 133, 133, 0.25);
      margin-top: 30px;
      padding: 14px 14px 24px;

      table {
        font-weight: 600;
        margin: 0;
        width: 280px;
        height: 100%;
        color: white;

        thead {
          float: left;
          font-size: 20px;
        }

        tbody {
          tr {
            td {
              width: 50%;
              text-align: center;
              border: 1px solid transparent;

              img {
                display: block;
                width: 70px;
                margin: auto auto 10px;

                &.small {
                  width: 60px;
                  margin-bottom: 15px;
                }
              }

              span {
                color: white;
              }
            }
          }
        }
      }
    }
  }
}
</style>

<!--로그인 한 경우-->
<style lang="less">
.right_content {
  .login_box.login {
    padding: 20px 20px 0 !important;
    height: 260px !important;

    .top.login {
      font-weight: 700;
      font-size: 18px;
      color: #222;
    }

    .box {
      margin-top: 8px;
      background-color: #54AEAD;
      padding: 14px 10px;
      border-radius: 10px;
      ;

      .title {
        color: #fff;
        font-weight: 700;
        font-size: 22px;
        line-height: 18px;
        display: flex;
        justify-content: space-between;
        padding: 10px 5px;

        .plus {
          font-size: 34px;

          &:hover {
            cursor: pointer;
          }
        }
      }

      .list {
        background-color: #fff;
        margin-top: 5px;
        padding: 10px;
        border-radius: 10px;
        font-size: 14px;

        .mint {
          color: #54AEAD;
          font-weight: 600;
        }
      }
    }
  }
}
</style>