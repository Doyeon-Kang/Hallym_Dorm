import http from "../http-common";

class UserInfoDataService {
  getInfoAll() {
    return http.get(`/user-member/info`);
  }

  getInfo(studentNo) {
    return http.get(`/user-member/info/${studentNo}`);
  }
  
  getSleepout(studentNo) {
    return http.get(`/user-member/sleepout-info/${studentNo}`);
  }
}
export default new UserInfoDataService();