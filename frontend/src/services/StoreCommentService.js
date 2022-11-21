import http from "../http-common";

class StoreCommentService {
  getAll(id) {
    return http.get(`/board-store/${id}/comments`);
  }

  get(id) {
    return http.get(`/store-comments/${id}`);
  }

  create(id, data) {
    return http.post(`/board-store/${id}/comments`, data);
  }

  update(id, data) {
    return http.put(`/store-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/store-comments/${id}`);
  }

  deleteAll(id) {
    return http.delete(`/board-store/${id}/comments`);
  }
}

export default new StoreCommentService();