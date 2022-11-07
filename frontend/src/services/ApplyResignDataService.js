import http from "../http-common";

class ApplyResignDataService {
  getAll() {
    return http.get(`/apply-resign`);
  }

  get(id) {
    return http.get(`/apply-resign/${id}`);
  }

  create(studentNo, data) {
    return http.post(`/apply-resign/${studentNo}`, data);
  }

  update(resignId, data) {
    return http.put(`/apply-resign/${resignId}`, data);
  }

  updateApprove(resignId, data) {
    return http.put(`/apply-resign/${resignId}/approve`, data);
  }

  delete(resignId) {
    return http.delete(`/apply-resign/${resignId}`);
  }

  deleteAll() {
    return http.delete(`/apply-resign`);
  }
}

export default new ApplyResignDataService();