import http from "../http-common";

class LostSubcommentDataService {
  getAll(lostId, commentId) {
    return http.get(`/board-lost/${lostId}/comments/${commentId}/subcomments`);
  }

  get(id) {
    return http.get(`/lost-subcomments/${id}`);
  }

  create(lostId, commentId, data) {
    return http.post(`/board-lost/${lostId}/comments/${commentId}/subcomments`, data);
  }

  update(id, data) {
    return http.put(`/lost-subcomments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/lost-subcomments/${id}`);
  }

  deleteAll(lostId, commentId) {
    return http.delete(`/board-lost/${lostId}/comments/${commentId}/subcomments`);
  }
}

export default new LostSubcommentDataService();