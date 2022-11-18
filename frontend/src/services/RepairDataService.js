import http from "../http-common";

class RepairDataService {
  getAll() {
    return http.get("/board-repair");
  }

  get(id) {
    return http.get(`/board-repair/${id}`);
  }

  getMy(studentNo) {
    return http.get(`/board-repair/my-repair?studentNo=`, studentNo);
  }

  create(data) {
    return http.post("/board-repair", data);
  }

  update(id, data) {
    return http.put(`/board-repair/${id}`, data);
  }

  delete(id) {
    return http.delete(`/board-repair/${id}`);
  }

  deleteAll() {
    return http.delete(`/board-repair`);
  }
}

export default new RepairDataService();