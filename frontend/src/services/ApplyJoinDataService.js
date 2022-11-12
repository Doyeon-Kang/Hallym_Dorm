import http from "../http-common";

class ApplyJoinDataService {
  getAll() {
    return http.get(`/apply-join`);
  }

  get(id) {
    return http.get(`/apply-join/${id}`);
  }
  create(studentNo, data) {
    return http.post(`/apply-join/${studentNo}`, data);
  }

  update(joinId, data) {
    return http.put(`/apply-join/${joinId}`, data);
  }

  updateApprove(joinId, data) {
    return http.put(`/apply-join/${joinId}/approve`, data);
  }

  delete(joinId) {
    return http.delete(`/apply-join/${joinId}`);
  }

  deleteAll() {
    return http.delete(`/apply-join`);
  }
}

export default new ApplyJoinDataService();