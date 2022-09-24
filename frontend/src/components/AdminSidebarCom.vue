<template>
    <div id="side_container">
        <div class="page_name">
            <img class="hallym_mark" src="@/assets/logo.png" alt="" />
            <img class="hallym_name" src="@/assets/univ.png" alt="" />
            <div class="univ">| Dormitory</div>
        </div>
        <ul class="list_title">
            <li v-for="(item, index) in listItem" :key="index" :class="{active: item.active, semi: item.semi}">
                <router-link :to="item.path">
                    <img class="item_img" :src="item.img" />
                    {{item.title }}
                </router-link>
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
    width: 280px;
    height: 100%;
    box-shadow: 3px 0 3px 0 #e2e2e2;

    .page_name {
        position: relative;
        display: flex;
        color: #222222;
        padding: 20px;
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

    .list_title {
        background-color: #fff;

        .item_img{
            height: 16px;
        }

        li {
            &.active {
                background-color: #447ec3;
                .item_img{
                    filter: drop-shadow(0 0 0 white);
                }

                & a {
                    color: #fff;
                }

                .semi_title li {
                    background-color: #fff;
                }
            }
            text-align: left;
            padding: 20px 20px;

            &.semi {
                padding-bottom: 0;
            }

            &:hover .semi_title li {
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

                    &:last-child {
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