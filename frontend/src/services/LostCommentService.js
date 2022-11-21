import http from "../http-common";

class LostCommentService {
  getAll(id) {
    return http.get(`/board-lost/${id}/comments`);
  }

  get(id) {
    return http.get(`/lost-comments/${id}`);
  }

  create(id, data) {
    return http.post(`/board-lost/${id}/comments`, data);
  }

  update(id, data) {
    return http.put(`/lost-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/lost-comments/${id}`);
  }

  deleteAll(id) {
    return http.delete(`/board-lost/${id}/comments`);
  }
}

export default new LostCommentService();