import http from "../http-common";

class RepairCommentService {
  getAll(id) {
    return http.get(`/board-repair/${id}/comments`);
  }

  get(id) {
    return http.get(`/repair-comments/${id}`);
  }

  create(id, data) {
    return http.post(`/board-repair/${id}/comments`, data);
  }

  update(id, data) {
    return http.put(`/repair-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/repair-comments/${id}`);
  }

  deleteAll(id) {
    return http.delete(`/board-repair/${id}/comments`);
  }
}

export default new RepairCommentService();