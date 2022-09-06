<template>
  <div class="footer_container">
    <div class="description">
      <span>한림대학교 HALLYM UNIVERSITY</span>
      <span>(24252)강원도 춘천시 한림대학길1 학생생활관 | 행정실</span>
      <span
        >033-259-4701~2 | E-mail de3601@hallym.ac.kr | Fax 033-257-7037</span
      >
      <span
        >| 관리실 1관 033-259-4717 4관, 5관, 7관 033-259-4711 2관, 3관, 6관
        033-259-4712 8관 033-259-4718</span
      >
      <span
        >copyright (C) HALLYM University Dormitory All rights reserved.</span
      >
    </div>
  </div>
</template>

<style scoped lang="less">
.footer_container {
  width: 100%;
  background-color: #323232;
  margin-top: 30px;
  bottom: 0;
  .description {
    color: #ebecf0;
    font-size: 12px;
    padding: 20px 0 25px 0;
    text-align: center;
    opacity: 70%;
    span {
      display: block;
      margin-top: 5px;
      &:first-child {
        margin-top: 0;
      }
    }
  }
}
</style>
