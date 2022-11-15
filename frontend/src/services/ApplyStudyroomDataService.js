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

  update(joinId, data) {
    return http.put(`/apply-studyroom/${joinId}`, data);
  }

  updateApprove(joinId, data) {
    return http.put(`/apply-studyroom/${joinId}/approve`, data);
  }

  delete(joinId) {
    return http.delete(`/apply-studyroom/${joinId}`);
  }

  deleteAll() {
    return http.delete(`/apply-studyroom`);
  }
}

export default new ApplyStudyroomDataService();