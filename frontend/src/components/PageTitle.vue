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
  color: #222;
  margin-top: 1em;
}
</style>
