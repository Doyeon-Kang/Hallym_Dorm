import http from "../http-common";

class ApplyResignDataService {
  getAll() {
    return http.get("/apply-resign");
  }

  get(id) {
    return http.get(`/apply-resign/${id}`);
  }

  create(id, data) {
    return http.post(`/apply-resign/${id}`, data);
  }

  update(id, data) {
    return http.put(`/apply-resign/${id}`, data);
  }

  delete(id) {
    return http.delete(`/apply-resign/${id}`);
  }

  deleteAll() {
    return http.delete(`/apply-resign`);
  }
}

export default new ApplyResignDataService();