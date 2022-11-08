import http from "../http-common";

class ApplyJoinDataService {
  getAll() {
    return http.get("/apply-join");
  }

  get(id) {
    return http.get(`/apply-join/${id}`);
  }

  create(id, data) {
    return http.post(`/apply-join/${id}`, data);
  }

  update(id, data) {
    return http.put(`/apply-join/${id}`, data);
  }

  delete(id) {
    return http.delete(`/apply-join/${id}`);
  }

  deleteAll() {
    return http.delete(`/apply-join`);
  }
}

export default new ApplyJoinDataService();