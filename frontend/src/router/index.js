import { createRouter, createWebHistory } from "vue-router";
// import {
//   HomeView
// } from'../views/HomeView.vue'

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/LoginView.vue"),
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("../views/MyPage.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
