import http from "../http-common";

class StoreDataService {
  getAll() {
    return http.get("/board-store");
  }

  get(id) {
    return http.get(`/board-store/${id}`);
  }

  create(data) {
    return http.post("/board-store", data);
  }

  update(id, data) {
    return http.put(`/board-store/${id}`, data);
  }

  delete(id) {
    return http.delete(`/board-store/${id}`);
  }

  deleteAll() {
    return http.delete(`/board-store`);
  }
}

export default new StoreDataService();