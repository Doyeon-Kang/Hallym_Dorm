<template>
  <div class="titlebox">
    <h1>{{ title }}</h1>
    <div class="btn">
      <button class="add">{{add}}</button>
      <button  class="del">삭제</button>
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
      default: "추가",
    },
  },
};
</script>
  
<style lang="less" scoped>
.titlebox {

  justify-content: space-between;
  display: flex;
  width: 100%;
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
    }

    .del {
      margin-left: 10px;
      background-color: #DD6464;
    }
  }

}
</style>
  