import http from "../http-common";

class LostDataService {
  getAll() {
    return http.get("/board-lost");
  }

  get(id) {
    return http.get(`/board-lost/${id}`);
  }

  getMy(data) {
    return http.get(`/board-lost/my-lost?studentNo=`, data);
  }

  create(data) {
    return http.post("/board-lost", data);
  }

  update(id, data) {
    return http.put(`/board-lost/${id}`, data);
  }

  delete(id) {
    return http.delete(`/board-lost/${id}`);
  }

  deleteAll() {
    return http.delete(`/board-lost`);
  }
}

export default new LostDataService();