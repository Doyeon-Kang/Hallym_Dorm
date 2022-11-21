<template>
  <div class="wrapper">
    <div class="left_container">
      <SidebarCom :pageName="pageName" :listItem="side"></SidebarCom>
    </div>
    <div class="right_container">
      <PageTitle :title="title"></PageTitle>
      <BoardList
        v-if="$route.name === 'community'"
        :listItem="communityList" 
        :listTitle="communityTitle"
      ></BoardList>
      <BoardList
        v-else-if="$route.name === 'notice1'"
        :listItem="noticeList"
        :listTitle="noticeTitle"
      ></BoardList>
      <BoardList
        v-else-if="$route.name === 'data'"
        :listItem="dataList"
        :listTitle="dataTitle"
      ></BoardList>
      <FaqQuestion v-else-if="$route.name === 'faq'"> </FaqQuestion>
      <BoardList
        v-else-if="$route.name === 'repair'"
        :listItem="repairList"
        :listTitle="repairTitle"
      ></BoardList>
      <PhotoBoard
        v-else-if="$route.name === 'market'"
        :photoList="marketList"
      ></PhotoBoard>
      <PhotoBoard
        v-else-if="$route.name === 'lost'"
        :photoList="lostList"
      ></PhotoBoard>
      <div v-else-if="$route.name === 'article'">
        <CreateArticle ></CreateArticle>
      </div>
      <div v-else-if="$route.name === 'modify-article'">
        <ModifyArticle></ModifyArticle>
      </div>
      <!--로그인 버튼-->
      <div v-if="$route.name !== 'faq' && $route.name !== 'article' && $route.name !== 'modify-article' && loggedIn"> 
        <a v-show= "this.role === 'user' && ($route.name === 'market' || $route.name === 'lost')" class="create_btn" href="/community/create-article">글쓰기</a>
        <a v-show= "this.role === 'member' && ($route.name === 'repair' || $route.name === 'market' || $route.name === 'lost')" class="create_btn" href="/community/create-article">글쓰기</a>
        <a v-show= "this.role === 'admin'" class="create_btn" href="/community/create-article">글쓰기</a>
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from "@/components/PageTitle.vue";
import SidebarCom from "../../components/SidebarCom.vue";
import BoardList from "../../components/BoardList.vue";
import FaqQuestion from "../../components/FaqQuestion.vue";
import PhotoBoard from "@/components/PhotoBoard.vue";
import CreateArticle from "@/components/CreateArticle.vue"
import NoticeDataService from "@/services/NoticeDataService"
import NewsDataService from "@/services/NewsDataService";
import RepairDataService from "@/services/RepairDataService"
import StoreDataService from "@/services/StoreDataService";
import StorePhotoService from "@/services/StorePhotoService";
import StoreCommentService from "@/services/StoreCommentService";
import LostDataService from "@/services/LostDataService";
import LostPhotoService from "@/services/LostPhotoService";
import LostCommentService from "@/services/LostCommentService";
import ModifyArticle from '../../components/ModifyArticle.vue';

export default {
  data() {
    return {
      title: "String",
      pageName: "게시판",
      // 사이드바 정보
      side: [
        {
          title: "공지사항",
          path: "/community",
          semi: true,
          semiTitle: [
            { title: "학생기숙사", path: "/community", active: true },
            { title: "사생자치회", path: "/community/notice1", active: false },
          ],
        },
        { title: "서식자료실", path: "/community/data" },
        { title: "FAQ", path: "/community/faq" },
        { title: "불편/수리 요청", path: "/community/repair" },
        { title: "나눔 장터", path: "/community/market" },
        { title: "분실물", path: "/community/lost" },
      ],
      // 게시판 정보
      communityTitle: ["번호", "글제목", "작성일", "작성자", "조회수"],
      communityList: [],
      noticeTitle: ["번호", "글제목", "작성일", "작성자", "조회수", ""],
      noticeList: [],
      dataTitle: ["번호", "글제목", "작성일", "작성자", "조회수", ""],
      dataList: [],
      faqTitle: ["번호", "글제목", "진행상태", "처리일자"],
      faqList: [{}],
      repairTitle: ["번호", "분야", "글제목", "작성일자", "작성자", "진행상태"],
      repairList: [],
      marketList: [],
      lostList: [],
      role: ""
    };
  },
  components: {
    PageTitle,
    SidebarCom,
    BoardList,
    FaqQuestion,
    PhotoBoard,
    CreateArticle,
    ModifyArticle
  },
  created() {
    this.routeCheck();
    this.rolesCheck()
    this.init()
  },
  methods: {
    rolesCheck() {
      if(this.user.roles.length === 1) {
        if(this.user.roles[0] === 'ROLE_ADMIN') { // admin일 경우
          this.role = 'admin'
        } else { // user일 경우
          this.role = 'user'
        } 
      } else { // user_member일 경우
        this.role = 'member'
      }
    },
    routeCheck() {
      this.activeReset();
      this.side[0].semi = false;
      if (this.$route.name === "community") {
        this.title = "공시사항 > 학생기숙사";
        this.side[0].active = true;
        this.side[0].semiTitle[0].active = true;
        this.side[0].semiTitle[1].active = false;
        this.side[0].semi = true;
      } else if (this.$route.name === "notice1") {
        this.title = "공시사항 > 사생자치회";
        this.side[0].active = true;
        this.side[0].semiTitle[0].active = false;
        this.side[0].semiTitle[1].active = true;
        this.side[0].semi = true;
      } else if (this.$route.name === "data") {
        this.title = "게시판 > 서식자료실";
        this.side[1].active = true;
      } else if (this.$route.name === "faq") {
        this.title = "게시판 > FAQ";
        this.side[2].active = true;
      } else if (this.$route.name === "repair") {
        this.title = "게시판 > 불편/수리 요청";
        this.side[3].active = true;
      } else if (this.$route.name === "market") {
        this.title = "게시판 > 나눔 장터";
        this.side[4].active = true;
      } else if (this.$route.name === "lost") {
        this.title = "게시판 > 분실물";
        this.side[5].active = true;
      } else if (this.$route.name === "article") {
        this.title = "게시글 작성";
      } else if (this.$route.name === "modify-article") {
        this.title = "게시글 수정";
      }
      else {
        this.title = "Error Page";
      }
    },
    activeReset() {
      for (var i = 0; i < this.side.length; i++) {
        this.side[i].active = false;
      }
    },
    init() {
      // 공지사항
      NoticeDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
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

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = res[i].id // 번호
          list[i].title = res[i].title // 글제목
          list[i].date = res[i].date // 작성일
          list[i].writer = res[i].writer_name // 작성자
          list[i].views = res[i].views // 조회수
        }
        this.noticeList = list
      })
      // 서식자료실
      NewsDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = res[i].id // 번호
          list[i].title = res[i].title // 글제목
          list[i].date = res[i].date // 작성일
          list[i].writer = res[i].writer_name // 작성자
          list[i].views = res[i].views // 조회수
        }
        this.dataList = list
      }
      )
      // 불편수리
      RepairDataService.getAll().then(resolveData => {
        let res = resolveData.data
        let list = []

        for (let i=0; i<res.length; i++) {
          list.push({})
          list[i].no = res[i].id // 번호
          list[i].category = '-'// 분야
          list[i].date = res[i].title // 글제목
          list[i].writer = res[i].date // 작성일자
          list[i].views = res[i].writer_name // 작성자
          list[i].progress = '-'//진행상태
        }
        this.repairList = list
      }
      )
      // 나눔장터
      StoreDataService.getAll().then(resolveData => {
        let res = resolveData.data

        for (let i=0; i<res.length; i++) {
          this.marketList.push({})
          this.marketList[i].id = res[i].id
          this.marketList[i].title = res[i].title // 글제목
          this.marketList[i].date = res[i].date // 작성자
          this.marketList[i].writer = res[i].writer_name // 작성일
          this.marketList[i].views = res[i].views // 조회수
          // 나눔장터 댓글수
          StoreCommentService.getAll(this.marketList[i].id).then(res => {
            this.marketList[i].comment_cnt = res.data.length
          })
          // 나눔장터 사진
          StorePhotoService.getAll(this.marketList[i].id).then(photo_data => {
            this.marketList[i].photo = photo_data.data[0].url
          })
        }
      })
      // 분실물
      LostDataService.getAll().then(resolveData => {
        let res = resolveData.data

        for (let i=0; i<res.length; i++) {
          this.lostList.push({})
          this.lostList[i].id = res[i].id
          this.lostList[i].title = res[i].title // 글제목
          this.lostList[i].date = res[i].date // 작성자
          this.lostList[i].writer = res[i].writer_name // 작성일
          this.lostList[i].views = res[i].views // 조회수
          // 분실물 댓글수
          LostCommentService.getAll(this.lostList[i].id).then(res => {
            this.lostList[i].comment_cnt = res.data.length
          })
          // 분실물 사진
          LostPhotoService.getAll(this.lostList[i].id).then(photo_data => {
            this.lostList[i].photo = photo_data.data[0].url
          })
        }
      })
      
      
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn
    },
    user() {
        return this.$store.state.auth.user
    }
  },
  watch: {
    $route(to) {
      this.routeCheck();
      if (to.path == "/community" || to.path == "/community/notice1") {
        this.side[0].semi = true;
        if (to.path == "/community") {
          this.side[0].semiTitle[0].active = true;
          this.side[0].semiTitle[1].active = false;
        } else {
          this.side[0].semiTitle[1].active = true;
          this.side[0].semiTitle[0].active = false;
        }
      } else {
        for (var i = 0; i < this.side.length; i++) {
          this.side[i].semi = false;
        }
      }
    },
  },
};
</script>

<style lang="less" scoped>
.wrapper {
  margin: 100px 0 250px;
  display: flex;
  .left_container {
    width: 18%;
  }
  .right_container {
    width: 82%;
    .create_btn {
      display: block;
      float: right;
      background-color: #336EB4;
      color: #fff;
      padding: 14px 20px; 
      margin-top: 30px;
    }
  }
}
</style>
