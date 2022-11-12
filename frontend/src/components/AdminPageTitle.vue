<template>
  <div class="titlebox">
    <h1>{{ title }}</h1>
    <div class="btn">
      <button class="add" v-if="this.$route.name === 'adminuser'"
        @click="this.$router.push('/admin/user/add')">{{add}}</button>
      <button class="add" v-else-if="this.$route.name === 'adminpoint'"
        @click="this.$router.push('/admin/point/add')">{{add}}</button>
      <button class="add" v-else @click="this.$router.push('/admin/user')" v-show="
      $route.name !== 'adminstudy' &&
      $route.name !== 'admininout' &&
      $route.name !== 'adminlife' &&
      $route.name !== 'adminuseradd'">{{add}}</button>
      <button class="del">삭제</button>
    </div>
  </div>
</template>
  
<script>
export default {
  props: {
    title: {
      type: String,
      default: "페이지 제목",
    },
    add: {
      type: String,
      default: "승인",
    },
  },
};
</script>
  
<style lang="less" scoped>
.titlebox {
  justify-content: space-between;
  display: flex;
  border-bottom: solid 2px #e2e2e2;

  h1 {
    margin: 0;
    color: #222;
    padding: 17px 30px;
    font-size: 22px;
  }

  .btn {
    margin: auto 0;

    button {
      font-size: 13px;
      border: 0;
      color: #fff;
      padding: 5px 10px;
    }

    .add {
      background-color: #00B6AD;

      &:hover {
        cursor: pointer;
      }
    }

    .del {
      margin: 0 10px;
      background-color: #DD6464;

      &:hover {
        cursor: pointer;
      }
    }
  }

}
</style>