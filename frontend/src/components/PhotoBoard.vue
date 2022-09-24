<template>
  <div class="photo_list">
    <div v-for="(item, index) in paginatedData" :key="index" class="photo_item">
      <div class="photo">
        <img :src="require(`@/assets/${item.photo}`)" alt="미리보기" />
      </div>
      <div class="first_line">
        <div class="title">{{ item.title }}</div>
        <div class="comment_cnt">[{{ item.comment_cnt }}]</div>
      </div>
      <div class="second_line">
        <div class="writer">{{ item.writer }}</div>
        <div class="date">{{ item.date }}</div>
      </div>
    </div>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn"
      >
        다음
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageNum: 0,
    };
  },
  props: {
    photoList: {
      type: Array,
      default: () => [
        {
          photo: "",
          title: "",
          writer: "",
          date: "",
          comment_cnt: "",
        },
      ],
    },
    pageSize: {
      type: Number,
      required: false,
      default: 6,
    },
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.photoList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.photoList.slice(start, end);
    },
  },
};
</script>

<style lang="less" scoped>
.photo_list {
  .photo_item {
    display: inline-block;
    width: 25%;
    margin-top: 20px;
    margin-right: 4.5em;
    color: #858585;
    &:hover {
      cursor: pointer;
    }
    .photo {
      img {
        width: 100%;
        height: 200px;
      }
    }
    .first_line {
      display: flex;
      justify-content: space-between;
      .title {
        width: 92%;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
    .second_line {
      display: flex;
      justify-content: space-between;
    }
  }
  .btn-cover {
    margin-top: 1.5rem;
    text-align: center;
    .page-btn {
      width: 4rem;
      height: 2rem;
      letter-spacing: 0.5px;
      border: 1px solid #c0c0c0;
      background-color: #fff;
      border-radius: 5px;
      color: #858585;
      font-weight: 800;
      &:hover {
        cursor: pointer;
        border: 1px solid #336eb4;
      }
      &[disabled] {
        color: #c0c0c0;
        &:hover {
          cursor: auto;
          border: 1px solid #c0c0c0;
        }
      }
    }
    .page-count {
      padding: 0 1rem;
    }
  }
}
</style>
