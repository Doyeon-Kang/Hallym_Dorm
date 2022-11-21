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

  update(id, data) {
    return http.put(`/apply-consult/${id}`, data);
  }

  updateApprove(id, data) {
    return http.put(`/apply-consult/${id}/approve`, data);
  }

  delete(id) {
    return http.delete(`/apply-consult/${id}`);
  }

  deleteAll() {
    return http.delete(`/apply-consult`);
  }
}

export default new ApplyConsultDataService();