import http from "../http-common";

class StoreSubcommentDataService {
  getAll(storeId, commentId) {
    return http.get(`/board-store/${storeId}/comments/${commentId}/subcomments`);
  }

  get(id) {
    return http.get(`/store-subcomments/${id}`);
  }

  create(storeId, commentId, data) {
    return http.post(`/board-store/${storeId}/comments/${commentId}/subcomments`, data);
  }

  update(id, data) {
    return http.put(`/store-subcomments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/store-subcomments/${id}`);
  }

  deleteAll(storeId, commentId) {
    return http.delete(`/board-store/${storeId}/comments/${commentId}/subcomments`);
  }
}

export default new StoreSubcommentDataService();