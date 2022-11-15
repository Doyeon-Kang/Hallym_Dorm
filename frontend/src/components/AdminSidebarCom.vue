<template>
    <div id="side_container">
        <div class="page_name" @click="this.$router.push('/')">
            <img class="hallym_mark" src="@/assets/logo.png" alt="" />
            <img class="hallym_name" src="@/assets/univ.png" alt="" />
            <div class="univ">| Dormitory</div>
        </div>
        <ul class="list_title">
            <li v-for="(item, index) in listItem" :key="index" :class="{active: item.active || item.path==='/admin/user' && $route.name==='admindetail'}">
                <router-link :to="item.path">
                    <img class="item_img" :src="item.img" />
                    {{item.title}}
                </router-link>
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
    height: 100%;
    box-shadow: 3px 0 3px 0 #e2e2e2;

    .page_name {
        width: 100%;
        position: relative;
        display: flex;
        color: #222222;
        padding: 20px;
        padding-right: 50px;
        z-index: auto;
        border-bottom: #eaeaea 1px solid;

        .hallym_mark {
            margin-left: 10px;
            width: 45px;
            height: 22px;
        }

        .hallym_name {
            width: 75px;
            height: 22px;
        }

        .univ {
            font-size: 15px;
            font-weight: bold;
            line-height: 20px;
        }
    }

    .page_name:hover {
        cursor: pointer;
    }

    .list_title {
        margin-top: 0px;
        background-color: #fff;

        .item_img {
            height: 16px;
        }

        li {
            &.active {
                background-color: #447ec3;

                & a {
                    color: #fff;
                }
            }

            text-align: left;
            padding: 20px 20px;

            &:hover .semi_title li {
                background-color: #fff;
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