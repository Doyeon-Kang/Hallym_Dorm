import http from "../http-common";

class StudyroomScheduleGet {
  getAll() {
    return http.get(`/apply-studyroom-schedule`);
  }
}

export default new StudyroomScheduleGet();