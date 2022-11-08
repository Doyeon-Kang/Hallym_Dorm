import http from "../http-common";

class ApplySleepDataService {
  getAll() {
    return http.get("/apply-sleepout");
  }

  get(id) {
    return http.get(`/apply-sleepout/${id}`);
  }

  create(id, data) {
    return http.post(`/apply-sleepout/${id}`, data);
  }

  update(id, data) {
    return http.put(`/apply-sleepout/${id}`, data);
  }

  delete(id) {
    return http.delete(`/apply-sleepout/${id}`);
  }

  deleteAll() {
    return http.delete(`/apply-sleepout`);
  }
}

export default new ApplySleepDataService();