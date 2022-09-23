<template>
  <div>
    <table class="faq_title">
      <tbody>
        <tr>
          <td :class="{ active: id === 1 }" @click="idChange(1)">
            입사 시 필요한 물품/서류
          </td>
          <td :class="{ active: id === 2 }" @click="idChange(2)">
            기숙사 생활 관련
          </td>
          <td :class="{ active: id === 3 }" @click="idChange(3)">
            비용 관련 비용 관련
          </td>
        </tr>
        <tr>
          <td :class="{ active: id === 4 }" @click="idChange(4)">
            수리 및 시설 관련
          </td>
          <td :class="{ active: id === 5 }" @click="idChange(5)">
            선발/입사/퇴사 관련
          </td>
          <td :class="{ active: id === 6 }" @click="idChange(6)">기타</td>
        </tr>
      </tbody>
    </table>
    <table class="faq_contents">
      <tbody v-for="(content, index) in showContent" :key="index">
        <tr class="question">
          <td class="icon">Q</td>
          <td class="title">{{ content.title }}</td>
          <td
            v-if="!content.open"
            class="show_btn"
            @click="content.open = true"
          >
            펼치기 &gt;
          </td>
          <td
            v-else-if="content.open"
            class="show_btn"
            @click="content.open = false"
          >
            접기 &lt;
          </td>
        </tr>
        <tr v-show="content.open" class="answer">
          <td class="icon">A</td>
          <td class="content" colspan="2">{{ content.answer }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: 1,
      showContent: "",
      firstContent: [
        {
          title: "학생생활관 입사서류 등록은 어떻게 하나요?",
          answer:
            '학생생활관에 입사한 학생이라면 누구나 입사서류(결핵진단서)를 안내된 기간내에 등록해야 하며,미 등록 시 기간에 따라 벌점이 부과 됩니다. \n1. 등록기간: 선발받은 시점부터 10일 내 입사서류 등록- 학기 개강 전 입사가 확정된 학생은 입실 전인 개강일까지 등록 완료 2. 등록방법: 통합정보시스템 접속 후 일반행정→학생생활관 →사생정보 →학기중(신입생은 별도의 사이트 접속)- 개인정보수집 및 활용 동의서  동의 클릭 후 학기중 신청하기 클릭- 새로운 창의 아래에 "납부확인 및 입사서류 등록" 클릭- 또다른 새로운 창이 뜨면, 학생생활관 환불 안내 동의 클릭 후 결핵진단서 등록의 "추가" 클릭하고"등록" 클릭하면 완료 - 파일 첨부여부 확인은 파일첨부 옆의 네모박스에 글씨들이 기재되어 있으면  첨부 완료 됨.※ 등록한 입사서류 변경 시 "삭제" 클릭 후 다시 위의 절차에 따라 다시 진행 \n3.  벌점부과: 안내된 기간 내까지 입사서류 미등록 시 벌점 3점 부과 하며 이후 15일 간격으로 벌점 추가 부과하며, 이후  퇴사 조치 및 다음학기 입사 제외 될 수 있음. ※ 서류제출 이후 추가 벌점은 없으나, 이전에 부과된 벌점은 삭제되지 않음.',
          open: true,
        },
        {
          title: "세탁물이 더럽게 나온 경우 어떻게 대처해야하나요?",
          answer:
            "통합정보시스템(Interent Explore를 이용) https://was1.hallym.ac.kr:8081/HLMS/main/LoginPage.do에 들어가서 일반행정 → 학생생활관 → 사생정보 → 불편/수리요청에 고장및수리신고 요청은 수리로 선택하여 신청하시면 됩니다. ",
          open: false,
        },
        {
          title: "수리신청은 어떻게 하나요?",
          answer:
            "통합정보시스템(Interent Explore를 이용) https://was1.hallym.ac.kr:8081/HLMS/main/LoginPage.do에 들어가서 일반행정 → 학생생활관 → 사생정보 → 불편/수리요청에 고장및수리신고 요청은 수리로 선택하여 신청하시면 됩니다. ",
          open: false,
        },
      ],
      secondContent: [
        {
          title: "학생생활관 호실별 전화 이용 방법은? ",
          answer: "",
          open: false,
        },
        {
          title: "학내에서 구급약품 필요시 어디서 받나요? ",
          answer: "",
          open: false,
        },
        {
          title: "생활관내 무선인터넷 사용은 어떻게 하나요?",
          answer: "",
          open: false,
        },
        {
          title: "폐문 후, 학생생활관 출입이 가능한가요? ",
          answer: "",
          open: false,
        },
        {
          title: "외박신청은 어떻게 하나요?(자율기숙사 포함) ",
          answer: "",
          open: false,
        },
      ],
      thirdContent: [
        {
          title: "기숙사비에 자치회비는 무엇인가요? ",
          answer: "",
          open: false,
        },
        {
          title: "기숙사비에 식비 포함인가요? ",
          answer: "",
          open: false,
        },
      ],
      fourthContent: [
        {
          title: "벽지가 젖어있는데 어떻게 해야 하나요?",
          answer:
            "여름에서 가을로 계절이 넘어가는 시점결로 현상으로 인하여 벽지가 우는 문제가 발생되고 있습니다. 사생실의 주기적인 환기와 환절기 때 각 방 보일러 가동으로 적정한 실내 온도 및 환경을 유지해야합니다. 점검이 필요한 경우는 관 관리사감실로 문의 해 주시기 바랍니다.",
          open: false,
        },
        {
          title: "세탁물이 더럽게 나온 경우 어떻게 대처해야하나요?",
          answer:
            "거주하는 관의 관리사감실에 신고합니다. 오염된 세탁물에 대해 “오염세탁물 사후처리 정책”에 따라, 관리사감실에서 세탁 비용을 돌려받을 수 있습니다. ",
          open: false,
        },
        {
          title: "수리신청은 어떻게 하나요?",
          answer:
            "통합정보시스템(Interent Explore를 이용) https://was1.hallym.ac.kr:8081/HLMS/main/LoginPage.do에 들어가서 일반행정 → 학생생활관 → 사생정보 → 불편/수리요청에 고장및수리신고 요청은 수리로 선택하여 신청하시면 됩니다. ",
          open: false,
        },
      ],
      fifthContent: [
        {
          title: "선발 제외 대상자란? ",
          answer: "",
          open: false,
        },
        {
          title: "퇴사신청(사용기간 조정 포함)은 어떻게 하나요?",
          answer:
            "거주하는 관의 관리사감실에 신고합니다. 오염된 세탁물에 대해 “오염세탁물 사후처리 정책”에 따라, 관리사감실에서 세탁 비용을 돌려받을 수 있습니다. ",
          open: false,
        },
      ],
      sixthContent: [
        {
          title: "연말연시 시설에 따른 특별방역 강화 조치 안내 ",
          answer: "",
          open: false,
        },
        {
          title: "생활관 홈페이지 회원가입해야 하나요? ",
          answer: "",
          open: false,
        },
        {
          title: "사감단(지도사감, 보조사감)과 사생위원단의 차이는 무엇인가요?",
          answer: "",
          open: false,
        },
      ],
    };
  },
  methods: {
    idChange(id) {
      this.id = id;
    },
  },
  created() {
    this.showContent = this.firstContent;
  },
  watch: {
    id() {
      if (this.id === 1) {
        this.showContent = this.firstContent;
      } else if (this.id === 2) {
        this.showContent = this.secondContent;
      } else if (this.id === 3) {
        this.showContent = this.thirdContent;
      } else if (this.id === 4) {
        this.showContent = this.fourthContent;
      } else if (this.id === 5) {
        this.showContent = this.fifthContent;
      } else if (this.id === 6) {
        this.showContent = this.sixthContent;
      }
    },
  },
};
</script>

<style lang="less" scoped>
.faq_title {
  background-color: #336eb4;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  width: 100%;
  tr {
    td {
      width: 33%;
      text-align: center;
      color: #fff;
      font-size: 18px;
      font-weight: 600;
      padding: 16px 0;
      border: 1px solid #fff;
      cursor: pointer;
      &.active {
        background-color: #629ce1;
      }
    }
  }
}
.faq_contents {
  margin-top: 30px;
  tbody {
    &::before {
      content: "";
      display: block;
      height: 6px;
    }
    .question {
      .icon {
        background-color: #336eb4;
        color: #fff;
        font-size: 24px;
        font-weight: 700;
        text-align: center;
        padding: 0px 20px;
      }
      .title {
        width: 85%;
        color: #222;
        font-weight: 700;
        font-size: 20px;
        padding: 14px 20px;
      }
      .show_btn {
        width: 10%;
        color: #336eb4;
        font-weight: 600;
        text-align: end;
        &:hover {
          cursor: pointer;
        }
      }
    }
    .answer {
      background-color: #d9d9d9;
      .icon {
        color: #fff;
        font-size: 28px;
        font-weight: 700;
        padding: 5px 20px 20px;
        text-align: center;
        vertical-align: text-top;
      }
      .content {
        color: #222;
        padding: 20px;
      }
    }
  }
}
</style>
