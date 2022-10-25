import http from "../http-common";

class NoticeDataService {
  getAll() {
    return http.get("/board-notice");
  }

  getAllNotice1() {
    return http.get("/board-notice1")
  }

  get(id) {
    return http.get(`/board-notice/${id}`);
  }

  create(data) {
    return http.post("/board-notice", data);
  }

  update(id, data) {
    return http.put(`/board-notice/${id}`, data);
  }

  delete(id) {
    return http.delete(`/board-notice/${id}`);
  }

  deleteAll() {
    return http.delete(`/board-notice`);
  }
}

export default new NoticeDataService();