import http from "../http-common";

class LostSubcommentService {
  getAll(id, com_id) {
    return http.get(`/board-lost/${id}/comments/${com_id}/subcomments`);
  }

  get(com_id) {
    return http.get(`/lost-subcomments/${com_id}`);
  }

  create(id, com_id, data) {
    return http.post(`/board-lost/${id}/comments/${com_id}/subcomments`, data);
  }

  update(com_id, data) {
    return http.put(`/lost-subcomments/${com_id}`, data);
  }

  delete(com_id) {
    return http.delete(`/lost-subcomments/${com_id}`);
  }

  deleteAll(id, com_id) {
    return http.delete(`/board-lost/${id}/comments/${com_id}/subcomments`);
  }
}

export default new LostSubcommentService();