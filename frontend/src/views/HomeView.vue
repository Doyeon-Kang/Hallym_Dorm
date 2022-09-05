<template>
  <div class="content_container">
    <div class="left_content">
      <div class="notice_box">
        <div class="first_notice">
          <div class="top">
            <div class="board_title">공지사항</div>
            <a href="/life/community">더보기 ></a>
          </div>

          <div class="item_list">
            <div
              class="item"
              v-for="(item, index) in notice1_item"
              :key="index"
            >
              <div class="title">{{ item.title }}</div>
              <div class="date">{{ item.date }}</div>
            </div>
          </div>
        </div>
        <div class="second_notice">
          <div class="top">
            <div class="board_title">사생자치회</div>
            <a href="/life/community/notice1">더보기 ></a>
          </div>
          <div class="item_list">
            <div
              class="item"
              v-for="(item, index) in notice2_item"
              :key="index"
            >
              <div class="title">{{ item.title }}</div>
              <div class="date">{{ item.date }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="right_content">
      <div class="login_box">
        <div class="top">
          <img src="@/assets/logo.png" alt="" />
          <img src="@/assets/univ.png" alt="" />
          <div class="univ">Dormitory</div>
        </div>
        <div class="description">
          더 많은 서비스를 이용하려면 로그인 하세요.
        </div>
        <div class="login_btn" @click="this.$router.push('login')">로그인</div>
        <div class="join"><a href="/login/join">회원가입</a></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      notice1_item: [
        {
          title: "title1",
          date: "2022.07.22",
        },
        {
          title: "title2",
          date: "2022.07.22",
        },
        {
          title: "title3",
          date: "2022.07.22",
        },
        {
          title: "title4",
          date: "2022.07.22",
        },
        {
          title: "title5",
          date: "2022.07.22",
        },
      ],
      notice2_item: [
        {
          title: "title1",
          date: "2022.07.22",
        },
        {
          title: "title2",
          date: "2022.07.22",
        },
        {
          title: "title3",
          date: "2022.07.22",
        },
        {
          title: "title4",
          date: "2022.07.22",
        },
        {
          title: "title5",
          date: "2022.07.22",
        },
      ],
    };
  },
  components: {},
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
  }
}
</style>
