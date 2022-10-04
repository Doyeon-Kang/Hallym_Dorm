<template>
    <div class="searchbar">
        <div class="search_input">
            <img src="@/assets/search.png" alt="">
            <input v-if="this.$route.name !== 'adminlife'" type="text" v-model="name" placeholder="전체 사용자 검색" />
            <input v-if="this.$route.name === 'adminlife'" type="text" v-model="name" placeholder="일정 제목 검색" />
        </div>
        <div class="search_button">
            <button @click="show">
                검색
            </button>
        </div>
    </div>
</template>
  
<script>

</script>
  
<style lang="less" scoped>
.searchbar {
    margin: 10px 0 10px 10px;
    width: 100%;
    display: flex;

    .search_input {
        display: flex;
        width: 90%;
        margin-right: 10px;
        border: 1px solid #336EB4;

        img {
            width: 15px;
            padding: 12px;
        }

        input {
            width: 100%;
            border: none;
            padding: 10px 10px;
            font-size: 14px;
        }
    }

    .search_button {
        width: 8%;

        button {
            border-radius: 0;
            width: 100%;
            border: 0px;
            height: 100%;
            font-size: 16px;
            font-weight: bold;
            background-color: #3675C7;
            color: #FFFFFF;

            &:hover{
                cursor: pointer;
            }
        }
    }
}
</style>
  