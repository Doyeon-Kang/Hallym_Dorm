import http from "../http-common";

class NoticeCommentService {
  getAll(id) {
    return http.get(`/board-notice/${id}/comments`);
  }

  get(id) {
    return http.get(`/notice-comments/${id}`);
  }

  create(id, data) {
    return http.post(`/board-notice/${id}/comments`, data);
  }

  update(id, data) {
    return http.put(`/notice-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/notice-comments/${id}`);
  }

  deleteAll(id) {
    return http.delete(`/board-notice/${id}/comments`);
  }
}

export default new NoticeCommentService();