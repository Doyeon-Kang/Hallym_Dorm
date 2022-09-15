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
    path: "/mypage",
    name: "mypage",
    component: () => import("../views/user/MyPage.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
