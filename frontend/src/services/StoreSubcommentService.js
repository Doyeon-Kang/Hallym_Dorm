import http from "../http-common";

class StoreSubcommentService {
  getAll(id, com_id) {
    return http.get(`/board-store/${id}/comments/${com_id}/subcomments`);
  }

  get(com_id) {
    return http.get(`/store-subcomments/${com_id}`);
  }

  create(id, com_id, data) {
    return http.post(`/board-store/${id}/comments/${com_id}/subcomments`, data);
  }

  update(com_id, data) {
    return http.put(`/store-subcomments/${com_id}`, data);
  }

  delete(com_id) {
    return http.delete(`/store-subcomments/${com_id}`);
  }

  deleteAll(id, com_id) {
    return http.delete(`/board-store/${id}/comments/${com_id}/subcomments`);
  }
}

export default new StoreSubcommentService();