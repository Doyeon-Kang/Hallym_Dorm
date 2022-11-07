import http from "../http-common";

class LostCommentDataService {
  getAll(lostId) {
    return http.get(`/board-lost/${lostId}/comments`);
  }

  get(id) {
    return http.get(`/lost-comments/${id}`);
  }

  create(lostId, data) {
    return http.post(`/board-lost/${lostId}/comments`, data);
  }

  update(id, data) {
    return http.put(`/lost-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/lost-comments/${id}`);
  }

  deleteAll(lostId) {
    return http.delete(`/board-lost/${lostId}/comments`);
  }
}

export default new LostCommentDataService();