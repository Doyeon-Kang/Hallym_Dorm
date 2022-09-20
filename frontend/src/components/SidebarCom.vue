<template>
  <div id="side_container">
    <div class="page_name">
      {{ pageName }}
    </div>
    <ul class="list_title">
      <li v-for="(item, index) in listItem" :key="index" :class="{active: item.active, semi: item.semi}">
        <router-link :to="item.path">{{ item.title }}</router-link>
        <ul v-if="item.semi" class="semi_title">
          <li v-for="(semi, index) in item.semiTitle" :key="index">
            <router-link :to="item.path">{{ semi }}</router-link>  
          </li>
        </ul>
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

  .list_title {
    margin-top: 24px;
    background-color: #fff;
    li {
      &.active {
        background-color: #447ec3;
        & a {
          color: #fff;
        }
        .semi_title li {
          background-color: #fff;
        }
      }
      border-top: 1px solid #447ec3;
      text-align: center;
      padding: 20px 0;
      &.semi {
        padding-bottom: 0;
      }
      &:last-child {
        border-bottom: 1px solid #447ec3;
      }
      &:hover .semi_title li{
        background-color: #fff;
      }
      a {
        color: #447ec3;
        font-weight: 600;
        font-size: 16px;
      }
      .semi_title {
        margin: 30px 0 20px;
        li {
          padding: 0 0 0 20px;
          text-align: start;
          border-top: none;
          list-style: inside;
          list-style-type: circle;
          &:last-child  {
            border: none;
          }
        }
        a {
          color: #858585;
          font-weight: 500;
          &:hover {
            color: #447ec3;
            font-weight: 600;
          }
        }
      }
    }
  }
}
</style>
