import http from "../http-common";

class NoticeCommentDataService {
  getAll(noticeId) {
    return http.get(`/board-notice/${noticeId}/comments`);
  }

  get(id) {
    return http.get(`/notice-comments/${id}`);
  }

  create(noticeId, data) {
    return http.post(`/board-notice/${noticeId}/comments`, data);
  }

  update(id, data) {
    return http.put(`/notice-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/notice-comments/${id}`);
  }

  deleteAll(noticeId) {
    return http.delete(`/board-notice/${noticeId}/comments`);
  }
}

export default new NoticeCommentDataService();