import http from "../http-common";

class StoreCommentDataService {
  getAll(storeId) {
    return http.get(`/board-store/${storeId}/comments`);
  }

  get(id) {
    return http.get(`/store-comments/${id}`);
  }

  create(storeId, data) {
    return http.post(`/board-store/${storeId}/comments`, data);
  }

  update(id, data) {
    return http.put(`/store-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/store-comments/${id}`);
  }

  deleteAll(storeId) {
    return http.delete(`/board-store/${storeId}/comments`);
  }
}

export default new StoreCommentDataService();