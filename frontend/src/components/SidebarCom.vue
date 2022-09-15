<template>
  <div>
    <router-link
      :to="item.path"
      v-for="(item, index) in listItem"
      :key="index"
      >{{ item.title }}</router-link
    >
  </div>
</template>

<script>
export default {
  props: {
    listItem: {
      type: Array,
      default: () => {
        return [{}];
      },
    },
  },
};
</script>
