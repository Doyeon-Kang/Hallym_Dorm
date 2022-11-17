import http from "../http-common";

class ApplyStudyroomDataService {
  getAll() {
    return http.get(`/apply-studyroom`);
  }

  get(id) {
    return http.get(`/apply-studyroom/${id}`);
  }
  create(data) {
    return http.post(`/apply-studyroom`, data);
  }

  update(id, data) {
    return http.put(`/apply-studyroom/${id}`, data);
  }

  updateApprove(id, data) {
    return http.put(`/apply-studyroom/${id}/approve`, data);
  }

  delete(id) {
    return http.delete(`/apply-studyroom/${id}`);
  }

  deleteAll() {
    return http.delete(`/apply-studyroom`);
  }
}

export default new ApplyStudyroomDataService();