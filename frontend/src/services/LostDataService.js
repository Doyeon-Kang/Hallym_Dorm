import http from "../http-common";

class LostDataService {
  getAll() {
    return http.get("/losts");
  }

  get(id) {
    return http.get(`/losts/${id}`);
  }

  create(data) {
    return http.post("/losts", data);
  }

  update(id, data) {
    return http.put(`/losts/${id}`, data);
  }

  delete(id) {
    return http.delete(`/losts/${id}`);
  }

  deleteAll() {
    return http.delete(`/losts`);
  }
}

export default new LostDataService();