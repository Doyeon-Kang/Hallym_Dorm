<template>
  <div id="side_container">
    <div class="page_name">
      {{ pageName }}
    </div>
    <ul>
      <li v-for="(item, index) in listItem" :key="index">
        <router-link :to="item.path">{{ item.title }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    pageName: {
      type: String,
      default: "페이지제목",
    },
    listItem: {
      type: Array,
      default: () => {
        return [{}];
      },
    },
  },
};
</script>

<style lang="less" scoped>
#side_container {
  width: 160px;
  .page_name {
    position: relative;
    font-size: 20px;
    font-weight: 600;
    background-color: #447ec3;
    color: #fff;
    width: 100px;
    padding: 36px 30px;
    z-index: auto;
    &::after {
      content: "";
      width: 160px;
      height: 100px;
      background-color: #00b6ad;
      position: absolute;
      top: 5px;
      left: 7px;
      z-index: -1;
    }
  }

  ul {
    margin-top: 24px;
    background-color: #fff;
    li {
      border-top: 1px solid #447ec3;
      text-align: center;
      padding: 20px 0;
      &:last-child {
        border-bottom: 1px solid #447ec3;
      }
      &:hover {
        background-color: #447ec3;
        & a {
          color: #fff;
        }
      }
      a {
        color: #447ec3;
        font-weight: 600;
        font-size: 16px;
      }
    }
  }
}
</style>
