<template>
    <h1>{{ title }}</h1>
  </template>
  
  <script>
  export default {
    props: {
      title: {
        type: String,
        default: "페이지 제목",
      },
    },
  };
  </script>
  
  <style lang="less" scoped>
  h1 {
    width: 100%;
    color: #222;
    box-shadow: 0 1px 0 1px #e2e2e2;
  }
  </style>
  