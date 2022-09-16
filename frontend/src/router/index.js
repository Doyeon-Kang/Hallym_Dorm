import { createRouter, createWebHistory } from "vue-router";
// import {
//   HomeView
// } from'../views/HomeView.vue'

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/user/HomeView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/preHome/LoginView.vue"),
  },
  {
    path: "/join",
    name: "join",
    component: () => import("../views/preHome/JoinView.vue"),
  },
  {
    path: "/findpw",
    name: "findpw",
    component: () => import("../views/preHome/FindpwView.vue"),
  },
  {
    path: "/mypage/",
    name: "mypage",
    component: () => import("../views/user/MyPage.vue"),
  },
  {
    path: "/mypage/myassey",
    name: "myassey",
    component: () => import("../views/user/MyPageDetail.vue"),
  },
  {
    path: "/mypage/myconsulting",
    name: "myconsulting",
    component: () => import("../views/user/MyPageDetail.vue"),
  },
  {
    path: "/mypage/mystudy",
    name: "mystudy",
    component: () => import("../views/user/MyPageDetail.vue"),
  },
  {
    path: "/mypage/mysleep",
    name: "mysleep",
    component: () => import("../views/user/MyPageDetail.vue"),
  },
  {
    path: "/mypage/mypoint",
    name: "mypoint",
    component: () => import("../views/user/MyPageDetail.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
