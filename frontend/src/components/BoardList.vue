<template>
  <div class="wrapper_list">
    <table v-if="this.$route.name === 'community' || this.$route.name === 'notice1' || 
      this.$route.name === 'data' || this.$route.name === 'faq' || 
      this.$route.name === 'repair' || this.$route.name === 'market' || 
      this.$route.name === 'lost'">
      <thead>
        <th v-for="(title, index) in listTitle" :key="index">{{ title }}</th>
      </thead>
      <tbody>
        <tr v-for="item in paginatedData" :key="item.no" @click="this.$router.push(item.url)">
          <td v-for="(text, index) in objectKey(item)" :key="index" @click="$router.push({path:this.$route.path + '/view', query: { no: item.no }})">
            {{ text }}
          </td>
        </tr>
      </tbody>
    </table>
    
    <table v-if="this.$route.name === 'myassey' || this.$route.name === 'myconsulting' || 
      this.$route.name === 'mystudy' || this.$route.name === 'mysleep' || 
      this.$route.name === 'mypoint'">
      <thead>
        <th v-for="(title, index) in listTitle" :key="index">{{ title }}</th>
      </thead>
      <tbody v-if="this.$route.name === 'myassey'">
        <tr v-for="item in paginatedData" :key="item.no" @click="this.$router.push(item.url)">
          <td v-for="(text, index) in objectKey(item)" :key="index" @click="$router.push({path: `/community${item.type}` + '/view', query: { no: item.no }})">
            {{ text }}
          </td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr v-for="item in paginatedData" :key="item.no">
          <td v-for="(text, index) in objectKey(item)" :key="index">
            {{ text }}
          </td>
        </tr>
      </tbody>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
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
    listTitle: {
      type: Array,
      required: true,
    },
    listItem: {
      type: Array,
      required: true,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 5,
    },
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    objectKey(ob) {
      let array = [];
      for (let key in ob) {
        if (key !== "url") array.push(ob[key]);
      }
      return array;
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.listItem.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.listItem.slice(start, end);
    },
  },
};
</script>

<style lang="less" scoped>
.wrapper_list {
  table {
    width: 100%;

    thead {
      background-color: #336eb4;

      th {
        color: #fff;
        padding: 12px;
      }
    }

    tbody {
      tr {
        border-bottom: 1px solid #858585;

        &:hover {
          cursor: pointer;
        }

        td {
          text-align: center;
          padding: 20px;
          color: #222;
        }
      }
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
