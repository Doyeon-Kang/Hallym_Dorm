import http from "../http-common";

class NoticeDataService {
  getAll() {
    return http.get("/notices");
  }

  get(id) {
    return http.get(`/notices/${id}`);
  }

  create(data) {
    return http.post("/notices", data);
  }

  update(id, data) {
    return http.put(`/notices/${id}`, data);
  }

  delete(id) {
    return http.delete(`/notices/${id}`);
  }

  deleteAll() {
    return http.delete(`/notices`);
  }

  findByTitle(title) {
    return http.get(`/notices?title=${title}`);
  }
}

export default new NoticeDataService();