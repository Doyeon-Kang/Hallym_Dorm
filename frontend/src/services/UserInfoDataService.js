import http from "../http-common";

class UserInfoDataService {
  getInfo(studentNo) {
    return http.get(`/user-member/info/${studentNo}`);
  }

  getSleepout(studentNo) {
    return http.get(`/user-member/sleepout-info/${studentNo}`);
  }
}
export default new UserInfoDataService();