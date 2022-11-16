import http from "../http-common";

class ApplyConsultDataService {
  getAll() {
    return http.get(`/apply-consult`);
  }

  get(id) {
    return http.get(`/apply-consult/${id}`);
  }
  create(data) {
    return http.post(`/apply-consult`, data);
  }

  update(joinId, data) {
    return http.put(`/apply-consult/${joinId}`, data);
  }

  updateApprove(joinId, data) {
    return http.put(`/apply-consult/${joinId}/approve`, data);
  }

  delete(joinId) {
    return http.delete(`/apply-consult/${joinId}`);
  }

  deleteAll() {
    return http.delete(`/apply-consult`);
  }
}

export default new ApplyConsultDataService();