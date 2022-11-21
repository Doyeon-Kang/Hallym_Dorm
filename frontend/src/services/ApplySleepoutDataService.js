import http from "../http-common";

class ApplySleepoutDataService {
  getAll() {
    return http.get(`/apply-sleepout`);
  }

  get(id) {
    return http.get(`/apply-sleepout/${id}`);
  }

  create(data) {
    return http.post(`/apply-sleepout`, data);
  }

  update(sleepoutId, data) {
    return http.put(`/apply-sleepout/${sleepoutId}`, data);
  }

  updateApprove(sleepoutId, data) {
    return http.put(`/apply-sleepout/${sleepoutId}/approve`, data);
  }

  delete(sleepoutId) {
    return http.delete(`/apply-sleepout/${sleepoutId}`);
  }

  deleteAll() {
    return http.delete(`/apply-sleepout`);
  }
}

export default new ApplySleepoutDataService();